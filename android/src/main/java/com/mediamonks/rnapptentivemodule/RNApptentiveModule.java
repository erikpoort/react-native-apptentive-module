package com.mediamonks.rnapptentivemodule;

import android.app.Application;

import com.apptentive.android.sdk.Apptentive;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;

import java.util.HashMap;

/**
 * Created by erik on 26/07/2017.
 * MediaMonks 2017
 */

class RNApptentiveModule extends ReactContextBaseJavaModule
{
	private static final String APPTENTIVE = "apptentive";
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
	public void register(final String appKey, final String signature, final Promise promise)
	{
		if (_initialised)
		{
			promise.reject(APPTENTIVE, "Apptentive is already initialised");
			return;
		}
		if (appKey == null || appKey.isEmpty())
		{
			promise.reject(APPTENTIVE, "Your appKey is empty");

			return;
		}
		if (signature == null || signature.isEmpty())
		{
			promise.reject(APPTENTIVE, "Your signature is empty");
			return;
		}

		Apptentive.register(_application, appKey, signature);
		promise.resolve(true);
		_initialised = true;
	}

	@ReactMethod
	public void presentMessageCenter(final Promise promise)
	{
		if (!_initialised)
		{
			promise.reject(APPTENTIVE, "Apptentive is not initialised");
			return;
		}

		Apptentive.canShowMessageCenter(new Apptentive.BooleanCallback()
		{
			@Override public void onFinish(boolean canShowMessageCenter)
			{
				if (canShowMessageCenter)
				{
					Apptentive.showMessageCenter(getReactApplicationContext(), new Apptentive.BooleanCallback()
					{
						@Override public void onFinish(boolean shown)
						{
							promise.resolve(shown);
						}
					});
				} else
					{
					promise.reject(APPTENTIVE, "Apptentive message center can't be shown");
				}
			}
		});
	}

	@ReactMethod
	public void presentMessageCenterWithCustomData(final ReadableMap customData, final Promise promise)
	{
		if (customData == null)
		{
			this.presentMessageCenter(promise);
			return;
		}
		if (!_initialised)
		{
			promise.reject(APPTENTIVE, "Apptentive is not initialised");
			return;
		}
		if (!(customData instanceof ReadableNativeMap))
		{
			promise.reject(APPTENTIVE, "Apptentive can't handle this customData");
			return;
		}

		ReadableNativeMap nativeMap = (ReadableNativeMap) customData;
		final HashMap<String, Object> hashMap = nativeMap.toHashMap();

		Apptentive.canShowMessageCenter(new Apptentive.BooleanCallback()
		{
			@Override public void onFinish(boolean canShowMessageCenter)
			{
				Apptentive.showMessageCenter(getReactApplicationContext(), new Apptentive.BooleanCallback()
				{
					@Override public void onFinish(boolean shown)
					{
						promise.resolve(shown);
					}
				}, hashMap);
			}
		});
	}

	@ReactMethod
	public void engageEvent(final String event, final Promise promise)
	{
		if (!_initialised)
		{
			promise.reject(APPTENTIVE, "Apptentive is not initialised");
			return;
		}
		if (event == null || event.isEmpty())
		{
			promise.reject(APPTENTIVE, "Your event is empty");
			return;
		}
		Apptentive.engage(getReactApplicationContext(), event, new Apptentive.BooleanCallback()
		{
			@Override public void onFinish(boolean engaged)
			{
				promise.resolve(engaged);
			}
		});
	}

	@ReactMethod
	public void addPersonDataString(final String string, final String key, final Promise promise)
	{
		if (!_initialised)
		{
			promise.reject(APPTENTIVE, "Apptentive is not initialised");
			return;
		}
		if (string == null || string.isEmpty())
		{
			promise.reject(APPTENTIVE, "Your string is empty");
			return;
		}
		if (key == null || key.isEmpty())
		{
			promise.reject(APPTENTIVE, "Your key is empty");
			return;
		}

		Apptentive.addCustomPersonData(key, string);
		promise.resolve(true);
	}

	@ReactMethod
	public void addPersonDataNumber(final Number number, final String key, final Promise promise)
	{
		if (!_initialised)
		{
			promise.reject(APPTENTIVE, "Apptentive is not initialised");
			return;
		}
		if (number == null)
		{
			promise.reject(APPTENTIVE, "Your number is empty");
			return;
		}
		if (key == null || key.isEmpty())
		{
			promise.reject(APPTENTIVE, "Your key is empty");
			return;
		}

		Apptentive.addCustomPersonData(key, number);
		promise.resolve(true);
	}

	@ReactMethod
	public void addPersonDataBool(final Boolean bool, final String key, final Promise promise)
	{
		if (!_initialised)
		{
			promise.reject(APPTENTIVE, "Apptentive is not initialised");
			return;
		}
		if (bool == null)
		{
			promise.reject(APPTENTIVE, "Your bool is empty");
			return;
		}
		if (key == null || key.isEmpty())
		{
			promise.reject(APPTENTIVE, "Your key is empty");
			return;
		}

		Apptentive.addCustomPersonData(key, bool);
		promise.resolve(true);
	}

	@ReactMethod
	public void addDeviceDataString(final String string, final String key, final Promise promise)
	{
		if (!_initialised)
		{
			promise.reject(APPTENTIVE, "Apptentive is not initialised");
			return;
		}
		if (string == null || string.isEmpty())
		{
			promise.reject(APPTENTIVE, "Your string is empty");
			return;
		}
		if (key == null || key.isEmpty())
		{
			promise.reject(APPTENTIVE, "Your key is empty");
			return;
		}

		Apptentive.addCustomDeviceData(key, string);
		promise.resolve(true);
	}

	@ReactMethod
	public void addDeviceDataNumber(final Number number, final String key, final Promise promise)
	{
		if (!_initialised)
		{
			promise.reject(APPTENTIVE, "Apptentive is not initialised");
			return;
		}
		if (number == null)
		{
			promise.reject(APPTENTIVE, "Your number is empty");
			return;
		}
		if (key == null || key.isEmpty())
		{
			promise.reject(APPTENTIVE, "Your key is empty");
			return;
		}

		Apptentive.addCustomDeviceData(key, number);
		promise.resolve(true);
	}

	@ReactMethod
	public void addDeviceDataBool(final Boolean bool, final String key, final Promise promise)
	{
		if (!_initialised)
		{
			promise.reject(APPTENTIVE, "Apptentive is not initialised");
			return;
		}
		if (bool == null)
		{
			promise.reject(APPTENTIVE, "Your bool is empty");
			return;
		}
		if (key == null || key.isEmpty())
		{
			promise.reject(APPTENTIVE, "Your key is empty");
			return;
		}

		Apptentive.addCustomDeviceData(key, bool);
		promise.resolve(true);
	}
}
