package com.mycompany.app;

import java.io.IOException;

public class Board 
{
    private BoardElementStatus[][] board;

    public Board() {
        board = new BoardElementStatus[3][3];
        initialiseBoard();
    }

    public void initialiseBoard () {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = BoardElementStatus.N;
            }
        }
    }

    public void printBoard() {
        System.out.println("y|x  1 2 3");
        System.out.println(" |---------");
        for (int i = 0; i < 5; i++) {
            if ((i == 1) || (i == 3)) {
                System.out.println(" |   -+-+-");
                continue;
            }
            System.out.print((i/2) + 1);
            System.out.print("|   ");
            for (int j = 0; j < 5; j++) {
                if ((j == 1) || (j == 3)) { 
                    System.out.print("|");
                    continue;
                }
                System.out.print(board[i/2][j/2].getValue());
            }
            System.out.println();
        }
    }

    public void input(int x, int y, BoardElementStatus characterInput) throws IOException, RuntimeException {

        if ((x < 1) || (x > 3)) {
            throw new IOException("Please enter a value within 1 to 3");
        }
        if ((y < 1) || (y > 3)) {
            throw new IOException("Please enter a value within 1 to 3");
        }
        if (board[y-1][x-1] != BoardElementStatus.N){
            throw new RuntimeException("There is already an existing character there");
        }

        board[y-1][x-1] = characterInput;
    }

    public BoardElementStatus getSquare(int i, int j) {
        BoardElementStatus element = null;
        try {
            element = board[j][i];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return element;
    }
}
