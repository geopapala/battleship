package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.HumanPlayer;

class HumanPlayerTest {
    /*
    HumanPlayer humanPlayer;

    @BeforeEach
    void setUp() {
        humanPlayer = new HumanPlayer("Player");
        
    }

    @AfterEach
    void tearDown() {
        humanPlayer = null;
    }

    @Test
    void testEnterNextStrike() {

        String[] invalidNextStrike = {"K 0\n", 
                                      "L 11\n", 
                                      "KLM\n", 
                                      "012\n"};
        
        String[] validNextStrike = {"A 1\n", 
                                    "A 10\n", 
                                    "J 1\n", 
                                    "J 10\n", 
                                    "f 5\n"};
        
        int[][] expectedOutPut = {{0,0},{0,9},{9,0},{9,9},{5,4}};
        
        
        for(int i = 0; i < validNextStrike.length; i++) {
            InputStream inputStream = new ByteArrayInputStream(validNextStrike[i].getBytes());
            System.setIn(inputStream);
            
            int[] nextStrikeInt = humanPlayer.enterNextStrike();
            
            assertEquals(expectedOutPut[i][0], nextStrikeInt[0]);
            assertEquals(expectedOutPut[i][1], nextStrikeInt[1]);
        }
        
        System.setIn(System.in);
    }*/
}
