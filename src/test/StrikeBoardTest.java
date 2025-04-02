package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.StrikeBoard;

class StrikeBoardTest {

    private StrikeBoard strikeBoard;

    @BeforeEach
    void setUp() {
        strikeBoard = new StrikeBoard();
    }

    @AfterEach
    void tearDown() {
        strikeBoard = null;
    }

    @ParameterizedTest
    @CsvSource({"0,0", "0,9", "9,0", "9,9", "4,4"})
    void testAddStrikeIsHit(int columnIndex, int rowIndex) {
        boolean isHit = true;
        boolean isMiss = false;
        
        strikeBoard.addStrike(columnIndex, rowIndex, isHit);
        assertEquals(1, strikeBoard.getBoard()[rowIndex][columnIndex]);
        
        // Ensure that a hit position wont turn to a miss
        strikeBoard.addStrike(columnIndex, rowIndex, isMiss);
        assertEquals(1, strikeBoard.getBoard()[rowIndex][columnIndex]);
    }
    
    @ParameterizedTest
    @CsvSource({"0,0", "0,9", "9,0", "9,9", "4,4"})
    void testAddStrikeIsMiss(int columnIndex, int rowIndex) {
        boolean isHit = true;
        boolean isMiss = false;
        
        strikeBoard.addStrike(columnIndex, rowIndex, isMiss);
        assertEquals(-1, strikeBoard.getBoard()[rowIndex][columnIndex]);
        
        // Ensure that a miss position wont turn to a hit
        strikeBoard.addStrike(columnIndex, rowIndex, isHit);
        assertEquals(-1, strikeBoard.getBoard()[rowIndex][columnIndex]);
    }
    
    @Test
    void testGetSymbol() {
        boolean isHit = true;
        boolean isMiss = false;
        
        strikeBoard.addStrike(0, 0, isHit);
        strikeBoard.addStrike(1, 0, isMiss);
        
        assertEquals("*", strikeBoard.getSymbol(0,0));
        assertEquals("o", strikeBoard.getSymbol(1,0));
        assertEquals("~", strikeBoard.getSymbol(2,0));
    }
}
