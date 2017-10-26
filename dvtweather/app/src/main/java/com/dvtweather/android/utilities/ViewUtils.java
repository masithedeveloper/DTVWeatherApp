package com.dvtweather.android.utilities;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by Masi Stoto on 27/01/17.
 */

public final class ViewUtils {
    //---------------------------------------------------------------------------------------
    private ViewUtils() {
        // This utility class is not publicly instantiable
    }
    //---------------------------------------------------------------------------------------
     public static void changeIconDrawableToGray(Context context, Drawable drawable) {
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(ContextCompat
                    .getColor(context, com.dvtweather.android.R.color.dark_gray), PorterDuff.Mode.SRC_ATOP);
        }
    }
    //---------------------------------------------------------------------------------------
}
