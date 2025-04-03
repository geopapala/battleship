package main;

import java.util.Scanner;

public class BattleShip {
    
    private final Scanner scanner;
    
    public BattleShip(Scanner scanner) {
        this.scanner = scanner;
    }
    
    private void HumanVsHuman() {
        boolean isGameOver = false;
        int roundCounter = 0;

        String[] names = enterPlayersNames();

        HumanPlayer player1 = initializeHumanPlayer(names[0]);
        HumanPlayer player2 = initializeHumanPlayer(names[1]);

        System.out.println(Messages.BATTLE_BEGIN);

        HumanPlayer[] players = {player1, player2};

        while (!isGameOver) {
            System.out.printf(Messages.ROUND_SEPARATOR, ++roundCounter);

            // Alternate turns between players
            for (int i = 0; i < players.length; i++) {
                isGameOver = playTurn(players[i], players[1 - i]);
                if (isGameOver) break;
            }
        }
    }
    
    private String[] enterPlayersNames() {
        System.out.println(Messages.NAMES_ASKING_INTRO);
        String name1 = enterPlayerName(Messages.PLAYER1_NAME_ASKING, 
                                       Constants.PLAYER1_DEFAULT_NAME);
        String name2 = enterValidatedPlayerName(Messages.PLAYER2_NAME_ASKING, 
                                                Constants.PLAYER2_DEFAULT_NAME,
                                                name1);
        return new String[] {name1, name2};
    }
    
    private String enterPlayerName(String message, String defaultName) {
        System.out.print(message);
        
        String playerName = scanner.nextLine();
        System.out.println();
        
        return playerName.isEmpty() ? defaultName : playerName;
    }
    
    private String enterValidatedPlayerName(String message, 
                                            String defaultName, 
                                            String existingName) {
        String playerName;
        do {
            playerName = enterPlayerName(message, defaultName);
            if (playerName.equals(existingName)) {
                System.out.println(Messages.PLAYERS_WITH_SAME_NAME);
            }
        } while (playerName.equals(existingName));
        return playerName;
    }
    
    private HumanPlayer initializeHumanPlayer(String name) {
        System.out.printf(Messages.SHIP_PLACEMENT_INSTRUCTIONS, name);
        return new HumanPlayer(name, scanner);
    }
    
    private boolean playTurn(HumanPlayer currentPlayer, 
                             HumanPlayer opponentPlayer) {
        System.out.println("   PLAYER " + currentPlayer);
        System.out.print(Messages.SEPARATOR);
        currentPlayer.printBoards();
        int[] nextStrike = currentPlayer.enterNextStrike(scanner);
        boolean isPlayerStrikeHit = opponentPlayer.getStrike(nextStrike[0], nextStrike[1]);
        currentPlayer.update(nextStrike[0], nextStrike[1], isPlayerStrikeHit);
        
        if (opponentPlayer.allShipsSank()) {
            System.out.println(currentPlayer + " WINS");
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BattleShip game = new BattleShip(scanner);
        System.out.println(Messages.WELCOME_TO_BATTLESHIP);
        System.out.println(Messages.INSTRUCTIONS);
        game.HumanVsHuman();
    }
}
