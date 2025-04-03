package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.HumanPlayer;
import main.Messages;

class HumanPlayerTest {
    
    HumanPlayer humanPlayer;

    @BeforeEach
    void setUp() {
        humanPlayer = new HumanPlayer();
    }

    @AfterEach
    void tearDown() {
        humanPlayer = null;
    }

    @Test
    void testEnterNextStrike() {

        String invalidNextStrike = "K 0\n"
                                 + "L 11\n"
                                 + "A -9\n"
                                 + "KLM\n"
                                 + "0 1 2\n";
        String expectedOutPutMessage = 
                "--------------------------------------------------------------------------------\n"
              + "Captain null enter your next strike position formatted as [A-J] [1-10]:\n"
              + Messages.INVALID_STRIKE_POSITION;
        
        String[] validNextStrike = {"K 0\\nA 1\n", 
                                    "A 1\n", 
                                    "A 10\n", 
                                    "J 1\n", 
                                    "J 10\n", 
                                    "f 5\n"};
        int[][] expectedOutPut = {{0,0},{0,9},{9,0},{9,9},{5,4}};
        
/*
        InputStream inputStream = new ByteArrayInputStream(invalidNextStrike.getBytes());
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        humanPlayer.enterNextStrike(inputStream);

        assertEquals(expectedOutPutMessage, outputStream.toString());
            
        System.setOut(originalOut);

        
        for (int i = 0; i < validNextStrike.length; i++) {
            InputStream inputStream = new ByteArrayInputStream(validNextStrike[i].getBytes());
            int[] nextStrikeInt = humanPlayer.enterNextStrike(inputStream);
            
            assertEquals(expectedOutPut[i][0], nextStrikeInt[0]);
            assertEquals(expectedOutPut[i][1], nextStrikeInt[1]);
        }*/
    }/**/
}
