//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: ../../../../demo/andr-demo-prj/app/src/main/java/com/prod/vals/andr_demo_prj/DemoBase.java
//

#include "IOSClass.h"
#include "IOSObjectArray.h"
#include "IOSPrimitiveArray.h"
#include "J2ObjC_source.h"
#include "com/prod/vals/andr_demo_prj/Appointment.h"
#include "com/prod/vals/andr_demo_prj/Bootstrap.h"
#include "com/prod/vals/andr_demo_prj/DemoBase.h"
#include "com/vals/a2ios/amfibian/intf/AnOrm.h"
#include "com/vals/a2ios/sqlighter/intf/SQLighterDb.h"
#include "com/vals/a2ios/sqlighter/intf/SQLighterRs.h"
#include "java/io/PrintStream.h"
#include "java/lang/Double.h"
#include "java/lang/Exception.h"
#include "java/lang/Integer.h"
#include "java/lang/Long.h"
#include "java/lang/System.h"
#include "java/util/Collection.h"
#include "java/util/LinkedList.h"
#include "java/util/List.h"

@interface DemoBase () {
 @public
  id<SQLighterDb> sqLighterDb_;
}

@end

J2OBJC_FIELD_SETTER(DemoBase, sqLighterDb_, id<SQLighterDb>)

static jint DemoBase_passedTestCount_ = 0;
J2OBJC_STATIC_FIELD_GETTER(DemoBase, passedTestCount_, jint)
J2OBJC_STATIC_FIELD_REF_GETTER(DemoBase, passedTestCount_, jint)
static id<JavaUtilList> DemoBase_testList_;
J2OBJC_STATIC_FIELD_GETTER(DemoBase, testList_, id<JavaUtilList>)
J2OBJC_STATIC_FIELD_SETTER(DemoBase, testList_, id<JavaUtilList>)

J2OBJC_INITIALIZED_DEFN(DemoBase)

@implementation DemoBase

+ (void)resetTestCounters {
  DemoBase_resetTestCounters();
}

+ (void)checkTestWithNSString:(NSString *)name
                  withBoolean:(jboolean)isPassed {
  DemoBase_checkTestWithNSString_withBoolean_(name, isPassed);
}

+ (void)startTestWithNSString:(NSString *)name {
  DemoBase_startTestWithNSString_(name);
}

+ (void)verifyTestWithBoolean:(jboolean)isPassed {
  DemoBase_verifyTestWithBoolean_(isPassed);
}

+ (jboolean)testSummaryCheck {
  return DemoBase_testSummaryCheck();
}

+ (void)extraAmfibianTestsWithAnOrm:(id<AnOrm>)anOrm {
  DemoBase_extraAmfibianTestsWithAnOrm_(anOrm);
}

+ (void)printAppointmentsWithAnOrm:(id<AnOrm>)anOrm {
  DemoBase_printAppointmentsWithAnOrm_(anOrm);
}

+ (void)printWithJavaUtilCollection:(id<JavaUtilCollection>)appointments {
  DemoBase_printWithJavaUtilCollection_(appointments);
}

+ (void)printWithAppointment:(Appointment *)appointment {
  DemoBase_printWithAppointment_(appointment);
}

+ (void)printWithSQLighterRs:(id<SQLighterRs>)rs {
  DemoBase_printWithSQLighterRs_(rs);
}

J2OBJC_IGNORE_DESIGNATED_BEGIN
- (instancetype)init {
  DemoBase_init(self);
  return self;
}
J2OBJC_IGNORE_DESIGNATED_END

+ (void)initialize {
  if (self == [DemoBase class]) {
    DemoBase_testList_ = new_JavaUtilLinkedList_init();
    J2OBJC_SET_INITIALIZED(DemoBase)
  }
}

@end

void DemoBase_resetTestCounters() {
  DemoBase_initialize();
  [((id<JavaUtilList>) nil_chk(DemoBase_testList_)) clear];
  DemoBase_passedTestCount_ = 0;
}

void DemoBase_checkTestWithNSString_withBoolean_(NSString *name, jboolean isPassed) {
  DemoBase_initialize();
  [((id<JavaUtilList>) nil_chk(DemoBase_testList_)) addWithId:name];
  if (isPassed) {
    DemoBase_passedTestCount_++;
  }
}

void DemoBase_startTestWithNSString_(NSString *name) {
  DemoBase_initialize();
  [((id<JavaUtilList>) nil_chk(DemoBase_testList_)) addWithId:name];
}

void DemoBase_verifyTestWithBoolean_(jboolean isPassed) {
  DemoBase_initialize();
  if (isPassed) {
    DemoBase_passedTestCount_++;
  }
}

jboolean DemoBase_testSummaryCheck() {
  DemoBase_initialize();
  return [((id<JavaUtilList>) nil_chk(DemoBase_testList_)) size] == DemoBase_passedTestCount_;
}

void DemoBase_extraAmfibianTestsWithAnOrm_(id<AnOrm> anOrm) {
  DemoBase_initialize();
  [((id<AnOrm>) nil_chk(anOrm)) addInclAttribsWithNSStringArray:[IOSObjectArray newArrayWithObjects:(id[]){ @"id" } count:1 type:NSString_class_()]];
  [anOrm startSqlSelect];
  NSString *sql = [anOrm getQueryString];
  DemoBase_checkTestWithNSString_withBoolean_(@"restricted select clause test 1", [((NSString *) nil_chk(sql)) hasPrefix:@"select appointment0.id "]);
  [anOrm resetSkipInclAttrNameList];
  [anOrm addSkipAttribsWithNSStringArray:[IOSObjectArray newArrayWithObjects:(id[]){ @"id", @"name" } count:2 type:NSString_class_()]];
  [anOrm startSqlSelect];
  sql = [anOrm getQueryString];
  DemoBase_checkTestWithNSString_withBoolean_(@"restricted select clause test 2", [((NSString *) nil_chk(sql)) hasPrefix:@"select appointment0.is_processed "]);
}

void DemoBase_printAppointmentsWithAnOrm_(id<AnOrm> anOrm) {
  DemoBase_initialize();
  [((JavaIoPrintStream *) nil_chk(JreLoadStatic(JavaLangSystem, out_))) printlnWithNSString:@"Appointment records"];
  [((id<AnOrm>) nil_chk(anOrm)) startSqlSelect];
  DemoBase_printWithJavaUtilCollection_([anOrm getRecords]);
}

void DemoBase_printWithJavaUtilCollection_(id<JavaUtilCollection> appointments) {
  DemoBase_initialize();
  for (Appointment * __strong a in nil_chk(appointments)) {
    DemoBase_printWithAppointment_(a);
  }
}

void DemoBase_printWithAppointment_(Appointment *appointment) {
  DemoBase_initialize();
  [((JavaIoPrintStream *) nil_chk(JreLoadStatic(JavaLangSystem, out_))) printlnWithNSString:JreStrcat("$@$$", @"Appointment object. id: ", [((Appointment *) nil_chk(appointment)) getId], @", name: ", [appointment getName])];
}

void DemoBase_printWithSQLighterRs_(id<SQLighterRs> rs) {
  DemoBase_initialize();
  JavaLangLong *pk = [((id<SQLighterRs>) nil_chk(rs)) getLongWithInt:0];
  NSString *e = [rs getStringWithInt:1];
  NSString *n = [rs getStringWithInt:2];
  IOSByteArray *dataBytes = [rs getBlobWithInt:3];
  NSString *dataString = nil;
  if (dataBytes != nil) {
    dataString = [NSString stringWithBytes:dataBytes];
  }
  NSNumber *h = [rs getDoubleWithInt:4];
  [((JavaIoPrintStream *) nil_chk(JreLoadStatic(JavaLangSystem, out_))) printlnWithNSString:JreStrcat("$@$$$$$$$@", @"pk: ", pk, @", email: ", e, @", name: ", n, @", blob data: ", dataString, @", height: ", h)];
}

void DemoBase_init(DemoBase *self) {
  (void) NSObject_init(self);
  self->sqLighterDb_ = [((Bootstrap *) nil_chk(Bootstrap_getInstance())) getSqLighterDb];
}

DemoBase *new_DemoBase_init() {
  DemoBase *self = [DemoBase alloc];
  DemoBase_init(self);
  return self;
}

J2OBJC_CLASS_TYPE_LITERAL_SOURCE(DemoBase)
