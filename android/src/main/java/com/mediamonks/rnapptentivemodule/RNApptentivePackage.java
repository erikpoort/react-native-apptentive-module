package com.mediamonks.rnapptentivemodule;

import android.app.Application;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by erik on 26/07/2017.
 * MediaMonks 2017
 */

public class RNApptentivePackage implements ReactPackage
{
	private final Application _application;

	public RNApptentivePackage(Application application)
	{
		super();

		this._application = application;
	}

	@Override
	public List<NativeModule> createNativeModules(ReactApplicationContext reactContext)
	{
		List<NativeModule> modules = new ArrayList<>();
		modules.add(new RNApptentiveModule(reactContext, this._application));
		return modules;
	}

	// RN deprecated Android createJSModules
	// @Override
	public List<Class<? extends JavaScriptModule>> createJSModules()
	{
		return Collections.emptyList();
	}

	@Override
	public List<ViewManager> createViewManagers(ReactApplicationContext reactContext)
	{
		return Collections.emptyList();
	}
}
