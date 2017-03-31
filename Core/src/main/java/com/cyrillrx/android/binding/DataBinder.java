package com.cyrillrx.android.binding;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Link a data set to a view.
 *
 * @author Cyril Leroux
 *         Created on 04/12/14
 */
public class DataBinder<Data> implements RequestLifecycle {

    protected final Set<DataLinkedView<Data>> linkedViews;

    /**
     * Initializes an empty data binder.
     */
    public DataBinder() { linkedViews = new HashSet<>(); }

    /**
     * Initializes the binder with the view to update.
     *
     * @param views The views that will be updated when setData is called.
     */
    public DataBinder(DataLinkedView<Data>... views) {
        this();
        addLinkedViews(views);
    }

    /**
     * Calls {@link #bindData(Object, ViewBoundCallback)} with a null callback.
     *
     * @param data The new data.
     * @return True if the data has been bound.
     */
    public boolean bindData(Data data) { return bindData(data, null); }

    /**
     * Binds the given data to the linked views then trigger the callback if any.
     *
     * @param data The new data.
     * @return True if the data has been bound.
     */
    public boolean bindData(Data data, ViewBoundCallback callback) {

        for (DataLinkedView<Data> view : linkedViews) {
            view.bind(data);
        }

        if (callback != null) {
            callback.onBound();
        }

        return true;
    }

    /** Start loading data (from a database, a webservice, etc.). */
    @Override
    public void onStartLoading() {
        for (DataLinkedView<Data> view : linkedViews) {
            view.onStartLoading();
        }
    }

    /** Stop loading data. */
    @Override
    public void onStopLoading() {
        for (DataLinkedView<Data> view : linkedViews) {
            view.onStopLoading();
        }
    }

    /** Failure while loading data. */
    @Override
    public void onRequestFailure() {
        for (DataLinkedView<Data> view : linkedViews) {
            view.onRequestFailure();
        }
    }

    /**
     * Add a linked view that will be updated when {@link #bindData(Object, ViewBoundCallback)} is called.
     *
     * @param linkedView The linked view to add.
     * @return True if the linked view  set is modified, false otherwise.
     */
    public boolean addLinkedView(DataLinkedView<Data> linkedView) {
        return linkedViews.add(linkedView);
    }

    public boolean removeLinkedView(DataLinkedView<Data> linkedView) {
        return linkedViews.remove(linkedView);
    }

    /**
     * Add a list of linked view that will be updated when {@link #bindData(Object, ViewBoundCallback)} is called.
     *
     * @return True if the linked view set is modified, false otherwise.
     */
    public boolean addLinkedViews(DataLinkedView<Data>... views) {
        return Collections.addAll(linkedViews, views);
    }

}
