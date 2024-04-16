package com.mycompany.app;

import java.util.Scanner;

public class HumanPlayer extends Player{

    private Scanner userInput;

    HumanPlayer(BoardElementStatus element, PlayerType player, Scanner userInput) {
        super(element, player);
        this.userInput = userInput;
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

    public int[] input(Board board) {
        int i, j;

        System.out.println(String.format("Player %s:", assignedElement.getValue()));

        i = axisInput('x');
        j = axisInput('y');

        return new int[]{i,j};
    }
}
