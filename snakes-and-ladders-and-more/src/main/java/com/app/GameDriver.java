package com.app;

import com.app.input.GameInput;
import com.app.player.Player;
import com.app.player.PlayerState;
import com.app.services.DiceService;
import com.app.services.InputService;
import com.app.services.SnakeLadderService;
import com.app.state.GameState;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class GameDriver {
    GameState gameState = GameState.INPUT_STAGE;
    private SnakeLadderBoard snakeLadderBoard;
    private DiceService diceService;
    private SnakeLadderService snakeLadderService = new SnakeLadderService();
    private Player winner = null;
    private DiceService.Mode mode = DiceService.Mode.NORMAL;

    public GameDriver(GameState gameState, SnakeLadderBoard snakeLadderBoard, DiceService diceService) {
        this.gameState = gameState;
        this.snakeLadderBoard = snakeLadderBoard;
        this.diceService = diceService;
    }

    public GameDriver() {

    }

    public GameDriver(boolean enableDebugMode) {
        this.mode = enableDebugMode ? DiceService.Mode.DEBUG : DiceService.Mode.NORMAL;
    }

    public void initialize() throws IOException {

        GameInput gameInput = InputService.getSnakesAndLadders();

        Map<Player, PlayerState> playerStateMap = new TreeMap<>(Comparator.comparingInt(Player::getId));
        for (Player player : gameInput.getPlayerNames()) {
            playerStateMap.put(player, PlayerState.waitingForTurn());
        }
        snakeLadderBoard = new SnakeLadderBoard(gameInput.getBoardSize(), gameInput.getSnakes(), gameInput.getLadders(),
                gameInput.getCrocodiles(), gameInput.getMines(), playerStateMap);
        diceService = new DiceService(gameInput.getDiceSize(), gameInput.getMovementStrategy(), mode);
        gameState = GameState.STARTED;
    }

    public void runGame() throws IOException {

        // game loop
        while (gameState != GameState.FINISHED) {
            Map<Player, PlayerState> loopMap = new TreeMap<>(Comparator.comparingInt(Player::getId));
            for (Map.Entry<Player, PlayerState> playerEntry : snakeLadderBoard.getPlayerMap().entrySet()) {

                // only check players which are waiting for turn
                if (playerEntry.getValue().getState() == PlayerState.State.WAITING_FOR_TURN) {

                    int diceResult = diceService.rollDice();
                    Map<Player, PlayerState> map = snakeLadderService.handleDiceRoll(playerEntry.getKey(), diceResult,
                            snakeLadderBoard);
                    loopMap.putAll(map);
                }

                // if the player is blocked by a mine reduce the turn count for current round.
                if (playerEntry.getValue().getState() == PlayerState.State.TURN_BLOCKED) {
                    PlayerState playerState = playerEntry.getValue();
                    playerState.setTurnsBlocked(playerState.getTurnsBlocked() - 1);
                    System.out.printf("Current turn is blocked for %s, remaining blocked turns %d%n",
                            playerEntry.getKey().getName(), playerState.getTurnsBlocked());
                    if (playerState.getTurnsBlocked() <= 0) {
                        loopMap.put(playerEntry.getKey(), PlayerState.waitingForTurn());
                    } else {
                        loopMap.put(playerEntry.getKey(), playerState);
                    }
                }
            }

            Map<Player, PlayerState> updatedMap = snakeLadderBoard.getPlayerMap();
            updatedMap.putAll(loopMap);
            snakeLadderBoard.setPlayerMap(updatedMap);

            // check if any player has reached the end of the board.
            for (Map.Entry<Player, PlayerState> playerEntry : snakeLadderBoard.getPlayerMap().entrySet()) {
                // check if we have a winner
                if (playerEntry.getKey().getPosition() == snakeLadderBoard.getSize()) {
                    winner = playerEntry.getKey();
                    gameState = GameState.FINISHED;
                }
            }
        }

        // print the winner if we have a winner.
        if (winner != null) {
            System.out.println("Congratulations " + winner.getName() + " you have won the snake and ladder game!!!");
        }
    }
}
