/*
 * Button.java
 */

// ----- Package ----- //

package minesweeper.game.graphic;

// ----- Imports ----- //

import java.awt.Graphics;

import javax.swing.JButton;

import minesweeper.Location;

/**
 * Button class. Personalize button for the minesweeper game (graphic mode).
 * 
 * @author Baert Quentin
 */

@SuppressWarnings("serial")
public class Button extends JButton {
	// ----- Attributes ----- //
	
	/**
	 * Determine if the button has been clicked.
	 */
	private boolean click;
	
	/**
	 * Text appeared on the button. 
	 */
	private String text;
	
	/**
	 * Location of the button.
	 */
	private Location location;
	
	// ----- Methods ----- //
	
	/**
	 * Constructor of the Button class.
	 * @param text		text appeared on the button.
	 * @param location	location of the button.
	 */
	public Button (String text, Location location) {
		super(text);
		
		this.click 	      = false;
		this.text 	      = text;
		this.location  	  = location;
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
	}
	
	/**
	 * Getter of the click attribute.
	 */
	public boolean isClicked () {
		return this.click;
	}
	
	/**
	 * Getter of the location attribute.
	 */
	public Location getButtonLocation () {
		return this.location;
	}
	
	/**
	 * Setter of the click attribute.
	 */
	public void setClick (boolean bool) {
		this.click = bool;
	}
	
	public String getText () {
		return this.text;
	}
	
	/**
	 * Setter of the text attribute.
	 */
	public void setText (String text) {
		this.text = text;
	}
}
