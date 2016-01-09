//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: ../../../../android//com/vals/a2ios/amfibian/intf/AnAttrib.java
//

#ifndef _ComValsA2iosAmfibianIntfAnAttrib_H_
#define _ComValsA2iosAmfibianIntfAnAttrib_H_

#include "J2ObjC_header.h"

@class IOSClass;
@class JavaLangReflectMethod;
@protocol AnAttrib_CustomConverter;
@protocol AnObject;

@protocol AnAttrib < NSObject, JavaObject >

- (void)setCustomSetConverterWithAnAttrib_CustomConverter:(id<AnAttrib_CustomConverter>)converter;

- (void)setCustomSetConverterWithNSString:(NSString *)key
             withAnAttrib_CustomConverter:(id<AnAttrib_CustomConverter>)converter;

- (id<AnAttrib_CustomConverter>)getCustomSetConverterWithNSString:(NSString *)key;

- (id<AnAttrib_CustomConverter>)getCustomSetConverter;

- (void)clearCustomSetConverters;

- (void)setDefaultSetConversionKeyWithNSString:(NSString *)key;

- (void)setCustomGetConverterWithAnAttrib_CustomConverter:(id<AnAttrib_CustomConverter>)converter;

- (void)setCustomGetConverterWithNSString:(NSString *)key
             withAnAttrib_CustomConverter:(id<AnAttrib_CustomConverter>)converter;

- (id<AnAttrib_CustomConverter>)getCustomGetConverterWithNSString:(NSString *)key;

- (id<AnAttrib_CustomConverter>)getCustomGetConverter;

- (void)clearCustomGetConverters;

- (void)setDefaultGetConversionKeyWithNSString:(NSString *)key;

- (void)setAnObjectWithAnObject:(id<AnObject>)anObject;

- (NSString *)getAttribName;

- (void)setAttribNameWithNSString:(NSString *)attribName;

- (NSString *)getColumnName;

- (void)setColumnNameWithNSString:(NSString *)columnName;

- (void)setValueWithId:(id)value;

- (id)getValue;

- (JavaLangReflectMethod *)getGetter;

- (JavaLangReflectMethod *)getSetter;

- (IOSClass *)getAttribClass;

- (NSString *)getJsonOrAttribName;

- (NSString *)getColumnOrAttribName;

@end

J2OBJC_EMPTY_STATIC_INIT(AnAttrib)

J2OBJC_TYPE_LITERAL_HEADER(AnAttrib)

#define ComValsA2iosAmfibianIntfAnAttrib AnAttrib

@protocol AnAttrib_CustomConverter < NSObject, JavaObject >

- (id)convertWithId:(id)value;

@end

J2OBJC_EMPTY_STATIC_INIT(AnAttrib_CustomConverter)

J2OBJC_TYPE_LITERAL_HEADER(AnAttrib_CustomConverter)

#endif // _ComValsA2iosAmfibianIntfAnAttrib_H_
