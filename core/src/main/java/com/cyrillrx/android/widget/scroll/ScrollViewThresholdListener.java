package com.cyrillrx.android.widget.scroll;

import android.widget.ScrollView;

/**
 * @author Cyril Leroux
 *         Created on 22/01/2015
 */
public abstract class ScrollViewThresholdListener implements OnScrollChangedListener, ScrollDirectionListener {

    private int lastScrollY;
    private int scrollThreshold;

    @Override
    public void onScrollChanged(ScrollView who, int x, int y, int oldX, int oldY) {
        boolean isSignificantDelta = Math.abs(y - lastScrollY) > scrollThreshold;
        if (isSignificantDelta) {
            if (y > lastScrollY) {
                onScrollUp();
            } else {
                onScrollDown();
            }
        }
        lastScrollY = y;
    }

    public void setThreshold(int scrollThreshold) { this.scrollThreshold = scrollThreshold; }
}