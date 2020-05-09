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

public class SetupScreen {

	private JFrame frame;
	private JTextField farmerNameField;
	private JLabel startLabel;
	private JTextField farmNameField;
	private GameEnviroBasic game;

	/**
	 * Create the application.
	 */
	public SetupScreen(GameEnviroBasic incomingGame) {
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
		game.closeSetupScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome!");
		lblWelcome.setBounds(321, 11, 87, 25);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lblWelcome);
		
		farmerNameField = new JTextField();
		farmerNameField.setBounds(157, 118, 127, 20);
		frame.getContentPane().add(farmerNameField);
		farmerNameField.setColumns(10);
		
		startLabel = new JLabel("To start, please input your information");
		startLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		startLabel.setBounds(256, 47, 252, 25);
		frame.getContentPane().add(startLabel);
		
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
		
		farmNameField = new JTextField();
		farmNameField.setBounds(507, 118, 199, 20);
		frame.getContentPane().add(farmNameField);
		farmNameField.setColumns(10);
		
		JLabel farmerAgeLabel = new JLabel("Please enter your age:");
		farmerAgeLabel.setBounds(10, 164, 162, 25);
		frame.getContentPane().add(farmerAgeLabel);
		
		JLabel gameLengthLabel = new JLabel("Please select the game length:");
		gameLengthLabel.setBounds(10, 225, 205, 34);
		frame.getContentPane().add(gameLengthLabel);
		
		JLabel farmNameLabel = new JLabel("Please enter your farm's name:");
		farmNameLabel.setBounds(321, 116, 212, 25);
		frame.getContentPane().add(farmNameLabel);
		
		JLabel farmTypeLabel = new JLabel("Please select your farm's type:");
		farmTypeLabel.setBounds(321, 169, 261, 17);
		frame.getContentPane().add(farmTypeLabel);
		
		JLabel farmerNameLabel = new JLabel("Please enter your name:");
		farmerNameLabel.setBounds(10, 115, 162, 26);
		frame.getContentPane().add(farmerNameLabel);
		
		JSpinner farmerAgeSpinner = new JSpinner();
		farmerAgeSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		farmerAgeSpinner.setBounds(157, 166, 58, 20);
		frame.getContentPane().add(farmerAgeSpinner);
		
		JTextPane farmInfo = new JTextPane();
		farmInfo.setBounds(507, 210, 199, 101);
		frame.getContentPane().add(farmInfo);
		
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
		
		JButton confirmButton = new JButton("CONFIRM");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (farmerNameField.getText().isBlank()) {
					JOptionPane.showMessageDialog(frame, "Please enter a name for your farmer",  "Insufficient Farmer Name", JOptionPane.ERROR_MESSAGE);
				} else if (farmNameField.getText().isBlank()) {
					JOptionPane.showMessageDialog(frame, "Please enter a name for your farm",  "Insufficient Farm Name", JOptionPane.ERROR_MESSAGE);
				} else {
					String farmerName = farmerNameField.getText();
					int farmerAge = (int) farmerAgeSpinner.getValue();
					String farmName = farmNameField.getText();
					String farmType = (String) farmTypeChoices.getSelectedItem();
					int numDays = gameLengthSlider.getValue();
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
						finishedWindow();
					}
				}
			}
		});
		confirmButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		confirmButton.setBounds(321, 380, 110, 39);
		frame.getContentPane().add(confirmButton);
		
		JLabel farmInfoLabel = new JLabel("Farm Information:");
		farmInfoLabel.setBounds(321, 210, 149, 26);
		frame.getContentPane().add(farmInfoLabel);
	}
}
