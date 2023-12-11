package com.mycompany.app;

import java.io.IOException;

public class Game {
    Board playSpace;

    public Game() throws IOException {
        playSpace = new Board();
        playSpace.input(2,2,BoardElementStatus.O);
        playSpace.printBoard();
    }
}
