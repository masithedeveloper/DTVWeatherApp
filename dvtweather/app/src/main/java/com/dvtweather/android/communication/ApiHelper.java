package com.dvtweather.android.communication;

import com.dvtweather.android.model.GetLocationRequest;
import com.dvtweather.android.model.GetLocationResponse;
import com.dvtweather.android.model.GetWeatherRequest;
import com.dvtweather.android.model.GetWeatherResponse;

import io.reactivex.Observable;

/**
 * Created by Masi Stoto on 27/01/17.
 */

public interface ApiHelper {
    //----------------------------------------------------------------------------------------------
    Observable<GetLocationResponse> makeLocationApiCall (GetLocationRequest.ServerGetLocationRequest request);
    //----------------------------------------------------------------------------------------------
    Observable<GetWeatherResponse> makeWeatherApiCall(GetWeatherRequest.ServerGetWeatherRequest request);
    //----------------------------------------------------------------------------------------------
}