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

    private String convBoardElement(BoardElementStatus element) throws RuntimeException {
        switch (element) {
            case N:
                return "-";
            case X:
                return "X";
            case O:
                return "O";       
            default:
                throw new RuntimeException();
        }
    }

    public void printBoard() {
        for (int i = 0; i < 5; i++) {
            if ((i == 1) || (i == 3)) {
                System.out.println("-+-+-");
                continue;
            }
            for (int j = 0; j < 5; j++) {
                if ((j == 1) || (j == 3)) { 
                    System.out.print("|");
                    continue;
                }
                System.out.print(convBoardElement(board[i/3][j/3]));
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
        if (board[x-1][y-1] != BoardElementStatus.N){
            throw new RuntimeException("There is already an existing character there");
        }

        board[x-1][y-1] = characterInput;
    }
}
