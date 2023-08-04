package com.app.items.enhancers;

import com.app.items.ItemType;
import com.app.items.StepBasedItem;

public class Ladder extends StepBasedItem {
    public Ladder(int start, int end) {
        super(start, end, ItemType.LADDER);
        if (start >= end) {
            throw new IllegalStateException("start is greater than end, ladder should have start < end");
        }
    }
}
