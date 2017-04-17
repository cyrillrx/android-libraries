package com.cyrillrx.android.ws

import android.support.v7.widget.RecyclerView

import com.cyrillrx.android.section.ItemType

/**
 * @author Cyril Leroux
 * *         Created on 17/03/15
 */
abstract class SimpleLoaderAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    protected var isLoading: Boolean = false

    abstract fun addLoader()

    abstract fun removeLoader()

    fun isLoader(position: Int): Boolean {
        return getItemViewType(position) == ItemType.LOADER
    }
}
