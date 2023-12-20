package com.mycompany.app;
public class Player {
    private PlayerType typeOfPlayer;
    private BoardElementStatus assignedElement;

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
}
