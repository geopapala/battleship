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
    
    public boolean isValidShipPositionInput(String input) {
        return input.matches(Constants.VALID_INPUT_REGEX);
    }
    
    public String[] getInputParts(String input) {
        int length = input.length();
        return new String[] {
                input.substring(0, 1),
                input.substring(1, length-1),
                input.substring(length-1)
        };
    }
    
    public boolean isShipFitInPosition(int startingColumn, 
                                       int startingRow, 
                                       char direction, 
                                       int shipSize) {
        if (direction == Constants.HORIZONTAL) {
            return (startingColumn + shipSize <= Constants.BOARD_SIZE);
        } else if (direction == Constants.VERTICAL) {
            return (startingRow + shipSize <= Constants.BOARD_SIZE);
        }
        return false;
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
