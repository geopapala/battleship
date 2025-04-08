package main;

public class HumanPlayer {

    private final String name;
    private ShipBoard shipBoard;
    private StrikeBoard strikeBoard;
    
    public HumanPlayer(String name) {
        this.name = name;
        shipBoard = new ShipBoard(Constants.BOARD_SIZE);
        strikeBoard = new StrikeBoard(Constants.BOARD_SIZE);
    }
    
    public void enterAllShipsManually(InputHandler inputHandler) {
        shipBoard.enterAllShipsManually(inputHandler);
    }
    
    public int[] enterNextStrike(InputHandler inputHandler) {
        System.out.printf(Messages.NEXT_STRIKE_POSITION, name);
        System.out.println();
        while (true) {
            int[] nextStrikePosition = inputHandler.askPlayerForNextStrikePosition();
            if (isPositionAlreadyStricken(nextStrikePosition[0], nextStrikePosition[1])) {
                System.out.println(Messages.ALREADY_STRICKEN_POSITION);
                continue;
            }
            return nextStrikePosition;
        }
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
    
    @Override
    public String toString() {
        return String.format("Player: %s", name);
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
