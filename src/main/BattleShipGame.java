package main;

import java.util.Scanner;

public class BattleShipGame {
    
    private final Scanner scanner;
    
    public BattleShipGame(Scanner scanner) {
        this.scanner = scanner;
    }
    
    private void startHumanVsHuman() {
        boolean isGameOver = false;
        int roundCounter = 0;

        String[] names = askPlayersForNames();

        HumanPlayer playerOne = initializeHumanPlayer(names[0]);
        HumanPlayer playerTwo = initializeHumanPlayer(names[1]);

        System.out.println(Messages.BATTLE_BEGIN);

        HumanPlayer[] players = {playerOne, playerTwo};

        while (!isGameOver) {
            System.out.printf(Messages.ROUND_SEPARATOR, ++roundCounter);
            // Alternate turns between players
            for (int i = 0; i < players.length; i++) {
                isGameOver = handlePlayerTurn(players[i],      // attacker
                                              players[1 - i]); // defender
                if (isGameOver) break;
            }
        }
    }
    
    private String[] askPlayersForNames() {
        System.out.println(Messages.NAMES_ASKING_INTRO);
        String name1 = askPlayerForName(Messages.PLAYER_ONE_NAME_ASKING, 
                                       Constants.PLAYER_ONE_DEFAULT_NAME);
        String name2 = askPlayerForName(Messages.PLAYER_TWO_NAME_ASKING, 
                                       Constants.PLAYER_TWO_DEFAULT_NAME,
                                       name1);
        return new String[] {name1, name2};
    }
    
    private String askPlayerForName(String message, String defaultName) {
        System.out.print(message);
        String playerName = scanner.nextLine();
        System.out.println();
        return playerName.isEmpty() ? defaultName : playerName;
    }
    
    private String askPlayerForName(String message, 
                                   String defaultName, 
                                   String existingName) {
        String playerName;
        do {
            playerName = askPlayerForName(message, defaultName);
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
    
    private boolean handlePlayerTurn(HumanPlayer attacker, 
                             HumanPlayer defender) {
        showPlayerBoards(attacker);
        int[] nextStrike = askPlayerForNextStrike(attacker);

        boolean isStrikeSuccessful = defender.getStrike(nextStrike[0], nextStrike[1]);
        attacker.updateStrikeBoard(nextStrike[0], nextStrike[1], isStrikeSuccessful);
        
        return checkGameOver(attacker, defender);
    }
    
    private void showPlayerBoards(HumanPlayer player) {
        System.out.printf(Messages.PLAYER_HEADER, player);
        System.out.print(Messages.SEPARATOR);
        player.showBoards();
    }
    
    private int[] askPlayerForNextStrike(HumanPlayer player) {
        return player.enterNextStrike(scanner);
    }
    
    private boolean checkGameOver(HumanPlayer attacker, HumanPlayer defender) {
        if (defender.allShipsSank()) {
            System.out.printf(Messages.PLAYER_WINS, attacker);
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            BattleShipGame game = new BattleShipGame(scanner);
            System.out.println(Messages.WELCOME_TO_BATTLESHIP);
            System.out.println(Messages.INSTRUCTIONS);
            game.startHumanVsHuman();
        } finally {
            scanner.close();
        }
        
    }
}
