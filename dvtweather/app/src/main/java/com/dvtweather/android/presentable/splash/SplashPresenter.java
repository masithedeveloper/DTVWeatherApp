package com.dvtweather.android.presentable.splash;

import com.dvtweather.android.presentable.base.BasePresenter;
import com.dvtweather.android.utilities.rx.SchedulerProvider;
import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Masi Stoto on 27/01/17.
 */

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V> implements SplashMvpPresenter<V>{
    //--------------------------------------------------------------------------------------
    @Inject
    public SplashPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(schedulerProvider, compositeDisposable);
    }
    //--------------------------------------------------------------------------------------
    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        getMvpView().startSyncService();
        navigateToMainActivity();
    }
    //--------------------------------------------------------------------------------------
    private void navigateToMainActivity() {
            getMvpView().openMainActivity();
    }
    //--------------------------------------------------------------------------------------
}