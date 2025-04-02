package main;

public class StrikeBoard {
    
    private int N = Constants.BOARD_SIZE;
    private int[][] board = new int[N][N];
    
    public void addStrike(int column, int row, boolean isHit) {
        if (board[row][column] == 0) { 
            board[row][column] = isHit ? 1 : -1;
        }
    }
    
    public String getSymbol(int column, int row) {
        if (board[row][column] == 1) return "*";  // Hit
        if (board[row][column] == -1) return "o"; // Miss
        if (board[row][column] == 0) return "~";  // Water
        return "?"; // Should never happen, just a safeguard
    }
    
    public int[][] getBoard() {
        return board;
    }
}
