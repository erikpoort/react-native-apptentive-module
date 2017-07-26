package com.mediamonks.rnapptentivemodule;

import android.app.Application;

import com.apptentive.android.sdk.Apptentive;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by erik on 26/07/2017.
 * MediaMonks 2017
 */

class RNApptentiveModule extends ReactContextBaseJavaModule
{
	public static final String APPTENTIVE = "apptentive";
	private final Application _application;
	private boolean _initialised;

	RNApptentiveModule(ReactApplicationContext reactContext, Application application)
	{
		super(reactContext);

		this._application = application;
	}

	@Override
	public String getName()
	{
		return "ApptentiveModule";
	}

	@ReactMethod
	public void register(String appKey, String signature, Promise promise) {
		if (_initialised) {
			promise.reject(APPTENTIVE, "Apptentive is already initialised");
			return;
		}
		if (appKey == null || appKey.isEmpty()) {
			promise.reject(APPTENTIVE, "Your appKey is empty");
			return;
		}
		if (signature == null || signature.isEmpty()) {
			promise.reject(APPTENTIVE, "Your signature is empty");
			return;
		}

		Apptentive.register(this._application, appKey, signature);
		promise.resolve(true);
		_initialised = true;
	}

	@ReactMethod
	public void presentMessageCenter(Promise promise) {
		if (!_initialised) {
			promise.reject(APPTENTIVE, "Apptentive is not initialised");
			return;
		}
		if (!Apptentive.canShowMessageCenter()) {
			promise.reject(APPTENTIVE, "Apptentive message center can't be shown");
			return;
		}

		boolean shown = Apptentive.showMessageCenter(getReactApplicationContext());
		promise.resolve(shown);
	}

	@ReactMethod
	public void presentMessageCenterWithCustomData(ReadableMap customData, Promise promise) {
		if (customData == null) {
			this.presentMessageCenter(promise);
			return;
		}
		if (!_initialised) {
			promise.reject(APPTENTIVE, "Apptentive is not initialised");
			return;
		}
		if (!Apptentive.canShowMessageCenter()) {
			promise.reject(APPTENTIVE, "Apptentive message center can't be shown");
			return;
		}
		if (!(customData instanceof ReadableNativeMap)) {
			promise.reject(APPTENTIVE, "Apptentive can't handle this customData");
			return;
		}

		ReadableNativeMap nativeMap = (ReadableNativeMap) customData;
		HashMap<String, Object> hashMap = nativeMap.toHashMap();

		boolean shown = Apptentive.showMessageCenter(getReactApplicationContext(), hashMap);
		promise.resolve(shown);
	}

	@ReactMethod
	public void engageEvent(String event, Promise promise) {
		if (!_initialised) {
			promise.reject(APPTENTIVE, "Apptentive is not initialised");
			return;
		}
		if (event == null || event.isEmpty()) {
			promise.reject(APPTENTIVE, "Your event is empty");
			return;
		}

		boolean engaged = Apptentive.engage(getReactApplicationContext(), event);
		promise.resolve(engaged);
	}

	@ReactMethod
	public void engageEventWithCustomData(String event, ReadableMap customData, Promise promise) {
		if (!_initialised) {
			promise.reject(APPTENTIVE, "Apptentive is not initialised");
			return;
		}
		if (event == null || event.isEmpty()) {
			promise.reject(APPTENTIVE, "Your event is empty");
			return;
		}
		if (!(customData instanceof ReadableNativeMap)) {
			promise.reject(APPTENTIVE, "Apptentive can't handle this customData");
			return;
		}

		ReadableNativeMap nativeMap = (ReadableNativeMap) customData;
		HashMap<String, Object> hashMap = nativeMap.toHashMap();

		boolean engaged = Apptentive.engage(getReactApplicationContext(), event, hashMap);
		promise.resolve(engaged);
	}
}
