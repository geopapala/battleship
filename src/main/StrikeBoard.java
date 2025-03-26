package main;

public class StrikeBoard {
    
    private int N = Constants.BOARD_SIZE;
    private int[][] board = new int[N][N];
    
    public void addStrike(int column, int row, boolean isHit) {
        if (isHit) {
            board[row][column] = 1;
            return;
        }
        board[row][column] = -1;
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
                    System.out.print("~ ");
                }
                if (board[row][col] == 1) {
                    System.out.print("* ");
                }
                if (board[row][col] == -1) {
                    System.out.print("o ");
                }
            }
            System.out.println("|");
        }
        System.out.println("   -----------------------");
    }
    
    public int[][] getBoard() {
        return board;
    }
}
