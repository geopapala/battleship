package main;

public class Helper {
    
    private int N = Constants.BOARD_SIZE;
    
    public String resolveShipType(int id) {
        switch(id) {
        case 1:
            return "Aircraft Carrier";
        case 2:
            return "Battleship";
        case 3:
            return "Cruiser";
        case 4:
            return "Submarine";
        case 5:
            return "Destroyer";
        default:
            return "Unknown";
        }
    }
    
    public char resolveShipTypeInitialLetter(int id) {
        return resolveShipType(id).charAt(0);
    }
    
    public int mapColumnLetterToIndex(char columnLetter) {
        return Character.toUpperCase(columnLetter) - 'A';
    }
    
    public boolean isShipPositionInputValid(String[] input) {
        return input.length == 3 
                && input[0].matches("[A-Ja-j]")
                && input[1].matches("^(10|[1-9])$")
                && input[2].matches("[HhVv]");
    }
    
    public boolean isShipFitInPosition(int startingColumn, 
                                       int startingRow, 
                                       char direction, 
                                       int shipSize) {
        if(direction == 'H' && startingColumn + shipSize <= N) {
            return true;
        }
        
        if(direction == 'V' && startingRow + shipSize <= N) {
            return true;
        }
        
        return false;
    }
    
    public boolean isShipCollidingWithOther(int startingColumn, 
                                            int startingRow, 
                                            char direction, 
                                            int shipSize, 
                                            int[][] board) {
        if(direction == 'H') {
            for(int col = startingColumn; col < startingColumn + shipSize; col++) {
                if(board[startingRow][col] != 0) {
                    return true;
                }
            }
        }
        
        if(direction == 'V') {
            for(int row = startingRow; row < startingRow + shipSize; row++) {
                if(board[row][startingColumn] != 0) {
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
        if(direction == 'H') {
            for(int col = startingColumn; col < startingColumn + shipSize; col++) {
                board[startingRow][col] = shipId;
            }
        }
            
        if(direction == 'V') {
            for(int row = startingRow; row < startingRow + shipSize; row++) {
                board[row][startingColumn] = shipId;
            }
        }
    }
}
