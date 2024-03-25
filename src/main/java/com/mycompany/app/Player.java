package com.mycompany.app;

import java.util.Scanner;

/*
 *  Implement player as an interface
 *  Refactor so AI and Human players extend the interface
 */

public class Player {
    private PlayerType typeOfPlayer;
    private BoardElementStatus assignedElement;
    private Scanner userInput;

    public Player(BoardElementStatus element, PlayerType player, Scanner userInput) {
        this.userInput = userInput; 
        typeOfPlayer = player;
        assignedElement = element;
    }
    public void close() {
        // Placeholder function
    }

    public PlayerType getPlayerType() {
        return typeOfPlayer;
    }

    public BoardElementStatus getBoardElement() {
        return assignedElement;
    }

    private int axisInput(char axis) {
        int value = -1;
        System.out.print("Please input the " + axis + " value: ");
        try {
            value = userInput.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    private int minimax(Board board, int depth, boolean isMaximising) {
        return 1;
    }

    public int[] input(Board currentBoard) {
        int[] output;
        int i, j;
        Board copy = currentBoard.copy();
        switch (typeOfPlayer) {
            case PLAYER:
                System.out.println(String.format("Player %s:", assignedElement.getValue()));

                i = axisInput('x');
                j = axisInput('y');

                output = new int[]{i,j};
                return output;

            case AI:
                int score = 0;
                int bestScore = -10000;
                j = -1;
                boolean found = false;
                output = new int[]{-1, -1};

                for (i = 0; i < 3; i++) {
                    for (j = 0; j < 3; j++) {
                        if (copy.getSquare(i, j) == BoardElementStatus.N) {
                            found = true;
                            score = minimax(copy, 0, true);
                            if (score > bestScore) {
                                bestScore = Math.max(score, bestScore);
                                output = new int[]{i+1, j+1};
                            }
                        }
                    }
                    if (found) break;
                }
                
                return output;

            default: 
                i = -1;
                j = -1;
                output = new int[]{i,j};
                return output;
        }
    }
}
