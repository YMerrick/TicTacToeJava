package com.mycompany.app;

import java.io.IOException;
import java.util.Scanner;

/**
 *  
 *  To do:
 *      ~~Game loop~~
 *      Implement AI
 *      Move player input to the player object
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
        players[0] = new Player(BoardElementStatus.X, PlayerType.PLAYER, userInput);
        if (numOfPlayers == 2) players[1] = new Player(BoardElementStatus.O, PlayerType.PLAYER, userInput); else players[1] = new Player(BoardElementStatus.O, PlayerType.AI, userInput);
    }

    private void endGame(Player winner, GameState state) {
        userInput.close();
        playSpace.printBoard();
        if (state == GameState.DRAW) {
            System.out.println("The game was tied");
        } else {
            System.out.println(String.format("Player %s has won the game!",winner.getBoardElement().getValue()));
        }

    }

    private void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
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
        if (checkHorizontal(currentPlayer) || checkVertical(currentPlayer) || checkDiagonal(currentPlayer)) return true;
        return false;
    }

    private void playerTurn(Player currentPlayer) {
        boolean pass;
        int[] input;
        do {

            playSpace.printBoard();
            pass = false;

            input = currentPlayer.input(playSpace);

            try {
                playSpace.input(input[0], input[1], currentPlayer.getBoardElement());
            } catch (IOException e) {
                System.out.println(e.getMessage());
                pass = true;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                pass = true;
            }

        } while (pass);
    }

    public void startGame() {
        int id = 1;
        do {
            if (id == 1) id = 0; else id = 1;
            playerTurn(players[id]);
            clearConsole();
        } while ((checkWin(players[id]) == false) && (!checkValidMove()));

        // Check for draw
        // If true then game is a draw
        if (checkWin(players[id])) endGame(players[id], GameState.WIN); else endGame(players[id],GameState.DRAW);
    }
}
