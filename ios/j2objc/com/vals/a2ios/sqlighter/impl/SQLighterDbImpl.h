//
//  SQLighterDb.h
//  mi
//
//  Created by Vlad Sayenko on 8/21/15.
//  Copyright (c) 2015 Vlad Sayenko. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "com/vals/a2ios/sqlighter/intf/SQLighterDb.h"
#import <sqlite3.h>

@interface SQLighterDbImpl : NSObject<SQLighterDb> {
    int code;
    BOOL isOpen;
    BOOL isDeployed;
    long stmtOpenCnt;
    long stmtCloseCnt;
}

@property (nonatomic, retain) NSString *dbName;
// @property (nonatomic, retain) NSMutableArray *parameterArray;
@property (nonatomic, retain) NSMutableDictionary *parameterDictionary;
@property BOOL replaceDatabase;
@property BOOL isDateNamedColumn;
@property sqlite3 *database;
@property sqlite3_stmt *lastPreparedStmt;
@property NSDateFormatter *dateFormatter;
@property NSString *dateColumnHint;


-(void) analyzeReturnCodeForErrors: (int) code;
-(void) closeStmt: (sqlite3_stmt *) statement;
-(int)  threadsafe;
@end
