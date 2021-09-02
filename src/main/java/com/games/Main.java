package com.games;

import java.util.*;


public class Main {
    public static void main(String[] args) {

        Player player = new Player();
        Game game = new Game();
        ExecutorTheCommand executorTheCommand = new ExecutorTheCommand(player);
        game.start(player);
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()&& !executorTheCommand.stop) {
            executorTheCommand.executeCommand(scanner.nextLine());
        }
        scanner.close();
    }


}