//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: ../../../../android//com/vals/a2ios/amfibian/impl/AnObjectImpl.java
//

#ifndef _ComValsA2iosAmfibianImplAnObjectImpl_H_
#define _ComValsA2iosAmfibianImplAnObjectImpl_H_

#include "J2ObjC_header.h"
#include "com/vals/a2ios/amfibian/intf/AnObject.h"

@class IOSClass;
@class IOSObjectArray;
@class OrgJsonJSONObject;
@protocol AnAttrib;
@protocol JavaUtilCollection;
@protocol JavaUtilMap;

@interface AnObjectImpl : NSObject < AnObject > {
 @public
  IOSClass *nativeClass_;
}

#pragma mark Public

- (instancetype)init;

- (instancetype)initWithIOSClass:(IOSClass *)anObjClass
               withAnAttribArray:(IOSObjectArray *)propertyMappers;

- (instancetype)initWithIOSClass:(IOSClass *)anObjClass
               withAnAttribArray:(IOSObjectArray *)propertyMappers
                    withAnObject:(id<AnObject>)parentMapper;

- (instancetype)initWithIOSClass:(IOSClass *)anObjClass
                    withAnObject:(id<AnObject>)parentMapper;

- (instancetype)initWithIOSClass:(IOSClass *)anObjClass
               withNSStringArray:(IOSObjectArray *)propertyNames;

- (instancetype)initWithIOSClass:(IOSClass *)anObjClass
               withNSStringArray:(IOSObjectArray *)propertyNames
                    withAnObject:(id<AnObject>)parentMapper;

- (void)addAttribWithAnAttrib:(id<AnAttrib>)anAttribMapper;

- (OrgJsonJSONObject *)asJSONObjectWithId:(id)nativeObject;

- (NSString *)asJsonStringWithId:(id)nativeObject;

- (id<JavaUtilCollection>)asListWithNSString:(NSString *)jsonArrayString;

- (id<JavaUtilMap>)asMapWithId:(id)nativeObject;

- (id)asNativeObjectWithOrgJsonJSONObject:(OrgJsonJSONObject *)jsonObject;

- (id)asNativeObjectWithNSString:(NSString *)jsonString;

- (id<AnAttrib>)getAttribWithNSString:(NSString *)propertyName;

- (id<JavaUtilMap>)getAttribList;

- (id<JavaUtilMap>)getJsonMap;

- (IOSClass *)getNativeClass;

- (id)getNativeObject;

- (void)resetNativeObject;

- (void)setNativeClassWithIOSClass:(IOSClass *)anObjClass;

- (void)setNativeObjectWithId:(id)o;

#pragma mark Protected

- (void)init__WithIOSClass:(IOSClass *)anObjClass
         withAnAttribArray:(IOSObjectArray *)propertyMappers OBJC_METHOD_FAMILY_NONE;

- (void)init__WithIOSClass:(IOSClass *)anObjClass
         withAnAttribArray:(IOSObjectArray *)propertyMappers
              withAnObject:(id<AnObject>)parentMapper OBJC_METHOD_FAMILY_NONE;

- (void)init__WithIOSClass:(IOSClass *)anObjClass
              withAnObject:(id<AnObject>)parentMapper OBJC_METHOD_FAMILY_NONE;

- (void)init__WithIOSClass:(IOSClass *)anObjClass
         withNSStringArray:(IOSObjectArray *)propertyNames OBJC_METHOD_FAMILY_NONE;

- (void)init__WithIOSClass:(IOSClass *)anObjClass
         withNSStringArray:(IOSObjectArray *)propertyNames
              withAnObject:(id<AnObject>)parentMapper OBJC_METHOD_FAMILY_NONE;

@end

J2OBJC_EMPTY_STATIC_INIT(AnObjectImpl)

J2OBJC_FIELD_SETTER(AnObjectImpl, nativeClass_, IOSClass *)

FOUNDATION_EXPORT void AnObjectImpl_init(AnObjectImpl *self);

FOUNDATION_EXPORT AnObjectImpl *new_AnObjectImpl_init() NS_RETURNS_RETAINED;

FOUNDATION_EXPORT void AnObjectImpl_initWithIOSClass_withAnObject_(AnObjectImpl *self, IOSClass *anObjClass, id<AnObject> parentMapper);

FOUNDATION_EXPORT AnObjectImpl *new_AnObjectImpl_initWithIOSClass_withAnObject_(IOSClass *anObjClass, id<AnObject> parentMapper) NS_RETURNS_RETAINED;

FOUNDATION_EXPORT void AnObjectImpl_initWithIOSClass_withNSStringArray_withAnObject_(AnObjectImpl *self, IOSClass *anObjClass, IOSObjectArray *propertyNames, id<AnObject> parentMapper);

FOUNDATION_EXPORT AnObjectImpl *new_AnObjectImpl_initWithIOSClass_withNSStringArray_withAnObject_(IOSClass *anObjClass, IOSObjectArray *propertyNames, id<AnObject> parentMapper) NS_RETURNS_RETAINED;

FOUNDATION_EXPORT void AnObjectImpl_initWithIOSClass_withAnAttribArray_withAnObject_(AnObjectImpl *self, IOSClass *anObjClass, IOSObjectArray *propertyMappers, id<AnObject> parentMapper);

FOUNDATION_EXPORT AnObjectImpl *new_AnObjectImpl_initWithIOSClass_withAnAttribArray_withAnObject_(IOSClass *anObjClass, IOSObjectArray *propertyMappers, id<AnObject> parentMapper) NS_RETURNS_RETAINED;

FOUNDATION_EXPORT void AnObjectImpl_initWithIOSClass_withNSStringArray_(AnObjectImpl *self, IOSClass *anObjClass, IOSObjectArray *propertyNames);

FOUNDATION_EXPORT AnObjectImpl *new_AnObjectImpl_initWithIOSClass_withNSStringArray_(IOSClass *anObjClass, IOSObjectArray *propertyNames) NS_RETURNS_RETAINED;

FOUNDATION_EXPORT void AnObjectImpl_initWithIOSClass_withAnAttribArray_(AnObjectImpl *self, IOSClass *anObjClass, IOSObjectArray *propertyMappers);

FOUNDATION_EXPORT AnObjectImpl *new_AnObjectImpl_initWithIOSClass_withAnAttribArray_(IOSClass *anObjClass, IOSObjectArray *propertyMappers) NS_RETURNS_RETAINED;

J2OBJC_TYPE_LITERAL_HEADER(AnObjectImpl)

@compatibility_alias ComValsA2iosAmfibianImplAnObjectImpl AnObjectImpl;

#endif // _ComValsA2iosAmfibianImplAnObjectImpl_H_