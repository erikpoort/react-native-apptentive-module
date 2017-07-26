/**
 * Configure Apptentive using your key and signature.
 * You can add an optional AppleID to redirect the user to the App Store
 * @param appKey Found in Apptentive Dashboard
 * @param appSignature Found in Apptentive Dashboard
 * @return Promise
 */
function init(Module, appKey, appSignature) {
  return Module.register(appKey, appSignature);
}

module.exports = {
  init,
}
