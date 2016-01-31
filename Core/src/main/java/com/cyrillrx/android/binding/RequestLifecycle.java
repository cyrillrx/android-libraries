package com.cyrillrx.android.binding;

/**
 * @author Cyril Leroux
 *         Created on 31/01/2016
 */
public interface RequestLifecycle {

    void onStartLoading();

    void onStopLoading();

    void onRequestFailure();
}
