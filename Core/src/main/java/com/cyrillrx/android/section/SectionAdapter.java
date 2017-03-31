package com.cyrillrx.android.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cyrillrx.android.toolbox.NamedList;
import com.cyrillrx.android.toolbox.OnDataClickListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Parent for section adapters.
 *
 * @author Cyril Leroux
 *         Created on 28/07/2015.
 */
public abstract class SectionAdapter<Data, VH extends SectionAdapter.SectionViewHolder<Data>>
        extends RecyclerView.Adapter<VH> {

    protected final List<ItemWrapper<Data>> wrappers;
    protected final OnDataClickListener<Data> onDataClickListener;

    public static class SectionViewHolder<Data> extends RecyclerView.ViewHolder {

        protected TextView tvTitle;

        public SectionViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(ItemWrapper<Data> wrapper, OnDataClickListener<Data> clickListener) {
            if (wrapper.isHeader()) {
                bindHeader(wrapper.getHeader());
            } else {
                bindData(wrapper.getData(), clickListener);
            }
        }

        protected void bindData(final Data data, final OnDataClickListener<Data> clickListener) {
            // Handle data click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onDataClick(data);
                }
            });
        }

        protected void bindHeader(String header) {
            tvTitle.setText(header);
            // Reset click
            itemView.setOnClickListener(null);
        }
    }

    public SectionAdapter(OnDataClickListener<Data> clickListener) {
        wrappers = new ArrayList<>();
        onDataClickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bind(wrappers.get(position), onDataClickListener);
    }

    @Override
    public int getItemCount() { return wrappers.size(); }

    /** Populates the adapter using a collection of named lists. */
    public void populate(Collection<NamedList<Data>> namedLists) {

        int oldLength = wrappers.size();

        String header;
        for (NamedList<Data> namedList : namedLists) {
            header = namedList.getTitle();
            wrappers.add(new ItemWrapper<Data>(header));
            final List<Data> items = namedList.getItems();
            for (Data item : items) {
                wrappers.add(new ItemWrapper<>(header, item));
            }
        }

        int newLength = wrappers.size();
        notifyItemRangeInserted(oldLength, newLength - oldLength);
    }

    public void clear() {
        wrappers.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) { return wrappers.get(position).getItemType(); }

    public ItemWrapper<Data> getWrapper(int position) { return wrappers.get(position); }
}
