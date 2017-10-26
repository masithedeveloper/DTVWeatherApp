
package com.dvtweather.android.presentable.splash;

import com.dvtweather.android.presentable.base.MvpView;

/**
 * Created by Masi Stoto on 27/01/17.
 */

public interface SplashMvpView extends MvpView {
    void openMainActivity();
    void startSyncService();
}
