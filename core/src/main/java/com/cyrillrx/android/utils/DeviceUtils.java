package com.cyrillrx.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.Display;

import com.cyrillrx.android.R;

import androidx.annotation.Nullable;

/**
 * @author Cyril Leroux
 *         Created on 17/02/16
 */
public class DeviceUtils {

    /**
     * @return True is the device is in landscape mode.
     * Returns false if the resource is null.
     */
    public static boolean isLandscape(@Nullable Resources res) {

        return res != null && Configuration.ORIENTATION_LANDSCAPE == res.getConfiguration().orientation;
    }

    /**
     * @return True is the device is in portrait mode.
     * Returns false if the resource is null.
     */
    public static boolean isPortrait(@Nullable Resources res) {

        return res != null && Configuration.ORIENTATION_PORTRAIT == res.getConfiguration().orientation;
    }

    /**
     * @return True if the device density is sw600dp or superior.
     * Returns false if the resource is null.
     */
    public static boolean isTablet(Resources res) {

        return res != null && res.getBoolean(R.bool.is_tablet);
    }

    /**
     * @return True if the device density is inferior to sw600dp.
     * Returns false if the resource is null.
     */
    public static boolean isPhone(Resources res) {

        return res != null && !res.getBoolean(R.bool.is_tablet);
    }

    /**
     * @return The size of the display, in pixels
     */
    public static Point getScreenSize(Context context) {

        final Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        return size;
    }
}
