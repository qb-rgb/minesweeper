/*
 * Square.java
 */

// ----- Package ----- //

package minesweeper;

/**
 * Square class. Square in the minesweeper game.
 * 
 * @author Baert Quentin
 */

public class Square {
	// ----- Attributes ----- //
	
	/**
	 * Content of the square.
	 */
	@SuppressWarnings("unused")
	private SquareContent content;
	
	/**
	 * Location of the square.
	 */
	private Location location;
	
	/**
	 * Determine if the square is undermined.
	 */
	private boolean undermined;
	
	/**
	 * Determine if the square is discovered.
	 */
	private boolean discovered;
	
	/**
	 * Determine if the square has a flag on.
	 */
	private boolean flag;
	
	// ----- Methods ----- //
	
	/**
	 * Constructor of the Square class.
	 * A new construct square is not undermined, not discovered and has no flag on.
	 * @param l 	location of the square. 
	 */
	public Square (Location l) {
		this.content 	= SquareContent.EMPTY;
		this.location 	= l;
		this.undermined = false;
		this.discovered = false;
		this.flag 		= false;
	}
	
	/**
	 * Return the location of this square.
	 * @return location of this square.
	 */
	public Location getLocation () {
		return this.location;
	}
	
	/**
	 * Determine if this square is undermined.
	 * @return true is this square is undermined, false otherwise.
	 */
	public boolean isUndermined () {
		return this.undermined;
	}
	
	/**
	 * Determine if this square is discovered.
	 * @return true if the square is discovered, false otherwise.
	 */
	public boolean isDiscovered () {
		return this.discovered;
	}
	
	/**
	 * Tell if this square has a flag on.
	 * @return true if this square has a flag on, false otherwise.
	 */
	public boolean hasFlag () {
		return this.flag;
	}
	
	/**
	 * Put a mine under this square.
	 */
	public void mine () {
		this.content 	= SquareContent.MINE;
		this.undermined = true;
	}
	
	/**
	 * Discover this square.
	 */
	public void discover () {
		this.discovered = true;
	}
	
	/**
	 * Put a flag on this square if it has not one.
	 */
	public void putFlag () {
		if (!flag) {
			this.flag = true;
		}
	}
	
	/**
	 * Remove the flag on this square if it has one.
	 */
	public void removeFlag () {
		if (flag) {
			this.flag = false;
		}
	}
	
	/**
	 * Return a description of this square.
	 * @return description of this square.
	 */
	public String toString () {
		String res = "";
		
		if (this.isUndermined()) {
			res += "Undermined";
		}
		else {
			res += "Empty";
		}
		
		return res + " square.";
	}
	
	/**
	 * A square is equal to an other square which have the same location and the same content.
	 * @return true if o is equal to this square, false otherwise.
	 */
	public boolean equals (Object o) {
		if (o instanceof Square) {
			Square s = (Square) o;
			
			return s.getLocation().equals(this.location) && (s.isUndermined() == this.undermined);
		}
		else {
			return false;
		}
	}
} // Square
