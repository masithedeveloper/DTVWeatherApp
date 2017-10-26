package com.dvtweather.android.presentable.splash;

import com.dvtweather.android.injectable.interfaces.PerActivity;
import com.dvtweather.android.presentable.base.MvpPresenter;

/**
 * Created by Masi Stoto on 27/01/17.
 */

@PerActivity
public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {}
