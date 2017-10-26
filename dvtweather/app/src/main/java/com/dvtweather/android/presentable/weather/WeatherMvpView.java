package com.dvtweather.android.presentable.weather;

import com.dvtweather.android.model.WeatherDisplayModel;
import com.dvtweather.android.presentable.base.MvpView;

/**
 * Created by Masi Stoto on 27/01/17.
 */

public interface WeatherMvpView extends MvpView {
    void setWeather(WeatherDisplayModel weather);
}
