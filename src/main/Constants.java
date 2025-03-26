package main;

import java.util.Map;

public final class Constants {

    public static final int BOARD_SIZE = 10;
    
    public static final char HORIZONTAL = 'H';
    public static final char VERTICAL = 'V';
    
    public static final String AIRCRAFT_CARRIER = "Aircraft Carrier";
    public static final String BATTLESHIP = "Battleship";
    public static final String CRUISER = "Cruiser";
    public static final String SUBMARINE = "Submarine";
    public static final String DESTROYER = "Destroyer";
    public static final String UNKNOWN = "Unknown";
    
    public static final Map<Integer, String> SHIP_TYPES = Map.of(
        1, AIRCRAFT_CARRIER,
        2, BATTLESHIP,
        3, CRUISER,
        4, SUBMARINE,
        5, DESTROYER,
        6, UNKNOWN
    );
    
    public static final String VALID_COLUMN_REGEX = "[A-Ja-j]";
    public static final String VALID_ROW_REGEX = "^(10|[1-9])$";
    public static final String VALID_DIRECTION_REGEX = "[HhVv]";
}
