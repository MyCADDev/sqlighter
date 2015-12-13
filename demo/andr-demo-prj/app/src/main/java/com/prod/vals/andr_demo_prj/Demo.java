package com.prod.vals.andr_demo_prj;

import com.vals.a2ios.sqlighter.intf.SQLighterDb;
import com.vals.a2ios.sqlighter.intf.SQLighterRs;

import java.util.Date;

/**
 * This class is being converted into iOS module. It represents some business
 * logic that utilizes SQLite db access. Produces the same output in iOS.
 */
public class Demo {

    /**
     * Demo sequence of Db operations with SQLighter.
     * @return - greeting string to be displayed at the screen
     */
    public static String dbOperations() {
        String greetingStr = null;
        try {
            SQLighterRs rs = null;
            SQLighterDb db = Bootstrap.getInstance().getSqLighterDb();
            /**
             * initial database structure
             *
             * CREATE TABLE "user" (
             * `name`  TEXT,
             * `email` TEXT,
             * `id`    INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,
             * `data`  BLOB,
             * `height`    REAL
             * );
             *
             * initial database content.
             *
             * INSERT INTO user(name, email, data, height) values ('user 1', 'user1@email.com', null, 1.4);
             * INSERT INTO user(name, email, data, height) values ('user 2', 'user2@email.com', null, null);
             * INSERT INTO user(name, email, data, height) values ('user 3', 'user3@email.com', null, 4.89);
             * INSERT INTO user(name, email, data, height) values ('user 4', null, null, null);
             */
            printUserTable("initial state ", db);

            /**
             * Let's insert greeting record in User table
             */
            db.addParam("user name 5"); // name
            db.addParam("qw@er.ty1"); // email
                // BLOB column
                String dataStr = "Hello, sqlighter!";
                byte[] data = dataStr.getBytes();
            db.addParam(data); // data
            db.addParam(5.67); // height
            Long rowId = db.executeChange("insert into user( name, email, data, height) values (?, ?, ?, ?)");
            System.out.println("Inserted id: " + rowId);

            /**
             * Let's query what we just inserted
             */
            db.addParam("qw@er.ty1"); // select records with email == "qw@er.ty1"
            System.out.println("check if the record was inserted");
            rs = db.executeSelect("select id, email, name, data, height from user where email = ?");
            while (rs.hasNext()) {
                print(rs);
            }
            rs.close();

            /**
             * Lets make one more update
             */
            db.addParamNull(); // set email as null
            db.addParam("qw@er.ty1"); // where email == "qw@er.ty1"
            Long alteredRows = db.executeChange("update user set email = ? where email = ?");
            System.out.println("Updated rows: " + alteredRows);

            printUserTable("after update state 1 ", db);

            /**
             * More complex update where clause
             */
            db.addParam("user@email.com"); // set email = "user@email.com"
            db.addParam("qw@er.ty1"); // where email == "qw@er.ty1"
            db.executeChange("update user set email = ? where email is null or email = ?");

            /**
             * And verify table content again
             */
            System.out.println("after update state 2");
            rs = db.executeSelect("select id, email, name, data, height from user");
            while (rs.hasNext()) {
                print(rs);
                String s = rs.getString(1);
                if (!"user@email.com".equals(s)) {
                    Number id = rs.getLong(0);
                    db.addParam("inloop@email.com");
                    db.addParam(id.longValue());
                    db.executeChange("update user set email = ? where id = ?");
                }
            }
            rs.close();

            /**
             * Delete example
             */
            db.addParam(2); // delete records where id == 2
            alteredRows = db.executeChange("delete from user where id = ?");
            System.out.println("Deleted rows: " + alteredRows);

            printUserTable("after delete state", db);

            /**
             * Create table example
             */
            db.executeChange("create table address(" +
                    "id integer primary key autoincrement unique, " +
                    "name text, " +
                    "user_id integer, " +
                    "update_date text)");
            /**
             * Add some table data
             */
            db.addParam("123 main str, walnut creek, ca");
            db.addParam(1);
            Date dateNow = new Date();
            System.out.println("Date now: " + dateNow.toString());
            db.addParam(new Date());
            db.executeChange("insert into address(name, user_id, update_date) values(?, ?, ?)");

            /**
             * Run some multi table SELECT
             */
            System.out.println("after address creation/population");
            rs = db.executeSelect("select a.user_id, u.email, u.name, " +
                    "u.data, u.height, a.name, a.update_date from user u, address a " +
                    "where a.user_id = u.id");
            while (rs.hasNext()) {
                print(rs);
                System.out.println(" address: " + rs.getString(5));
                System.out.println(" update_date: " + rs.getDate(6));
                /*
                This will treat the column as date because it contains '_date' in
                its name.
                 */
                System.out.println(" update_date: " + rs.getObject(6));
            }
            rs.close();

            /**
             * Transaction handling and Exception handling example.
             *
             * We would like to execute 2 updates as one
             * transaction.
             */
            try {
                /**
                 * Starts the transaction
                 */
                db.beginTransaction();

                /**
                 * First update
                 */
                db.addParam("trans@email.com");
                db.addParam("inloop@email.com");
                db.executeChange("update user set email = ? where email = ?");

                printUserTable("inside transaction", db);

                /**
                 * Second update
                 */
                db.addParam("inloop2@email.com");
                db.addParam("trans@email.com");
                /**
                 * intentional SQL syntax error to model an exception during
                 * transaction execution
                 */
                db.executeChange("updte user set email = ? where email = ?");
                /**
                 * Commit, which will not happen as we'll jump over
                 * into the catch clause after the exception at the previous
                 * operator.
                 */
                db.commitTransaction();
            } catch (Throwable e) {
                // Do something....
                System.out.println(e.getMessage());
                /**
                 * Rollback as something went wrong, and we wanted all
                 * or nothing.
                 */
                db.rollbackTransaction();
            }

            printUserTable("after transaction commit or rollback", db);

            /**
             * Retrieving greeting string
             */
            db.addParam(5.67);
            rs = db.executeSelect("select data from user where height = ?");
            if (rs.hasNext()) {
                byte[] greet = rs.getBlob(0);
                greetingStr =  new String(greet);
            }
            rs.close();

            db.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            /**
             * Return error message to display at the screen
             * if anything didn't work in the demo.
             */
            return e.getMessage();
        }
        /**
         * Return greet string to display on the screen
         */
        return greetingStr;
    }

    /**
     * Prints single SQL result record
     *
     * @param rs - SQLighterRs reference
     */
    private static void print(SQLighterRs rs) {
        Long pk = rs.getLong(0);
        String e = rs.getString(1);
        String n = rs.getString(2);
        byte[] dataBytes = rs.getBlob(3);
        String dataString = null;
        if (dataBytes != null) {
            dataString = new String(dataBytes);
        }
        Number h = rs.getDouble(4);
        System.out.println("pk: " + pk + ", email: " + e + ", name: " + n + ", blob data: " + dataString + ", height: " + h );
    }

    /**
     * Iterate through all records in User table
     *
     * @param title - report title
     * @param db - SQLighterDb reference
     * @throws Exception
     */
    private static void printUserTable(String title, SQLighterDb db) throws Exception {
        System.out.println(title);
        SQLighterRs rs = db.executeSelect("select id, email, name, data, height from user");
        while (rs.hasNext()) {
            print(rs);
        }
        rs.close();
    }
}
