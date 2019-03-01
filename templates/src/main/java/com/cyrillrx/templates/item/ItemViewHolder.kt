package com.cyrillrx.templates.item

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyrillrx.android.utils.inflate
import com.cyrillrx.templates.R
import com.cyrillrx.templates.model.Item

/**
 * @author Cyril Leroux
 *         Created on 06/04/2018.
 */
class ItemViewHolder(parent: ViewGroup)
    : RecyclerView.ViewHolder(parent.inflate(R.layout.item_default)) {

    private var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    private var tvSubtitle: TextView = itemView.findViewById(R.id.tv_subtitle)

    fun bind(item: Item) {
        bind(item.title, item.subtitle, item.click)
    }

    private fun bind(title: String?, subtitle: String?, click: ((View) -> Unit)?) {
        tvTitle.text = title
        tvSubtitle.text = subtitle

        itemView.setOnClickListener(click)
    }
}
