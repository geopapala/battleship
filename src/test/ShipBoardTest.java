package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Constants;
import main.ShipBoard;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class ShipBoardTest {
    
    private ShipBoard shipBoard;

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
        
        String simulatedInput = "w 15 k\nj 5 h\n" // Invalid inputs
                                 + "a 5 h\n"
                                 + "c 3 v\n"      // Invalid input
                                 + "d 2 h\nh 10 h\nh 4 v\na 9 v";
        
        int[][] expectedBoard = {{0,0,0,0,0,0,0,0,0,0},
                                 {0,0,0,4,4,4,4,0,0,0},
                                 {0,0,0,0,0,0,0,0,0,0},
                                 {0,0,0,0,0,0,0,3,0,0},
                                 {5,5,5,5,5,0,0,3,0,0},
                                 {0,0,0,0,0,0,0,3,0,0},
                                 {0,0,0,0,0,0,0,0,0,0},
                                 {0,0,0,0,0,0,0,0,0,0},
                                 {2,0,0,0,0,0,0,0,0,0},
                                 {2,0,0,0,0,0,0,3,3,3}};
        
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        shipBoard.enterAllShipsManually();

        int[][] board = shipBoard.getBoard();
        
        for(int i = 0; i < Constants.BOARD_SIZE; i++) {
            for(int j = 0; j < Constants.BOARD_SIZE; j++) {
                assertEquals(expectedBoard[i][j], board[i][j]);
            }
        }

        System.setIn(System.in);
    }
/*
    @Test
    void testGetStrike() {
        fail("Not yet implemented");
    }

    @Test
    void testAllShipsSank() {
        fail("Not yet implemented");
    }

    @Test
    void testLastStrikeSankShip() {
        fail("Not yet implemented");
    }

    @Test
    void testPrintBoard() {
        fail("Not yet implemented");
    }
*/
}
