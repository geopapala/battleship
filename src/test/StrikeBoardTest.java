package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.Constants;
import main.StrikeBoard;

class StrikeBoardTest {

    private static final boolean HIT = true;
    private static final boolean MISS = false;

    private int N = 10;
    private StrikeBoard strikeBoard;
    
    @BeforeEach
    void setUp() {
        strikeBoard = new StrikeBoard(N);
    }

    @AfterEach
    void tearDown() {
        strikeBoard = null;
    }
    
    @Test
    void testBoardWithNoStrikes() {
        int[][] board = strikeBoard.getBoard();
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                assertEquals(0, board[i][j]);
            }
        }
    }

    @ParameterizedTest
    @CsvSource({"0,0", "1,0", "8,0", "9,0",
                "0,1",               "9,1",
                       "4,4", "5,4",
                       "4,5", "5,5",
                "0,8",               "9,8",
                "0,9", "1,9", "8,9", "9,9"})
    void testAddStrikeIsHit(int columnIndex, int rowIndex) {
        strikeBoard.addStrike(columnIndex, rowIndex, HIT);
        assertEquals(1, strikeBoard.getBoard()[rowIndex][columnIndex]);
    }
    
    @Test
    void testMultipleHitsDoNotChangeStateOnStruckPosition() {
        strikeBoard.addStrike(0, 0, HIT);
        strikeBoard.addStrike(0, 0, HIT);
        assertEquals(1, strikeBoard.getBoard()[0][0]);
    }
    
    @Test
    void testMissDoesNotOverwriteHitOnStruckPosition() {
        strikeBoard.addStrike(0, 0, HIT);
        strikeBoard.addStrike(0, 0, MISS);
        assertEquals(1, strikeBoard.getBoard()[0][0]);
    }
    
    @ParameterizedTest
    @CsvSource({"0,0", "1,0", "8,0", "9,0",
                "0,1",               "9,1",
                       "4,4", "5,4",
                       "4,5", "5,5",
                "0,8",               "9,8",
                "0,9", "1,9", "8,9", "9,9"})
    void testAddStrikeIsMiss(int columnIndex, int rowIndex) {
        strikeBoard.addStrike(columnIndex, rowIndex, MISS);
        assertEquals(-1, strikeBoard.getBoard()[rowIndex][columnIndex]);
    }
    
    @Test
    void testHitDoesNotOverwriteMissOnStruckPosition() {
        strikeBoard.addStrike(0, 0, MISS);
        strikeBoard.addStrike(0, 0, HIT);
        assertEquals(-1, strikeBoard.getBoard()[0][0]);
    }
    
    @Test
    void testMultipleMissesDoNotChangeStateOnStruckPosition() {
        strikeBoard.addStrike(0, 0, MISS);
        strikeBoard.addStrike(0, 0, MISS);
        assertEquals(-1, strikeBoard.getBoard()[0][0]);
    }
    
    @Test
    void testGetSymbol() {
        strikeBoard.addStrike(0, 0, HIT);
        strikeBoard.addStrike(1, 0, MISS);
        
        assertEquals("*", strikeBoard.getSymbol(0,0));
        assertEquals("o", strikeBoard.getSymbol(1,0));
        assertEquals("~", strikeBoard.getSymbol(2,0));
    }
}
