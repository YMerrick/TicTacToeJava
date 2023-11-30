package com.mycompany.app;

import java.io.IOException;

/**
 *  
 *  To do:
 *      Pretty print the board
 *      ~~Implement player input to affect the board~~
 *      Implement AI to play against
 *      Lock out the command line except for keyboard interrupt to quit program
 *      Write the tests for each class
*
 */

public class App 
{
    public static void main( String[] args ) throws IOException, RuntimeException
    {
        Game gameLoop = new Game();
        System.out.println("Done!");
    }
}
