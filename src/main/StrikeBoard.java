package main;

public class StrikeBoard {
    
    // Static constants (shared across all instances)
    private static final int WATER = 0;
    private static final int HIT = 1;
    private static final int MISS = -1;
    
    private final int N;
    private final int[][] board;
    
    
    public StrikeBoard(int boardSize) {
        this.N = boardSize;
        this.board = new int[N][N];
    }
    
    public void addStrike(int column, int row, boolean isHit) {
        if (board[row][column] == WATER) { 
            board[row][column] = isHit ? HIT : MISS;
        }
    }
    
    public String getSymbol(int row, int column) {
        return switch (board[row][column]) {
            case HIT -> "*";
            case MISS -> "o";
            default -> "~"; // Water
        };
    }
    
    public int[][] getBoard() {
        return board;
    }
}
