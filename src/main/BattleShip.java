package main;

import java.util.Scanner;

public class BattleShip {
    
    public static void main(String[] args) {
        BattleShip game = new BattleShip();
        System.out.println(Messages.WELCOME_TO_BATTLESHIP);
        System.out.println(Messages.INSTRUCTIONS);
        game.HumanVsHuman();
    }

    private void HumanVsHuman() {
        String defaultName1 = "A";
        String defaultName2 = "B";
        boolean isGameOver = false;
        int roundCounter = 0;
        
        System.out.println(Messages.NAMES_ASKING_INTRO);

        String name1 = enterPlayerName(Messages.PLAYER1_NAME_ASKING, 
                                       defaultName1);
        
        String name2 = enterPlayerName(Messages.PLAYER2_NAME_ASKING, 
                                       defaultName2);
        
        System.out.printf(Messages.SHIP_PLACEMENT_INSTRUCTIONS, name1);
        
        HumanPlayer player1 = new HumanPlayer(name1);
        
        System.out.printf(Messages.SHIP_PLACEMENT_INSTRUCTIONS, name2);

        
        HumanPlayer player2 = new HumanPlayer(name2);
        
        System.out.println(Messages.BATTLE_BEGIN);
        
        while (!isGameOver) {
            System.out.println(player1 + " boards before assault");
            player1.printBoards();
            int[] nextStrike1 = player1.enterNextStrike(System.in);
            boolean isPlayer1StrikeHit = player2.getStrike(nextStrike1[0], nextStrike1[1]);
            player1.update(nextStrike1[0], nextStrike1[1], isPlayer1StrikeHit);
            if (player2.allShipsSank()) {
                isGameOver = true;
                System.out.println(player1 + " WINS");
                continue;
            }
            
            System.out.println(player2 + " boards before assault");
            player2.printBoards();
            int[] nextStrike2 = player2.enterNextStrike(System.in);
            boolean isPlayer2StrikeHit = player1.getStrike(nextStrike2[0], nextStrike2[1]);
            player2.update(nextStrike2[0], nextStrike2[1], isPlayer2StrikeHit);
            if (player1.allShipsSank()) {
                isGameOver = true;
                System.out.println(player2 + " WINS");
                continue;
            }
        }
    }
    
    private String enterPlayerName(String message, String defaultName) {
        Scanner scanner = new Scanner(System.in);
        String playerName = "";

        System.out.print(message);
        playerName = scanner.nextLine();
        System.out.println();
        
        if (playerName.isEmpty()) {
            return defaultName;
        }    
        return playerName;
    }
    
    private void printLines(int num) {
        for (int i = 0; i < num; i++) {
            System.out.println();
        }
    }
}
