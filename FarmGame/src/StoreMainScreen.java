import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StoreMainScreen {

	private JFrame frame;
	private GameEnviroBasic game;

	/**
	 * Create the application.
	 */
	public StoreMainScreen(GameEnviroBasic incomingGame) {
		game = incomingGame;
		initialize();
		frame.setVisible(true);
	}
	
	/**
	 * Close the window
	 */
	public void closeWindow() {
		frame.dispose();
	}
	
	/**
	 * Hands control back to the main class
	 */
	public void finishedWindow() {
		game.closeStoreMainScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		StoreMainScreen screen = this;
		
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
				game.closeStoreMainScreen(screen);
				game.launchStoreAnimalScreen();
			}
		});
		btnNewButton.setBounds(311, 111, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Crops");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
				game.launchStoreCropScreen();
			}
		});
		btnNewButton_1.setBounds(311, 152, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Items");
		btnNewButton_2.setBounds(311, 194, 117, 29);
		frame.getContentPane().add(btnNewButton_2);
		
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
