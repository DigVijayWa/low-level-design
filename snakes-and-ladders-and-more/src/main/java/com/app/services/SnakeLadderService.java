package com.app.services;

import com.app.SnakeLadderBoard;
import com.app.items.Item;
import com.app.player.Player;
import com.app.player.PlayerState;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SnakeLadderService {

    /**
     * The method handles the movement using diceRoll and the currentPosition of the player. The method also checks for
     * snakes, ladders, crocodiles, mines and other player positions.
     *
     * @param player
     * @param diceRoll
     * @param snakeLadderBoard
     *
     * @return
     */
    public Map<Player, PlayerState> handleDiceRoll(Player player, int diceRoll, SnakeLadderBoard snakeLadderBoard) {
        int maxSize = snakeLadderBoard.getSize();
        Map<Player, PlayerState> map = new HashMap<>();

        // player cannot move as the position goes out of board.
        if (player.getPosition() + diceRoll > maxSize) {
            System.out.println(
                    String.format("%s rolled a %d but couldn't move as it goes out of board. current position: %d",
                            player.getName(), diceRoll, player.getPosition()));
            return Collections.singletonMap(player, PlayerState.waitingForTurn());
        }
        int previousPosition = player.getPosition();
        player.setPosition(player.getPosition() + diceRoll);
        System.out.println(String.format("%s rolled a %d and moved from %d to %d", player.getName(), diceRoll,
                previousPosition, player.getPosition()));

        // look if we have encountered a snake
        for (Item snake : snakeLadderBoard.getSnakes()) {
            if (snake.getStart() == player.getPosition()) {
                // encountered a snake
                System.out.println(String.format("Oh no..%s got eaten by the snake at %d and moved down to %d",
                        player.getName(), player.getPosition(), snake.getEnd()));
                player.setPosition(snake.getEnd());
            }
        }

        // look if we have encountered a crocodile
        for (Item crocodile : snakeLadderBoard.getCrocodiles()) {
            if (crocodile.getStart() == player.getPosition()) {
                // encountered a crocodile
                System.out.println(String.format("Oh no..%s got eaten by the crocodile at %d and moved down to %d",
                        player.getName(), player.getPosition(), crocodile.getEnd()));
                player.setPosition(crocodile.getEnd());
            }
        }

        // look if we have encountered a mine
        for (Item mine : snakeLadderBoard.getMines()) {
            if (mine.getStart() == player.getPosition()) {
                // encountered a mine
                System.out.println(String.format("Oh no..%s stepped on a mine at %d and is blocked for 2 turns",
                        player.getName(), player.getPosition()));
                map.put(player, PlayerState.turnBlocked(2));
            }
        }

        // look if we have encountered a ladder
        for (Item ladder : snakeLadderBoard.getLadders()) {
            if (ladder.getStart() == player.getPosition()) {
                // encountered a ladder
                System.out.println(String.format("Wow..%s climbed a ladder at %d and moved up to %d", player.getName(),
                        player.getPosition(), ladder.getEnd()));
                player.setPosition(ladder.getEnd());
            }
        }

        // look if we have encountered another player
        for (Map.Entry<Player, PlayerState> playerEntry : snakeLadderBoard.getPlayerMap().entrySet()) {
            if (player.getId() != playerEntry.getKey().getId()) {
                if (playerEntry.getKey().getPosition() == player.getPosition()) {
                    playerEntry.getKey().setPosition(1);
                    map.put(playerEntry.getKey(), playerEntry.getValue());
                    map.put(player, PlayerState.waitingForTurn());
                    System.out.println(
                            String.format("Oh no..%s kicked %s out of the place at %d, %s will start from beginning",
                                    player.getName(), playerEntry.getKey().getName(), player.getPosition(),
                                    playerEntry.getKey().getName()));
                }
            }
        }

        return map;
    }
}
