package com.app.player;

public class PlayerState {
    public enum State {
        WAITING_FOR_TURN, TURN_FINISHED, IN_TURN, TURN_BLOCKED;
    }

    private State state;
    private int turnsBlocked;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getTurnsBlocked() {
        return turnsBlocked;
    }

    public void setTurnsBlocked(int turnsBlocked) {
        this.turnsBlocked = turnsBlocked;
    }

    public PlayerState(State state, int turnsBlocked) {
        this.state = state;
        this.turnsBlocked = turnsBlocked;
    }

    public static PlayerState waitingForTurn() {
        return new PlayerState(State.WAITING_FOR_TURN, 0);
    }

    public static PlayerState inTurn() {
        return new PlayerState(State.IN_TURN, 0);
    }

    public static PlayerState turnBlocked(int turns) {
        return new PlayerState(State.TURN_BLOCKED, turns);
    }

    public static PlayerState turnFinished() {
        return new PlayerState(State.TURN_FINISHED, 0);
    }
}
