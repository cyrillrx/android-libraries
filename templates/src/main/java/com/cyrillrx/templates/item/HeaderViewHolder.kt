package com.cyrillrx.templates.item

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyrillrx.android.utils.inflate
import com.cyrillrx.templates.R
import com.cyrillrx.templates.model.Header

/**
 * @author Cyril Leroux
 *         Created on 06/04/2018.
 */
class HeaderViewHolder(parent: ViewGroup)
    : RecyclerView.ViewHolder(parent.inflate(R.layout.item_header)) {

    private var tvTitle: TextView = itemView.findViewById(R.id.tv_title)

    fun bind(header: Header) {
        tvTitle.text = header.title
    }
}
