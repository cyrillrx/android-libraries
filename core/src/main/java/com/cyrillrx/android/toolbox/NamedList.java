package com.cyrillrx.android.toolbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author Cyril Leroux
 *         Created on 30/08/2015.
 */
public class NamedList<Data> {

    protected String id;
    protected String title;
    protected List<Data> items;

    public NamedList(String title) {
        this.title = title;
        items = new ArrayList<>();
    }

    public NamedList(String title, List<Data> items) {
        this.title = title;
        this.items = items;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    @NonNull
    public List<Data> getItems() { return items == null ? new ArrayList<Data>() : items; }

    public Data getItem(int index) { return items.get(index); }

    public void add(Data data) { items.add(data); }

    public void addAll(@Nullable Collection<? extends Data> data) {
        if (data != null) { items.addAll(data); }
    }

    public void addAll(@Nullable Data[] data) {
        if (data != null) { items.addAll(Arrays.asList(data)); }
    }

    public boolean isEmpty() { return items == null || items.isEmpty(); }
}