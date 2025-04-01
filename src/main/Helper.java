package main;

public class Helper {
        
    public String resolveShipType(int id) {
        return Constants.SHIP_TYPES.getOrDefault(id, "Unknown");
    }
    
    public char resolveShipTypeInitialLetter(int id) {
        return resolveShipType(id).charAt(0);
    }
    
    public int mapColumnLetterToIndex(char columnLetter) {
        return Character.toUpperCase(columnLetter) - 'A';
    }
    
    public boolean isShipPositionInputValid(String[] input) {
        return input.length == 3 
                && input[0].matches(Constants.VALID_COLUMN_REGEX)
                && input[1].matches(Constants.VALID_ROW_REGEX)
                && input[2].matches(Constants.VALID_DIRECTION_REGEX);
    }
    
    public boolean isShipFitInPosition(int startingColumn, 
                                       int startingRow, 
                                       char direction, 
                                       int shipSize) {
        return (direction == Constants.HORIZONTAL 
                        && startingColumn + shipSize <= Constants.BOARD_SIZE) 
                || (direction == Constants.VERTICAL 
                        && startingRow + shipSize <= Constants.BOARD_SIZE);
    }
    
    public boolean isShipCollidingWithOther(int startingColumn, 
                                            int startingRow, 
                                            char direction, 
                                            int shipSize, 
                                            int[][] board) {
        if (direction == Constants.HORIZONTAL) {
            for (int col = startingColumn; col < startingColumn + shipSize; col++) {
                if (board[startingRow][col] != 0) {
                    return true;
                }
            }
        }
        
        if (direction == Constants.VERTICAL) {
            for (int row = startingRow; row < startingRow + shipSize; row++) {
                if (board[row][startingColumn] != 0) {
                    return true;
                }
            }
        }
        
        return false;
    }
        
    public void placeShip(int startingColumn, 
                          int startingRow, 
                          char direction, 
                          int shipId,
                          int shipSize, 
                          int[][] board) {
        if (direction == Constants.HORIZONTAL) {
            for (int col = startingColumn; col < startingColumn + shipSize; col++) {
                board[startingRow][col] = shipId;
            }
        }
            
        if (direction == Constants.VERTICAL) {
            for (int row = startingRow; row < startingRow + shipSize; row++) {
                board[row][startingColumn] = shipId;
            }
        }
    }
}
