package com.dvtweather.android.injectable.module;

import android.app.Application;
import android.content.Context;
import com.dvtweather.android.R;
import com.dvtweather.android.communication.ApiHelper;
import com.dvtweather.android.communication.AppApiHelper;
import com.dvtweather.android.communication.LocationPreferencesHelper;
import com.dvtweather.android.injectable.interfaces.ApplicationContext;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Masi Stoto on 27/01/17.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;
    //------------------------------------------------------------------------------
    public ApplicationModule(Application application) {
        mApplication = application;
    }
    //------------------------------------------------------------------------------
    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }
    //------------------------------------------------------------------------------
    @Provides
    Application provideApplication() {
        return mApplication;
    }
    //------------------------------------------------------------------------------
    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }
    //------------------------------------------------------------------------------
    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
    //------------------------------------------------------------------------------
    @Provides
    @Singleton
    LocationPreferencesHelper providePreferencesHelper(LocationPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }
    //------------------------------------------------------------------------------
}
