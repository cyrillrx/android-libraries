package com.cyrillrx.android.ws;

import android.support.v7.widget.RecyclerView;

import com.cyrillrx.android.section.ItemType;

/**
 * @author Cyril Leroux
 *         Created on 17/03/15
 */
public abstract class SimpleLoaderAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected boolean isLoading;

    public abstract void addLoader();

    public abstract void removeLoader();

    public boolean isLoader(int position) { return getItemViewType(position) == ItemType.LOADER; }
}
