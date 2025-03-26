package main;

public class Messages {

    public static final String SEPARATOR = 
            "--------------------------------------------------------------------------------\n";
    
    public static final String WELCOME_TO_BATTLESHIP = SEPARATOR
          + "BATTLESHIP\n"
          + "\n"
          + "Welcome to Battleship, a classic game that was invented almost a century ago.\n"
          + "For more info visit https://en.wikipedia.org/wiki/Battleship_(game)\n";
    
    public static final String INSTRUCTIONS = SEPARATOR
          + "Instructions\n"
          + "\n"
          + "1. Each player enters their name when prompt and secretly places their ships on\n"
          + "   their own board. Ships vary in size and can be placed horizontally or\n"
          + "   vertically, but not diagonally.\n"
          + "2. Players alternate turns striking coordinates (like 'C 4') to guess where\n"
          + "   the opponent's ships are.\n"
          + "3. The opponent announces whether the guess was a 'hit' (a part of a ship) or\n"
          + "   a 'miss' (empty water). Your guesses are marked on your strike board."
          + "4. Once all the coordinates of a ship are hit, that ship is 'sunk'. Keep going\n"
          + "   until all ships of the enemy are sunk.\n"
          + "5. The first player to sink all of their opponent’s ships wins the game!\n";
    
    public static final String NAMES_ASKING_INTRO = SEPARATOR
          + "Welcome aboard, Captains! Before we embark on this epic naval battle, let me\n"
          + "have your names!\n";
    
    public static final String PLAYER1_NAME_ASKING = 
            "Captain of the 1st fleet, what is your name? ";
    
    public static final String PLAYER2_NAME_ASKING = 
            "And you, Captain of the 2nd fleet, what shall we call you? ";
        
    public static final String SHIP_PLACEMENT_INSTRUCTIONS = SEPARATOR
          + "Captain %s place your ships on your board. For each ship provide its\n"
          + "starting position and direction in the following format:\n"
          + "   [Column A-J] [Row 1-10] [H for Horizontal or V for Vertical].\n"
          + "For example, 'A 5 H' means the ship starts at column A, row 5, and is placed\n"
          + "horizontally. Remember, if a ship doesn’t fit, I’ll ask you to try again.\n"
          + "\n"
          + "The board is a 10x10 grid like the one below where the symbol '~' means water\n";
    
    public static final String ENTER_SHIP_POSITION = SEPARATOR
          + "Enter the position and the direction of your %s (size %d):";
    
    public static final String SHIP_COLLIDING_WITH_OTHER = 
            "There's already another ship blocking this direction. Let's try again\n!";

    public static final String SHIP_DONT_FIT = 
            "The ship doesn't fit in this location. No worries - try again!\n";

    public static final String INVALID_SHIP_PLACEMENT = 
            "The position or direction you entered isn't valid. Please make sure that:\n"
          + "1. You have left spaces between the entries (e.g., 'A 5 H').\n"
          + "2. The column is between A and J\n"
          + "3. The row is between 1 and 10\n"
          + "4. The direction is either H (Horizontal) or V (Vertical), and\n"
          + "Don't worry, just give it another go!\n";
    
    public static final String BATTLE_BEGIN = SEPARATOR
          + "Captains let the battleship begin!\n"
          + "\n";
    
    public static final String NEXT_STRIKE_POSITION = SEPARATOR
          + "Captain %s enter your next strike position formatted as [A-J] [1-10]:";
    
    public static final String ALREADY_STRICKEN_POSITION = 
            "You have entered an already stricken position. Try again!\n";
    
    public static final String INVALID_STRIKE_POSITION = 
            "You have entered an invalid position. Try again!\n";
    
    public static final String HIT = "Yes! You have hit a ship!\n";
    
    public static final String MISS = "No! You have missed the target!\n";
}
