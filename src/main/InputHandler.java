package main;

import java.io.InputStream;
import java.util.Scanner;

public class InputHandler {
    
    Scanner scanner;

    public InputHandler(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
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
    
    public int[] askPlayerForNextStrikePosition() {
        do {
            String input = scanner.nextLine();
            if (isStrikePositionValid(input)) {
                return parseStrikePosition(input);
            }
            System.out.println(Messages.INVALID_STRIKE_POSITION);
        } while(true);
    }
        
    private boolean isStrikePositionValid(String input) {
        return input.matches(Constants.VALID_STRIKE_POSITION_REGEX);
    }
    
    private int[] parseStrikePosition(String input) {
        String[] inputParts = getStrikePositionParts(input);
        int column = mapColumnLetterToIndex(inputParts[0].charAt(0));
        int row = Integer.parseInt(inputParts[1]) - 1;
        return new int[]{column, row};
    }

    
    private String[] getStrikePositionParts(String input) {
        return new String[] {
                input.substring(0,1), 
                input.substring(1)
        };
    }
    
    public int[] askPlayerForShipPlacement() {
        do {
            String input = scanner.nextLine();
            if (isValidShipPositionInput(input)) {
                return parseShipPlacement(input);
            }
            System.out.println(Messages.INVALID_SHIP_PLACEMENT);
        } while (true);
    }
    
    private int[] parseShipPlacement(String input) {
        String[] inputParts = getShipPlacementParts(input);
        int column = mapColumnLetterToIndex(inputParts[0].charAt(0));
        int row = Integer.parseInt(inputParts[1]) - 1;
        char direction = Character.toUpperCase(inputParts[2].charAt(0));
        return new int[] {column, row, direction};
    }
    
    public boolean isValidShipPositionInput(String input) {
        return input.matches(Constants.VALID_INPUT_REGEX);
    }
    
    public String[] getShipPlacementParts(String input) {
        int length = input.length();
        return new String[] {
                input.substring(0, 1),
                input.substring(1, length-1),
                input.substring(length-1)
        };
    }
    
    public int mapColumnLetterToIndex(char columnLetter) {
        return Character.toUpperCase(columnLetter) - 'A';
    }
    
    public void close() {
        scanner.close();
    }
    
}
