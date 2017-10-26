package com.dvtweather.android.presentable.weather;

import com.dvtweather.android.injectable.interfaces.PerActivity;
import com.dvtweather.android.presentable.base.MvpPresenter;

/**
 * Created by Masi Stoto on 27/01/17.
 */

@PerActivity
public interface WeatherMvpPresenter<V extends WeatherMvpView> extends MvpPresenter<V> {
    void getWeather(String lat, String lon);
}
