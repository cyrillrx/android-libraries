package com.cyrillrx.android.section;

/**
 * An object that wraps item to display in a recycler view.
 *
 * @author Cyril Leroux
 *         Created on 24/04/15
 */
public class ItemWrapper<Data> {

    public static final int NO_GROUP = -1;

    protected int    itemType;
    protected String header;
    protected Data   data;
    protected int    groupId;

    /** Constructor that wraps data with a header. */
    public ItemWrapper(int viewType, String header, Data data, int groupId) {
        this.itemType = viewType;
        this.header = header;
        this.data = data;
        this.groupId = groupId;
    }

    /** Constructor that wraps data with a header. */
    public ItemWrapper(String header, Data data) { this(ItemType.DEFAULT, header, data, NO_GROUP); }

    /** Constructor that only defines the item type. */
    public ItemWrapper(int viewType) { this(viewType, null, null, NO_GROUP); }

    /** Constructor that wraps data without header. */
    public ItemWrapper(Data data) { this(ItemType.DEFAULT, null, data, NO_GROUP); }

    /** Constructor that wraps a header. */
    public ItemWrapper(String header) { this(ItemType.HEADER, header, null, NO_GROUP); }

    public int getItemType() { return itemType; }

    public ItemWrapper<Data> setItemType(int itemType) {
        this.itemType = itemType;
        return this;
    }

    public String getHeader() { return header; }

    public ItemWrapper<Data> setHeader(String header) {
        this.header = header;
        return this;
    }

    public Data getData() { return data; }

    public ItemWrapper<Data> setData(Data data) {
        itemType = ItemType.DEFAULT;
        this.data = data;
        return this;
    }

    public int getGroupId() { return groupId; }

    public ItemWrapper<Data> setGroupId(int groupId) {
        this.groupId = groupId;
        return this;
    }

    public boolean hasData() { return data != null; }

    public boolean isHeader() { return itemType == ItemType.HEADER; }
}
