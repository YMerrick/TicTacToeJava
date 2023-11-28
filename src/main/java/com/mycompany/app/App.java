package com.mycompany.app;

import com.mycompany.app.Board;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Board playSpace = new Board();
        playSpace.printBoard();
        System.out.println("Done!");
    }
}
