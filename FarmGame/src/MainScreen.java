import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Font;

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
		
		/**
		 * Information Box
		 */
		JTextPane infoBox = new JTextPane();
		infoBox.setEditable(false);
		String introText = "Hi there! Welcome to your new farm! Here are all the activities you can do during the day. You can perform two important tasks a day before you get tired.";
		introText += "To learn more about a particular activity, please click the information button next to it!";
		infoBox.setText(introText);
		infoBox.setBounds(65, 359, 450, 93);
		frame.getContentPane().add(infoBox);
		
		/**
		 * Main label of Screen
		 */
		String farmName = game.getFarm().getName();
		String farmerName = game.getFarm().getFarmer().getName();
		String farmLabelString = String.format("%s, owned by %s", farmName, farmerName);
		JLabel farmNameLabel = new JLabel(farmLabelString);
		farmNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		farmNameLabel.setBounds(10, 11, 716, 14);
		frame.getContentPane().add(farmNameLabel);
		
		/**
		 * Current Day Label
		 */
		String currentDayString = String.format("Current Day : %d", game.getCurrentDays());
		JLabel currentDayLabel = new JLabel(currentDayString);
		currentDayLabel.setBounds(10, 56, 106, 14);
		frame.getContentPane().add(currentDayLabel);
		
		/**
		 * Remianing Days Label
		 * Determines how many days are left, and displays it
		 */
		int daysLeft = game.getRequiredDays() - game.getCurrentDays();
		String daysLeftString = String.format("Days Left : %d", daysLeft);
		JLabel daysLeftLabel = new JLabel(daysLeftString);
		daysLeftLabel.setBounds(609, 56, 89, 14);
		frame.getContentPane().add(daysLeftLabel);
		
		/**
		 * Main greeting label of screen
		 */
		JLabel greetingLabel = new JLabel("What would you like to do today?");
		greetingLabel.setBounds(257, 71, 228, 14);
		frame.getContentPane().add(greetingLabel);
		
		/**
		 * Button to advance to the next day
		 */
		JButton nextDayButton = new JButton("Next Day");
		nextDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.advanceDays();
				int daysLeft = game.getRequiredDays()-game.getCurrentDays();
				if (daysLeft == 0) {
					game.launchScoreScreen(screen);
				}
				currentDayLabel.setText(String.format("Current Day : %d", game.getCurrentDays()));
				daysLeftLabel.setText(String.format("Days Left: %d", game.getRequiredDays()-game.getCurrentDays()));
				infoBox.setText("Advanced one day");
			}
		});
		nextDayButton.setBounds(573, 373, 125, 45);
		frame.getContentPane().add(nextDayButton);
		
		/**
		 * Button to allow player to feed their animals
		 * Prompts the user to visit the store if they don't have any animals or food items
		 */
		JButton feedAnimalButton = new JButton("Feed Animals");
		feedAnimalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (game.getFarm().getAnimals().size() != 0 && game.getFarm().getAnimalItems().size() != 0) {
						game.launchFeedAnimalScreen(screen);
					} else if (game.getFarm().getAnimals().size() == 0) {
						String str = "Sorry, you don't have any animals currently. Please visit the store to buy some.";
						JOptionPane.showMessageDialog(frame, str, "Crop Information", JOptionPane.INFORMATION_MESSAGE);
					} else {
						String str = "Sorry, you don't have any items to feed your animals. Please visit the store to buy some.";
						JOptionPane.showMessageDialog(frame, str, "Crop Information", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (ActionCountException except) {
					infoBox.setText(except.getMessage());
				}
			}
		});
		feedAnimalButton.setBounds(65, 139, 127, 23);
		frame.getContentPane().add(feedAnimalButton);
		
		/**
		 * Button: Allows the player to play with their animals
		 * Prompts the user to visit the store if they don't have any animals
		 * Catches a thrown ActionCountException from the game if the player has performed all activities for the day
		 */
		JButton playAnimalsButton = new JButton("Play with Animals");
		playAnimalsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (game.getFarm().getAnimals().size() != 0) {
						game.launchSelectAnimalScreen(screen);
					} else {
						String str = "Sorry, you don't have any animals to play with. Please visit the store to buy some.";
						JOptionPane.showMessageDialog(frame, str, "Crop Information", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (ActionCountException except) {
					infoBox.setText(except.getMessage());
				}
			}
		});
		playAnimalsButton.setBounds(65, 215, 127, 23);
		frame.getContentPane().add(playAnimalsButton);
		
		/**
		 * Button: Allows the player to view their animal's status
		 * Calls the viewAnimalStatus method in the GameEnvironBasic class
		 * Displays the resulting string in a seperate pop-up window for space purposes
		 */
		JButton viewAnimalButton = new JButton("View Animals");
		viewAnimalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = game.viewAnimalStatus();
				JOptionPane.showMessageDialog(frame, str, "Animal Information", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		viewAnimalButton.setBounds(65, 289, 127, 23);
		frame.getContentPane().add(viewAnimalButton);
		
		/**
		 * Button: Allows the player to tend to their crops
		 * Closes current screen and launches a TendCropsScreen
		 */
		JButton tendCropsButton = new JButton("Tend to Crops");
		tendCropsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (game.getFarm().getCrops().size() != 0) {
						game.launchTendCropsScreen(screen);
					} else {
						String str = "Sorry, you don't have any crops currently. Please visit the store to buy some.";
						JOptionPane.showMessageDialog(frame, str, "Crop Information", JOptionPane.INFORMATION_MESSAGE);
						
					}
				} catch (ActionCountException except) {
					infoBox.setText(except.getMessage());
				}
			}
		});
		tendCropsButton.setBounds(228, 139, 128, 23);
		frame.getContentPane().add(tendCropsButton);
		
		/**
		 * Button: Allows the player to harvest any crops that are available
		 * Closes current screen and launches the SelectCropScreen
		 */
		JButton harvestCropsButton = new JButton("Harvest Crops");
		harvestCropsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (game.getFarm().getHarvestableCrops().size() != 0) {
						game.launchSelectCropScreen(screen);
					} else {
						String str = "Sorry, you don't have any crops that are ready to harvest. Please view their status to see how many days are left.";
						JOptionPane.showMessageDialog(frame, str, "Crop Information", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (ActionCountException except) {
					infoBox.setText(except.getMessage());
				}
			}
		});
		harvestCropsButton.setBounds(228, 215, 128, 23);
		frame.getContentPane().add(harvestCropsButton);
		
		/**
		 * Button: Allows the player to view the status of their crops
		 * Calls the viewCropStatus method of GameEnviroBasic
		 * Displays the resulting string in a pop-up dialog window
		 */
		JButton viewCropButton = new JButton("View Crops");
		viewCropButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = game.viewCropStatus();
				JOptionPane.showMessageDialog(frame, str, "Crop Information", JOptionPane.INFORMATION_MESSAGE);
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
		
		/**
		 * Button: Allows the player to view their owned items
		 * Calls the viewItems method of GameEnviroBasic
		 * Displays the resulting string in a new pop-up dialog box
		 */
		JButton viewItemButton = new JButton("View Items");
		viewItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemString = game.viewItems();
				JOptionPane.showMessageDialog(frame, itemString, "Your Items", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		viewItemButton.setBounds(401, 215, 117, 23);
		frame.getContentPane().add(viewItemButton);
		
		/**
		 * Button: Allows the player to view the status of their farm
		 * Calls the viewFarmStatus method of GameEnviroBasic
		 * Displays the resulting string in a pop-up dialog window
		 */
		JButton viewFarmButton = new JButton("View Farm");
		viewFarmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String farmString = game.viewFarmStatus();
				JOptionPane.showMessageDialog(frame, farmString, "Farm Information.", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		viewFarmButton.setBounds(559, 139, 117, 23);
		frame.getContentPane().add(viewFarmButton);
		
		/**
		 * Button: Allows the user to tend to their land
		 * Calls the tendToLand method of GameEnviroBasic
		 * Catches a thrown ActionCountException if the player has no actions left for the day
		 */
		JButton tendLandButton = new JButton("Tend to Land");
		tendLandButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					game.tendToLand();
					infoBox.setText("Tended to land; more crops and animals can be bought!");
				} catch (ActionCountException except) {
					infoBox.setText(except.getMessage());
				}
			}
		});
		tendLandButton.setBounds(559, 215, 117, 23);
		frame.getContentPane().add(tendLandButton);
		
		/**
		 * Each of the following buttons provided information about the main buttons
		 * 
		 */
		JButton tendLandInfo = new JButton("i");
		tendLandInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String infoText = "This allows you to tend to your farmland. Tending to your farmland increases the amount of crop and animals "
						+ "that can be placed on your land. It also keeps your animals happier for longer.\nDoing this counts as one of your two "
						+ "important activities.";
				infoBox.setText(infoText);
			}
		});
		tendLandInfo.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 12));
		tendLandInfo.setBounds(639, 249, 37, 23);
		frame.getContentPane().add(tendLandInfo);
		
		JButton viewFarmInfo = new JButton("i");
		viewFarmInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String infoText = "This allows you to have a look at everything you need to know about your farm: current money, crops growing, your animals, "
						+ "and the items you own.\nThis does not count as an important activity - you can do this as many times a day as you want.";
				infoBox.setText(infoText);
			}
		});
		viewFarmInfo.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 12));
		viewFarmInfo.setBounds(639, 173, 37, 23);
		frame.getContentPane().add(viewFarmInfo);
		
		JButton visitStoreInfo = new JButton("i");
		visitStoreInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String infoText = "This allows you to visit the county store. Here, you can buy anything your farm might need: crops, animals, and items are "
						+ "all on sale here.\nThis does not count as an important activity, so you can do this as many times as you want, as long as you have the money!";
				infoBox.setText(infoText);
			}
		});
		visitStoreInfo.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 12));
		visitStoreInfo.setBounds(481, 173, 37, 23);
		frame.getContentPane().add(visitStoreInfo);
		
		JButton viewItemInfo = new JButton("i");
		viewItemInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String infoText = "This allows you to view the information about all the items you currently own: what you have, and how much it'll help your "
						+ "animals or crops be healthy.\nThis does not count as one of your important activities.";
				infoBox.setText(infoText);
			}
		});
		viewItemInfo.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 12));
		viewItemInfo.setBounds(481, 249, 37, 23);
		frame.getContentPane().add(viewItemInfo);
		
		JButton harvestCropInfo = new JButton("i");
		harvestCropInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String infoText = "This allows you to harvest any crops you currently have, provided that they have been growing for long enough."
						+ " You can sell these crops for additional income for your farm."
						+ ".\nThis counts as one of your two important activities of the day.";
				infoBox.setText(infoText);
			}
		});
		harvestCropInfo.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 12));
		harvestCropInfo.setBounds(319, 249, 37, 23);
		frame.getContentPane().add(harvestCropInfo);
		
		JButton tendCropInfo = new JButton("i");
		tendCropInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String infoText = "This allows you to feed your crops with any items you may have. This will allow the crops to grow faster than before. "
						+ "You always have water available, so you can always water them if you don't have an item."
						+ "\nThis counts as one of your two important daily activities.";
				infoBox.setText(infoText);
			}
		});
		tendCropInfo.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 12));
		tendCropInfo.setBounds(319, 173, 37, 23);
		frame.getContentPane().add(tendCropInfo);
		
		JButton feedAnimalInfo = new JButton("i");
		feedAnimalInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String infoText = "This allows you to feed your animals with any suitable items you may have. This will keep your animals healthy, which helps you"
						+ "earn more money. You need both an animal, and an item to feed it with, in order to perform this action."
						+ "\nThis counts as one of your two important daily activities.";
				infoBox.setText(infoText);
			}
		});
		feedAnimalInfo.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 12));
		feedAnimalInfo.setBounds(155, 173, 37, 23);
		frame.getContentPane().add(feedAnimalInfo);
		
		JButton playAnimalInfo = new JButton("i");
		playAnimalInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String infoText = "Here, you can play with any animals that you might have. Playing with animals increases their happiness, and a happy "
						+ "animal means a happy wallet!\nThis counts as one of your two important daily activities.";
				infoBox.setText(infoText);
			}
		});
		playAnimalInfo.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 12));
		playAnimalInfo.setBounds(155, 249, 37, 23);
		frame.getContentPane().add(playAnimalInfo);
		
		JButton viewAnimalInfo = new JButton("i");
		viewAnimalInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String infoText = "Here you can see the information about all of the animals on your farm; what you have, how happy they are, and how healthy they are."
						+ "\nThis does not count as one of your important activities for the day.";
				infoBox.setText(infoText);
			}
		});
		viewAnimalInfo.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 12));
		viewAnimalInfo.setBounds(155, 323, 37, 23);
		frame.getContentPane().add(viewAnimalInfo);
		
		JButton viewCropInfo = new JButton("i");
		viewCropInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String infoText = "This button allows you to view the information of all the crops currently on your farm; whether they are ready for harvest,"
						+ "or how many days you have to wait until they are." 
						+ "\nThis does not count as one of your important activities for the day.";
				infoBox.setText(infoText);
			}
		});
		viewCropInfo.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 12));
		viewCropInfo.setBounds(319, 323, 37, 23);
		frame.getContentPane().add(viewCropInfo);
		
		JButton nextDayInfo = new JButton("i");
		nextDayInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String infoText = "This advances you to the next day. Useful when you've run out of things to do today.";
				infoBox.setText(infoText);
			}
		});
		nextDayInfo.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 12));
		nextDayInfo.setBounds(661, 429, 37, 23);
		frame.getContentPane().add(nextDayInfo);
	}
}
