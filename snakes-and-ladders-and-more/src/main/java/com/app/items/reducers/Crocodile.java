package com.app.items.reducers;

import com.app.items.ItemType;
import com.app.items.StepBasedItem;

public class Crocodile extends StepBasedItem {

    public Crocodile(int start) {
        super(start, start - 5, ItemType.CROCODILE);
    }
}
