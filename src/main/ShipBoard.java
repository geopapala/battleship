package main;

import java.io.InputStream;
import java.util.Scanner;

public class ShipBoard {
    private int N = Constants.BOARD_SIZE;
    private int[] ships = {5, 4, 3, 3, 2};
    private int[][] board = new int[N][N];
    private boolean[][] strickenBoardPositions = new boolean[N][N];
    private boolean lastStrikeSankShip = false;
    private Helper helper = new Helper();
    
    public void enterAllShipsManually(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        for (int shipId = 1; shipId <= ships.length; shipId++) {
            enterShipManually(shipId, scanner);
        }
    }
    
    private void enterShipManually(int id, Scanner scanner) {
        int startingColumn;
        int startingRow;
        char direction;
        String shipType = helper.resolveShipType(id);
        int shipSize = ships[id - 1];
        
        System.out.printf(Messages.ENTER_SHIP_POSITION, shipType, shipSize);
        System.out.println();
        
        while (true) {
            String input = scanner.nextLine();
            String[] inputParts = input.split(" ");
            
            if (helper.isShipPositionInputValid(inputParts)) {
                startingColumn = helper.mapColumnLetterToIndex(inputParts[0].charAt(0));
                startingRow = Integer.parseInt(inputParts[1]) - 1;
                direction = Character.toUpperCase(inputParts[2].charAt(0));
                
                if (!helper.isShipFitInPosition(startingColumn, startingRow, direction, shipSize)) {
                    System.out.println(Messages.SHIP_DONT_FIT);
                    continue;
                }
                
                if (helper.isShipCollidingWithOther(startingColumn, startingRow, direction, shipSize, board)) {
                    System.out.println(Messages.SHIP_COLLIDING_WITH_OTHER);
                    continue;
                }
                
                helper.placeShip(startingColumn, startingRow, direction, id, shipSize, board);
                break;
            }
            System.out.println(Messages.INVALID_SHIP_PLACEMENT);
        }
        printBoard();
    }
    
    public boolean getStrike(int column, int row) {
        lastStrikeSankShip = false; //TODO Review 
        
        strickenBoardPositions[row][column] = true;
        
        if (board[row][column] == 0) {
            return false;
        }
        
        int shipId = board[row][column];
        int shipRemainingPositions = --ships[shipId - 1];
        
        if (shipRemainingPositions == 0) {
            lastStrikeSankShip = true;
            System.out.println("*** TO BE REVIEWED - Last strike have sank your "
                    + helper.resolveShipType(shipId));
        }
        return true;
    }
    
    public boolean allShipsSank() {
        for (int shipSize: ships) {
            if (shipSize != 0) {
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
            if (row != N - 1) {
                System.out.print(" ");
            }
            System.out.print((row + 1) + " | ");
            for (int col = 0; col < N; col++) {
                if (board[row][col] == 0) {
                    System.out.print(strickenBoardPositions[row][col] == true 
                                     ? "o " 
                                     : "~ ");
                } else {
                    System.out.print(strickenBoardPositions[row][col] == true 
                                     ? "* " 
                                     : helper.resolveShipTypeInitialLetter(board[row][col]) + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println("   -----------------------");
        System.out.println();
    }

    public int[][] getBoard() {
        return this.board;
    }
}
