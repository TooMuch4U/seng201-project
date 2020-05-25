package guiScreens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import gameLogic.GameInformation;
import gameLogic.ScreenManager;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StoreMainScreen {

	/**
	 * The frame for the GUI screen.
	 */
	private JFrame frame;
	/**
	 * The manager of the GUI screens.
	 */
	private ScreenManager manager;

	/**
	 * Create the GUI screen.
	 * @param incomingManager The ScreenManager object that will launch and close this screen.
	 * @param gameInfo The logic class containing information about the current game.
	 */
	public StoreMainScreen(ScreenManager incomingManager, GameInformation gameInfo) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	/**
	 * Close the window.
	 */
	public void closeWindow() {
		frame.dispose();
	}
	
	/**
	 * Hands control back to the main class.
	 */
	public void finishedWindow() {
		manager.closeStoreMainScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel buyPromptLabel = new JLabel("What would you like to buy?");
		buyPromptLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		buyPromptLabel.setHorizontalAlignment(SwingConstants.CENTER);
		buyPromptLabel.setBounds(203, 36, 339, 59);
		frame.getContentPane().add(buyPromptLabel);
		
		/*
		 * Animals button
		 */
		JButton animalButton = new JButton("Animals");
		animalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
				manager.launchStoreAnimalScreen();
			}
		});
		animalButton.setBounds(311, 111, 117, 29);
		frame.getContentPane().add(animalButton);
		
		JButton cropsButton = new JButton("Crops");
		cropsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
				manager.launchStoreCropScreen();
			}
		});
		cropsButton.setBounds(311, 152, 117, 29);
		frame.getContentPane().add(cropsButton);
		
		/*
		 * Item button
		 */
		JButton itemsButton = new JButton("Items");
		itemsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
				manager.launchStoreItemScreen();
			}
		});
		itemsButton.setBounds(311, 194, 117, 29);
		frame.getContentPane().add(itemsButton);
		
		/*
		 * Back Button
		 */
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		backButton.setBounds(83, 419, 117, 29);
		frame.getContentPane().add(backButton);
	}

}
