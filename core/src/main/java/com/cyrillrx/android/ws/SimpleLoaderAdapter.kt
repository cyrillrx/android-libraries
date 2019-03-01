package com.cyrillrx.android.ws

import androidx.recyclerview.widget.RecyclerView

import com.cyrillrx.android.section.ItemType

/**
 * @author Cyril Leroux
 *         Created on 17/03/2015
 */
abstract class SimpleLoaderAdapter<VH : RecyclerView.ViewHolder> : androidx.recyclerview.widget.RecyclerView.Adapter<VH>() {

    protected var isLoading: Boolean = false

    abstract fun addLoader()

    abstract fun removeLoader()

    fun isLoader(position: Int): Boolean {
        return getItemViewType(position) == ItemType.LOADER
    }
}
