package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.Constants;

class ConstantsTest {

    @Test
    void test() {
        Constants constants = new Constants();
        assertEquals(10, Constants.BOARD_SIZE);
        constants = null;
    }

}
