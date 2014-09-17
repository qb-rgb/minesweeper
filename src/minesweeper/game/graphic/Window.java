/*
 * Window.java 
 */

// ----- Package ----- //

package minesweeper.game.graphic;

// ----- Imports ----- //

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import minesweeper.Board;
import minesweeper.Location;
import minesweeper.Square;

/**
 * Window class. Window in the minesweeper game (graphic mode).
 * 
 * @author Baert Quentin
 */

@SuppressWarnings("serial")
public class Window extends JFrame {
	// ----- Attributes ----- //
	
	/**
	 * Board of the game.
	 */
	private Board board;
	
	/**
	 * Width of the board.
	 */
	private int width;
	
	/**
	 * Height of the board.
	 */
	private int height;
	
	/**
	 * Counter of square discovered.
	 */
	private Integer counter;
	
	/**
	 * Print the counter on the window.
	 */
	private JLabel counterLabel;
	
	/**
	 * List of the buttons of the minesweeper.
	 */
	private List<Button> buttonsList;
	
	/**
	 * Determine if the game is win.
	 */
	private boolean win;
	
	/**
	 * Determine if the game is loose.
	 */
	private boolean loose;

	// ----- Methods ----- //
	
	/**
	 * Constructor of the Window class.
	 * @param width		width of the window.
	 * @param height	height of the window.
	 */
	public Window (int width, int height) {
		this.width 		  = width;
		this.height       = height;
		this.board	 	  = new Board(width, height);
		this.counter 	  = 0;
		this.counterLabel = new JLabel(this.counter.toString());
		this.buttonsList  = new ArrayList<Button>();
		this.win		  = false;
		this.loose		  = false;
		
		// Board initialization
		int nbMine = (20 * (width * height)) / 100;
		this.board.init(nbMine);
		
		// Components
		JPanel 		  north = new JPanel();
		JPanel    container = new JPanel();
		JPanel 	    buttons = new JPanel();
		JButton 	restart = new JButton();
		
		// Window's parameters
		this.setTitle("Minesweeper");
		this.setSize(50 * width, 50 * height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		// Layout
		north.setLayout(new BorderLayout());
		container.setLayout(new BorderLayout());
		buttons.setLayout(new GridLayout(width, height, -5, -5));
		
		
		// Addition of the buttons
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < height; x++) {
				Location l = new Location(x, y);
				Button   b = new Button("", l);
				
				b.addMouseListener(new ButtonListener(b));
				
				buttons.add(b);
				buttonsList.add(b);
			}
		}
		
		// Label
		this.counterLabel.setHorizontalAlignment(JLabel.CENTER);
		
		// Restart button
		restart.setText("Restart");
		restart.addMouseListener(new RestartButtonListener());
		
		// Positioning
		north.add(counterLabel, BorderLayout.CENTER);
		north.add(restart, BorderLayout.WEST);
		container.add(north, BorderLayout.NORTH);
		container.add(buttons, BorderLayout.CENTER);
		
		this.setContentPane(container);
		
		this.setVisible(true);
	}
	
	/**
	 * Return the button at the location passed in parameter.
	 * @param l 	location of the button.
	 * @return button at location l.
	 */
	private Button getButtonAt (Location l) {
		Button res = null;
		
		for (Button b : this.buttonsList) {
			if (b.getButtonLocation().equals(l)) {
				res = b;
			}
		}
		
		return res;
	}
	
	// ----- Internal classes ----- //
	
	/**
	 * ButtonListener class. Listener of the minesweeper buttons.
	 * 
	 * @author Baert Quentin
	 */
	class ButtonListener implements MouseListener {
		// ----- Attribute ----- //
		
		/**
		 * Button listened.
		 */
		private Button button;
		
		// ----- Methods ----- //
		
		/**
		 * Constructor of the ButtonListener class.
		 * @param b		button listened.
		 */
		public ButtonListener (Button button) {
			this.button = button;
		}
		
		/**
		 * Reveal the square around the button if it has a "0" value.
		 * @param b		button which potentially have a "0" value.
		 */
		public void nextTo (Button b) {
			Location 	 l = b.getButtonLocation();
			
			if (board.describeSquareAt(l).equals(" 0 ")) {
				int 	 	 x = l.getX();
				int 	 	 y = l.getY();
				
				for (int i = x - 1; i <= x + 1; i++) {
					for (int j = y - 1; j <= y + 1; j++) {
						Location lBis = new Location(i, j);
						Button 	 bBis = getButtonAt(lBis);
						
						if ((bBis != null) && !bBis.isClicked()) {
							Square s = board.getSquareAt(lBis);
							
							s.discover();
								
							bBis.setClick(true);
							bBis.setText(board.describeSquareAt(lBis));
							bBis.doClick();
							
							// Update of the Label
							if (!loose & !win) {
								counter++;
								counterLabel.setText(counter.toString());
							}
							
							if (board.win()) {
								win = true;
							}
							
							if (win && !loose) {
								counterLabel.setText("YOU WIN !");
							}
							
							this.nextTo(bBis);
						}
					}
				}
			}
		}
		
		// ----- MouseListener methods ----- //

		public void mouseClicked (MouseEvent e) {
			if (!this.button.isClicked()) {
				Location l = this.button.getButtonLocation();
				
				// Right click
				if (e.getButton() == MouseEvent.BUTTON3) {
					Square s = board.getSquareAt(l);
					
					if (s.hasFlag()) {
						s.removeFlag();
					}
					else {
						s.putFlag();
					}
					
					this.button.doClick();
				}
				// Left click
				else {
					Square s = board.getSquareAt(l);
					
					s.discover();
						
					this.button.setClick(true);
					
					this.nextTo(this.button);
					
					// Update of the Label
					counter++;
					counterLabel.setText(counter.toString());
					
					if (s.isUndermined()) {
						loose = true;
					}
					else if (board.win()) {
						win = true;
					}
				}
				
				if (win || loose) {
					if (win) {
						counterLabel.setText("YOU WIN !");
					}
					else if (loose) {
						counterLabel.setText("BOOM !");
					}
					
					for (Button b : buttonsList) {
						if (!b.isClicked()) {
							Location lBis = b.getButtonLocation();
							
							board.getSquareAt(lBis).discover();
							
							b.setClick(true);
							b.setText(board.describeSquareAt(lBis));
							b.doClick();
						}
					}
				}
				
				this.button.setText(board.describeSquareAt(l));
			}
		}

		public void mousePressed (MouseEvent e) {}

		public void mouseReleased (MouseEvent e) {}

		public void mouseEntered (MouseEvent e) {}

		public void mouseExited (MouseEvent e) {}
	}
	
	/**
	 * RestartButtonListener class. Listener of the restart button.
	 * 
	 * @author Baert Quentin
	 */
	class RestartButtonListener implements MouseListener {
		// ----- MouseListener methods ----- //
		
		public void mouseClicked (MouseEvent e) {
			for (Button b : buttonsList) {
				if (b.getText() != "") {
					b.setText("");
					b.setClick(false);
					b.doClick();
				}
			}
			
			board = new Board(width, height);
			
			// Board initialization
			int nbMine = (25 * (width * height)) / 100;
			board.init(nbMine);
			
			win   = false;
			loose = false;
			
			// Label
			counter = 0;
			counterLabel.setText(counter.toString());
		}

		public void mousePressed (MouseEvent e) {}

		public void mouseReleased (MouseEvent e) {}

		public void mouseEntered (MouseEvent e) {}

		public void mouseExited (MouseEvent e) {}
	}
}
