package com.cyrillrx.android.section;

/**
 * @author Cyril Leroux
 *         Created on 31/01/2016
 */
public class ItemGroup {

    public static final int NO_POSITION = -1;

    protected int id;
    protected int position;
    protected int size;
    protected int firstItemPosition;
    protected int lastItemPosition;

    public ItemGroup(int id) {
        this.id = id;
        this.position = NO_POSITION;
        this.firstItemPosition = NO_POSITION;
        this.lastItemPosition = NO_POSITION;
    }

    public int getId() { return id; }

    public int getPosition() { return position; }

    public ItemGroup setPosition(int position) {
        this.position = position;
        updatePosition();
        return this;
    }

    public int size() { return size; }

    public ItemGroup setSize(int size) {
        this.size = size;
        updatePosition();
        return this;
    }

    public int getFirstItemPosition() { return firstItemPosition; }

    public int getLastItemPosition() { return lastItemPosition; }

    public void addItem() { addItems(1); }

    public void addItems(int addedCount) {
        size += addedCount;
        updatePosition();
    }

    public void removeItem() { removeItems(1); }

    public void removeItems(int removeCount) {
        size -= removeCount;
        if (size < 0) {
            size = 0;
        }
        updatePosition();
    }

    protected void updatePosition() {

        if (position == NO_POSITION || size <= 0) {
            firstItemPosition = NO_POSITION;
            lastItemPosition = NO_POSITION;
            return;
        }

        firstItemPosition = position + 1;
        lastItemPosition = position + size;
    }
}
