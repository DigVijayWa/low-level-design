package com.app;

import com.app.items.Item;
import com.app.player.Player;
import com.app.player.PlayerState;

import java.util.*;

public class SnakeLadderBoard {
    private int size;
    private List<Item> snakes;
    private List<Item> ladders;
    private List<Item> crocodiles;
    private List<Item> mines;
    private Map<Player, PlayerState> playerMap = new TreeMap<>(Comparator.comparingInt(Player::getId));

    public SnakeLadderBoard(int size, List<Item> snakes, List<Item> ladders) {
        this.size = size;
        this.snakes = snakes;
        this.ladders = ladders;
    }

    public SnakeLadderBoard(int size, List<Item> snakes, List<Item> ladders, List<Item> crocodiles, List<Item> mines,
            Map<Player, PlayerState> playerMap) {
        this.size = size;
        this.snakes = snakes;
        this.ladders = ladders;
        this.playerMap = playerMap;
        this.crocodiles = crocodiles;
        this.mines = mines;
    }

    public Map<Player, PlayerState> getPlayerMap() {
        return playerMap;
    }

    public void setPlayerMap(Map<Player, PlayerState> playerMap) {
        this.playerMap = playerMap;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Item> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<Item> snakes) {
        this.snakes = snakes;
    }

    public List<Item> getLadders() {
        return ladders;
    }

    public void setLadders(List<Item> ladders) {
        this.ladders = ladders;
    }

    public List<Item> getCrocodiles() {
        return crocodiles;
    }

    public void setCrocodiles(List<Item> crocodiles) {
        this.crocodiles = crocodiles;
    }

    public List<Item> getMines() {
        return mines;
    }

    public void setMines(List<Item> mines) {
        this.mines = mines;
    }
}
