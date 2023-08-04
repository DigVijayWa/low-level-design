package com.app.input;

import com.app.items.Item;
import com.app.player.Player;
import com.app.services.DiceService;

import java.util.List;

public class GameInput {
    private int playerSize;
    private int boardSize;
    private int snakeSize;
    private int ladderSize;
    private List<Item> snakes;
    private List<Item> ladders;
    private List<Item> crocodiles;
    private List<Item> mines;
    private List<Player> playerNames;
    private DiceService.MovementStrategy movementStrategy;
    private int crocodileSize;
    private int mineSize;

    private int diceSize;

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

    public int getCrocodileSize() {
        return crocodileSize;
    }

    public void setCrocodileSize(int crocodileSize) {
        this.crocodileSize = crocodileSize;
    }

    public int getMineSize() {
        return mineSize;
    }

    public void setMineSize(int mineSize) {
        this.mineSize = mineSize;
    }

    public int getDiceSize() {
        return diceSize;
    }

    public void setDiceSize(int diceSize) {
        this.diceSize = diceSize;
    }

    public DiceService.MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    public void setMovementStrategy(DiceService.MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public int getPlayerSize() {
        return playerSize;
    }

    public void setPlayerSize(int playerSize) {
        this.playerSize = playerSize;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getSnakeSize() {
        return snakeSize;
    }

    public void setSnakeSize(int snakeSize) {
        this.snakeSize = snakeSize;
    }

    public int getLadderSize() {
        return ladderSize;
    }

    public void setLadderSize(int ladderSize) {
        this.ladderSize = ladderSize;
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

    public List<Player> getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(List<Player> playerNames) {
        this.playerNames = playerNames;
    }
}
