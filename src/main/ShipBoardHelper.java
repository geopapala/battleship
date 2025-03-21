package main;

public class ShipBoardHelper {
    
    public void printGameInstructions() {
        
        System.out.println("   Instructions");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("1. Each player places their ships secretly on their own board. Ships vary in");
        System.out.println("   size and must be placed horizontally or vertically, not diagonally.");
        System.out.println("2. Players alternate turns striking coordinates (like 'C 4') to guess where");
        System.out.println("   the opponent's ships are.");
        System.out.println("3. The opponent announces whether the guess was a 'hit' (a part of a ship) or");
        System.out.println("   a 'miss' (empty water). Your guesses are marked on your strike board.");
        System.out.println("4. Once all the coordinates of a ship are hit, that ship is 'sunk'. Keep going");
        System.out.println("   until all ships of the enemy are sunk.");
        System.out.println("5. The first player to sink all of their opponent’s ships wins the game!");
    }
    
    public void printShipPlacementInstructions() {
        
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("It is time to place your ships on the board. For each ship provide its starting");
        System.out.println("position and direction in the following format:");
        System.out.println("   [Column A-J] [Row 1-10] [H for Horizontal or V for Vertical].");
        System.out.println("For example, 'A 5 H' means the ship starts at column A, row 5, and is placed");
        System.out.println("horizontally. Remember, if a ship doesn’t fit, I’ll ask you to try again.");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("The board is a 10x10 grid like the one below where the symbol '~' means water");
    }
    
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
    
    public int mapLetterToColumnIndex(char columnLetter) {
        
        return Character.toUpperCase(columnLetter) - 'A';
    }
    
    public boolean isInputValid(String[] input) {
        
        return input.length == 3 
                && input[0].matches("[A-Ja-j]")
                && input[1].matches("^(10|[1-9])$")
                && input[2].matches("[HhVv]");
    }
    
    public boolean isShipFit(int startingColumn, 
                             int startingRow, 
                             char direction, 
                             int shipSize) {
        
        if(direction == 'H' && startingColumn + shipSize <= Constants.BOARD_SIZE) {
            return true;
        }
        
        if(direction == 'V' && startingRow + shipSize <= Constants.BOARD_SIZE) {
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
