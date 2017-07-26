# react-native-apptentive-module
ReactNative module for Apptentive 4.0.0+
Version 1.0.0

## Installation

```bash
npm install --save react-native-apptentive-module
```
```bash
react-native link react-native-apptentive-module
```

### iOS
iOS is depending on CocoaPods. Follow the Apptentive guides to learn how to add apptentive.

### Android
Android needs some extra attention as well, because Apptentive needs a reference to your 
Application. In your Application class, make sure the package is added as followed:
```java
new RNApptentivePackage(this)
``` 

## Usage
Everything is handled in JS, no native code necessary, other than described above. 
```js
// Import
import Apptentive from 'react-native-apptentive-module';
```

The following calls are implemented:
```js
/**
 * Configure Apptentive using your key and signature.
 * You can add an optional AppleID to redirect the user to the App Store
 * @param appKey Found in Apptentive Dashboard
 * @param appSignature Found in Apptentive Dashboard
 * @param appleID optional Found in iTunesConnect
 * @return Promise
 */
Apptentive.register(appKey, appSignature, appleID);

/**
 * Present the feedback view
 * @return Promise with success boolean or error
 */
Apptentive.presentMessageCenter();

/**
 * Present the feedback view with custom data
 * @param customData optional Object
 * @return Promise with success boolean or error
 */
Apptentive.presentMessageCenterWithCustomData(customData);

/**
 * Log an event to apptentive
 * @param event String event name
 * @return Promise with success boolean or error
 */
Apptentive.engageEvent(event);

/**
 * Log an event with custom data to apptentive
 * @param event String event name
 * @param customData optional Object
 * @return Promise with success boolean or error
 */
Apptentive.engageEventWithCustomData(event, customData);
```