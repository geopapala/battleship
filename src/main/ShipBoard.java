package main;

import java.util.Scanner;

public class ShipBoard {

    private int N = Constants.BOARD_SIZE;
    private int[] ships = {5, 4, 3, 3, 2};
    private int[][] board = new int[N][N];
    private boolean[][] strickenPositions = new boolean[N][N];
    private boolean lastStrikeSankShip = false;
    
    ShipBoardHelper helper = new ShipBoardHelper();
    
    public void enterAllShipsManually() {
        
        Scanner scanner = new Scanner(System.in);
        
        helper.printGameInstructions();
        helper.printShipPlacementInstructions();
        printBoard();
        
        for(int shipId = 1; shipId <= ships.length; shipId++) {
            enterShipManually(shipId, scanner);
        }
        
        scanner.close();
    }
    
    private void enterShipManually(int id, Scanner scanner) {
        
        int startingRow;
        int startingColumn;
        char direction;
        String shipType = helper.resolveShipType(id);
        int shipSize = ships[id - 1];
        
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Give position and direction of "
                + shipType + " (size " + shipSize + "):");
        
        while(true) {
            
            String input = scanner.nextLine();
            String[] inputParts = input.split(" ");
            
            if(helper.isInputValid(inputParts)) {
                
                startingColumn = helper.mapLetterToColumnIndex(inputParts[0].charAt(0));
                startingRow = Integer.parseInt(inputParts[1]) - 1;
                direction = Character.toUpperCase(inputParts[2].charAt(0));
                
                if(!helper.isShipFit(startingColumn, startingRow, direction, shipSize)) {
                    System.out.println("The ship does not fit here. Try again!");
                    continue;
                }
                
                if(helper.isShipCollidingWithOther(startingColumn, startingRow, direction, shipSize, board)) {
                    System.out.println("There is another ship on this direction blocking this one. Try again!");
                    continue;
                }
                
                helper.placeShip(startingColumn, startingRow, direction, id, shipSize, board);
                
                break;
            }
            
            System.out.println("The position or direction you have entered is not valid. Please make sure that");
            System.out.println("the column is between A and J, the row is between 1 and 10, and the direction");
            System.out.println("is either H (Horizontal) or V (Vertical). Try again!");
        }
        
        printBoard();
    }
    
    public boolean getStrike(int row, int column) {
        
        lastStrikeSankShip = false; //TODO Review 
        
        //TODO Review if that check is not needed here
        if(strickenPositions[row][column] == true) {
            return false;
        }
        
        strickenPositions[row][column] = true;
        
        if(board[row][column] == 0) {
            return false;
        }
        
        int shipId = board[row][column];
        int shipRemainingPositions = --ships[shipId - 1];
        
        if(shipRemainingPositions == 0) {
            lastStrikeSankShip = true;
            System.out.println("*** TO BE REVIEWED - Last strike have sank your "
                    + helper.resolveShipType(shipId));
        }
        
        return true;
    }
    
    public boolean allShipsSank() {
        for(int shipSize: ships) {
            if(shipSize != 0) {
                return false;
            }
        }
        return true;
    }
    
    public boolean lastStrikeSankShip() {
        
        return lastStrikeSankShip;
    }
    
    public void printBoard() {
        
        System.out.println("   --A-B-C-D-E-F-G-H-I-J--");
        for (int row = 0; row < N; row++) {
            if(row != N - 1) {
                System.out.print(" ");
            }
            System.out.print((row + 1) + " | ");
            for (int col = 0; col < N; col++) {
                if(board[row][col] == 0) {
                    System.out.print(strickenPositions[row][col] == true 
                                     ? "o " 
                                     : "~ ");
                } else {
                    System.out.print(strickenPositions[row][col] == true 
                                     ? "* " 
                                     : helper.resolveShipTypeInitialLetter(board[row][col]) + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println("   -----------------------");
    }

    public int[][] getBoard() {
        
        return this.board;
    }
    
/*    public static void main(String[] args) {
        ShipBoard board = new ShipBoard();
        board.enterAllShipsManually();
        
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                System.out.print(board.strickenPositions[i][j] + " ");
            }
            System.out.println();
        }
    }*/

}
