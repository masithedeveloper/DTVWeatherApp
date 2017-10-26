package com.dvtweather.android.utilities.rx;

import io.reactivex.Scheduler;

/**
 * Created by Masi Stoto on 5/8/2017.
 */

public interface SchedulerProvider {
    Scheduler ui();
    Scheduler computation();
    Scheduler io();
}
