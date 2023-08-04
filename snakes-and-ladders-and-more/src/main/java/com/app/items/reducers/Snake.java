package com.app.items.reducers;

import com.app.items.Item;
import com.app.items.ItemType;
import com.app.items.StepBasedItem;

public class Snake extends StepBasedItem {

    public Snake(int start, int end) {
        super(start, end, ItemType.SNAKE);
        if (start <= end) {
            throw new IllegalStateException("start is less than end, snake should have start > end");
        }
    }
}
