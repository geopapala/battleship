package main;

public class ShipBoard {
    private final int N;
    private final int[] ships = {5, 4, 3, 3, 2};
    private final int[] shipsHealth;
    private final int[][] board;
    private final boolean[][] strickenBoardPositions;
    private boolean lastStrikeSankShip;
    private Helper helper;
        
    public ShipBoard(int boardSize) {
        this.N = boardSize;
        shipsHealth = ships.clone();
        board = new int[N][N];
        strickenBoardPositions = new boolean[N][N];
        lastStrikeSankShip = false;
        helper = new Helper();
    };
    
    public void enterAllShipsManually(InputHandler inputHandler) {
        for (int shipId = 1; shipId <= ships.length; shipId++) {
            enterShipManually(shipId, inputHandler);
        }
    }
    
    private void enterShipManually(int id, InputHandler inputHandler) {
        int startingColumn;
        int startingRow;
        char direction;
        String shipType = helper.resolveShipType(id);
        int shipSize = ships[id - 1];
        
        System.out.printf(Messages.ENTER_SHIP_POSITION, shipType, shipSize);
        System.out.println();
        
        while (true) {
            
            int[] shipPlacement = inputHandler.askPlayerForShipPlacement();
            startingColumn = shipPlacement[0];
            startingRow = shipPlacement[1];
            direction = (char) shipPlacement[2];
        
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
        printBoard();
    }
    
    public boolean getStrike(int column, int row) {
        lastStrikeSankShip = false; //TODO Review 
        
        strickenBoardPositions[row][column] = true;
        
        if (board[row][column] == 0) {
            return false;
        }
        
        int shipId = board[row][column];
        int shipRemainingPositions = --shipsHealth[shipId - 1];
        
        if (shipRemainingPositions == 0) {
            lastStrikeSankShip = true;
            System.out.println("*** TO BE REVIEWED - Last strike have sank your "
                    + helper.resolveShipType(shipId));
        }
        return true;
    }
    
    public boolean allShipsSank() {
        for (int shipHealth: shipsHealth) {
            if (shipHealth != 0) {
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
            System.out.printf("%2d | ", row + 1);
            for (int col = 0; col < N; col++) {
                System.out.print(getSymbol(row, col) + " ");
            }
            System.out.println("|");
        }
        System.out.println("   -----------------------");
        System.out.println();
    }
    
    public String getSymbol(int row, int col) {
        if (board[row][col] == 0) {
            return strickenBoardPositions[row][col] ? "o" : "~";
        }
        return strickenBoardPositions[row][col] 
                ? "*" : String.valueOf(helper.resolveShipTypeInitialLetter(board[row][col]));
    }

    public int[][] getBoard() {
        return this.board;
    }
}
