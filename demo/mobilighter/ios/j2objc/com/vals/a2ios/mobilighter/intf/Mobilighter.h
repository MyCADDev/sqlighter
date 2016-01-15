//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: ../android//com/vals/a2ios/mobilighter/intf/Mobilighter.java
//

#ifndef _ComValsA2iosMobilighterIntfMobilighter_H_
#define _ComValsA2iosMobilighterIntfMobilighter_H_

#include "J2ObjC_header.h"

@protocol MobilAction;

@protocol Mobilighter < NSObject, JavaObject >

- (void)setContextWithId:(id)context;

- (void)showOkDialogWithNSString:(NSString *)title
                    withNSString:(NSString *)message;

- (void)showOkDialogWithNSString:(NSString *)title
                    withNSString:(NSString *)message
                 withMobilAction:(id<MobilAction>)okAction;

- (void)showConfirmDialogWithNSString:(NSString *)title
                         withNSString:(NSString *)message
                      withMobilAction:(id<MobilAction>)yesAction
                      withMobilAction:(id<MobilAction>)noAction;

- (void)setPlaceholderWithId:(id)textWidget
                withNSString:(NSString *)text;

- (void)setTextWithId:(id)textWidget
         withNSString:(NSString *)text;

- (NSString *)getTextWithId:(id)textWidget;

- (void)hideWithId:(id)widget;

- (void)showWithId:(id)widget;

- (void)addActionListenerWithId:(id)widget
                withMobilAction:(id<MobilAction>)action;

- (NSString *)dateToStringWithId:(id)date
                    withNSString:(NSString *)pattern;

@end

J2OBJC_EMPTY_STATIC_INIT(Mobilighter)

FOUNDATION_EXPORT NSString *Mobilighter_DATE_FORMAT_STR_;
J2OBJC_STATIC_FIELD_GETTER(Mobilighter, DATE_FORMAT_STR_, NSString *)

FOUNDATION_EXPORT NSString *Mobilighter_DATE_TIME_FORMAT_STR_;
J2OBJC_STATIC_FIELD_GETTER(Mobilighter, DATE_TIME_FORMAT_STR_, NSString *)

FOUNDATION_EXPORT NSString *Mobilighter_MESSAGE_BOX_ERROR_TITLE_;
J2OBJC_STATIC_FIELD_GETTER(Mobilighter, MESSAGE_BOX_ERROR_TITLE_, NSString *)

FOUNDATION_EXPORT NSString *Mobilighter_MESSAGE_BOX_OK_BUTTON_;
J2OBJC_STATIC_FIELD_GETTER(Mobilighter, MESSAGE_BOX_OK_BUTTON_, NSString *)

J2OBJC_TYPE_LITERAL_HEADER(Mobilighter)

#define ComValsA2iosMobilighterIntfMobilighter Mobilighter

#endif // _ComValsA2iosMobilighterIntfMobilighter_H_