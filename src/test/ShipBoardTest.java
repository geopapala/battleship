package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Constants;
import main.ShipBoard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

class ShipBoardTest {
    
    private ShipBoard shipBoard;
    private String simulatedInput1 = "w 15 k\n" // Invalid input
                                   + "j 5 h\n"  // Invalid input
                                   + "a 5 h\n"
                                   + "c 3 v\n"  // Invalid input
                                   + "d 2 h\n"
                                   + "h 10 h\n"
                                   + "h 4 v\n"
                                   + "a 9 v";
    private String simulatedInput2 = "a 1 h\n"
                                   + "f 1 h\n"
                                   + "a 2 h\n"
                                   + "d 2 h\n"
                                   + "j 1 v";

    @BeforeEach
    void setUp() {
        shipBoard = new ShipBoard();
    }

    @AfterEach
    void tearDown() {
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
        
        enterSimulatedInput(shipBoard, simulatedInput1);

        int[][] board = shipBoard.getBoard();
        
        for(int i = 0; i < Constants.BOARD_SIZE; i++) {
            for(int j = 0; j < Constants.BOARD_SIZE; j++) {
                assertEquals(expectedBoard[i][j], board[i][j]);
            }
        }
    }

    @Test
    void testGetStrike() {
        
        enterSimulatedInput(shipBoard, simulatedInput2);
        
        int row1 = 0;
        int row3 = 2;
        int columnA = 0;
        
        // Got strike in water for the 1st time
        assertEquals(false, shipBoard.getStrike(row3, columnA));
        // Got strike in the same water position for 2nd time
        assertEquals(false, shipBoard.getStrike(row3, columnA));
        // Got strike on a ship
        assertEquals(true, shipBoard.getStrike(row1, columnA));
        // Got strike on a ship in the same position 
        assertEquals(false, shipBoard.getStrike(row1, columnA));
    }

    @Test
    void testAllShipsSank() {
        
        assertEquals(false, shipBoard.allShipsSank());
        
        enterSimulatedInput(shipBoard, simulatedInput2);
        
        for(int row = 0; row <= 1; row++) {
            for(int col = 0; col < Constants.BOARD_SIZE; col++) {
                shipBoard.getStrike(row, col);
            }
        }
        
        assertEquals(true, shipBoard.allShipsSank());
    }

    @Test
    void testLastStrikeSankShip() {
        
        int row1 = 0;
        int columnA = 0;
        int shipSize1 = 5;
        
        enterSimulatedInput(shipBoard, simulatedInput2);
        
        shipBoard.getStrike(row1, columnA);
        
        assertEquals(false, shipBoard.lastStrikeSankShip());
        
        for(int col = columnA; col < columnA + shipSize1; col++) {
            shipBoard.getStrike(row1, col);
        }
        
        assertEquals(true, shipBoard.lastStrikeSankShip());
    }

    @Test
    void testPrintBoard() {
        
        int row1 = 0;
        int row4 = 3;
        int columnA = 0;
        
        String expectedPrintOutput = 
                "   --A-B-C-D-E-F-G-H-I-J--" + System.lineSeparator()
              + " 1 | * A A A A B B B B D |" + System.lineSeparator()
              + " 2 | C C C S S S ~ ~ ~ D |" + System.lineSeparator()
              + " 3 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |" + System.lineSeparator()
              + " 4 | o ~ ~ ~ ~ ~ ~ ~ ~ ~ |" + System.lineSeparator()
              + " 5 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |" + System.lineSeparator()
              + " 6 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |" + System.lineSeparator()
              + " 7 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |" + System.lineSeparator()
              + " 8 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |" + System.lineSeparator()
              + " 9 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |" + System.lineSeparator()
              + "10 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |" + System.lineSeparator()
              + "   -----------------------" + System.lineSeparator();
        
        enterSimulatedInput(shipBoard, simulatedInput2);
        
        shipBoard.getStrike(row1, columnA);
        shipBoard.getStrike(row4, columnA);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        
        shipBoard.printBoard();
        
        assertEquals(expectedPrintOutput, outputStream.toString());
        
        System.setOut(System.out);

    }

    private void enterSimulatedInput(ShipBoard shipBoard, String simulatedInput) {
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        
        shipBoard.enterAllShipsManually();
        
        System.setIn(System.in);
    }
}
