import { NativeModules } from 'react-native';
const { ApptentiveModule } = NativeModules;
import { init } from './platform-specific';

function register(appKey, appSignature, ...args) {
  return init(ApptentiveModule, appKey, appSignature, args[0])
}

/**
 * Present the feedback view
 * @return Promise
 */
function presentMessageCenter() {
  return ApptentiveModule.presentMessageCenter();
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
  register,
  presentMessageCenter,
}
