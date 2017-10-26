package com.dvtweather.android;

import android.app.Application;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.dvtweather.android.injectable.component.ApplicationComponent;
import com.dvtweather.android.injectable.component.DaggerApplicationComponent;
import com.dvtweather.android.injectable.module.ApplicationModule;

/**
 * Created by Masi Stoto on 27/01/17.
 */

public class DvtWeatherApp extends Application {

    private ApplicationComponent mApplicationComponent;
    //---------------------------------------------------------------------------------------
    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }
    }
    //---------------------------------------------------------------------------------------
    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }
    //---------------------------------------------------------------------------------------
}
