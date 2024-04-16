package com.mycompany.app;

public class AiPlayer extends Player {
    AiPlayer(BoardElementStatus element, PlayerType player) {
        super(element, player);
    }

    private int minimax(Board board, int depth, boolean isMaximising) {
        return 1;
    }

    public int[] input(Board currentBoard) {
        int i, j;
        int score = 0;
        int bestScore = -10000;
        Board copy = currentBoard.copy();
        int[] output;

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

    }
}
