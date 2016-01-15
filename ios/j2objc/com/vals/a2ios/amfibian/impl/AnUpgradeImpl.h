//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: ../../../../android//com/vals/a2ios/amfibian/impl/AnUpgradeImpl.java
//

#ifndef _ComValsA2iosAmfibianImplAnUpgradeImpl_H_
#define _ComValsA2iosAmfibianImplAnUpgradeImpl_H_

#include "J2ObjC_header.h"
#include "com/vals/a2ios/amfibian/intf/AnUpgrade.h"

@class JavaUtilDate;
@protocol JavaUtilList;
@protocol JavaUtilSet;
@protocol SQLighterDb;

@interface AnUpgradeImpl : NSObject < AnUpgrade >

#pragma mark Public

- (instancetype)initWithSQLighterDb:(id<SQLighterDb>)sqLighterDb;

- (jint)applyUpdates;

- (id<JavaUtilSet>)getAppliedUpdates;

- (id<JavaUtilList>)getTaskByKeyWithNSString:(NSString *)key;

- (id<JavaUtilList>)getUpdateKeys;

#pragma mark Protected

- (NSString *)getTableName;

@end

J2OBJC_EMPTY_STATIC_INIT(AnUpgradeImpl)

FOUNDATION_EXPORT void AnUpgradeImpl_initWithSQLighterDb_(AnUpgradeImpl *self, id<SQLighterDb> sqLighterDb);

J2OBJC_TYPE_LITERAL_HEADER(AnUpgradeImpl)

@compatibility_alias ComValsA2iosAmfibianImplAnUpgradeImpl AnUpgradeImpl;

@interface AnUpgradeImpl_Upgrade : NSObject

#pragma mark Public

- (instancetype)init;

- (JavaUtilDate *)getCreateDate;

- (NSString *)getKey;

- (NSString *)getValue;

- (void)setCreateDateWithJavaUtilDate:(JavaUtilDate *)createDate;

- (void)setKeyWithNSString:(NSString *)key;

- (void)setValueWithNSString:(NSString *)value;

@end

J2OBJC_EMPTY_STATIC_INIT(AnUpgradeImpl_Upgrade)

FOUNDATION_EXPORT NSString *AnUpgradeImpl_Upgrade_TABLE_;
J2OBJC_STATIC_FIELD_GETTER(AnUpgradeImpl_Upgrade, TABLE_, NSString *)

FOUNDATION_EXPORT void AnUpgradeImpl_Upgrade_init(AnUpgradeImpl_Upgrade *self);

FOUNDATION_EXPORT AnUpgradeImpl_Upgrade *new_AnUpgradeImpl_Upgrade_init() NS_RETURNS_RETAINED;

J2OBJC_TYPE_LITERAL_HEADER(AnUpgradeImpl_Upgrade)

#endif // _ComValsA2iosAmfibianImplAnUpgradeImpl_H_