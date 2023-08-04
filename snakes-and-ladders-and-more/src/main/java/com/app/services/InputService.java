package com.app.services;

import com.app.input.GameInput;
import com.app.items.Item;
import com.app.items.ItemType;
import com.app.items.enhancers.Ladder;
import com.app.items.reducers.Crocodile;
import com.app.items.reducers.Mine;
import com.app.items.reducers.Snake;
import com.app.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputService {

    private static BufferedReader getBufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Get board size from the input.
     *
     * @return
     *
     * @throws IOException
     */
    public static int getBoardSize() throws IOException {
        System.out.println("Please input board size of the snake \uD83D\uDC0D and ladder \uD83E\uDE9C game:");
        BufferedReader reader = getBufferedReader();
        // String reading internally
        String str = reader.readLine();

        // Integer reading internally
        return Integer.parseInt(str);
    }

    /**
     * Get Dice input in case of DEBUG mode.
     *
     * @param size
     *
     * @return
     *
     * @throws IOException
     */
    public static int getDiceInput(int size) throws IOException {
        System.out.println("Please input dice value of the snake \uD83D\uDC0D and ladder \uD83E\uDE9C game:");
        BufferedReader reader = getBufferedReader();
        // String reading internally
        String str = reader.readLine();

        // Integer reading internally
        int diceValue = Integer.parseInt(str);

        if (diceValue > size * 6) {
            System.out.println(
                    "Input dice value is greater than max dice value hence returning max dice value " + size * 6);
            return size * 6;
        }

        return diceValue;
    }

    /**
     * Input method for player size.
     *
     * @return
     *
     * @throws IOException
     */
    public static int getPlayerSize() throws IOException {
        System.out.println("Please input player size of the snake \uD83D\uDC0D and ladder \uD83E\uDE9C game:");
        BufferedReader reader = getBufferedReader();
        // String reading internally
        String str = reader.readLine();

        // Integer reading internally
        return Integer.parseInt(str);
    }

    /**
     * General method for input handling of snake, ladder, crocodile and mine size.
     *
     * @param itemType
     * @param boardSize
     *
     * @return
     *
     * @throws IOException
     */
    public static int getSnakeLadderSize(String itemType, int boardSize) throws IOException {
        System.out
                .println("Please input " + itemType + " size of the snake \uD83D\uDC0D and ladder \uD83E\uDE9C game:");
        BufferedReader reader = getBufferedReader();
        // String reading internally
        String str = reader.readLine();

        // Integer reading internally
        int num = Integer.parseInt(str);

        if (num > boardSize) {
            throw new IllegalStateException(itemType + " size cannot exceed board size");
        }

        return num;
    }

    /**
     * Input method to accept movement strategy.
     *
     * @return
     *
     * @throws IOException
     */
    public static DiceService.MovementStrategy getMovementStrategy() throws IOException {
        System.out.println("Please input movement strategy of the snake \uD83D\uDC0D and ladder \uD83E\uDE9C game:");
        BufferedReader reader = getBufferedReader();
        // String reading internally
        String str = reader.readLine();

        return switch (str) {
        case "MIN" -> DiceService.MovementStrategy.MIN;
        case "MAX" -> DiceService.MovementStrategy.MAX;
        default -> DiceService.MovementStrategy.SUM;
        };
    }

    /**
     * This method is used to input required parameters for the game.
     *
     * @return
     *
     * @throws IOException
     */
    public static GameInput getSnakesAndLadders() throws IOException {
        GameInput gameInput = new GameInput();

        BufferedReader reader = getBufferedReader();
        List<Item> snakes = new ArrayList<>();
        List<Item> ladders = new ArrayList<>();
        List<Item> crocodiles = new ArrayList<>();
        List<Item> mines = new ArrayList<>();

        List<Player> players = new ArrayList<>();

        int boardSize = getBoardSize();

        int snakeSize = getSnakeLadderSize("Snakes", boardSize);

        System.out.println("Input format: start <SPACE> end");

        // input snakes
        for (int i = 0; i < snakeSize; i++) {
            String str = reader.readLine();

            String inputs[] = str.split(" ");

            if (inputs.length != 2) {
                throw new IllegalStateException(
                        "Invalid input given. " + Arrays.toString(inputs) + " is not a valid input.");
            }

            int num1 = Integer.parseInt(inputs[0]);
            int num2 = Integer.parseInt(inputs[1]);

            if (checkOverlapping(ItemType.SNAKE, new Snake(num1, num2), snakes, ladders, crocodiles, mines)) {
                throw new IllegalStateException("Cannot add snake where snake is already placed");
            }
            snakes.add(new Snake(num1, num2));
        }

        int ladderSize = getSnakeLadderSize("Ladders", boardSize);

        System.out.println("Input format: start <SPACE> end");

        // input ladders
        for (int i = 0; i < ladderSize; i++) {
            String str = reader.readLine();

            String inputs[] = str.split(" ");

            if (inputs.length != 2) {
                throw new IllegalStateException(
                        "Invalid input given. " + Arrays.toString(inputs) + " is not a valid input.");
            }

            int num1 = Integer.parseInt(inputs[0]);
            int num2 = Integer.parseInt(inputs[1]);

            if (checkOverlapping(ItemType.LADDER, new Ladder(num1, num2), snakes, ladders, crocodiles, mines)) {
                throw new IllegalStateException("Cannot add ladder where ladder/snake is already placed");
            }
            ladders.add(new Ladder(num1, num2));
        }

        int crocodileSize = getSnakeLadderSize("Crocodile", boardSize);

        System.out.println("Input format: start");

        // input crocodiles
        for (int i = 0; i < crocodileSize; i++) {
            int num1 = Integer.parseInt(reader.readLine());

            if (checkOverlapping(ItemType.CROCODILE, new Crocodile(num1), snakes, ladders, crocodiles, mines)) {
                throw new IllegalStateException("Cannot add crocodiles where ladder/snake/crocodile is already placed");
            }
            crocodiles.add(new Crocodile(num1));
        }

        int mineSize = getSnakeLadderSize("Mine", boardSize);

        System.out.println("Input format: start");

        // input mines
        for (int i = 0; i < mineSize; i++) {
            int num1 = Integer.parseInt(reader.readLine());

            if (checkOverlapping(ItemType.MINE, new Mine(num1, 2), snakes, ladders, crocodiles, mines)) {
                throw new IllegalStateException("Cannot add mines where ladder/snake/crocodile/mine is already placed");
            }
            mines.add(new Mine(num1, 2));
        }

        int playerSize = getPlayerSize();

        for (int i = 0; i < playerSize; i++) {
            players.add(new Player(reader.readLine(), 1));
        }

        DiceService.MovementStrategy movementStrategy = getMovementStrategy();

        int diceSize = getSnakeLadderSize("Dice", boardSize);

        gameInput.setBoardSize(boardSize);
        gameInput.setSnakeSize(snakeSize);
        gameInput.setLadderSize(ladderSize);
        gameInput.setPlayerSize(playerSize);
        gameInput.setMovementStrategy(movementStrategy);
        gameInput.setDiceSize(diceSize);
        gameInput.setCrocodileSize(crocodileSize);
        gameInput.setMineSize(mineSize);

        gameInput.setLadders(ladders);
        gameInput.setSnakes(snakes);
        gameInput.setPlayerNames(players);
        gameInput.setCrocodiles(crocodiles);
        gameInput.setMines(mines);

        return gameInput;
    }

    /**
     * This method is used to detect if we already have an item placed where we are intending to place an item. returns
     * true if there exist any item. returns false if we can place the item safely.
     *
     * @param itemType
     * @param item
     * @param snakes
     * @param ladders
     * @param crocodiles
     * @param mines
     *
     * @return
     */
    private static boolean checkOverlapping(ItemType itemType, Item item, List<Item> snakes, List<Item> ladders,
            List<Item> crocodiles, List<Item> mines) {
        // snakes should be checked against snakes
        // ladders should be checked against snakes, ladders
        // crocodiles checked against snakes, ladders, crocodiles
        // mines should be checked against snakes, ladders, crocodiles, mines

        return switch (itemType) {
        case SNAKE -> snakes.stream().anyMatch(item1 -> item1.getStart() == item.getStart());
        case LADDER -> snakes.stream().anyMatch(item1 -> item1.getStart() == item.getStart())
                || ladders.stream().anyMatch(item1 -> item1.getStart() == item.getStart());
        case CROCODILE -> snakes.stream().anyMatch(item1 -> item1.getStart() == item.getStart())
                || ladders.stream().anyMatch(item1 -> item1.getStart() == item.getStart())
                || crocodiles.stream().anyMatch(item1 -> item1.getStart() == item.getStart());
        case MINE -> snakes.stream().anyMatch(item1 -> item1.getStart() == item.getStart())
                || ladders.stream().anyMatch(item1 -> item1.getStart() == item.getStart())
                || crocodiles.stream().anyMatch(item1 -> item1.getStart() == item.getStart())
                || mines.stream().anyMatch(item1 -> item1.getStart() == item.getStart());

        };

    }
}
