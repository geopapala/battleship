package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Constants;
import main.Helper;

class HelperTest {
    
    private int N = Constants.BOARD_SIZE;
    private Helper helper;
    
    @BeforeEach
    void setUp() {
        helper = new Helper();
    }
    
    @AfterEach
    void tearDown() {
        helper = null;
    }

    @Test
    void testResolveShipType() {
        int[] ships = {5, 4, 3, 3, 2, 0};
        String[] expectedShipType = {"Aircraft Carrier", 
                                     "Battleship", 
                                     "Cruiser", 
                                     "Submarine", 
                                     "Destroyer", 
                                     "Unknown"};
        
        for (int shipId = 1; shipId <= ships.length; shipId++) {
            assertEquals(expectedShipType[shipId - 1], 
                         helper.resolveShipType(shipId));
        }
    }
    
    @Test
    void testResolveShipTypeInitialLetter() {
        int[] ships = {5, 4, 3, 3, 2, 0};
        char[] shipTypeInitialLetter = {'A', 'B', 'C', 'S', 'D', 'U'};
        
        for (int shipId = 1; shipId <= ships.length; shipId++) {
            assertEquals(shipTypeInitialLetter[shipId - 1], 
                         helper.resolveShipTypeInitialLetter(shipId));
        }
    }
    
    @Test
    void testMapColumnLetterToIndex() {
        char[] upperLetters = {'A','B','C','D','E','F','G','H','I','J'};
        char[] lowerLetters = {'a','b','c','d','e','f','g','h','i','j'};
        
        int[] expectedIndexes = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        
        for (int i = 0; i < upperLetters.length; i++) {
            assertEquals(expectedIndexes[i], 
                         helper.mapColumnLetterToIndex(upperLetters[i]));
        }
        
        for (int i = 0; i < lowerLetters.length; i++) {
            assertEquals(expectedIndexes[i], 
                         helper.mapColumnLetterToIndex(lowerLetters[i]));
        }
    }

    @Test
    void testIsShipPositionInputValid() {
        
        boolean isValid = true;
        boolean isNotValid = false;

        String[] invalidInputs = {"abcd", "A B C D",
                                  "k 5 H", "K 5 H", 
                                  "a 15 H", "A -15 H", "A 0 H", 
                                  "A 5 k", "A 5 K"
                                  };
        
        String[] validInputs = {"a 5 H", "a 5 h", 
                                "A 5 H", "A 5 h", 
                                "a 5 V", "a 5 v", "a 10 v", 
                                "A 5 V", "A 5 v"
                                };

        for (String input: invalidInputs) {
            assertEquals(isNotValid, 
                         helper.isShipPositionInputValid(input.split(" ")));
        }
        
        for (String input: validInputs) {
            assertEquals(isValid, 
                         helper.isShipPositionInputValid(input.split(" ")));
        }
    }
    
    @Test
    void testIsShipFitInPosition() {
        int row1 = 0;
        int row5 = 4;
        int row10 = 9;
        int columnA = 0;
        int columnE = 4;
        int columnJ = 9;
        
        char horizontal = 'H';
        char vertical = 'V';
        int shipSize1 = 5;
        
        boolean isFit = true;
        boolean isNotFit = false;
        
        assertEquals(isFit, helper.isShipFitInPosition(columnA, row1, horizontal, shipSize1));
        assertEquals(isFit, helper.isShipFitInPosition(columnA, row5, horizontal, shipSize1));
        assertEquals(isFit, helper.isShipFitInPosition(columnA, row10, horizontal, shipSize1));
        assertEquals(isFit, helper.isShipFitInPosition(columnE, row1, horizontal, shipSize1));
        assertEquals(isFit, helper.isShipFitInPosition(columnE, row5, horizontal, shipSize1));
        assertEquals(isFit, helper.isShipFitInPosition(columnE, row10, horizontal, shipSize1));
        assertEquals(isNotFit, helper.isShipFitInPosition(columnJ, row1, horizontal, shipSize1));
        assertEquals(isNotFit, helper.isShipFitInPosition(columnJ, row5, horizontal, shipSize1));
        assertEquals(isNotFit, helper.isShipFitInPosition(columnJ, row10, horizontal, shipSize1));
        
        assertEquals(isFit, helper.isShipFitInPosition(columnA, row1, vertical, shipSize1));
        assertEquals(isFit, helper.isShipFitInPosition(columnA, row5, vertical, shipSize1));
        assertEquals(isNotFit, helper.isShipFitInPosition(columnA, row10, vertical, shipSize1));
        assertEquals(isFit, helper.isShipFitInPosition(columnE, row1, vertical, shipSize1));
        assertEquals(isFit, helper.isShipFitInPosition(columnE, row5, vertical, shipSize1));
        assertEquals(isNotFit, helper.isShipFitInPosition(columnE, row10, vertical, shipSize1));
        assertEquals(isFit, helper.isShipFitInPosition(columnJ, row1, vertical, shipSize1));
        assertEquals(isFit, helper.isShipFitInPosition(columnJ, row5, vertical, shipSize1));
        assertEquals(isNotFit, helper.isShipFitInPosition(columnJ, row10, vertical, shipSize1));
    }

    @Test
    void testIsShipCollidingWithOther() {
        
        int columnA = 0;
        int columnB = 1;
        int columnD = 3;
        int columnF = 5;
        int columnG = 6;
        int row1 = 0;
        int row4 = 3;
        int row5 = 4;
        int row7 = 6;
        int row9 = 8;
        int row10 = 9;
        char horizontal = 'H';
        char vertical = 'V';
        int shipId1 = 1;
        int shipSize1 = 5;
        int shipSize2 = 4;

        boolean isColliding = true;
        boolean isNotColliding = false;
        
        int[][] board = new int[N][N];
        helper.placeShip(columnB, row5, horizontal, shipId1, shipSize1, board);
        assertEquals(isColliding, 
                     helper.isShipCollidingWithOther(columnA,
                                                     row5,
                                                     horizontal,
                                                     shipSize2,
                                                     board));
        assertEquals(isColliding, 
                     helper.isShipCollidingWithOther(columnF,
                                                     row5,
                                                     horizontal,
                                                     shipSize2,
                                                     board));
        assertEquals(isNotColliding, 
                     helper.isShipCollidingWithOther(columnG,
                                                     row5,
                                                     horizontal,
                                                     shipSize2,
                                                     board));
        assertEquals(isNotColliding, 
                     helper.isShipCollidingWithOther(columnA,
                                                     row4,
                                                     vertical,
                                                     shipSize2,
                                                     board));
        assertEquals(isColliding, 
                     helper.isShipCollidingWithOther(columnB,
                                                     row4,
                                                     vertical,
                                                     shipSize2,
                                                     board));
        assertEquals(isColliding, 
                     helper.isShipCollidingWithOther(columnD,
                                                     row4,
                                                     vertical,
                                                     shipSize2,
                                                     board));
        assertEquals(isColliding, 
                     helper.isShipCollidingWithOther(columnF,
                                                     row4,
                                                     vertical,
                                                     shipSize2,
                                                     board));
        assertEquals(isNotColliding, 
                     helper.isShipCollidingWithOther(columnG,
                                                     row4,
                                                     vertical,
                                                     shipSize2,
                                                     board));
        
        board = new int[N][N];
        helper.placeShip(columnB, row5, vertical, shipId1, shipSize1, board);
        assertEquals(isNotColliding, 
                     helper.isShipCollidingWithOther(columnB,
                                                     row1,
                                                     vertical,
                                                     shipSize2,
                                                     board));
        assertEquals(isColliding, 
                     helper.isShipCollidingWithOther(columnB,
                                                     row4,
                                                     vertical,
                                                     shipSize2,
                                                     board));
        assertEquals(isColliding, 
                     helper.isShipCollidingWithOther(columnB,
                                                     row7,
                                                     vertical,
                                                     shipSize2,
                                                     board));
        assertEquals(isNotColliding, 
                     helper.isShipCollidingWithOther(columnA,
                                                     row4,
                                                     horizontal,
                                                     shipSize2,
                                                     board));
        assertEquals(isColliding, 
                     helper.isShipCollidingWithOther(columnA,
                                                     row5,
                                                     horizontal,
                                                     shipSize2,
                                                     board));
        assertEquals(isColliding, 
                     helper.isShipCollidingWithOther(columnA,
                                                     row7,
                                                     horizontal,
                                                     shipSize2,
                                                     board));
        assertEquals(isColliding, 
                     helper.isShipCollidingWithOther(columnA,
                                                     row9,
                                                     horizontal,
                                                     shipSize2,
                                                     board));
        assertEquals(isNotColliding, 
                     helper.isShipCollidingWithOther(columnA,
                                                     row10,
                                                     horizontal,
                                                     shipSize2,
                                                     board));
    }

    @Test
    void testPlaceShip() {
        
        int[][] board = new int[N][N];
        
        int columnA = 0;
        int row1 = 0;
        int row5 = 4;
        char horizontal = 'H';
        char vertical = 'V';
        
        int shipId1 = 1;
        int shipSize1 = 5;
        int shipId2 = 2;
        int shipSize2 = 4;
        
        helper.placeShip(columnA, row5, vertical, shipId1, shipSize1, board);
        for(int i = row5; i < row5 + shipSize1; i++) {
            assertEquals(shipId1, board[i][columnA]);
        }
        
        helper.placeShip(columnA, row1, horizontal, shipId2, shipSize2, board);
        for(int i = columnA; i < columnA + shipSize2; i++) {
            assertEquals(shipId2, board[row1][i]);
        }
    }
}
