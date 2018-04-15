package com.cyrillrx.android.utils

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
