package main;

import java.util.Scanner;

public class ShipBoard {

    private int N = Constants.BOARD_SIZE;
    private int[] ships = {5, 4, 3, 3, 2};
    private int[][] board = new int[N][N];
    private boolean[][] strikePositions = new boolean[N][N];
    private boolean lastStrikeSankShip = false;
    
    ShipBoardHelper helper = new ShipBoardHelper();
    
    public void enterAllShipsManually() {
        
        Scanner scanner = new Scanner(System.in);
        
        helper.printGameInstructions();
        helper.printShipPlacementInstructions();
        printBoard();
        
        for(int i = 0; i < ships.length; i++) {
            enterShipManually(i, scanner);
        }
        
        scanner.close();
    }
    
    private void enterShipManually(int id, Scanner scanner) {
        
        int startingRow;
        int startingColumn;
        char direction;
        int shipSize = ships[id];
        
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Give position and direction of "
                + helper.resolveShipType(shipSize, id) + " (size " + shipSize + "):");
        
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
                
                helper.placeShip(startingColumn, startingRow, direction, shipSize, board);
                
                break;
            }
            
            System.out.println("The position or direction you have entered is not valid. Please make sure that");
            System.out.println("the column is between A and J, the row is between 1 and 10, and the direction");
            System.out.println("is either H (Horizontal) or V (Vertical). Try again!");
        }
        
        printBoard();
    }
    
    public void getStrike(int x, int y) {
        
    }
    
    public boolean allShipsSank() {
        
        return false;
    }
    
    public boolean lastStrikeSankShip() {
        
        return false;
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
                    System.out.print("~ ");
                } else {
                    System.out.print(board[row][col] + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println("   -----------------------");
    }

    
    public static void main(String[] args) {
        ShipBoard board = new ShipBoard();
        board.enterAllShipsManually();
    }
}
