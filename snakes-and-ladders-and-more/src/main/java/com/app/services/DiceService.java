package com.app.services;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiceService {
    public enum MovementStrategy {
        SUM, MAX, MIN;
    };

    public enum Mode {
        DEBUG, NORMAL, TEST
    };

    private int size;
    private Mode mode = Mode.NORMAL;
    private MovementStrategy movementStrategy;
    private List<Integer> inputs;
    private int index = 0;

    public DiceService(int size, MovementStrategy movementStrategy) {
        this.size = size;
        this.movementStrategy = movementStrategy;
    }

    public DiceService(int size, MovementStrategy movementStrategy, Mode mode) {
        this.size = size;
        this.movementStrategy = movementStrategy;
        this.mode = mode;
    }

    public DiceService(int size, MovementStrategy movementStrategy, List<Integer> inputs, Mode mode) {
        this.size = size;
        this.movementStrategy = movementStrategy;
        this.inputs = inputs;
        this.mode = mode;
    }

    /**
     * Get dice output for n dices.
     *
     * @return
     */
    private List<Integer> getDiceOutput() {
        return IntStream.range(0, size).map(_item -> new Random().nextInt(6) + 1).boxed().collect(Collectors.toList());
    }

    /**
     * Get diceOutput according to movementStrategy.
     *
     * @return
     *
     * @throws IOException
     */
    public int rollDice() throws IOException {
        List<Integer> diceOutput = getDiceOutput();
        if (mode == Mode.NORMAL) {
            return switch (movementStrategy) {
            case MAX -> diceOutput.stream().max(Integer::compareTo).orElseGet(() -> 0);
            case MIN -> diceOutput.stream().min(Integer::compareTo).orElseGet(() -> 0);
            case SUM -> diceOutput.stream().mapToInt(Integer::intValue).sum();
            };
        } else if (mode == Mode.DEBUG) {
            return InputService.getDiceInput(size);
        } else {
            int value = inputs.get(index % inputs.size());
            index++;
            return value;
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
