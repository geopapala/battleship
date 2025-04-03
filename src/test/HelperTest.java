package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import main.Constants;
import main.Helper;

class HelperTest {
    
    private Helper helper;
    
    @BeforeEach
    void setUp() {
        helper = new Helper();
    }
    
    @AfterEach
    void tearDown() {
        helper = null;
    }

    @ParameterizedTest
    @CsvSource({"1, Aircraft Carrier",
                "2, Battleship",
                "3, Cruiser",
                "4, Submarine",
                "5, Destroyer",
                "6, Unknown"})
    void testResolveShipType(int shipId, String expectedShipType) {
        assertEquals(expectedShipType, helper.resolveShipType(shipId));
    }
    
    @ParameterizedTest
    @CsvSource({"1, A", "2, B", "3, C", "4, S", "5, D", "6, U"})
    void testResolveShipTypeInitialLetter(int shipId, char expectedLetter) {
        assertEquals(expectedLetter, helper.resolveShipTypeInitialLetter(shipId));
    }
    
    @ParameterizedTest
    @CsvSource({"A, 0", "B, 1", "C, 2", "D, 3", "E, 4",
                "F, 5", "G, 6", "H, 7", "I, 8", "J, 9"})
    void testMapColumnLetterToIndexWithUpperCaseLetters(char letter, 
                                                        int expectedIndex) {
        assertEquals(expectedIndex, helper.mapColumnLetterToIndex(letter));
    }
    
    @ParameterizedTest
    @CsvSource({"a, 0", "b, 1", "c, 2", "d, 3", "e, 4", 
                "f, 5", "g, 6", "h, 7", "i, 8", "j, 9"})
    void testMapColumnLetterToIndexWithLowerCaseLetters(char letter, 
                                                        int expectedIndex) {
        assertEquals(expectedIndex, helper.mapColumnLetterToIndex(letter));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"a1h",  "A1h",  "a1H",  "A1H",
                            "j1h",  "J1h",  "j1H",  "J1H",
                            "a10h", "A10h", "a10H", "A10H",
                            "j10h", "J10h", "j10H", "J10H",
                            "a5h",  "A5h",  "a5H",  "A5H",
                            "a1v",  "A1v",  "a1V",  "A1V",
                            "j1v",  "J1v",  "j1V",  "J1V",
                            "a10v", "A10v", "a10V", "A10V",
                            "j10v", "J10v", "j10V", "J10V",
                            "a5v",  "A5v",  "a5V",  "A5V",})
    void testIsValidShipPositionInputReturnsTrueForValidInput(String input) {
        assertTrue(helper.isValidShipPositionInput(input));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"abcd", "ABCD", "k5H", "K5H", 
                            "a15H", "A-15H", "A0H", "A5k", "A5K"})
    void testIsValidShipPositionInputReturnsFalseForInvalidInput(String input) {
        assertFalse(helper.isValidShipPositionInput(input));
    }
    
    @ParameterizedTest
    @CsvSource({"a1h,  a, 1,  h", 
                "a1H,  a, 1,  H", 
                "a10h, a, 10, h", 
                "a10H, a, 10, H", 
                "A1h,  A, 1,  h", 
                "A1H,  A, 1,  H", 
                "A10h, A, 10, h", 
                "A10H, A, 10, H"})
    void testGetInputParts(String input,
                           String expectedColumn, 
                           String expectedRow, 
                           String expectedDirection) {
        String[] expectedInputPart = new String[]{expectedColumn, 
                                                  expectedRow, 
                                                  expectedDirection};
        assertArrayEquals(expectedInputPart, helper.getInputParts(input));
    }
    
    // columnIndex, rowIndex, direction, shipSize
    @ParameterizedTest
    @CsvSource({"0, 0, H, 5", "0, 4, H, 5", "0, 9, H, 5",
                "4, 0, H, 5", "4, 4, H, 5", "4, 9, H, 5",
                "0, 0, V, 5", "0, 4, V, 5",
                "4, 0, V, 5", "4, 4, V, 5",
                "9, 0, V, 5", "9, 4, V, 5",
                "5, 5, H, 5", "5, 5, V, 5",
                "5, 6, H, 5", "6, 5, V, 5"})
    void testIsShipFitInPositionReturnsTrue(int startingColumnIndex, 
                                            int startingRowIndex, 
                                            char direction, 
                                            int shipSize) {
        assertTrue(helper.isShipFitInPosition(startingColumnIndex, 
                                              startingRowIndex, 
                                              direction, 
                                              shipSize));
    }

    // columnIndex, rowIndex, direction, shipSize
    @ParameterizedTest
    @CsvSource({"9, 0, H, 5", "9, 4, H, 5", "9, 9, H, 5",
                "0, 9, V, 5", "4, 9, V, 5", "9, 9, V, 5",
                "5, 6, V, 5", "6, 5, H, 5",
                "6, 6, H, 5", "6, 6, V, 5",
                /*two cases with invalid direction*/
                "0, 0, A, 5", "9, 0, A, 5"})
    void testIsShipFitInPositionReturnsFalse(int startingColumnIndex, 
                                             int startingRowIndex, 
                                             char direction, 
                                             int shipSize){
        assertFalse(helper.isShipFitInPosition(startingColumnIndex, 
                                               startingRowIndex, 
                                               direction, 
                                               shipSize));
    }
    
    @ParameterizedTest
    @CsvSource({"0, 4, H, 4, true", 
                "5, 4, H, 4, true", 
                "6, 4, H, 4, false", 
                "0, 3, V, 4, false", 
                "1, 3, V, 4, true", 
                "3, 3, V, 4, true", 
                "5, 3, V, 4, true", 
                "6, 3, V, 4, false"})
    void testIsShipCollidingWithOtherHorizontallyPlaced(int startingColumnIndex, 
                                                        int startingRowIndex, 
                                                        char direction, 
                                                        int shipSize,
                                                        boolean expectedResult) {
        int columnB = 1;
        int row5 = 4;
        int shipId1 = 1;
        int shipSize1 = 5;
        int[][] board = new int[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        
        helper.placeShip(columnB, 
                         row5, 
                         Constants.HORIZONTAL, 
                         shipId1, 
                         shipSize1, 
                         board);
        
        assertEquals(expectedResult, 
                     helper.isShipCollidingWithOther(startingColumnIndex, 
                                                     startingRowIndex, 
                                                     direction, 
                                                     shipSize,
                                                     board));
    }
    
    @ParameterizedTest
    @CsvSource({"1, 0, V, 4, false",
                "1, 3, V, 4, true",
                "1, 6, V, 4, true",
                "0, 3, H, 4, false",
                "0, 4, H, 4, true",
                "0, 6, H, 4, true",
                "0, 8, H, 4, true",
                "0, 9, H, 4, false"})
    void testIsShipCollidingWithOtherVerticallyPlaced(int startingColumnIndex, 
                                                      int startingRowIndex, 
                                                      char direction, 
                                                      int shipSize,
                                                      boolean expectedResult) {
        int columnB = 1;
        int row5 = 4;
        int shipId1 = 1;
        int shipSize1 = 5;
        int[][] board = new int[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        
        helper.placeShip(columnB, 
                         row5, 
                         Constants.VERTICAL, 
                         shipId1, 
                         shipSize1, 
                         board);
        
        assertEquals(expectedResult, 
                     helper.isShipCollidingWithOther(startingColumnIndex, 
                                                     startingRowIndex, 
                                                     direction, 
                                                     shipSize,
                                                     board));
    }

    @Test
    void testPlaceShipVertically() {
        int columnA = 0;
        int row5 = 4;
        int shipId1 = 1;
        int shipSize1 = 5;
        int[][] board = new int[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        
        helper.placeShip(columnA, 
                         row5, 
                         Constants.VERTICAL, 
                         shipId1, 
                         shipSize1, 
                         board);
        
        for(int i = row5; i < row5 + shipSize1; i++) {
            assertEquals(shipId1, board[i][columnA]);
        }
        
        
    }
    
    @Test
    void testPlaceShipHorizontally() {
        int columnA = 0;
        int row1 = 0;
        int shipId2 = 2;
        int shipSize2 = 4;
        int[][] board = new int[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        
        helper.placeShip(columnA, 
                         row1, 
                         Constants.HORIZONTAL, 
                         shipId2, 
                         shipSize2, 
                         board);
        
        for(int i = columnA; i < columnA + shipSize2; i++) {
            assertEquals(shipId2, board[row1][i]);
        }
    }
}
