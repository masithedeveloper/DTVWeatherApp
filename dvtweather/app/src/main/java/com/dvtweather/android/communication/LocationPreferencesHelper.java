package com.dvtweather.android.communication;

import android.content.Context;
import android.content.SharedPreferences;

import com.dvtweather.android.injectable.interfaces.ApplicationContext;

import javax.inject.Singleton;

/**
 * Created by masi on 18/10/2017.
 */

@Singleton
public class LocationPreferencesHelper implements LocationPreferences {

    private static final String PREF_KEY_LONGITUDE = "PREF_KEY_LONGITUDE";
    private static final String PREF_KEY_LATITUDE = "PREF_KEY_LATITUDE";
    private final SharedPreferences sharedPreferences;

    //----------------------------------------------------------------------------------------------
    public LocationPreferencesHelper(@ApplicationContext Context context){
        sharedPreferences = context.getSharedPreferences("com.dvtweather.android", Context.MODE_PRIVATE);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void setLongitude(double longitude) {
        sharedPreferences.edit().putString(PREF_KEY_LONGITUDE, String.valueOf(longitude)).apply();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public String getLongitude() {
        return sharedPreferences.getString(PREF_KEY_LONGITUDE, null);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void setLatitude(double latitude) {
        sharedPreferences.edit().putString(PREF_KEY_LATITUDE, String.valueOf(latitude)).apply();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public String getLatitude() {
        return sharedPreferences.getString(PREF_KEY_LATITUDE, null);
    }
    //----------------------------------------------------------------------------------------------
}
