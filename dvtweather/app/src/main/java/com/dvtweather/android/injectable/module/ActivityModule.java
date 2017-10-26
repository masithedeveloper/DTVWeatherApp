package com.dvtweather.android.injectable.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.dvtweather.android.injectable.interfaces.ActivityContext;
import com.dvtweather.android.injectable.interfaces.PerActivity;
import com.dvtweather.android.presentable.weather.WeatherPresenter;
import com.dvtweather.android.presentable.splash.SplashMvpPresenter;
import com.dvtweather.android.presentable.splash.SplashMvpView;
import com.dvtweather.android.presentable.weather.WeatherMvpPresenter;
import com.dvtweather.android.utilities.rx.AppSchedulerProvider;
import com.dvtweather.android.utilities.rx.SchedulerProvider;
import com.dvtweather.android.presentable.weather.WeatherMvpView;
import com.dvtweather.android.presentable.splash.SplashPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Masi Stoto on 27/01/17.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;
    //----------------------------------------------------------------------------
    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }
    //----------------------------------------------------------------------------
    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }
    //----------------------------------------------------------------------------
    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }
    //----------------------------------------------------------------------------
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }
    //----------------------------------------------------------------------------
    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }
    //----------------------------------------------------------------------------
    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }
    //----------------------------------------------------------------------------
    @Provides
    @PerActivity
    WeatherMvpPresenter<WeatherMvpView> provideMainPresenter(
            WeatherPresenter<WeatherMvpView> presenter) {
        return presenter;
    }
    //----------------------------------------------------------------------------
    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
    //----------------------------------------------------------------------------
}
