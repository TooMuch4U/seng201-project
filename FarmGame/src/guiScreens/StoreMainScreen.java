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
	 * Create the application.
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
		
		JLabel lblNewLabel = new JLabel("What would you like to buy?");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(203, 36, 339, 59);
		frame.getContentPane().add(lblNewLabel);
		
		/*
		 * Animals button
		 */
		JButton btnNewButton = new JButton("Animals");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
				manager.launchStoreAnimalScreen();
			}
		});
		btnNewButton.setBounds(311, 111, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Crops");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
				manager.launchStoreCropScreen();
			}
		});
		btnNewButton_1.setBounds(311, 152, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
		
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
		JButton btnNewButton_3 = new JButton("Back");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnNewButton_3.setBounds(83, 419, 117, 29);
		frame.getContentPane().add(btnNewButton_3);
	}

}
