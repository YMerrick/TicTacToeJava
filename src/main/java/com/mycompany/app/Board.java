package com.mycompany.app;

public class Board 
{
    private enum boardElementStatus {
        O,
        X,
        N
    }

    private boardElementStatus[][] board;

    public Board() {
        board = new boardElementStatus[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = boardElementStatus.N;
            }
        }
    }

    private String convBoardElement(boardElementStatus element) {
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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(convBoardElement(board[i][j]));
            }
            System.out.println();
        }
    }
}
