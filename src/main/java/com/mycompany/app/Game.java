package com.mycompany.app;

import java.io.IOException;
import java.util.Scanner;

/**
 *  
 *  To do:
 *      Game loop 
 *      
 *
 */

public class Game {
    private Board playSpace;
    private Scanner userInput;

    public Game() throws IOException {
        userInput = new Scanner(System.in);
        playSpace = new Board();
        //playSpace.printBoard();
        //Player player1 = new Player(BoardElementStatus.X, PlayerType.Player);
        //playerInput(player1);
        //playSpace.printBoard();
        //endGame();
    }

    private void endGame() {
        userInput.close();
    }

    private void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }

    private int xInput() {
        int number = -1;
        System.out.print("Please input the x value: ");
        try {
            number = userInput.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return number;
    }

    private int yInput() {
        int number = -1;
        System.out.print("Please input the y value: ");
        try {
            number = userInput.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return number;
    }

    private void playerInput(Player currentPlayer) throws IOException, RuntimeException {
        
        int i, j;
        switch (currentPlayer.getPlayerType()) {
            case Player:
                System.out.println(String.format("Player %s:", currentPlayer.getBoardElement().getValue()));

                i = xInput();
                j = yInput();

                try {
                    playSpace.input(i, j, currentPlayer.getBoardElement());
                } catch (IOException ex) {
                    throw ex;
                } catch (RuntimeException ex) {
                    throw ex;
                }
                break;

            case AI:
                //Implement AI
                break;

            default:
                break;
        }
    }

    public void startGame() {
        
    }
}
