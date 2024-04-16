package com.mycompany.app;

/*
 *  Implement player as an interface
 *  Refactor so AI and Human players extend the interface
 */

public abstract class Player {
    protected PlayerType typeOfPlayer;
    protected BoardElementStatus assignedElement;

    public Player(BoardElementStatus element, PlayerType player) {
        typeOfPlayer = player;
        assignedElement = element;
    }

    public PlayerType getPlayerType() {
        return typeOfPlayer;
    }

    public BoardElementStatus getBoardElement() {
        return assignedElement;
    }

    public abstract int[] input(Board board);
}
