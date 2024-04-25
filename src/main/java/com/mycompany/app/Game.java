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
        players[1] = new HumanPlayer(BoardElementStatus.X, PlayerType.PLAYER, userInput);
        players[0] = (numOfPlayers == 2)
            ? new HumanPlayer(BoardElementStatus.O, PlayerType.PLAYER, userInput)
            : new AiPlayer(BoardElementStatus.O, PlayerType.AI);
        //if (numOfPlayers == 2) players[1] = new HumanPlayer(BoardElementStatus.O, PlayerType.PLAYER, userInput); else players[1] = new AiPlayer(BoardElementStatus.O, PlayerType.AI);
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
        } while (!playSpace.checkTerminalState(players[id].getBoardElement()));

        // Check for draw
        // If true then game is a draw
        if (playSpace.checkWin(players[id].getBoardElement())) endGame(players[id], GameState.WIN); else endGame(players[id], GameState.DRAW);
    }
}
