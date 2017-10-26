package com.dvtweather.android.communication;

import com.dvtweather.android.BuildConfig;

/**
 * Created by Masi Stoto on 01/02/17.
 */

public final class ApiEndPoint {
    //----------------------------------------------------------------------------------------------
    private ApiEndPoint() {
        // This class is not publicly instantiable
    }
    //----------------------------------------------------------------------------------------------
    static final String ENDPOINT_GET_WEATHER = BuildConfig.WEATHER_SERVICE_BASE_URL;
    static final String ENDPOINT_IMAGE_WEATHER = BuildConfig.WEATHER_SERVICE_IMAGE_URL; // why can't I use this to load image in the activity
    static final String ENDPOINT_GET_LOCATION = BuildConfig.WEATHER_SERVICE_BASE_URL;
    static final String GET_API_KEY = BuildConfig.WEATHER_SERVICE_API_KEY;
    //----------------------------------------------------------------------------------------------
}
