package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
    void testPrintBoard() {
        
        int row1 = 0;
        int columnA = 0;
        int columnB = 1;
        boolean isHit = true;
        boolean isMiss = false;
        
        String expectedPrintOutput = 
                "   --A-B-C-D-E-F-G-H-I-J--" + System.lineSeparator()
              + " 1 | * o ~ ~ ~ ~ ~ ~ ~ ~ |" + System.lineSeparator()
              + " 2 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |" + System.lineSeparator()
              + " 3 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |" + System.lineSeparator()
              + " 4 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |" + System.lineSeparator()
              + " 5 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |" + System.lineSeparator()
              + " 6 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |" + System.lineSeparator()
              + " 7 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |" + System.lineSeparator()
              + " 8 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |" + System.lineSeparator()
              + " 9 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |" + System.lineSeparator()
              + "10 | ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |" + System.lineSeparator()
              + "   -----------------------" + System.lineSeparator();
        
        strikeBoard.addStrike(columnA, row1, isHit);
        strikeBoard.addStrike(columnB, row1, isMiss);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        
        strikeBoard.printBoard();
        assertEquals(expectedPrintOutput, outputStream.toString());
        
        System.setOut(System.out);
    }

}
