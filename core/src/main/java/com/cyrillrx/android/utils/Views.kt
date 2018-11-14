package com.cyrillrx.android.utils

import android.app.Activity
import android.content.ContextWrapper
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author Cyril Leroux
 *         Created on 15/04/2018.
 */

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
        LayoutInflater
                .from(this.context)
                .inflate(layoutRes, this, attachToRoot)

fun View.getActivity(): Activity? {

    val viewContext = this.context
    if (viewContext is Activity) { // Android > 4
        return viewContext
    }

    if (viewContext is ContextWrapper && viewContext.baseContext is Activity) {
        return viewContext.baseContext as Activity
    }

    val rootContext = this.rootView.context
    if (rootContext is Activity) { // Android >= 4
        return rootContext
    }

    return null
}