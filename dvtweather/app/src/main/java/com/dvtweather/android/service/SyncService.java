package com.dvtweather.android.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dvtweather.android.DvtWeatherApp;
import com.dvtweather.android.communication.LocationPreferencesHelper;
import com.dvtweather.android.injectable.component.DaggerServiceComponent;
import com.dvtweather.android.injectable.component.ServiceComponent;

/**
 * Created by Masi Stoto on 01/02/17.
 */

public class SyncService extends Service {
    private static final String TAG = "SyncService";
    //private LocationManager locationManager = null;
    private static final int LOCATION_INTERVAL = 1000;
    private static final float LOCATION_DISTANCE = 10f;

    private LocationPreferencesHelper locationPreferences;

    //@Inject
    LocationManager mLocationManager;
    //-----------------------------------------------------------------------------
    public static Intent getStartIntent(Context context) {
        return new Intent(context, SyncService.class);
    }
    //-----------------------------------------------------------------------------
    public static void start(Context context) {
        Intent starter = new Intent(context, SyncService.class);
        context.startService(starter);
    }
    //-----------------------------------------------------------------------------
    public static void stop(Context context) {
        context.stopService(new Intent(context, SyncService.class));
    }
    //-----------------------------------------------------------------------------
    @Override
    public void onCreate() {
        super.onCreate();
        locationPreferences = new LocationPreferencesHelper(getApplicationContext());
        ServiceComponent component = DaggerServiceComponent.builder()
                .applicationComponent(((DvtWeatherApp) getApplication()).getComponent())
                .build();
        component.inject(this);
        Log.e(TAG, "SyncService onCreate()");
        // request for permissions from user at runtime. FINE and COARSE
        initializeLocationManager();
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }
    }
    //-----------------------------------------------------------------------------
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
    //-----------------------------------------------------------------------------
    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listeners, ignore", ex);
                }
            }
        }
    }
    //-----------------------------------------------------------------------------
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    //-----------------------------------------------------------------------------
    private class LocationListener implements android.location.LocationListener{
        Location mLastLocation;

        public LocationListener(String provider){
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
        }
        //-----------------------------------------------------------------------
        @Override
        public void onLocationChanged(Location location){
            Log.e(TAG, "onLocationChanged: " + location);
            mLastLocation.set(location);
            locationPreferences.setLatitude(location.getLatitude());
            locationPreferences.setLongitude(location.getLongitude());
        }
        //-----------------------------------------------------------------------
        @Override
        public void onProviderDisabled(String provider){
            Log.e(TAG, "onProviderDisabled: " + provider);
        }
        //-----------------------------------------------------------------------
        @Override
        public void onProviderEnabled(String provider){
            Log.e(TAG, "onProviderEnabled: " + provider);
        }
        //-----------------------------------------------------------------------
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras){
            Log.e(TAG, "onStatusChanged: " + provider);
        }
        //-----------------------------------------------------------------------
    }
    //---------------------------------------------------------------------------
    LocationListener[] mLocationListeners = new LocationListener[] {
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };
    //-----------------------------------------------------------------------------
    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }
    //-----------------------------------------------------------------------------
}
