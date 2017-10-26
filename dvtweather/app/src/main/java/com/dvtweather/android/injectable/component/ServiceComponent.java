package com.dvtweather.android.injectable.component;

import com.dvtweather.android.injectable.interfaces.PerService;
import com.dvtweather.android.injectable.module.ServiceModule;
import com.dvtweather.android.service.SyncService;

import dagger.Component;

/**
 * Created by Masi Stoto on 01/02/17.
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
    void inject(SyncService service);
}
