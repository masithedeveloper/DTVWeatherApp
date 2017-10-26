package com.dvtweather.android.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Masi Stoto on 27/01/17.
 */

public final class NetworkUtils {
    //---------------------------------------------------------------------------------------
    private NetworkUtils() {}
    //---------------------------------------------------------------------------------------
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
    //---------------------------------------------------------------------------------------
}
