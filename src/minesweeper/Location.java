/*
 * Location.java
 */

// ----- Package ----- //

package minesweeper;

/**
 * Location class. Location in the minesweeper game board.
 *  
 * @author Baert Quentin
 */

public class Location {
	// ----- Attributes ----- //
	
	/**
	 * Abscissa of the location (horizontal scale).
	 */
	private int x;
	
	/**
	 * Ordinate of the location (vertical scale).
	 */
	private int y;
	
	// ----- Methods ----- //
	
	/**
	 * Constructor of the Location class.
	 * @param x 	abscissa of this location.
	 * @param y 	ordinate of this location. 
	 */
	public Location (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Give the abscissa of this location.
	 * @return abscissa of this location.
	 */
	public int getX () {
		return this.x;
	}
	
	/**
	 * Give the ordinate of this location.
	 * @return ordinate of this location.
	 */
	public int getY () {
		return this.y;
	}
	
	/**
	 * Give a description of this location.
	 * @return description of this location.
	 */
	public String toString () {
		return "(" + this.getX() + ", " + this.getY() + ")";
	}
	
	/**
	 * A location is equal to an other location which have the same abscissa and the same ordinate.
	 * @return true if o is equal to this location, false otherwise.
	 */
	public boolean equals (Object o) {
		if (o instanceof Location) {
			Location l = (Location) o;
			
			return (this.getX() == l.getX()) && (this.getY() == l.getY());
		}
		else {
			return false;
		}
	}
} // Location
