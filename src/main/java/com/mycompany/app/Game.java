package com.mycompany.app;

public class Game {
    Board playSpace;

    public Game() {
        playSpace = new Board();
        playSpace.printBoard();
    }
}
