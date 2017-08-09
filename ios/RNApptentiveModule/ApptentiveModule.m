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
	debug:(BOOL)debug
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

	[configuration setLogLevel:debug ? ApptentiveLogLevelVerbose : ApptentiveLogLevelCrit];

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
	if (![Apptentive shared].canShowMessageCenter) {
		reject(kRejectCode, @"Apptentive message center can't be shown", nil);
		return;
	}

	BOOL presented = [[Apptentive shared] presentMessageCenterFromViewController:[self viewController]];
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
	if (![Apptentive shared].canShowMessageCenter) {
		reject(kRejectCode, @"Apptentive message center can't be shown", nil);
		return;
	}
	if (!customData) {
		customData = @{};
	}

	BOOL presented = [[Apptentive shared] presentMessageCenterFromViewController:[self viewController] withCustomData:customData];
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

	BOOL engaged = [[Apptentive shared] engage:event fromViewController:[self viewController]];
	resolve(@(engaged));
}

#pragma mark - Adding data

RCT_EXPORT_METHOD(
	addPersonDataString:(NSString *)string
	withKey:(NSString *)key
	resolver:(RCTPromiseResolveBlock)resolve
	rejecter:(RCTPromiseRejectBlock)reject
) {
	if (!_initialised) {
		reject(kRejectCode, @"Apptentive is not initialised", nil);
		return;
	}
	if (!key || [key isEqualToString:@""]) {
		reject(kRejectCode, @"Your key is empty", nil);
		return;
	}
	if (!string || [string isEqualToString:@""]) {
		reject(kRejectCode, @"Your string is empty", nil);
		return;
	}

	[[Apptentive shared] addCustomPersonDataString:string withKey:key];
	resolve(@YES);
}

RCT_EXPORT_METHOD(
	addPersonDataNumber:(nonnull NSNumber *)number
	withKey:(NSString *)key
	resolver:(RCTPromiseResolveBlock)resolve
	rejecter:(RCTPromiseRejectBlock)reject
) {
	if (!_initialised) {
		reject(kRejectCode, @"Apptentive is not initialised", nil);
		return;
	}
	if (!key || [key isEqualToString:@""]) {
		reject(kRejectCode, @"Your key is empty", nil);
		return;
	}
	if (!number) {
		reject(kRejectCode, @"Your string is empty", nil);
		return;
	}

	[[Apptentive shared] addCustomPersonDataNumber:number withKey:key];
	resolve(@YES);
}

RCT_EXPORT_METHOD(
	addPersonDataBool:(BOOL)boolean
	withKey:(NSString *)key
	resolver:(RCTPromiseResolveBlock)resolve
	rejecter:(RCTPromiseRejectBlock)reject
) {
	if (!_initialised) {
		reject(kRejectCode, @"Apptentive is not initialised", nil);
		return;
	}
	if (!key || [key isEqualToString:@""]) {
		reject(kRejectCode, @"Your key is empty", nil);
		return;
	}

	[[Apptentive shared] addCustomPersonDataBool:boolean withKey:key];
	resolve(@YES);
}

RCT_EXPORT_METHOD(
	addDeviceDataString:(NSString *)string
	withKey:(NSString *)key
	resolver:(RCTPromiseResolveBlock)resolve
	rejecter:(RCTPromiseRejectBlock)reject
) {
	if (!_initialised) {
		reject(kRejectCode, @"Apptentive is not initialised", nil);
		return;
	}
	if (!key || [key isEqualToString:@""]) {
		reject(kRejectCode, @"Your key is empty", nil);
		return;
	}
	if (!string || [string isEqualToString:@""]) {
		reject(kRejectCode, @"Your string is empty", nil);
		return;
	}

	[[Apptentive shared] addCustomDeviceDataString:string withKey:key];
	resolve(@YES);
}

RCT_EXPORT_METHOD(
	addDeviceDataNumber:(nonnull NSNumber *)number
	withKey:(NSString *)key
	resolver:(RCTPromiseResolveBlock)resolve
	rejecter:(RCTPromiseRejectBlock)reject
) {
	if (!_initialised) {
		reject(kRejectCode, @"Apptentive is not initialised", nil);
		return;
	}
	if (!key || [key isEqualToString:@""]) {
		reject(kRejectCode, @"Your key is empty", nil);
		return;
	}
	if (!number) {
		reject(kRejectCode, @"Your string is empty", nil);
		return;
	}

	[[Apptentive shared] addCustomDeviceDataNumber:number withKey:key];
	resolve(@YES);
}

RCT_EXPORT_METHOD(
	addDeviceDataBool:(BOOL)boolean
	withKey:(NSString *)key
	resolver:(RCTPromiseResolveBlock)resolve
	rejecter:(RCTPromiseRejectBlock)reject
) {
	if (!_initialised) {
		reject(kRejectCode, @"Apptentive is not initialised", nil);
		return;
	}
	if (!key || [key isEqualToString:@""]) {
		reject(kRejectCode, @"Your key is empty", nil);
		return;
	}

	[[Apptentive shared] addCustomDeviceDataBool:boolean withKey:key];
	resolve(@YES);
}

#pragma mark - Helpers

- (UIViewController *)viewController {
	UIViewController *viewController = [UIApplication sharedApplication].keyWindow.rootViewController;
	while (viewController.presentedViewController) {
		viewController = viewController.presentedViewController;
	}
	return viewController;
}

@end
