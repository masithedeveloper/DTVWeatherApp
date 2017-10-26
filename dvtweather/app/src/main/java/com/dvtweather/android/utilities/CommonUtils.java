package com.dvtweather.android.utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import com.dvtweather.android.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Masi Stoto on 27/01/17.
 */

public final class CommonUtils {

    private static final String TAG = "CommonUtils";
    //--------------------------------------------------------------------------------------
    private CommonUtils() {
        // This utility class is not publicly instantiable
    }
    //--------------------------------------------------------------------------------------
    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
    //--------------------------------------------------------------------------------------
    public static String getTimeStamp() {
        return new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.ENGLISH).format(new Date());
    }
    //--------------------------------------------------------------------------------------
    public static String getToday() {
        return new SimpleDateFormat(AppConstants.DATE_FORMAT, Locale.ENGLISH).format(new Date());
    }
    //--------------------------------------------------------------------------------------
}
