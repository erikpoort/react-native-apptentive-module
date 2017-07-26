package com.mediamonks.rnapptentivemodule;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

/**
 * Created by erik on 26/07/2017.
 * MediaMonks 2017
 */

class RNApptentiveModule extends ReactContextBaseJavaModule
{
	RNApptentiveModule(ReactApplicationContext reactContext)
	{
		super(reactContext);
	}

	@Override
	public String getName()
	{
		return "RNApptentiveModule";
	}
}
