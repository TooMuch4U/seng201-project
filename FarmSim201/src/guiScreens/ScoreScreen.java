package guiScreens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.UIManager;

import farmsAndFarmer.Farm;
import gameLogic.EndScreen;
import gameLogic.GameInformation;
import gameLogic.ScreenManager;

public class ScoreScreen {
	
	/**
	 * The frame for the GUI screen.
	 */
	private JFrame frame;
	/**
	 * The information about the game, which all functions can be accessed from.
	 */
	private GameInformation game;
	/**
	 * The manager of the GUI screens.
	 */
	private ScreenManager manager;
	
	/**
	 * Create the GUI screen.
	 * @param incomingManager The ScreenManager object that will launch and close this screen.
	 * @param gameInfo The logic class containing information about the current game.
	 */
	public ScoreScreen(ScreenManager incomingManager, GameInformation gameInfo) {
		manager = incomingManager;
		game = gameInfo;
		initialize();
		frame.setVisible(true);
	}
	
	/**
	 * Closes the GUI setup window. 
	 * Called from the screen manager.
	 */
	public void closeWindow() {
		frame.dispose();
	}
	
	/**
	 * Notifies the game environment that the screen must be closed and a new game started.
	 */
	public void finishedWindow() {
		manager.closeScoreScreen(this);
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/**
		 * Label: Displays "game over"
		 */
		JLabel gameOverLabel = new JLabel("Game Over");
		gameOverLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameOverLabel.setBounds(10, 11, 716, 50);
		frame.getContentPane().add(gameOverLabel);
		
		/**
		 * Button to allow the user to finish the game without starting a new game
		 */
		JButton exitGameButton = new JButton("Exit Game");
		exitGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		exitGameButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		exitGameButton.setBounds(200, 375, 125, 45);
		frame.getContentPane().add(exitGameButton);
		
		/**
		 * Button allowing player to finish this game and start a new game
		 */
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		newGameButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newGameButton.setBounds(425, 375, 125, 45);
		frame.getContentPane().add(newGameButton);
		
		/**
		 * Retrieves the final score, and displays it for the user
		 */
		EndScreen end = new EndScreen(game.getFarm());
		String score = end.displayScore();
		JLabel scoreLabel = new JLabel("Final Score:\n " + score);
		scoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setBounds(10, 72, 716, 33);
		frame.getContentPane().add(scoreLabel);
		
		/**
		 * Displays the final information about the player's farm
		 */
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		Farm farm = game.getFarm();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		String endText = String.format("The story of %s's farm, %s, ends.", farm.getFarmer().getName(), farm.getName());
		//Get the farm's profit, and update the end text depending on a profit or loss was made
		double farmProfit = farm.getProfit();
		String profitOrLoss = null;
		if (farmProfit >= 0) {
			profitOrLoss = "made";
		} else {
			profitOrLoss = "lost";
			farmProfit = farmProfit*-1;
		}
		endText += String.format("\nYou toiled on your farm for %d days, and %s a total of %.2f", game.getCurrentDays()-1, profitOrLoss, farmProfit);
		
		textPane.setText(endText);
		textPane.setBackground(UIManager.getColor("Button.background"));
		textPane.setBounds(10, 113, 716, 239);
		frame.getContentPane().add(textPane);
	}
}
