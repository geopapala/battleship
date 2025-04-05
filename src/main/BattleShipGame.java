package main;

import java.util.Scanner;

public class BattleShipGame {
    
    private final Scanner scanner;
    private InputHandler inputHandler;
    
    public BattleShipGame(Scanner scanner) {
        this.scanner = scanner;
        this.inputHandler = new InputHandler(scanner);
    }
    
    public void startHumanVsHuman() {
        boolean isGameOver = false;
        int roundCounter = 0;

        String[] names = inputHandler.askPlayersForNames();

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
    
    protected HumanPlayer initializeHumanPlayer(String name) {
        System.out.printf(Messages.SHIP_PLACEMENT_INSTRUCTIONS, name);
        return new HumanPlayer(name, scanner);
    }
    
    protected boolean handlePlayerTurn(HumanPlayer attacker, 
                                       HumanPlayer defender) {
        showPlayerBoards(attacker);
        int[] nextStrike = askPlayerForNextStrike(attacker);

        boolean isStrikeSuccessful = defender.getStrike(nextStrike[0], nextStrike[1]);
        attacker.updateStrikeBoard(nextStrike[0], nextStrike[1], isStrikeSuccessful);
        
        return checkGameOver(attacker, defender);
    }
    
    protected void showPlayerBoards(HumanPlayer player) {
        System.out.println(player);
        System.out.print(Messages.SEPARATOR);
        player.showBoards();
    }
    
    protected int[] askPlayerForNextStrike(HumanPlayer player) {
        return player.enterNextStrike(scanner);
    }
    
    protected boolean checkGameOver(HumanPlayer attacker, HumanPlayer defender) {
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
