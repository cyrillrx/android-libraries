package com.cyrillrx.android.section;

/**
 * An object that wraps item to display in a recycler view.
 *
 * @author Cyril Leroux
 *         Created on 24/04/15
 */
public class ItemWrapper<Data> {

    protected int    mItemType;
    protected String mHeader;
    protected Data   mData;

    /** Constructor that wraps data with a header. */
    public ItemWrapper(int viewType, String header, Data data) {
        mItemType = viewType;
        mHeader = header;
        mData = data;
    }

    /** Constructor that wraps data with a header. */
    public ItemWrapper(String header, Data data) { this(ItemType.DEFAULT, header, data); }

    /** Constructor that only defines the item type. */
    public ItemWrapper(int viewType) { this(viewType, null, null); }

    /** Constructor that wraps data without header. */
    public ItemWrapper(Data data) { this(ItemType.DEFAULT, null, data); }

    /** Constructor that wraps a header. */
    public ItemWrapper(String header) {this(ItemType.HEADER, header, null); }

    public int getItemType() { return mItemType; }

    public String getHeader() { return mHeader; }

    public void setHeader(String header) { mHeader = header; }

    public Data getData() { return mData; }

    public void setData(Data data) {
        mItemType = ItemType.DEFAULT;
        mData = data;
    }

    public boolean hasData() { return mData != null; }

    public boolean isHeader() { return mItemType == ItemType.HEADER; }
}
