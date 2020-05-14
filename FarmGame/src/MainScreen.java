import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class MainScreen {

	private JFrame frame;
	/**
	 * The game environment
	 */
	private GameEnviroBasic game;
	/**
	 * A variable that allows the current screen to be passed in action listeners.
	 */
	private MainScreen screen = this;

	/**
	 * Create the application.
	 */
	public MainScreen(GameEnviroBasic incomingGame) {
		game = incomingGame;
		initialize();
		frame.setVisible(true);
	}
	
	/**
	 * Closes the GUI setup window.
	 * Called from the game environment.
	 */
	public void closeWindow() {
		frame.dispose();
	}
	
	/**
	 * Notifies the game environment that the screen must be closed.
	 */
	public void finishedWindow() {
		game.closeMainScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane infoBox = new JTextPane();
		infoBox.setBounds(65, 359, 291, 93);
		frame.getContentPane().add(infoBox);
		
		String farmName = game.getFarm().getName();
		String farmerName = game.getFarm().getFarmer().getName();
		String farmLabelString = String.format("%s, owned by %s", farmName, farmerName);
		JLabel farmNameLabel = new JLabel(farmLabelString);
		farmNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		farmNameLabel.setBounds(10, 11, 716, 14);
		frame.getContentPane().add(farmNameLabel);
		
		String currentDayString = String.format("Current Day : %d", 0);
		JLabel currentDayLabel = new JLabel(currentDayString);
		currentDayLabel.setBounds(10, 56, 106, 14);
		frame.getContentPane().add(currentDayLabel);
		
		int daysLeft = game.getRequiredDays();
		String daysLeftString = String.format("Days Left: %d", daysLeft);
		JLabel daysLeftLabel = new JLabel(daysLeftString);
		daysLeftLabel.setBounds(609, 56, 89, 14);
		frame.getContentPane().add(daysLeftLabel);
		
		JLabel greetingLabel = new JLabel("What would you like to do today?");
		greetingLabel.setBounds(257, 71, 228, 14);
		frame.getContentPane().add(greetingLabel);
		
		JButton nextDayButton = new JButton("Next Day");
		nextDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.advanceDays();
				int daysLeft = game.getRequiredDays()-game.getCurrentDays();
				if (daysLeft == 0) {
					finishedWindow();
				}
				currentDayLabel.setText(String.format("Current Day : %d", game.getCurrentDays()));
				daysLeftLabel.setText(String.format("Days Left: %d", game.getRequiredDays()-game.getCurrentDays()));
				infoBox.setText("Advanced one day");
			}
		});
		nextDayButton.setBounds(593, 406, 106, 23);
		frame.getContentPane().add(nextDayButton);
		
		JButton feedAnimalButton = new JButton("Feed Animals");
		feedAnimalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getFarm().getAnimals().size() != 0 && game.getFarm().getAnimalItems().size() != 0) {
					game.launchFeedAnimalScreen(screen);
				} else if (game.getFarm().getAnimals().size() == 0) {
					infoBox.setText("Sorry, you don't have any animals currently. Please visit the store to buy some.");
				} else {
					infoBox.setText("Sorry, you don't have any items to feed your animals. Please visit the store to buy some.");
				}
			}
		});
		feedAnimalButton.setBounds(65, 139, 127, 23);
		frame.getContentPane().add(feedAnimalButton);
		
		JButton playAnimalsButton = new JButton("Play with Animals");
		playAnimalsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getFarm().getAnimals().size() != 0) {
					game.launchSelectAnimalScreen(screen);
				} else {
					infoBox.setText("Sorry, you don't have any animals to play with. Please visit the store to buy some.");
				}
			}
		});
		playAnimalsButton.setBounds(65, 215, 127, 23);
		frame.getContentPane().add(playAnimalsButton);
		
		JButton viewAnimalButton = new JButton("View Animals");
		viewAnimalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = game.viewAnimalStatus();
				infoBox.setText(str);
			}
		});
		viewAnimalButton.setBounds(65, 289, 127, 23);
		frame.getContentPane().add(viewAnimalButton);
		
		JButton tendCropsButton = new JButton("Tend to Crops");
		tendCropsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getFarm().getCrops().size() != 0) {
					game.launchTendCropsScreen(screen);
				} else {
					infoBox.setText("Sorry, you don't have any crops currently. Please visit the store to buy some.");
				}
			}
		});
		tendCropsButton.setBounds(228, 139, 128, 23);
		frame.getContentPane().add(tendCropsButton);
		
		JButton harvestCropsButton = new JButton("Harvest Crops");
		harvestCropsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getFarm().getHarvestableCrops().size() != 0) {
					game.launchSelectCropScreen(screen);
				} else {
					infoBox.setText("Sorry, you don't have any crops that are ready to harvest. Please view their status to see how many days are left.");
				}
			}
		});
		harvestCropsButton.setBounds(228, 215, 128, 23);
		frame.getContentPane().add(harvestCropsButton);
		
		JButton viewCropButton = new JButton("View Crops");
		viewCropButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = game.viewCropStatus();
				infoBox.setText(str);
			}
		});
		viewCropButton.setBounds(228, 289, 128, 23);
		frame.getContentPane().add(viewCropButton);
		
		/*
		 * Visit Store Button
		 */
		JButton visitStoreButton = new JButton("Visit Store");
		visitStoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.closeMainScreen(screen);
				game.launchStoreMainScreen();
			}
		});
		visitStoreButton.setBounds(401, 139, 117, 23);
		frame.getContentPane().add(visitStoreButton);
		
		JButton viewItemButton = new JButton("View Items");
		viewItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.viewItems();
			}
		});
		viewItemButton.setBounds(401, 215, 117, 23);
		frame.getContentPane().add(viewItemButton);
		
		JButton viewFarmButton = new JButton("View Farm");
		viewFarmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String farmString = game.viewFarmStatus();
				infoBox.setText(farmString);
			}
		});
		viewFarmButton.setBounds(559, 139, 117, 23);
		frame.getContentPane().add(viewFarmButton);
		
		JButton tendLandButton = new JButton("Tend to Farmland");
		tendLandButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.tendToLand();
				infoBox.setText("Tended to land; more crops and animals can be bought!");
			}
		});
		tendLandButton.setVerticalAlignment(SwingConstants.BOTTOM);
		tendLandButton.setBounds(559, 215, 117, 23);
		frame.getContentPane().add(tendLandButton);
	}
}
