package com.dvtweather.android.presentable.base;

/**
 * Created by Masi Stoto on 27/01/17.
 */

import android.support.annotation.StringRes;

public interface MvpView {
    void showLoading();
    void hideLoading();
    //void openActivityOnTokenExpire();
    void onError(@StringRes int resId);
    void onError(String message);
    void showMessage(String message);
    void showMessage(@StringRes int resId);
    boolean isNetworkConnected();
}
