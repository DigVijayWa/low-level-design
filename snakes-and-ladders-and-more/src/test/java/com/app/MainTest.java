package com.app;

import com.app.items.Item;
import com.app.items.enhancers.Ladder;
import com.app.items.reducers.Crocodile;
import com.app.items.reducers.Mine;
import com.app.items.reducers.Snake;
import com.app.player.Player;
import com.app.player.PlayerState;
import com.app.services.DiceService;
import com.app.state.GameState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

class MainTest {
    Player TEST_PLAYER_1 = new Player("TEST_PLAYER_1", 1);
    Player TEST_PLAYER_2 = new Player("TEST_PLAYER_2", 1);

    /**
     * [ 1 2 3 4 5 6 7 8 9 10 ]
     * [ 1 2 3 item 5 6 7 8 item 10 ]
     *
     * here item could be snake, ladder, crocodile. or mine.
     */

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void init() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    public void testIfLadderWorksProperly() throws IOException {
        //prepare
        List<Item> ladders = List.of(new Ladder(3, 8));
        List<Item> snakes = Collections.emptyList();
        List<Item> crocodiles = Collections.emptyList();
        List<Item> mines = Collections.emptyList();

        Map<Player, PlayerState> playerStateMap = new TreeMap<>(Comparator.comparingInt(Player::getId));
        playerStateMap.put(TEST_PLAYER_1, PlayerState.waitingForTurn());
        playerStateMap.put(TEST_PLAYER_2, PlayerState.waitingForTurn());

        SnakeLadderBoard snakeLadderBoard = new SnakeLadderBoard(10, snakes, ladders, crocodiles, mines, playerStateMap);
        DiceService diceService = new DiceService(1, DiceService.MovementStrategy.SUM, List.of(1, 2, 1), DiceService.Mode.TEST);

        GameState gameState = GameState.STARTED;


        GameDriver systemUnderTest = new GameDriver(gameState, snakeLadderBoard, diceService);

        //execute
        systemUnderTest.runGame();

        //validate
        List<String> expectedOutputList = List.of("TEST_PLAYER_1 rolled a 1 and moved from 1 to 2" ,
                "TEST_PLAYER_2 rolled a 2 and moved from 1 to 3" ,
                "Wow..TEST_PLAYER_2 climbed a ladder at 3 and moved up to 8" ,
                "TEST_PLAYER_1 rolled a 1 and moved from 2 to 3" ,
                "Wow..TEST_PLAYER_1 climbed a ladder at 3 and moved up to 8" ,
                "Oh no..TEST_PLAYER_1 kicked TEST_PLAYER_2 out of the place at 8, TEST_PLAYER_2 will start from beginning" ,
                "TEST_PLAYER_2 rolled a 1 and moved from 1 to 2" ,
                "TEST_PLAYER_1 rolled a 2 and moved from 8 to 10" ,
                "TEST_PLAYER_2 rolled a 1 and moved from 2 to 3" ,
                "Wow..TEST_PLAYER_2 climbed a ladder at 3 and moved up to 8" ,
                "Congratulations TEST_PLAYER_1 you have won the snake and ladder game!!!");

        String expectedString = String.join("", expectedOutputList);
        String actualString = outputStreamCaptor.toString().replaceAll("\n", "").replaceAll("\r", "");
        Assertions.assertEquals(expectedString, actualString
              .trim());

    }

    @Test
    public void testIfSnakeWorksProperly() throws IOException {
        //prepare
        List<Item> ladders = Collections.emptyList();
        List<Item> snakes = List.of(new Snake(9, 3));
        List<Item> crocodiles = Collections.emptyList();
        List<Item> mines = Collections.emptyList();

        Map<Player, PlayerState> playerStateMap = new TreeMap<>(Comparator.comparingInt(Player::getId));
        playerStateMap.put(TEST_PLAYER_1, PlayerState.waitingForTurn());
        playerStateMap.put(TEST_PLAYER_2, PlayerState.waitingForTurn());

        SnakeLadderBoard snakeLadderBoard = new SnakeLadderBoard(10, snakes, ladders, crocodiles, mines, playerStateMap);
        DiceService diceService = new DiceService(1, DiceService.MovementStrategy.SUM, List.of(1, 3, 1, 3, 1, 2, 1), DiceService.Mode.TEST);

        GameState gameState = GameState.STARTED;


        GameDriver systemUnderTest = new GameDriver(gameState, snakeLadderBoard, diceService);

        //execute
        systemUnderTest.runGame();

        //validate
        List<String> expectedOutputList = List.of("TEST_PLAYER_1 rolled a 1 and moved from 1 to 2",
                "TEST_PLAYER_2 rolled a 3 and moved from 1 to 4",
                "TEST_PLAYER_1 rolled a 1 and moved from 2 to 3",
                "TEST_PLAYER_2 rolled a 3 and moved from 4 to 7",
                "TEST_PLAYER_1 rolled a 1 and moved from 3 to 4",
                "TEST_PLAYER_2 rolled a 2 and moved from 7 to 9",
                "Oh no..TEST_PLAYER_2 got eaten by the snake at 9 and moved down to 3",
                "TEST_PLAYER_1 rolled a 1 and moved from 4 to 5",
                "TEST_PLAYER_2 rolled a 1 and moved from 3 to 4",
                "TEST_PLAYER_1 rolled a 3 and moved from 5 to 8",
                "TEST_PLAYER_2 rolled a 1 and moved from 4 to 5",
                "TEST_PLAYER_1 rolled a 3 but couldn't move as it goes out of board. current position: 8",
                "TEST_PLAYER_2 rolled a 1 and moved from 5 to 6",
                "TEST_PLAYER_1 rolled a 2 and moved from 8 to 10",
                "TEST_PLAYER_2 rolled a 1 and moved from 6 to 7",
                "Congratulations TEST_PLAYER_1 you have won the snake and ladder game!!!");

        String expectedString = String.join("", expectedOutputList);
        String actualString = outputStreamCaptor.toString().replaceAll("\n", "").replaceAll("\r", "");
        Assertions.assertEquals(expectedString, actualString
                .trim());

    }

    @Test
    public void testIfCrocodileWorksProperly() throws IOException {
        //prepare
        List<Item> ladders = Collections.emptyList();
        List<Item> snakes = Collections.emptyList();
        List<Item> crocodiles = List.of(new Crocodile(9));
        List<Item> mines = Collections.emptyList();

        Map<Player, PlayerState> playerStateMap = new TreeMap<>(Comparator.comparingInt(Player::getId));
        playerStateMap.put(TEST_PLAYER_1, PlayerState.waitingForTurn());
        playerStateMap.put(TEST_PLAYER_2, PlayerState.waitingForTurn());

        SnakeLadderBoard snakeLadderBoard = new SnakeLadderBoard(10, snakes, ladders, crocodiles, mines, playerStateMap);
        DiceService diceService = new DiceService(1, DiceService.MovementStrategy.SUM, List.of(1, 3, 1, 3, 1, 2, 1), DiceService.Mode.TEST);

        GameState gameState = GameState.STARTED;


        GameDriver systemUnderTest = new GameDriver(gameState, snakeLadderBoard, diceService);

        //execute
        systemUnderTest.runGame();

        //validate
        List<String> expectedOutputList = List.of("TEST_PLAYER_1 rolled a 1 and moved from 1 to 2",
                "TEST_PLAYER_2 rolled a 3 and moved from 1 to 4",
                "TEST_PLAYER_1 rolled a 1 and moved from 2 to 3",
                "TEST_PLAYER_2 rolled a 3 and moved from 4 to 7",
                "TEST_PLAYER_1 rolled a 1 and moved from 3 to 4",
                "TEST_PLAYER_2 rolled a 2 and moved from 7 to 9",
                "Oh no..TEST_PLAYER_2 got eaten by the crocodile at 9 and moved down to 4",
                "Oh no..TEST_PLAYER_2 kicked TEST_PLAYER_1 out of the place at 4, TEST_PLAYER_1 will start from beginning",
                "TEST_PLAYER_1 rolled a 1 and moved from 1 to 2",
                "TEST_PLAYER_2 rolled a 1 and moved from 4 to 5",
                "TEST_PLAYER_1 rolled a 3 and moved from 2 to 5",
                "Oh no..TEST_PLAYER_1 kicked TEST_PLAYER_2 out of the place at 5, TEST_PLAYER_2 will start from beginning",
                "TEST_PLAYER_2 rolled a 1 and moved from 1 to 2",
                "TEST_PLAYER_1 rolled a 3 and moved from 5 to 8",
                "TEST_PLAYER_2 rolled a 1 and moved from 2 to 3",
                "TEST_PLAYER_1 rolled a 2 and moved from 8 to 10",
                "TEST_PLAYER_2 rolled a 1 and moved from 3 to 4",
                "Congratulations TEST_PLAYER_1 you have won the snake and ladder game!!!");

        String expectedString = String.join("", expectedOutputList);
        String actualString = outputStreamCaptor.toString().replaceAll("\n", "").replaceAll("\r", "");
        Assertions.assertEquals(expectedString, actualString
                .trim());
    }

    @Test
    public void testIfMineWorksProperly() throws IOException {
        //prepare
        List<Item> ladders = Collections.emptyList();
        List<Item> snakes = Collections.emptyList();
        List<Item> crocodiles =  Collections.emptyList();
        List<Item> mines = List.of(new Mine(9, 2));

        Map<Player, PlayerState> playerStateMap = new TreeMap<>(Comparator.comparingInt(Player::getId));
        playerStateMap.put(TEST_PLAYER_1, PlayerState.waitingForTurn());
        playerStateMap.put(TEST_PLAYER_2, PlayerState.waitingForTurn());

        SnakeLadderBoard snakeLadderBoard = new SnakeLadderBoard(10, snakes, ladders, crocodiles, mines, playerStateMap);
        DiceService diceService = new DiceService(1, DiceService.MovementStrategy.SUM, List.of(1, 3, 1, 3, 1, 2, 1), DiceService.Mode.TEST);

        GameState gameState = GameState.STARTED;


        GameDriver systemUnderTest = new GameDriver(gameState, snakeLadderBoard, diceService);

        //execute
        systemUnderTest.runGame();

        //validate
        List<String> expectedOutputList = List.of("TEST_PLAYER_1 rolled a 1 and moved from 1 to 2",
                "TEST_PLAYER_2 rolled a 3 and moved from 1 to 4",
                "TEST_PLAYER_1 rolled a 1 and moved from 2 to 3",
                "TEST_PLAYER_2 rolled a 3 and moved from 4 to 7",
                "TEST_PLAYER_1 rolled a 1 and moved from 3 to 4",
                "TEST_PLAYER_2 rolled a 2 and moved from 7 to 9",
                "Oh no..TEST_PLAYER_2 stepped on a mine at 9 and is blocked for 2 turns",
                "TEST_PLAYER_1 rolled a 1 and moved from 4 to 5",
                "Current turn is blocked for TEST_PLAYER_2, remaining blocked turns 1",
                "TEST_PLAYER_1 rolled a 1 and moved from 5 to 6",
                "Current turn is blocked for TEST_PLAYER_2, remaining blocked turns 0",
                "TEST_PLAYER_1 rolled a 3 and moved from 6 to 9",
                "Oh no..TEST_PLAYER_1 stepped on a mine at 9 and is blocked for 2 turns",
                "Oh no..TEST_PLAYER_1 kicked TEST_PLAYER_2 out of the place at 9, TEST_PLAYER_2 will start from beginning",
                "TEST_PLAYER_2 rolled a 1 and moved from 1 to 2",
                "TEST_PLAYER_1 rolled a 3 but couldn't move as it goes out of board. current position: 9",
                "TEST_PLAYER_2 rolled a 1 and moved from 2 to 3",
                "TEST_PLAYER_1 rolled a 2 but couldn't move as it goes out of board. current position: 9",
                "TEST_PLAYER_2 rolled a 1 and moved from 3 to 4",
                "TEST_PLAYER_1 rolled a 1 and moved from 9 to 10",
                "TEST_PLAYER_2 rolled a 3 and moved from 4 to 7",
                "Congratulations TEST_PLAYER_1 you have won the snake and ladder game!!!");

        String expectedString = String.join("", expectedOutputList);
        String actualString = outputStreamCaptor.toString().replaceAll("\n", "").replaceAll("\r", "");
        Assertions.assertEquals(expectedString, actualString
                .trim());
    }
}