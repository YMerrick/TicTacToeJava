package com.mycompany.app;

import java.util.Scanner;

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

    public int[] input(Board currentBoard) {
        int[] output;
        int i, j;
        switch (typeOfPlayer) {
            case PLAYER:
                System.out.println(String.format("Player %s:", assignedElement.getValue()));
                i = axisInput('x');
                j = axisInput('y');
                output = new int[]{i,j};
                return output;
            case AI:
                j = -1;
                boolean found = false;
                for (i = 0; i < 3; i++) {
                    for (j = 0; j < 3; j++) {
                        if (currentBoard.getSquare(i, j) == BoardElementStatus.N) {
                            found = true;
                            break;
                        }
                    }
                    if (found) break;
                }
                output = new int[]{i+1,j+1};
                return output;
            default: 
                i = -1;
                j = -1;
                output = new int[]{i,j};
                return output;
        }
    }
}
