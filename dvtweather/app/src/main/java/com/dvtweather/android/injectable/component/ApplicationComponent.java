package com.dvtweather.android.injectable.component;

import android.app.Application;
import android.content.Context;

import com.dvtweather.android.DvtWeatherApp;
import com.dvtweather.android.injectable.interfaces.ApplicationContext;
import com.dvtweather.android.injectable.module.ApplicationModule;
import com.dvtweather.android.service.SyncService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(DvtWeatherApp app);
    void inject(SyncService service);

    @ApplicationContext
    Context context();
    Application application();

    //ServiceManager getLocationManager();
}