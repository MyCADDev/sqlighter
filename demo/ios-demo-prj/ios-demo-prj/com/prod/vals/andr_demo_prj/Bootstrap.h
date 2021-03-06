//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: ../../../../demo/andr-demo-prj/app/src/main/java/com/prod/vals/andr_demo_prj/Bootstrap.java
//

#ifndef _ComProdValsAndr_demo_prjBootstrap_H_
#define _ComProdValsAndr_demo_prjBootstrap_H_

#include "J2ObjC_header.h"

@protocol Mobilighter;
@protocol SQLighterDb;

@interface Bootstrap : NSObject

#pragma mark Public

+ (Bootstrap *)getInstance;

- (id<Mobilighter>)getMobilighter;

- (id<SQLighterDb>)getSqLighterDb;

- (void)setMobilighterWithMobilighter:(id<Mobilighter>)mobilighter;

- (void)setSqLighterDbWithSQLighterDb:(id<SQLighterDb>)sqLighterDb;

@end

J2OBJC_EMPTY_STATIC_INIT(Bootstrap)

FOUNDATION_EXPORT Bootstrap *Bootstrap_getInstance();

J2OBJC_TYPE_LITERAL_HEADER(Bootstrap)

@compatibility_alias ComProdValsAndr_demo_prjBootstrap Bootstrap;

#endif // _ComProdValsAndr_demo_prjBootstrap_H_
