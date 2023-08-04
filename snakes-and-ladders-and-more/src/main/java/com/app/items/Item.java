package com.app.items;

public class Item {
    private int start;
    private int end;
    private ItemType itemType;

    public Item(int start, int end, ItemType itemType) {
        this.start = start;
        this.end = end;
        this.itemType = itemType;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.getStart();
        hash = 31 * hash + this.getEnd();
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Item other = (Item) obj;
        return this.getStart() == other.getStart() && this.getEnd() == other.getEnd();
    }
}
