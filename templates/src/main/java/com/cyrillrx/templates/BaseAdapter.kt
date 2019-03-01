package com.cyrillrx.templates

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyrillrx.logger.Logger
import com.cyrillrx.templates.item.HeaderViewHolder
import com.cyrillrx.templates.item.ItemViewHolder
import com.cyrillrx.templates.model.ItemFactory
import java.util.*

/**
 * @author Cyril Leroux
 *         Created on 06/04/2018.
 */
class BaseAdapter(private val factory: ItemFactory)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: ArrayList<Any> = ArrayList()

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int =
            when (items[position]) {
                is String -> TYPE_HEADER
                else -> TYPE_ITEM
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                TYPE_HEADER -> HeaderViewHolder(parent)
                TYPE_ITEM -> ItemViewHolder(parent)
                else -> throw Exception("onCreateViewHolder - viewType '$viewType' not supported")
            }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(factory.createHeader(items[position]))
            is ItemViewHolder -> holder.bind(factory.createItem(items[position]))
            else -> Logger.error(TAG, "onBindViewHolder - holder '$holder' not supported")
        }
    }

    fun add(title: CharSequence?) {
        if (title == null) return

        items.add(title)
        notifyItemInserted(items.size - 1)
    }

    fun addAll(newItem: Collection<Any>) {

        val startPos = items.size
        items.addAll(newItem)
        notifyItemRangeInserted(startPos, newItem.size)
    }

    companion object {
        private const val TYPE_ITEM = 0
        private const val TYPE_HEADER = 1

        private val TAG = BaseAdapter::class.java.simpleName
    }
}