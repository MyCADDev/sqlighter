//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: ../../../../android//com/vals/a2ios/amfibian/impl/AnAttribImpl.java
//

#include "IOSClass.h"
#include "IOSObjectArray.h"
#include "J2ObjC_source.h"
#include "com/vals/a2ios/amfibian/impl/AnAttribImpl.h"
#include "com/vals/a2ios/amfibian/intf/AnAttrib.h"
#include "com/vals/a2ios/amfibian/intf/AnObject.h"
#include "java/lang/Exception.h"
#include "java/lang/reflect/Method.h"
#include "java/util/HashMap.h"
#include "java/util/Map.h"

@interface AnAttribImpl () {
 @public
  id<AnObject> parentAnObject_;
  NSString *attribName_;
  NSString *columnName_;
  NSString *dbColumnDefinition_;
  NSString *jsonName_;
  id<JavaUtilMap> converterMap_;
  id<JavaUtilMap> getConverterMap_;
}

@end

J2OBJC_FIELD_SETTER(AnAttribImpl, parentAnObject_, id<AnObject>)
J2OBJC_FIELD_SETTER(AnAttribImpl, attribName_, NSString *)
J2OBJC_FIELD_SETTER(AnAttribImpl, columnName_, NSString *)
J2OBJC_FIELD_SETTER(AnAttribImpl, dbColumnDefinition_, NSString *)
J2OBJC_FIELD_SETTER(AnAttribImpl, jsonName_, NSString *)
J2OBJC_FIELD_SETTER(AnAttribImpl, converterMap_, id<JavaUtilMap>)
J2OBJC_FIELD_SETTER(AnAttribImpl, getConverterMap_, id<JavaUtilMap>)

NSString *AnAttribImpl_NONAME_CONVERSION_KEY_ = @"nonameConverter";

@implementation AnAttribImpl

- (instancetype)initWithNSString:(NSString *)attribName
                    withNSString:(NSString *)columnName
                    withNSString:(NSString *)jsonName {
  AnAttribImpl_initWithNSString_withNSString_withNSString_(self, attribName, columnName, jsonName);
  return self;
}

- (instancetype)initWithNSString:(NSString *)attribColumnJsonName {
  AnAttribImpl_initWithNSString_(self, attribColumnJsonName);
  return self;
}

- (NSString *)getDbColumnDefinition {
  return dbColumnDefinition_;
}

- (void)setDbColumnDefinitionWithNSString:(NSString *)dbColumnDefinition {
  self->dbColumnDefinition_ = dbColumnDefinition;
}

- (void)setCustomSetConverterWithAnAttrib_CustomConverter:(id<AnAttrib_CustomConverter>)converter {
  [self setCustomSetConverterWithNSString:AnAttribImpl_NONAME_CONVERSION_KEY_ withAnAttrib_CustomConverter:converter];
}

- (void)setCustomSetConverterWithNSString:(NSString *)key
             withAnAttrib_CustomConverter:(id<AnAttrib_CustomConverter>)converter {
  (void) [((id<JavaUtilMap>) nil_chk(converterMap_)) putWithId:key withId:converter];
}

- (id<AnAttrib_CustomConverter>)getCustomSetConverterWithNSString:(NSString *)key {
  return [((id<JavaUtilMap>) nil_chk(converterMap_)) getWithId:key];
}

- (id<AnAttrib_CustomConverter>)getCustomSetConverter {
  return [((id<JavaUtilMap>) nil_chk(converterMap_)) getWithId:defaultConverterKey_];
}

- (void)clearCustomSetConverters {
  [((id<JavaUtilMap>) nil_chk(converterMap_)) clear];
}

- (void)setDefaultSetConversionKeyWithNSString:(NSString *)key {
  defaultConverterKey_ = key;
}

- (void)setCustomGetConverterWithAnAttrib_CustomConverter:(id<AnAttrib_CustomConverter>)converter {
  [self setCustomGetConverterWithNSString:AnAttribImpl_NONAME_CONVERSION_KEY_ withAnAttrib_CustomConverter:converter];
}

- (void)setCustomGetConverterWithNSString:(NSString *)key
             withAnAttrib_CustomConverter:(id<AnAttrib_CustomConverter>)converter {
  (void) [((id<JavaUtilMap>) nil_chk(getConverterMap_)) putWithId:key withId:converter];
}

- (id<AnAttrib_CustomConverter>)getCustomGetConverterWithNSString:(NSString *)key {
  return [((id<JavaUtilMap>) nil_chk(getConverterMap_)) getWithId:key];
}

- (id<AnAttrib_CustomConverter>)getCustomGetConverter {
  return [((id<JavaUtilMap>) nil_chk(getConverterMap_)) getWithId:defaultGetConverterKey_];
}

- (void)clearCustomGetConverters {
  [((id<JavaUtilMap>) nil_chk(getConverterMap_)) clear];
}

- (void)setDefaultGetConversionKeyWithNSString:(NSString *)key {
  defaultGetConverterKey_ = key;
}

- (void)setAnObjectWithAnObject:(id<AnObject>)anObject {
  self->parentAnObject_ = anObject;
}

- (NSString *)getAttribName {
  return attribName_;
}

- (void)setAttribNameWithNSString:(NSString *)attribName {
  self->attribName_ = attribName;
}

- (NSString *)getColumnName {
  return columnName_;
}

- (void)setColumnNameWithNSString:(NSString *)columnName {
  self->columnName_ = columnName;
}

- (void)setValueWithId:(id)value {
  JavaLangReflectMethod *m = [self getSetter];
  if (m != nil) {
    id convertedValue = nil;
    id<AnAttrib_CustomConverter> cc = [self getCustomSetConverter];
    if (cc != nil) {
      convertedValue = [cc convertWithId:value];
    }
    else {
      convertedValue = value;
    }
    (void) [m invokeWithId:[((id<AnObject>) nil_chk(parentAnObject_)) getNativeObject] withNSObjectArray:[IOSObjectArray newArrayWithObjects:(id[]){ convertedValue } count:1 type:NSObject_class_()]];
  }
}

- (id)getValue {
  id value = nil;
  JavaLangReflectMethod *m = [self getGetter];
  if (m != nil) {
    value = [m invokeWithId:[((id<AnObject>) nil_chk(parentAnObject_)) getNativeObject] withNSObjectArray:[IOSObjectArray newArrayWithLength:0 type:NSObject_class_()]];
    id<AnAttrib_CustomConverter> cc = [self getCustomGetConverter];
    if (cc != nil) {
      value = [cc convertWithId:value];
      return value;
    }
  }
  return value;
}

- (JavaLangReflectMethod *)getGetter {
  IOSObjectArray *methods = [((IOSClass *) nil_chk([((id<AnObject>) nil_chk(parentAnObject_)) getNativeClass])) getMethods];
  {
    IOSObjectArray *a__ = methods;
    JavaLangReflectMethod * const *b__ = ((IOSObjectArray *) nil_chk(a__))->buffer_;
    JavaLangReflectMethod * const *e__ = b__ + a__->size_;
    while (b__ < e__) {
      JavaLangReflectMethod *m = *b__++;
      if ([((NSString *) nil_chk([((JavaLangReflectMethod *) nil_chk(m)) getName])) equalsIgnoreCase:JreStrcat("$$", @"get", attribName_)]) {
        return m;
      }
    }
  }
  return nil;
}

- (JavaLangReflectMethod *)getSetter {
  IOSObjectArray *methods = [((IOSClass *) nil_chk([((id<AnObject>) nil_chk(parentAnObject_)) getNativeClass])) getMethods];
  {
    IOSObjectArray *a__ = methods;
    JavaLangReflectMethod * const *b__ = ((IOSObjectArray *) nil_chk(a__))->buffer_;
    JavaLangReflectMethod * const *e__ = b__ + a__->size_;
    while (b__ < e__) {
      JavaLangReflectMethod *m = *b__++;
      if ([((NSString *) nil_chk([((JavaLangReflectMethod *) nil_chk(m)) getName])) equalsIgnoreCase:JreStrcat("$$", @"set", attribName_)]) {
        return m;
      }
    }
  }
  return nil;
}

- (IOSClass *)getAttribClass {
  JavaLangReflectMethod *m = [self getGetter];
  if (m != nil) {
    IOSClass *rt = [m getReturnType];
    return rt;
  }
  return nil;
}

- (NSString *)getJsonOrAttribName {
  if (jsonName_ != nil && ![@"" isEqual:[jsonName_ trim]]) {
    return jsonName_;
  }
  return attribName_;
}

- (NSString *)getColumnOrAttribName {
  if (columnName_ != nil && ![@"" isEqual:[columnName_ trim]]) {
    return columnName_;
  }
  return attribName_;
}

@end

void AnAttribImpl_initWithNSString_withNSString_withNSString_(AnAttribImpl *self, NSString *attribName, NSString *columnName, NSString *jsonName) {
  (void) AnAttribImpl_initWithNSString_(self, attribName);
  self->columnName_ = columnName;
  self->jsonName_ = jsonName;
}

AnAttribImpl *new_AnAttribImpl_initWithNSString_withNSString_withNSString_(NSString *attribName, NSString *columnName, NSString *jsonName) {
  AnAttribImpl *self = [AnAttribImpl alloc];
  AnAttribImpl_initWithNSString_withNSString_withNSString_(self, attribName, columnName, jsonName);
  return self;
}

void AnAttribImpl_initWithNSString_(AnAttribImpl *self, NSString *attribColumnJsonName) {
  (void) NSObject_init(self);
  self->defaultConverterKey_ = AnAttribImpl_NONAME_CONVERSION_KEY_;
  self->defaultGetConverterKey_ = AnAttribImpl_NONAME_CONVERSION_KEY_;
  self->converterMap_ = new_JavaUtilHashMap_init();
  self->getConverterMap_ = new_JavaUtilHashMap_init();
  if ([((NSString *) nil_chk(attribColumnJsonName)) indexOfString:@","] != -1) {
    IOSObjectArray *propColumn = [attribColumnJsonName split:@","];
    self->attribName_ = [((NSString *) nil_chk(IOSObjectArray_Get(nil_chk(propColumn), 0))) trim];
    if (propColumn->size_ > 1 && IOSObjectArray_Get(propColumn, 1) != nil) {
      self->columnName_ = [((NSString *) nil_chk(IOSObjectArray_Get(propColumn, 1))) trim];
    }
    if (propColumn->size_ > 2 && IOSObjectArray_Get(propColumn, 2) != nil) {
      self->jsonName_ = [((NSString *) nil_chk(IOSObjectArray_Get(propColumn, 2))) trim];
    }
  }
  else {
    self->attribName_ = attribColumnJsonName;
  }
}

AnAttribImpl *new_AnAttribImpl_initWithNSString_(NSString *attribColumnJsonName) {
  AnAttribImpl *self = [AnAttribImpl alloc];
  AnAttribImpl_initWithNSString_(self, attribColumnJsonName);
  return self;
}

J2OBJC_CLASS_TYPE_LITERAL_SOURCE(AnAttribImpl)
