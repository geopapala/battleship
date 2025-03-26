package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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


    @Test
    void testAddStrike() {
        int row1 = 0;
        int row5 = 4;
        int row10 = 9;
        int columnA = 0;
        int columnF = 5;
        int columnJ = 9;
        boolean isHit = true;
        boolean isMiss = false;
        
        strikeBoard.addStrike(columnA, row1, isHit);
        assertEquals(1, strikeBoard.getBoard()[row1][columnA]);
        
        strikeBoard.addStrike(columnA, row1, isMiss);
        assertEquals(-1, strikeBoard.getBoard()[row1][columnA]);
        
        strikeBoard.addStrike(columnA, row10, isHit);
        assertEquals(1, strikeBoard.getBoard()[row10][columnA]);
        
        strikeBoard.addStrike(columnA, row10, isMiss);
        assertEquals(-1, strikeBoard.getBoard()[row10][columnA]);
        
        strikeBoard.addStrike(columnJ, row1, isHit);
        assertEquals(1, strikeBoard.getBoard()[row1][columnJ]);
        
        strikeBoard.addStrike(columnJ, row1, isMiss);
        assertEquals(-1, strikeBoard.getBoard()[row1][columnJ]);
        
        strikeBoard.addStrike(columnJ, row10, isHit);
        assertEquals(1, strikeBoard.getBoard()[row10][columnJ]);
        
        strikeBoard.addStrike(columnJ, row10, isMiss);
        assertEquals(-1, strikeBoard.getBoard()[row10][columnJ]);
        
        strikeBoard.addStrike(columnF, row5, isHit);
        assertEquals(1, strikeBoard.getBoard()[row5][columnF]);
        
        strikeBoard.addStrike(columnF, row5, isMiss);
        assertEquals(-1, strikeBoard.getBoard()[row5][columnF]);
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
