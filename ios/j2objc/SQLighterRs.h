//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: ../../../ma/app/src/main/java/com/vals/a2ios/sqlighter/SQLighterRs.java
//

#ifndef _SQLighterRs_H_
#define _SQLighterRs_H_

#include "J2ObjC_header.h"

@class IOSByteArray;

@protocol SQLighterRs < NSObject, JavaObject >

- (jboolean)hasNext;

- (NSNumber *)getDoubleWithInt:(jint)index;

- (NSNumber *)getLongWithInt:(jint)index;

- (NSString *)getStringWithInt:(jint)index;

- (IOSByteArray *)getBlobWithInt:(jint)index;

- (NSNumber *)getIntWithInt:(jint)index;

- (void)close;

@end

J2OBJC_EMPTY_STATIC_INIT(SQLighterRs)

J2OBJC_TYPE_LITERAL_HEADER(SQLighterRs)

#define ComValsA2iosSqlighterSQLighterRs SQLighterRs

#endif // _SQLighterRs_H_