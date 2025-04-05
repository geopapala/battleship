package main;

import java.util.Scanner;

public class InputHandler {
    
    Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public String[] askPlayersForNames() {
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
    
}
