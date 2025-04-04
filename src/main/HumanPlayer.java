package main;

import java.util.Scanner;

public class HumanPlayer {

    private String name;
    private ShipBoard shipBoard = new ShipBoard(Constants.BOARD_SIZE);
    private StrikeBoard strikeBoard = new StrikeBoard(Constants.BOARD_SIZE);
    private Helper helper = new Helper();

    public HumanPlayer() {}
    
    public HumanPlayer(String name, Scanner scanner) {
        this.name = name;
        shipBoard.enterAllShipsManually(scanner);
    }
    
    public int[] enterNextStrike(Scanner scanner) {
        int [] nextStrikePosition = new int[2]; // column, row
        
        System.out.printf(Messages.NEXT_STRIKE_POSITION, name);
        System.out.println();
        
        while (true) {
            String input = scanner.nextLine();
            if (isStrikePositionValid(input)) {
                String[] inputParts = getInputParts(input);
                int column = helper.mapColumnLetterToIndex(inputParts[0].charAt(0));
                int row = Integer.parseInt(inputParts[1]) - 1;
                
                if (isPositionAlreadyStricken(column, row)) {
                    System.out.println(Messages.ALREADY_STRICKEN_POSITION);
                    continue;
                }
                nextStrikePosition[0] = column;
                nextStrikePosition[1] = row;
                
                break;
            }
            System.out.println(Messages.INVALID_STRIKE_POSITION);
        }
        return nextStrikePosition;
    }
    
    private boolean isStrikePositionValid(String input) {
        return input.matches(Constants.VALID_STRIKE_POSITION_REGEX);
    }
    
    private String[] getInputParts(String input) {
        return new String[] {
                input.substring(0,1), 
                input.substring(1)
        };
    }
    
    private boolean isPositionAlreadyStricken(int column, int row) {
        return strikeBoard.getBoard()[row][column] != 0;
    }
    
    public void updateStrikeBoard(int column, int row, boolean isHit) {
        strikeBoard.addStrike(column, row, isHit);
        if (isHit) {
            System.out.println(Messages.HIT);
            return;
        }
        System.out.println(Messages.MISS);
    }
    
    public boolean getStrike(int column, int row) {
        return shipBoard.getStrike(column, row);
    }
    
    public boolean allShipsSank() {
        return shipBoard.allShipsSank();
    }
    
    public boolean lastStrikeSankShip() {
        return shipBoard.lastStrikeSankShip();
    }
    
    public String toString() {
        return this.name;
    }
    
    public void showBoards() {
        int N = Constants.BOARD_SIZE;
        System.out.println("         SHIP BOARD                    STRIKE BOARD");
        System.out.println("   --A-B-C-D-E-F-G-H-I-J--       --A-B-C-D-E-F-G-H-I-J--");
        for (int row = 0; row < N; row++) {
            System.out.printf("%2d | ", row + 1);
            for (int col = 0; col < N; col++) {
                System.out.print(shipBoard.getSymbol(row, col) + " ");
            }
            System.out.print("|    "); // Spacer between boards

            System.out.printf("%2d | ", row + 1);
            for (int col = 0; col < N; col++) {
                System.out.print(strikeBoard.getSymbol(row, col) + " ");
            }
            System.out.println("|");
        }
        System.out.println("   -----------------------       -----------------------");
    }
}
