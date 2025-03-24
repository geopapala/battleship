package main;

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
    
    public int[] enterNextStrike() {
        Scanner scanner = new Scanner(System.in);
        int [] nextStrikePosition = new int[2]; // row, column
        
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Captain " + name + " enter your next strike position formatted as [A-J] [1-10]:");
        
        while (true) {
            
            String input = scanner.nextLine();
            String[] inputParts = input.split(" ");
            
            if (isStrikePositionValid(inputParts)) {
                int column = helper.mapColumnLetterToIndex(inputParts[0].charAt(0));
                int row = Integer.parseInt(inputParts[1]) - 1;
                
                if (strikeBoard.getBoard()[row][column] != 0) {
                    System.out.println("You have entered an already stricken position. Try again!");
                    continue;
                }
                
                nextStrikePosition[0] = row;
                nextStrikePosition[1] = column;
                
                break;
            }
            
            System.out.println("You have entered an invalid position. Try again!");
        }
        
        return nextStrikePosition;
    }
    
    private boolean isStrikePositionValid(String[] input) {
        
        return input.length == 2
            && input[0].matches("[A-Ja-j]")
            && input[1].matches("^(10|[1-9])$");
    }
    
    public void update(int row, int column, boolean isHit) {
        
        strikeBoard.addStrike(row, column, isHit);
        
        if (isHit) {
            System.out.println("Yes! You have hit a ship!");
            return;
        }
        
        System.out.println("No! You have missed the target!");
    }
    
    public boolean getStrike(int row, int column) {
        return shipBoard.getStrike(row, column);
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
