package com.dvtweather.android.communication;

import com.dvtweather.android.model.GetLocationRequest;
import com.dvtweather.android.model.GetLocationResponse;
import com.dvtweather.android.model.GetWeatherRequest;
import com.dvtweather.android.model.GetWeatherResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by Masi Stoto on 28/01/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {
    //----------------------------------------------------------------------------------------------
    @Inject
    public AppApiHelper() {}

    //----------------------------------------------------------------------------------------------
    @Override
    public Observable<GetLocationResponse> makeLocationApiCall(GetLocationRequest.ServerGetLocationRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GET_LOCATION)
                .build()
                .getObjectObservable(GetLocationResponse.class);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public Observable<GetWeatherResponse> makeWeatherApiCall(GetWeatherRequest.ServerGetWeatherRequest request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_WEATHER)
                .addQueryParameter("lat", request.getLatitude())
                .addQueryParameter("lon", request.getLongitude())
                .addQueryParameter("APPID", ApiEndPoint.GET_API_KEY)
                // the weather service I subscribed to does not have any authentication
                .build()
                .getObjectObservable(GetWeatherResponse.class);
    }
    //----------------------------------------------------------------------------------------------
}

