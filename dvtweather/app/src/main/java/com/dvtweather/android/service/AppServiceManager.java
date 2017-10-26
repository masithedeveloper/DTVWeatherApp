package com.dvtweather.android.service;

import android.content.Context;

import com.dvtweather.android.communication.ApiHelper;
import com.dvtweather.android.injectable.interfaces.ApplicationContext;
import com.dvtweather.android.model.GetLocationRequest;
import com.dvtweather.android.model.GetLocationResponse;
import com.dvtweather.android.model.GetWeatherRequest;
import com.dvtweather.android.model.GetWeatherResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by masi on 16/10/2017.
 */
@Singleton
public class AppServiceManager implements ServiceManager {
    //-----------------------------------------------------------------------------------
    private final ApiHelper mApiHelper;
    private final Context mContext;

    @Inject
    public AppServiceManager(@ApplicationContext Context context , ApiHelper apiHelper) {
        mContext = context;
        mApiHelper = apiHelper;
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public Observable<GetLocationResponse> makeLocationApiCall(GetLocationRequest.ServerGetLocationRequest request) {
        return mApiHelper.makeLocationApiCall(request);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public Observable<GetWeatherResponse> makeWeatherApiCall(GetWeatherRequest.ServerGetWeatherRequest request) {
        return mApiHelper.makeWeatherApiCall(request);
    }
    //----------------------------------------------------------------------------------------------
}
