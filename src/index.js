import { NativeModules } from 'react-native';
const { RNApptentiveModule } = NativeModules;

/**
 * Configure Apptentive using you key and signature.
 * You can add an optional AppleID to redirect the user to the App Store
 * @param appKey Found in Apptentive Dashboard
 * @param appSignature Found in Apptentive Dashboard
 * @param appleID optional Found in iTunesConnect
 * @return Promise
 */
function registerWithAppKey(appKey, appSignature, appleID) {
  return ApptentiveModule.registerWithAppKey(appKey, appSignature, appleID);
}

/**
 * Present the feedback view
 * @return Promise
 */
function presentMessageCenterWithResolver() {
  return ApptentiveModule.presentMessageCenterWithResolver();
}

/**
 * Present the feedback view with custom data
 * @param customData optional
 * @return Promise
 */
function presentMessageCenterWithCustomData(customData) {
  return ApptentiveModule.presentMessageCenterWithCustomData(customData);
}

/**
 * Log an event to apptentive
 * @param event
 * @return Promise
 */
function engageEvent(event) {
  return ApptentiveModule.engageEvent(event);
}

/**
 * Log an event with custom data to apptentive
 * @param event
 * @param customData optional
 * @return Promise
 */
function engageEventWithCustomData(event, customData) {
  return ApptentiveModule.engageEventWithCustomData(event, customData);
}

module.exports = {
  registerWithAppKey,
}
