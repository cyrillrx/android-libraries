package com.cyrillrx.android.binding;

/**
 * Represents a view linked to a data object.
 *
 * @author Cyril Leroux
 *         Created on 04/12/14
 */
public interface DataLinkedView<Data> extends RequestLifecycle {

    /**
     * Binds the data to the view.
     *
     * @param data The data to bind.
     */
    void bind(Data data);
}
