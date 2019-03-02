package com.cyrillrx.templates

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyrillrx.logger.Logger
import com.cyrillrx.templates.item.HeaderViewHolder
import com.cyrillrx.templates.item.ItemViewHolder
import com.cyrillrx.templates.model.Converter
import com.cyrillrx.templates.model.Header
import com.cyrillrx.templates.model.Item
import java.util.*

/**
 * @author Cyril Leroux
 *         Created on 06/04/2018.
 */
open class BaseAdapter(private val converter: Converter)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: ArrayList<Any> = ArrayList()

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int = getItemViewType(items[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                TYPE_HEADER -> HeaderViewHolder(parent)
                TYPE_ITEM -> ItemViewHolder(parent)
                else -> throw Exception("onCreateViewHolder - viewType '$viewType' not supported")
            }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(converter.toHeader(items[position]))
            is ItemViewHolder -> holder.bind(converter.toItem(items[position]))
            else -> Logger.error(TAG, "onBindViewHolder - holder '$holder' not supported")
        }
    }

    protected fun getItem(position: Int) = items[position]

    protected open fun getItemViewType(item: Any): Int =
            when (item) {
                is Header -> TYPE_HEADER
                is String -> TYPE_HEADER
                is Item -> TYPE_ITEM
                else -> TYPE_ITEM
            }

    fun add(newItem: Any) {

        items.add(newItem)
        notifyItemInserted(items.size - 1)
    }

    fun addAll(newItems: Collection<Any>) {

        val startPos = items.size
        items.addAll(newItems)
        notifyItemRangeInserted(startPos, newItems.size)
    }

    companion object {
        private const val TYPE_ITEM = 0
        private const val TYPE_HEADER = 1

        private val TAG = BaseAdapter::class.java.simpleName
    }
}