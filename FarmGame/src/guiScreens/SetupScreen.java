package guiScreens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import farmsAndFarmer.*;
import gameLogic.ScreenManager;
import gameLogic.GameInformation;

import javax.swing.JCheckBox;

public class SetupScreen {

	/**
	 * The frame for the GUI screen.
	 */
	private JFrame frame;
	/**
	 * Textfield allowing user input for their farmer's name.
	 */
	private JTextField farmerNameField;
	/**
	 * A prompt for the user to input their information
	 */
	private JLabel startLabel;
	/**
	 * Textfield allowing user input for their farm's name.
	 */
	private JTextField farmNameField;
	/**
	 * The game logic responsible for managing the GUI screens.
	 */
	private ScreenManager manager;
	/**
	 * The logic class containing important informationa about the current game.
	 */
	private GameInformation game;

	/**
	 * Create the application.
	 */
	public SetupScreen(ScreenManager incomingManager, GameInformation gameInfo) {
		manager = incomingManager;
		game = gameInfo;
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
		manager.closeSetupScreen(this);
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
		 * Welcoming label.
		 */
		JLabel lblWelcome = new JLabel("Welcome!");
		lblWelcome.setBounds(321, 11, 87, 25);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lblWelcome);
		
		/**
		 * Textfield for user-inputted farmer names.
		 */
		farmerNameField = new JTextField();
		farmerNameField.setBounds(157, 118, 127, 20);
		frame.getContentPane().add(farmerNameField);
		farmerNameField.setColumns(10);
		
		/**
		 * Label prompting the user.
		 */
		startLabel = new JLabel("To start, please input your information");
		startLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		startLabel.setBounds(256, 47, 252, 25);
		frame.getContentPane().add(startLabel);
		
		/**
		 * Slider allowing user to select game length. 
		 * Minimum length of 5, and a maximum length of 25.
		 */
		JSlider gameLengthSlider = new JSlider();
		gameLengthSlider.setPaintLabels(true);
		gameLengthSlider.setPaintTicks(true);
		gameLengthSlider.setMajorTickSpacing(1);
		gameLengthSlider.setMinorTickSpacing(1);
		gameLengthSlider.setSnapToTicks(true);
		gameLengthSlider.setValue(15);
		gameLengthSlider.setMinimum(5);
		gameLengthSlider.setMaximum(25);
		gameLengthSlider.setBounds(10, 258, 380, 45);
		frame.getContentPane().add(gameLengthSlider);
		
		/**
		 * Textfield for user-inputted farmer names.
		 */
		farmNameField = new JTextField();
		farmNameField.setBounds(507, 118, 199, 20);
		frame.getContentPane().add(farmNameField);
		farmNameField.setColumns(10);
		
		/**
		 * Label prompting user to input age.
		 */
		JLabel farmerAgeLabel = new JLabel("Please enter your age:");
		farmerAgeLabel.setBounds(10, 164, 162, 25);
		frame.getContentPane().add(farmerAgeLabel);
		
		/**
		 * Label prompting user to input age.
		 */
		JLabel gameLengthLabel = new JLabel("Please select the game length:");
		gameLengthLabel.setBounds(10, 225, 205, 34);
		frame.getContentPane().add(gameLengthLabel);
		
		/**
		 * Label prompting user to input farm name.
		 */
		JLabel farmNameLabel = new JLabel("Please enter your farm's name:");
		farmNameLabel.setBounds(321, 116, 212, 25);
		frame.getContentPane().add(farmNameLabel);
		
		/**
		 * Label prompting user to select farm type.
		 */
		JLabel farmTypeLabel = new JLabel("Please select your farm's type:");
		farmTypeLabel.setBounds(321, 169, 261, 17);
		frame.getContentPane().add(farmTypeLabel);
		
		/**
		 * Label prompting user to input farmer name.
		 */
		JLabel farmerNameLabel = new JLabel("Please enter your name:");
		farmerNameLabel.setBounds(10, 115, 162, 26);
		frame.getContentPane().add(farmerNameLabel);
		
		/**
		 * Spinner to allow user to choose age.
		 */
		JSpinner farmerAgeSpinner = new JSpinner();
		farmerAgeSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		farmerAgeSpinner.setBounds(157, 166, 58, 20);
		frame.getContentPane().add(farmerAgeSpinner);
		
		/**
		 * Confirming whether random events are to be activated.
		 */
		JCheckBox randomEventBox = new JCheckBox("Random Events On");
		randomEventBox.setBounds(6, 327, 141, 23);
		frame.getContentPane().add(randomEventBox);
		
		/**
		 * Information box for farm types.
		 */
		JTextPane farmInfo = new JTextPane();
		farmInfo.setBounds(507, 210, 199, 101);
		frame.getContentPane().add(farmInfo);
		
		/**
		 * Combobox containing the famr types. Updates an information box with type details.
		 */
		JComboBox<String> farmTypeChoices = new JComboBox<String>();
		farmTypeChoices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int farmChoice = farmTypeChoices.getSelectedIndex();
				String displayString = "A basic farm. Nothing really special about it, but it's yours.";
				switch(farmChoice) {
				case 0:
					displayString = "A basic farm. Nothing really special about it, but it's yours.";
					break;
				case 1:
					displayString = "A small loan from your parents means you start with extra money.";
					break;
				case 2:
					displayString = "Your soil is great for growing crops; they grow a day quicker now.";
					break;
				case 3:
					displayString = "Your animals love it here! They will start happier when they join your farm.";
					break;
				}
				farmInfo.setText(displayString);
			}
		});
		farmTypeChoices.addItem("Basic Farm");
		farmTypeChoices.addItem("Trust Fund Farm");
		farmTypeChoices.addItem("Children of the Corn Farm");
		farmTypeChoices.addItem("George Orwell's Animal Farm");
		farmTypeChoices.setBounds(507, 167, 199, 22);
		frame.getContentPane().add(farmTypeChoices);
		
		/**
		 * Button to confirm the user's choices. Checks the farmer name is appropriate length and does not contain special characters. Creates a new farm using details.
		 */
		JButton confirmButton = new JButton("CONFIRM");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (farmerNameField.getText().isBlank()) {
					JOptionPane.showMessageDialog(frame, "Please enter a name for your farmer",  "Insufficient Farmer Name", JOptionPane.ERROR_MESSAGE);
				} else if (farmerNameField.getText().length() > 15 || farmerNameField.getText().length() < 3 || farmerNameField.getText().matches("[a-zA-Z]*") == false) {
					String prompt = "Please enter a name for your farmer that is between 3 and 15 characters long, and only contains letters.";
					JOptionPane.showMessageDialog(frame, prompt,  "Insufficient Farmer Name", JOptionPane.ERROR_MESSAGE);
				} else if (farmNameField.getText().isBlank()) {
					JOptionPane.showMessageDialog(frame, "Please enter a name for your farm",  "Insufficient Farm Name", JOptionPane.ERROR_MESSAGE);
				} else {
					String farmerName = farmerNameField.getText();
					int farmerAge = (int) farmerAgeSpinner.getValue();
					String farmName = farmNameField.getText();
					String farmType = (String) farmTypeChoices.getSelectedItem();
					int numDays = gameLengthSlider.getValue();
					boolean randomEventsOn = randomEventBox.isSelected();
					String confirmString = "You have a farmer named " + farmerName + " on a farm of type " + farmType + " called " + farmName + ".\nYou will play for "+numDays + " days.\nIs this correct?";
					int choice = JOptionPane.showConfirmDialog(frame, confirmString,  "Confirmation", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						Farmer farmer = new Farmer(farmerName, farmerAge);
						Farm farm = null;
						//Get the type of farm the user wants
						int farmChoice = farmTypeChoices.getSelectedIndex();
						switch(farmChoice) {
						case 0:
							farm = new FarmBasic(farmName, farmer);
							break;
						case 1:
							farm = new FarmRich(farmName, farmer);
							break;
						case 2:
							farm = new FarmGoodSoil(farmName, farmer);
							break;
						case 3:
							farm = new FarmGoodAnimal(farmName, farmer);
							break;
						}
						
						game.setFarm(farm);
						game.setRequiredDays(numDays);
						game.setRandomEventsOn(randomEventsOn);
						finishedWindow();
					}
				}
			}
		});
		confirmButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		confirmButton.setBounds(321, 380, 110, 39);
		frame.getContentPane().add(confirmButton);
		
		/**
		 * Label informing farm information.
		 */
		JLabel farmInfoLabel = new JLabel("Farm Information:");
		farmInfoLabel.setBounds(321, 210, 149, 26);
		frame.getContentPane().add(farmInfoLabel);
	}
}
