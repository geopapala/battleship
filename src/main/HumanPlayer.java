package main;

import java.io.InputStream;
import java.util.Scanner;

public class HumanPlayer {

    private String name;
    private ShipBoard shipBoard = new ShipBoard();
    private StrikeBoard strikeBoard = new StrikeBoard();
    private Helper helper = new Helper();

    public HumanPlayer() {}
    
    public HumanPlayer(String name) {
        this.name = name;
        shipBoard.enterAllShipsManually(System.in);
    }
    
    public int[] enterNextStrike(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        int [] nextStrikePosition = new int[2]; // column, row
        
        System.out.printf(Messages.NEXT_STRIKE_POSITION, name);
        System.out.println();
        
        while (true) {
            String input = scanner.nextLine();
            String[] inputParts = input.split(" ");
            
            if (isStrikePositionValid(inputParts)) {
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
    
    private boolean isStrikePositionValid(String[] input) {
        return input.length == 2
            && input[0].matches("[A-Ja-j]")
            && input[1].matches("^(10|[1-9])$");
    }
    
    private boolean isPositionAlreadyStricken(int column, int row) {
        return strikeBoard.getBoard()[row][column] != 0;
    }
    
    public void update(int column, int row, boolean isHit) {
        
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
    
    public void printShipBoard() {
        shipBoard.printBoard();
    }
    
    public void printStrikeBoard() {
        strikeBoard.printBoard();        
    }
    
    public void printBoards() {
        // TODO
    }
}
