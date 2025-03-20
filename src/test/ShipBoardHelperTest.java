package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Constants;
import main.ShipBoardHelper;

class ShipBoardHelperTest {
	
	private ShipBoardHelper helper;
	
	@BeforeEach
	void setUp() {
		helper = new ShipBoardHelper();
	}
	
	@AfterEach
	void tearDown() {
		helper = null;
	}

	@Test
	void testResolveShipType() {
		
		int[] ships = {5, 4, 3, 3, 2, 0};
		String[] shipType = {"Aircraft Carrier", 
							 "Battleship", 
							 "Cruiser", 
							 "Submarine", 
							 "Destroyer", 
							 "Check ship size. There is no such a ship"};
		
		for(int i = 0; i < ships.length; i++) {
			assertEquals(shipType[i], helper.resolveShipType(ships[i], i));
		}
	}
	
	@Test
	void testMapLetterToColumnIndex() {
		
		String columnUpperLetters = "ABCDEFGHIJ";
		for(int i = 0; i < columnUpperLetters.length(); i++) {
			assertEquals(i, helper.mapLetterToColumnIndex(columnUpperLetters.charAt(i)));
		}
		
		String columnLowerLetters = "abcdefghij";
		for(int i = 0; i < columnUpperLetters.length(); i++) {
			assertEquals(i, helper.mapLetterToColumnIndex(columnLowerLetters.charAt(i)));
		}
	}

	@Test
	void testIsInputValid() {

		String[] invalidInputs = {"abcd", "A B C D",
								  "k 5 H", "K 5 H", 
								  "a 15 H", "A -15 H", "A 0 H",  
								  "A 5 k", "A 5 K"
								  };
		
		String[] validInputs = {"a 5 H", "a 5 h", 
								"A 5 H", "A 5 h", 
								"a 5 V", "a 5 v", "a 10 v", 
								"A 5 V", "A 5 v"
								};

		for(int i = 0; i < invalidInputs.length; i++) {
			assertEquals(false, helper.isInputValid(invalidInputs[i].split(" ")));
		}
		
		for(int i = 0; i < validInputs.length; i++) {
			assertEquals(true, helper.isInputValid(validInputs[i].split(" ")));
		}
	}
	
	@Test
	void testIsShipFit() {
		
		// Ship of size 5 gets out of limits if positioned in I 5 Horizontally
		assertEquals(false, helper.isShipFit(8, 5, 'H', 5));
		
		// Ship of size 5 gets out of limits if positioned in A 9 Vertically
		assertEquals(false, helper.isShipFit(0, 8, 'V', 5));
		
		// Ship of size 2 fits if positioned in I 5 Horizontally
		assertEquals(true, helper.isShipFit(8, 5, 'H', 2));
		
		// Ship of size 2 fits if positioned in A 9 Vertically
		assertEquals(true, helper.isShipFit(0, 8, 'V', 2));
	}

	@Test
	void testIsShipCollidingWithOther() {
		
		int[][] board = new int[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
		
		int columnA = 0;
		int columnD = 3;
		int columnF = 5;
		int columnH = 7;
		int row4 = 3;
		int row5 = 4;
		char horizontalDirection = 'H';
		char verticalDirection = 'V';
		int shipSize4 = 4;
		int shipSize5 = 5;
		
		helper.placeShip(columnA, row5, horizontalDirection, shipSize5, board);
		
		assertEquals(true, 
				 helper.isShipCollidingWithOther(columnD,
						 						 row5,
						 						 horizontalDirection,
						 						 shipSize4,
						 						 board)
				 );
		
		assertEquals(false, 
				 helper.isShipCollidingWithOther(columnF,
						 						 row5,
						 						 horizontalDirection,
						 						 shipSize4,
						 						 board)
				 );
		
		assertEquals(true, 
					 helper.isShipCollidingWithOther(columnD,
							 						 row4,
							 						 verticalDirection,
							 						 shipSize4,
							 						 board)
					 );
		
		assertEquals(false, 
					 helper.isShipCollidingWithOther(columnH,
							 						 row4,
							 						 verticalDirection,
							 						 shipSize4,
							 						 board)
					 );
	}

	@Test
	void testPlaceShip() {
		
		int[][] board = new int[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
		
		int columnA = 0;
		
		int row5 = 4;
		char verticalDirection = 'V';
		int shipSize5 = 5;
		
		int row1 = 0;
		char horizontalDirection = 'H';
		int shipSize4 = 4;
		
		helper.placeShip(columnA, row5, verticalDirection, shipSize5, board);
		for(int i = row5; i < row5 + shipSize5; i++) {
			assertEquals(shipSize5, board[i][columnA]);
		}
		
		helper.placeShip(columnA, row1, horizontalDirection, shipSize4, board);
		for(int i = columnA; i < columnA + shipSize4; i++) {
			assertEquals(shipSize4, board[row1][i]);
		}
	}
}
