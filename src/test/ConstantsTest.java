package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.Constants;

class ConstantsTest {

    @Test
    void test() {        
        assertEquals(10, Constants.BOARD_SIZE);
        assertEquals("A", Constants.PLAYER_ONE_DEFAULT_NAME);
        assertEquals("B", Constants.PLAYER_TWO_DEFAULT_NAME);
        assertEquals('H', Constants.HORIZONTAL);
        assertEquals('V', Constants.VERTICAL);
        assertEquals("Aircraft Carrier", Constants.AIRCRAFT_CARRIER);
        assertEquals("Battleship", Constants.BATTLESHIP);
        assertEquals("Cruiser", Constants.CRUISER);
        assertEquals("Submarine", Constants.SUBMARINE);
        assertEquals("Destroyer", Constants.DESTROYER);
        assertEquals("Aircraft Carrier", Constants.SHIP_TYPES.get(1));
        assertEquals("Battleship", Constants.SHIP_TYPES.get(2));
        assertEquals("Cruiser", Constants.SHIP_TYPES.get(3));
        assertEquals("Submarine", Constants.SHIP_TYPES.get(4));
        assertEquals("Destroyer", Constants.SHIP_TYPES.get(5));
        assertEquals("^[A-Ja-j]([1-9]|10)[HhVv]$", Constants.VALID_INPUT_REGEX);
        assertEquals("^[A-Ja-j]([1-9]|10)$", Constants.VALID_STRIKE_POSITION_REGEX);
    }
}
