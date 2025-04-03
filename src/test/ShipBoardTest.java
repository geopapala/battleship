package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Constants;
import main.ShipBoard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

class ShipBoardTest {
    
    private int boardSize = 10;
    private ShipBoard shipBoard;
    private ByteArrayInputStream inputStream;
    private Scanner scanner;
    private PrintStream originalOut;
    private ByteArrayOutputStream outputStream;
    private String simulatedInput1 = "w15k\n" // Invalid input
                                   + "j5h\n"  // No fit input
                                   + "a5h\n"
                                   + "c3v\n"  // Colliding input
                                   + "d2h\n"
                                   + "h10h\n"
                                   + "h4v\n"
                                   + "a9v";
    private String simulatedInput2 = "a1h\n"
                                   + "f1h\n"
                                   + "a2h\n"
                                   + "d2h\n"
                                   + "j1v";

    @BeforeEach
    void setUp() {
        shipBoard = new ShipBoard(boardSize);
        redirectSystemOut();
    }

    @AfterEach
    void tearDown() {
        restoreSystemOut();
        shipBoard = null;
    }

    @Test
    void testEnterAllShipsManually() {
        int[][] expectedBoard = {{0,0,0,0,0,0,0,0,0,0},
                                 {0,0,0,2,2,2,2,0,0,0},
                                 {0,0,0,0,0,0,0,0,0,0},
                                 {0,0,0,0,0,0,0,4,0,0},
                                 {1,1,1,1,1,0,0,4,0,0},
                                 {0,0,0,0,0,0,0,4,0,0},
                                 {0,0,0,0,0,0,0,0,0,0},
                                 {0,0,0,0,0,0,0,0,0,0},
                                 {5,0,0,0,0,0,0,0,0,0},
                                 {5,0,0,0,0,0,0,3,3,3}};

        enterSimulatedInput(simulatedInput1);
        shipBoard.enterAllShipsManually(scanner);

        int[][] board = shipBoard.getBoard();
        
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                assertEquals(expectedBoard[i][j], board[i][j]);
            }
        }
    }

    @Test
    void testGetStrike() {        
        enterSimulatedInput(simulatedInput2);
        shipBoard.enterAllShipsManually(scanner);
        
        int columnA = 0;
        int row1 = 0;
        int row3 = 2;
        
        // Got strike in water for the 1st time
        assertEquals(false, shipBoard.getStrike(columnA, row3));
        // Got strike in the same water position for 2nd time
        assertEquals(false, shipBoard.getStrike(columnA, row3));
        // Got strike on a ship
        assertEquals(true, shipBoard.getStrike(columnA, row1));
        // Got strike on a ship in the same position 
        assertEquals(true, shipBoard.getStrike(columnA, row1));
    }

    @Test
    void testAllShipsSank() {        
        assertEquals(false, shipBoard.allShipsSank());
        
        enterSimulatedInput(simulatedInput2);
        shipBoard.enterAllShipsManually(scanner);
        
        for (int row = 0; row <= 1; row++) {
            for (int col = 0; col < Constants.BOARD_SIZE; col++) {
                shipBoard.getStrike(col, row);
            }
        }
        assertEquals(true, shipBoard.allShipsSank());
    }

    @Test
    void testLastStrikeSankShip() {        
        int columnA = 0;
        int columnE = 4;
        int row1 = 0;
        int shipSize1 = 5;

        enterSimulatedInput(simulatedInput2);
        shipBoard.enterAllShipsManually(scanner);
                
        for (int col = columnA; col < columnA + shipSize1; col++) {
            boolean expectedResult = false;
            shipBoard.getStrike(col, row1);
            if (col == columnE) { // Strike in that position sank the ship
                expectedResult = true;
            }
            assertEquals(expectedResult, shipBoard.lastStrikeSankShip());
        }
    }

    @Test
    void testPrintBoard() {        
        int columnA = 0;
        int row1 = 0;
        int row4 = 3;
        
        String[] expectedOutputLines = {"   --A-B-C-D-E-F-G-H-I-J--",
                                        " 1 | * A A A A B B B B D |",
                                        " 2 | C C C S S S ~ ~ ~ D |",
                                        " 3 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |",
                                        " 4 | o ~ ~ ~ ~ ~ ~ ~ ~ ~ |",
                                        " 5 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |",
                                        " 6 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |",
                                        " 7 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |",
                                        " 8 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |",
                                        " 9 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |",
                                        "10 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |",
                                        "   -----------------------"};
        
        enterSimulatedInput(simulatedInput2);
        shipBoard.enterAllShipsManually(scanner);
        shipBoard.getStrike(columnA, row1);
        shipBoard.getStrike(columnA, row4);
        
        redirectSystemOut();
        
        shipBoard.printBoard();
        String[] actualOutputLines = 
                outputStream.toString().split(System.lineSeparator());
        
        assertEquals(expectedOutputLines.length, actualOutputLines.length);
        assertArrayEquals(expectedOutputLines, actualOutputLines);
    }
    
    private void redirectSystemOut() {
        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
    
    private void restoreSystemOut() {
        System.setOut(new PrintStream(originalOut));
    }
    
    private void enterSimulatedInput(String input) {
        inputStream = new ByteArrayInputStream(input.getBytes());
        scanner = new Scanner(inputStream);
    }
}
