/*
 * SquareContent.java
 */

// ----- Package ----- //

package minesweeper;

/**
 * SquareContent enum. Square content in the minesweeper game.
 * 
 * @author Baert Quentin
 */

public enum SquareContent {
	// ----- Enum ----- //
	
	MINE, EMPTY;
	
	// ----- Methods ----- //
	
	/**
	 * Give the char representation of the content.
	 * @return char representation of the content.
	 */
	public char getChar () {
		if (this == SquareContent.MINE) {
			return 'M';
		}
		else {
			return ' ';
		}
	}
} // SquareContent
