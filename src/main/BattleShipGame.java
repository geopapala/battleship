package main;

public class BattleShipGame {
    
    private InputHandler inputHandler;
    
    public BattleShipGame(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }
    
    public void startHumanVsHuman() {
        boolean isGameOver = false;
        int roundCounter = 0;

        String[] names = inputHandler.askPlayersForNames();

        HumanPlayer playerOne = initializeHumanPlayer(names[0]);
        HumanPlayer playerTwo = initializeHumanPlayer(names[1]);
        HumanPlayer[] players = {playerOne, playerTwo};

        System.out.println(Messages.BATTLE_BEGIN);
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
        HumanPlayer player = new HumanPlayer(name);
        player.enterAllShipsManually(inputHandler);
        return player;
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
        return player.enterNextStrike(inputHandler);
    }
    
    protected boolean checkGameOver(HumanPlayer attacker, HumanPlayer defender) {
        if (defender.allShipsSank()) {
            System.out.printf(Messages.PLAYER_WINS, attacker);
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler(System.in);
        try {
            BattleShipGame game = new BattleShipGame(inputHandler);
            System.out.println(Messages.WELCOME_TO_BATTLESHIP);
            System.out.println(Messages.INSTRUCTIONS);
            game.startHumanVsHuman();
        } finally {
            inputHandler.close();
        }
        
    }
}
