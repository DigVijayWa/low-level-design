package com.app;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        boolean enableDebugMode = args.length > 0 && Boolean.parseBoolean(args[0]);
        System.out.println("Debug mode has been "+(enableDebugMode ? "enabled" : "disabled"));
        GameDriver gameDriver = new GameDriver(enableDebugMode);
        try {
            gameDriver.initialize();
            gameDriver.runGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
