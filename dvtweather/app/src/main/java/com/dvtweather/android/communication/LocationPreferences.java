package com.dvtweather.android.communication;

/**
 * Created by masi on 18/10/2017.
 */

public interface LocationPreferences {
    void setLongitude(double longitude);
    String getLongitude();
    void setLatitude(double latitude);
    String getLatitude();
}
