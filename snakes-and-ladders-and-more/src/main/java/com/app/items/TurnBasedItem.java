package com.app.items;

public class TurnBasedItem extends Item {
    private int turns;
    public TurnBasedItem(int start, ItemType itemType, int turns) {
        super(start, 0, itemType);
        this.turns = turns;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }
}
