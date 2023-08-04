package com.app.player;

import java.util.UUID;

public class Player {
    private int id;
    private String name;
    private int position;

    public Player(String name, int position) {
        this.id = PlayerIdGenerator.getId();
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    private static class PlayerIdGenerator {
        private static int id = 0;

        static int getId() {
            return id++;
        }
    }
}
