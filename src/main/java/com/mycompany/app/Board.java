package com.mycompany.app;

import java.io.IOException;

public class Board 
{
    private BoardElementStatus[][] board;

    Board() {
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

    public Board copy() {
        Board newBoard = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    newBoard.input(i+1, j+1, board[j][i]);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }
        return newBoard;
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

    public void set(int x, int y, BoardElementStatus characterInput) {
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

    private boolean checkHorizontal(BoardElementStatus currentPlayer) {
        for (int i = 0; i < 3; i++) {
            if ( (board[0][i] == currentPlayer) && (board[1][i] == currentPlayer) && (board[2][i] == currentPlayer) ) {
                return true;
            }
        }
        return false;
    }

    private boolean checkVertical(BoardElementStatus currentPlayer) {
        for (int j = 0; j < 3; j++) {
            if ( (board[j][0] == currentPlayer) && (board[j][1] == currentPlayer) && (board[j][2] == currentPlayer) ) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonal(BoardElementStatus currentPlayer) {
        if ( (board[0][0] == currentPlayer) && (board[1][1] == currentPlayer) && (board[2][2] == currentPlayer) ) return true;
        if ( (board[0][2] == currentPlayer) && (board[1][1] == currentPlayer) && (board[2][0] == currentPlayer) ) return true;
        return false;
    }

    // Returning True means there are no more valid moves
    public boolean checkValidMove() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[j][i] == BoardElementStatus.N) return false;
            }
        }
        return true;
    }

    public boolean checkWin(BoardElementStatus currentPlayer) {
        if (checkHorizontal(currentPlayer) || checkVertical(currentPlayer) || checkDiagonal(currentPlayer)) return true;
        return false;
    }

    // Returning true means game has reached a terminal state i.e. Someone has won or there are no more valid moves
    public boolean checkTerminalState(BoardElementStatus currentPlayer) {
        if (checkWin(currentPlayer) || (checkValidMove())) return true;
        return false;
    }
}
