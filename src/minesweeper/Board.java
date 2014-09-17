/*
 * Board.java
 */

// ----- Package ----- //

package minesweeper;

// ----- Imports ----- //

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Board class. Board in the minesweeper game.
 * 
 * @author Baert Quentin
 */

public class Board {
	// ----- Attributes ----- //
	
	/**
	 * Squares on the board.
	 */
	private Square[][] squares;
	
	/**
	 * Width of the board.
	 */
	private int width;
	
	/**
	 * Height of the board.
	 */
	private int height;
	
	// ----- Methods ----- //
	
	/**
	 * Constructor of the Board class. A new construct board only have empty square.
	 * @param width 	width of the board.
	 * @param height 	height of the board.
	 */
	public Board (int width, int height) {
		this.width 	= width;
		this.height = height;
		
		Square[][] res = new Square[width][height];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				res[x][y] = new Square(new Location(x, y));
			}
		}
		
		this.squares = res;
	}
	
	/**
	 * Return the square at the location past in parameter.
	 * @param l 	location of the square.
	 * @return 		square at the location l.
	 */
	public Square getSquareAt (Location l) {
		int x = l.getX();
		int y = l.getY();

		return this.squares[x][y];
	}
	
	/**
	 * Initialize this board with the number of mines past in parameter.
	 * @param nbOfMines 	number of mines put on this board.
	 * @exception 			If there is more nbOfMines than squares on the board.
	 */
	public void init (int nbOfMines) throws IllegalArgumentException {
		if ((this.width * this.height) < nbOfMines) {
			throw new IllegalArgumentException("There can not be more mines than squares on the board.");
		}
		// Normal configuration
		else {
			Random alea = new Random();

			for (int i = 1; i <= nbOfMines; i++) {
				int 	 x = alea.nextInt(this.width);
				int 	 y = alea.nextInt(this.height);
				Location l = new Location(x, y);
				Square 	 s = this.getSquareAt(l);

				if (!s.isUndermined()) {
					s.mine();
				}
				else {
					i--;
				}
			}
		}
	}
	
	/**
	 * Determine if the board is a win one.
	 * @return true if the board is a win one, false otherwise.
	 */
	public boolean win () {
		int n1 = 0; // For the number of square which are not discovered.
		int n2 = 0; // For the number of square which are undermined.
		
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				Location l = new Location(x, y);
				Square s = this.getSquareAt(l);
				
				if (!s.isDiscovered()) {
					n1++;
				}
				
				if (s.isUndermined()) {
					n2++;
				}
			}
		}
		
		return n1 == n2;
	}
	
	/**
	 * Describe the square at the location past in parameter.
	 * @param l 	location of the displayed square.
	 * @return 		description of the square at location l.
	 */
	public String describeSquareAt (Location l) {
		Square s = this.getSquareAt(l);

		if (s.isDiscovered()) {
			if (!s.isUndermined()) {
				int 		 		   x = l.getX();
				int 		 		   y = l.getY();
				int 				   n = 0; // For the number of mines around the square.
				List<Location> locations = new ArrayList<Location>();
				
				// North
				if ((y - 1) >= 0) {
					locations.add(new Location(x, y - 1));
				}

				// North east
				if (((y - 1) >= 0) && ((x + 1) < this.width)) {
					locations.add(new Location(x + 1, y - 1));
				}

				// East
				if ((x + 1) < this.width) {
					locations.add(new Location(x + 1, y));
				}

				// South east
				if (((x + 1) < this.width) && ((y + 1) < this.height)) {
					locations.add(new Location(x + 1, y + 1));
				}

				// South
				if ((y + 1) < this.height) {
					locations.add(new Location(x, y + 1));
				}

				// South west
				if (((x - 1) >= 0) && ((y + 1) < this.height)) {
					locations.add(new Location(x - 1, y + 1));
				}

				// West
				if ((x - 1) >= 0) {
					locations.add(new Location(x - 1, y));
				}

				// North west
				if (((x - 1) >= 0) && ((y - 1) >= 0)) {
					locations.add(new Location(x - 1, y - 1));
				}

				for (Location location : locations) {
					if (this.getSquareAt(location).isUndermined()) {
						n++;
					}
				}

				return " " + n + " ";
			}
			else {
				return " * ";
			}
		}
		else if (s.hasFlag()) {
			return " |>";
		}
		else {
			return " ? ";
		}
	}
	
	/**
	 * Display this board.
	 * @return display of this board.
	 */
	public void display () {
		// Clues
		System.out.print("    ");
		
		for (int n = 0; n < this.height; n++) {
			System.out.print(" " + n + " ");
		}
		
		System.out.println();
		
		// High border
		System.out.print("    ");
		
		for (int i = 0; i < this.width; i++) {
			System.out.print(" _ ");
		}
		
		System.out.println();
		
		for (int y = 0; y < this.height; y++) {
			// Clues
			System.out.print(" " + y + " ");
			
			// Inside the board
			for (int x = 0; x < this.width; x++) {
				Location l = new Location(x, y);
				
				if (x == 0) {
					System.out.print("|");
				}
				
				System.out.print(this.describeSquareAt(l));
				
				if (x == this.height - 1) {
					System.out.print("|");
				}
			}
			
			System.out.println();
		}
		
		// Bottom border
		System.out.print("    ");
		
		for (int i = 0; i < this.width; i++) {
			System.out.print(" _ ");
		}
	}
	
	/**
	 * Display this board with all the square discovered.
	 */
	public void displayClear () {
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				this.getSquareAt(new Location(x, y)).discover();
			}
		}
		
		this.display();
	}
} // Board
