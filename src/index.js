import { NativeModules } from 'react-native';
const { ApptentiveModule } = NativeModules;
import { init } from './platform-specific';

/**
 * Configure Apptentive using your key and signature.
 * You can add an optional AppleID to redirect the user to the App Store
 * @param appKey Found in Apptentive Dashboard
 * @param appSignature Found in Apptentive Dashboard
 * @param appleID optional Found in iTunesConnect
 * @return Promise
 */
function register(appKey, appSignature, ...args) {
  return init(ApptentiveModule, appKey, appSignature, args[0])
}

/**
 * Present the feedback view
 * @return Promise with success boolean or error
 */
function presentMessageCenter() {
  return ApptentiveModule.presentMessageCenter();
}

/**
 * Present the feedback view with custom data
 * @param customData optional
 * @return Promise with success boolean or error
 */
function presentMessageCenterWithCustomData(customData) {
  return ApptentiveModule.presentMessageCenterWithCustomData(customData);
}

/**
 * Log an event to apptentive
 * @param event
 * @return Promise with success boolean or error
 */
function engageEvent(event) {
  return ApptentiveModule.engageEvent(event);
}

/**
 * Log an event with custom data to apptentive
 * @param event
 * @param customData optional
 * @return Promise with success boolean or error
 */
function engageEventWithCustomData(event, customData) {
  return ApptentiveModule.engageEventWithCustomData(event, customData);
}

module.exports = {
  register,
  presentMessageCenter,
  presentMessageCenterWithCustomData,
  engageEvent,
  engageEventWithCustomData
}
