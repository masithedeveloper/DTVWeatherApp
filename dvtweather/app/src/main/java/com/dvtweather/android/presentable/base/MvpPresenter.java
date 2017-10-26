package com.dvtweather.android.presentable.base;

/**
 * Created by Masi Stoto on 27/01/17.
 */

import com.androidnetworking.error.ANError;

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);
    void onDetach();
    void handleApiError(ANError error);
}
