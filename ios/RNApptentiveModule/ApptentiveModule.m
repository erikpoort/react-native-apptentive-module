//
//  ApptentiveModule.m
//  react-native-apptentive-module
//
//  Created by Erik Poort on 26/07/2017.
//  Copyright (c) 2017 MediaMonks. All rights reserved.
//

#import <apptentive-ios/Apptentive.h>
#import "ApptentiveModule.h"

static NSString *const kRejectCode = @"apptentive";

@implementation ApptentiveModule
{
	BOOL _initialised;
}
RCT_EXPORT_MODULE();

#pragma mark - Configuration

RCT_EXPORT_METHOD(
	registerWithAppKey:(NSString *)appKey
	signature:(NSString *)signature
	appleID:(NSString *)appleID
	resolver:(RCTPromiseResolveBlock)resolve
	rejecter:(RCTPromiseRejectBlock)reject
) {
	if (_initialised) {
		reject(kRejectCode, @"Apptentive is already initialised", nil);
		return;
	}
	if (!appKey || [appKey isEqualToString:@""]) {
		reject(kRejectCode, @"Your appKey is empty", nil);
		return;
	}
	if (!signature || [signature isEqualToString:@""]) {
		reject(kRejectCode, @"Your signature is empty", nil);
		return;
	}

	ApptentiveConfiguration *configuration = [ApptentiveConfiguration
			configurationWithApptentiveKey:appKey
			apptentiveSignature:signature];

	if (configuration) {
		configuration.appID = appleID;
		[Apptentive registerWithConfiguration:configuration];
		_initialised = YES;
		resolve(configuration.distributionName);
	} else {
		reject(kRejectCode, @"Configuration returned nil", nil);
	}
}

#pragma mark - Message Center

RCT_EXPORT_METHOD(
	presentMessageCenter:(RCTPromiseResolveBlock)resolve
	rejecter:(RCTPromiseRejectBlock)reject
) {
	if (!_initialised) {
		reject(kRejectCode, @"Apptentive is not initialised", nil);
		return;
	}
	
	UIViewController *viewController = [UIApplication sharedApplication].keyWindow.rootViewController;
	BOOL presented = [[Apptentive shared] presentMessageCenterFromViewController:viewController];
	resolve(@(presented));
}

RCT_EXPORT_METHOD(
	presentMessageCenterWithCustomData:(NSDictionary *)customData
	resolver:(RCTPromiseResolveBlock)resolve
	rejecter:(RCTPromiseRejectBlock)reject
) {
	if (!_initialised) {
		reject(kRejectCode, @"Apptentive is not initialised", nil);
		return;
	}
	if (!customData) {
		customData = @{};
	}

	UIViewController *viewController = [UIApplication sharedApplication].keyWindow.rootViewController;
	BOOL presented = [[Apptentive shared] presentMessageCenterFromViewController:viewController withCustomData:customData];
	resolve(@(presented));
}

#pragma mark - Events

RCT_EXPORT_METHOD(
	engageEvent:(NSString *)event
	resolver:(RCTPromiseResolveBlock)resolve
	rejecter:(RCTPromiseRejectBlock)reject
) {
	if (!_initialised) {
		reject(kRejectCode, @"Apptentive is not initialised", nil);
		return;
	}
	if (!event || [event isEqualToString:@""]) {
		reject(kRejectCode, @"Your event is empty", nil);
		return;
	}

	UIViewController *viewController = [UIApplication sharedApplication].keyWindow.rootViewController;
	BOOL engaged = [[Apptentive shared] engage:event fromViewController:viewController];
	resolve(@(engaged));
}

RCT_EXPORT_METHOD(
	engageEventWithCustomData:(NSString *)event
	customData:(NSDictionary *)customData
	resolver:(RCTPromiseResolveBlock)resolve
	rejecter:(RCTPromiseRejectBlock)reject
) {
	if (!_initialised) {
		reject(kRejectCode, @"Apptentive is not initialised", nil);
		return;
	}
	if (!event || [event isEqualToString:@""]) {
		reject(kRejectCode, @"Your event is empty", nil);
		return;
	}
	if (!customData) {
		customData = @{};
	}

	UIViewController *viewController = [UIApplication sharedApplication].keyWindow.rootViewController;
	BOOL engaged = [[Apptentive shared] engage:event withCustomData:customData fromViewController:viewController];
	resolve(@(engaged));
}

@end
