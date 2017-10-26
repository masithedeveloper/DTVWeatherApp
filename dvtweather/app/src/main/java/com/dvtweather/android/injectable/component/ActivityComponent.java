package com.dvtweather.android.injectable.component;

import com.dvtweather.android.injectable.interfaces.PerActivity;
import com.dvtweather.android.injectable.module.ActivityModule;
import com.dvtweather.android.presentable.weather.WeatherActivity;
import com.dvtweather.android.presentable.splash.SplashActivity;

import dagger.Component;

/**
 * Created by Masi Stoto on 27/01/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(WeatherActivity activity);
    void inject(SplashActivity activity);
}
