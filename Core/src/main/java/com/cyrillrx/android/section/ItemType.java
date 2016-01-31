package com.cyrillrx.android.section;

/**
 * Item type for adapter items.
 * <p/>
 * Reserved range : 0 to 99.<br />
 * User defined item types start at 100.
 *
 * @author Cyril Leroux
 *         Created on 10/09/15
 */
public interface ItemType {

    int DEFAULT  = 0;
    int HEADER   = 1;
    int SUB_ITEM = 2;

    int DIVIDER = 10;
    int LOADER  = 11;
    /** Used for grids/lists with and action at the end. */
    int ACTION  = 12;

    int STUB = 20;
}
