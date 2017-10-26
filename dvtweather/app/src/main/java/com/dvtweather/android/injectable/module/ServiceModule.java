package com.dvtweather.android.injectable.module;

import android.app.Service;
import dagger.Module;

/**
 * Created by Masi Stoto on 01/02/17.
 */

@Module
public class ServiceModule {
    private final Service mService;
    //-----------------------------------------------------------------------------
    public ServiceModule(Service service) {
        mService = service;
    }
    //-----------------------------------------------------------------------------
}
