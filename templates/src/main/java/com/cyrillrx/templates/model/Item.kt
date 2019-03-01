package com.cyrillrx.templates.model

import android.view.View

/**
 * @author Cyril Leroux
 *         Created on 28/02/2019.
 */
class Item(
        val title: String?,
        val subtitle: String?,
        val click: ((View) -> Unit)?)