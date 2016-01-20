package com.prod.vals.andr_demo_prj;

import com.vals.a2ios.amfibian.intf.AnOrm;
import com.vals.a2ios.sqlighter.intf.SQLighterDb;
import com.vals.a2ios.sqlighter.intf.SQLighterRs;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is mostly to make Demo.java cleaner.
 *
 * Created by vsayenko on 1/18/16.
 */
public class DemoBase {

    private SQLighterDb sqLighterDb = Bootstrap.getInstance().getSqLighterDb();
    /**
     * Test tracking variables
     */
    private static int passedTestCount = 0;
    private static List<String> testList = new LinkedList<>();

    protected static void resetTestCounters() {
        testList.clear();
        passedTestCount = 0;
    }
    protected static void checkTest(String name, boolean isPassed) {
        testList.add(name);
        if(isPassed) {
            passedTestCount++;
        }
    }

    protected static void startTest(String name) {
        testList.add(name);
    }

    protected static void verifyTest(boolean isPassed) {
        if(isPassed) {
            passedTestCount++;
        }
    }

    protected static boolean testSummaryCheck() {
        return testList.size() == passedTestCount;
    }

    protected static void extraAmfibianTests(AnOrm<Appointment> anOrm) throws Exception {
        anOrm.addInclAttribs(new String[]{"id"});

        anOrm.startSqlSelect();
        String sql = anOrm.getQueryString();

        checkTest("restricted select clause test 1",
                sql.startsWith("select appointment0.id "));

        anOrm.resetSkipInclAttrNameList();
        anOrm.addSkipAttribs("id", "name");
        anOrm.startSqlSelect();
        sql = anOrm.getQueryString();

        checkTest("restricted select clause test 2",
                sql.startsWith("select appointment0.is_processed "));
    }

    protected static void printAppointments(AnOrm<Appointment> anOrm) throws Exception {
        System.out.println("Appointment records");
        anOrm.startSqlSelect();
        print(anOrm.getRecords());
    }

    protected static void print(Collection<Appointment> appointments) {
        for (Appointment a: appointments) {
            print(a);
        }
    }

    protected static void print(Appointment appointment) {
        System.out.println(
                "Appointment object. id: " + appointment.getId() +
                        ", name: " + appointment.getName());
    }

    /**
     * Prints single SQL result record
     *
     * @param rs - SQLighterRs reference
     */
    protected static void print(SQLighterRs rs) {
        Long pk = rs.getLong(0);
        String e = rs.getString(1);
        String n = rs.getString(2);
        byte[] dataBytes = rs.getBlob(3);
        String dataString = null;
        if (dataBytes != null) {
            dataString = new String(dataBytes);
        }
        Number h = rs.getDouble(4);
        System.out.println("pk: " + pk + ", email: " + e + ", name: " + n + ", blob data: " + dataString + ", height: " + h);
    }


}
