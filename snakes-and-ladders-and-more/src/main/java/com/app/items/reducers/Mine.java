package com.app.items.reducers;

import com.app.items.ItemType;
import com.app.items.TurnBasedItem;

public class Mine extends TurnBasedItem {
    public Mine(int start, int turns) {
        super(start, ItemType.MINE, turns);
    }
}
