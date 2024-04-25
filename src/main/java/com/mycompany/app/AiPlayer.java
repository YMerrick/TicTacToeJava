package com.mycompany.app;

public class AiPlayer extends Player {
    AiPlayer(BoardElementStatus element, PlayerType player) {
        super(element, player);
    }

    private int terminalScore(BoardElementStatus element) {
        switch (assignedElement) {
            case O:
                switch (element) {
                    case X:
                        return -1;
                    
                    case O:
                        return 1;
                    
                    default:
                        return 0;
                }
            
            case X:
                switch (element) {
                    case X:
                        return 1;
                        
                    case O:
                        return -1;
                
                    default:
                        return 0;
                }
            default:
                return 0;
        }
    }

    private BoardElementStatus elementFlipper(BoardElementStatus element) {
        switch (element) {
            case X:
                return BoardElementStatus.O;
            
            case O:
                return BoardElementStatus.X;
        
            default:
                return BoardElementStatus.N;
        }
    }

    private int minimax(Board board, int depth, boolean isMaximising) {
        int bestScore;
        int score;
        if (isMaximising) {
            bestScore = Integer.MIN_VALUE;
            //board.printBoard();
            if (board.checkTerminalState(elementFlipper(assignedElement))) {
                if (board.checkWin(elementFlipper(assignedElement))) return terminalScore(elementFlipper(assignedElement));
                return terminalScore(BoardElementStatus.N);
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getSquare(i, j) == BoardElementStatus.N) {
                        board.set(i+1, j+1, assignedElement);
                        score = minimax(board, depth+1, false);
                        board.set(i+1,j+1,BoardElementStatus.N);
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
        else {
            bestScore = Integer.MAX_VALUE;
            //board.printBoard();
            if (board.checkTerminalState(assignedElement)) {
                if (board.checkWin(assignedElement)) return terminalScore(assignedElement);
                return terminalScore(BoardElementStatus.N);
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getSquare(i, j) == BoardElementStatus.N) {
                        board.set(i+1, j+1, elementFlipper(assignedElement));
                        score = minimax(board, depth+1, true);
                        board.set(i+1, j+1, BoardElementStatus.N);
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    public int[] input(Board currentBoard) {
        int i, j;
        int score = 0;
        int bestScore = Integer.MIN_VALUE;
        Board copy = currentBoard.copy();
        int[] output;

        output = new int[]{-1, -1};
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (copy.getSquare(i, j) == BoardElementStatus.N) {

                    copy.set(i+1,j+1,assignedElement);

                    score = minimax(copy, 0, false);

                    copy.set(i+1,j+1, BoardElementStatus.N);

                    if (score > bestScore) {
                        bestScore = score;
                        output = new int[]{i+1, j+1};
                    }
                }
            }
        }
        
        return output;

    }
}
