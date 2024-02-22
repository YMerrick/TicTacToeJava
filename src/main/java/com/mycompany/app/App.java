package com.mycompany.app;

import java.io.IOException;

/**
 *  
 *  To do:
 *      ~~Pretty print the board~~
 *      ~~Implement player input to affect the board~~
 *      Implement AI to play against
 *      Lock out the command line except for keyboard interrupt to quit program
 *      Write the tests for each class
 *      ~~Create menu loop that passes params to game loop~~
 *
 */

public class App 
{
    private static int menu() {
        String playerNumber;
        int players = 1;
        boolean flag;
        System.out.println("Welcome to Tic Tac Toe\n1. 1 Player\n2. 2 Players" + //
                        "\nPlease select an option:");
        do {
            playerNumber = System.console().readLine();
            try {
                flag = false;
                players = Integer.parseInt(playerNumber);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter a valid number");
                flag = true;
            }
        } while (flag);
        
        return players;
    }
    public static void main( String[] args ) throws IOException, RuntimeException
    {
        int numberOfPlayers = menu();
        Game gameInstance = new Game(numberOfPlayers);
        gameInstance.startGame();
        System.out.println("Done!");
    }
}
