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
    private Player[] players;

    public Game(int numOfPlayers) throws IOException {
        userInput = new Scanner(System.in);
        playSpace = new Board();
        players = new Player[2];
        players[0] = new Player(BoardElementStatus.X, PlayerType.PLAYER);
        if (numOfPlayers == 2) players[1] = new Player(BoardElementStatus.O, PlayerType.PLAYER); else players[1] = new Player(BoardElementStatus.O, PlayerType.AI);
        startGame();
        //playSpace.printBoard();
        //Player player1 = new Player(BoardElementStatus.X, PlayerType.Player);
        //playerInput(player1);
        //playSpace.printBoard();
    }

    private void endGame(Player winner, GameState state) {
        
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
            case PLAYER:
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

    private boolean checkHorizontal(Player currentPlayer) {
        BoardElementStatus curElement = currentPlayer.getBoardElement();
        for (int i = 0; i < 3; i++) {
            if ( (playSpace.getSquare(i, 0) == curElement) && (playSpace.getSquare(i, 1) == curElement) && (playSpace.getSquare(i, 2) == curElement) ) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkVertical(Player currentPlayer) {
        BoardElementStatus curElement = currentPlayer.getBoardElement();
        for (int j = 0; j < 3; j++) {
            if ( (playSpace.getSquare(0, j) == curElement) && (playSpace.getSquare(1, j) == curElement) && (playSpace.getSquare(2, j) == curElement) ) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonal(Player currentPlayer) {
        BoardElementStatus curElement = currentPlayer.getBoardElement();
        if ( (playSpace.getSquare(0, 0) == curElement) && (playSpace.getSquare(1, 1) == curElement) && (playSpace.getSquare(2, 2) == curElement) ) return true;
        if ( (playSpace.getSquare(0, 2) == curElement) && (playSpace.getSquare(1, 1) == curElement) && (playSpace.getSquare(2, 0) == curElement) ) return true;
        return false;
    }

    // Returning True means there are no more valid moves
    private boolean checkValidMove() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (playSpace.getSquare(i, j) == BoardElementStatus.N) return false;
            }
        }
        return true;
    }

    private boolean checkWin(Player currentPlayer) {
        if (checkHorizontal(currentPlayer) || checkVertical(currentPlayer) || checkDiagonal(currentPlayer) || checkValidMove()) return true;
        return false;
    }

    private void playerTurn(int currentPlayer) {
        boolean pass;
        do {

            playSpace.printBoard();
            pass = false;
            try {
                playerInput(players[currentPlayer]);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                pass = true;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                pass = true;
            }

        } while (pass);
    }

/*
 *  Process:
 *      Player 1 is asked to take turn
 *      Player 1's input is verfied and asked to reinput if not correct
 *      Check if Player 1 won
 *      Player 2 is asked to take turn
 *      Player 2's input is verfied and asked to reinput if not correct
 *      Check if Player 2 won
 */

    public void startGame() {
        int id = 1;
        do {
            if (id == 1) id = 0; else id = 1;
            playerTurn(id);
            clearConsole();
        } while (checkWin(players[id]) == false);

        // Check for draw
        // If true then game is a draw
        if (checkValidMove()) endGame(players[id], GameState.DRAW);
        
        endGame(players[id], GameState.WIN);
    }
}
