/**
 * Configure Apptentive using your key and signature.
 * You can add an optional AppleID to redirect the user to the App Store
 * @param appKey Found in Apptentive Dashboard
 * @param appSignature Found in Apptentive Dashboard
 * @param appleID optional Found in iTunesConnect
 * @param debug Set debug
 * @return Promise
 */
function init(Module, appKey, appSignature, appleID, debug = false) {
  return Module.registerWithAppKey(appKey, appSignature, appleID, debug);
}

module.exports = {
  init,
}
