/*
 * MainTextMod.java 
 */

// ----- Package ----- //

package minesweeper.game;

// ----- Imports ----- //

import java.util.Scanner;
import minesweeper.Board;
import minesweeper.Location;
import minesweeper.Square;

/**
 * MainTextMod class. Class to game at the mineweeper game.
 * 
 * @author Baert Quentin
 */

public class MainTextMod {
	// ----- Attributes ----- //
	
	/**
	 * Board of the game.
	 */
	private Board board;
	
	/**
	 * Determine if the gamer can continue to play.
	 */
	private boolean loose;
	
	/**
	 * Determine if the gamer win the game.
	 */
	private boolean win;
	
	// ----- Methods ----- //
	
	/**
	 * Contructor of the MainTextMod class.
	 * @param b 	board of the game.
	 */
	private MainTextMod (Board b) {
		this.board = b;
		this.loose = false;
		this.win   = false;
	}
	
	/**
	 * Play a turn of the game.
	 * The gamer choose if he wants to discover a square or put a flag on it.
	 */
	private void playTurn () {
		int choice = -1;
		Scanner sc = new Scanner(System.in);
		
		while (!((choice == 1) || (choice == 2))) {
			System.out.println();
			this.board.display();
			System.out.println("\n\n");
			System.out.println("----------------------------------------\n");
			System.out.println("What do you want to do ?");
			System.out.println("1. Discover a square");
			System.out.println("2. Put a flag on a square");
			System.out.print("Type 1 or 2 : ");
			
			choice = sc.nextInt();
		}
		
		System.out.println();
		
		System.out.println("For what location ?");
		System.out.print("Abscissa : ");
		
		int x = sc.nextInt();
		
		System.out.print("Ordinate : ");
		
		int y = sc.nextInt();
		System.out.println();
		
		if (choice == 1) {
			Square s = this.board.getSquareAt(new Location(x, y));
			
			if (s.isUndermined()) {
				this.loose = true;
				sc.close();
			}
			else {
				s.discover();
			}
		}
		else {
			this.board.getSquareAt(new Location(x, y)).putFlag();
		}
		
		if (this.board.win()) {
			this.win = true;
			sc.close();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main (String[] args) {
		// Rules
		System.out.println("\t\t---------- MINESWEEPER ----------\n");
		System.out.println("Here's your grid.");
		System.out.println("Be prudent, if you choose an undermined" +
				" square, you instantaneously loose (because you're dead in fact, you" +
				" know, the mine, boom etc...)");
		System.out.println("I recommend you to put some flag on the square that you " +
				"suspect to be undermined.");
		System.out.println("Good game, and good luck. ;)\n\n");
		
		Board b 		 = new Board(5, 5);
		b.init(5);
		MainTextMod game = new MainTextMod(b);
		
		while (!((game.win) || (game.loose))) {
			game.playTurn();
		}
		
		System.out.println("----------------------------------------\n");
		
		if (game.win) {
			System.out.println("Bravo ! You finish this board !\n");
			System.out.println("Clean board : \n");
			
			b.displayClear();
		}
		else {
			System.out.println("BOOM !!!\n");
			System.out.println("Sorry, you loose...\n");
			System.out.println("Clean board : \n");
			
			b.displayClear();
		}
	}

} // MainTextMod
