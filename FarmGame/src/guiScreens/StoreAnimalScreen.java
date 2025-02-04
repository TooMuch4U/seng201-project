package guiScreens;

import java.awt.EventQueue;
import animals.*;
import crops.Crop;
import farmsAndFarmer.Farm;
import gameLogic.GameInformation;
import gameLogic.ScreenManager;
import gameLogic.StoreLogic;
import items.Item;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.event.ActionEvent;

public class StoreAnimalScreen {

	/**
	 * The frame for the GUI screen.
	 */
	private JFrame frame;
	/**
	 * Information about the player's farm, and other game information.
	 */
	private GameInformation game;
	/**
	 * All logic pertaining to operating the store
	 */
	private StoreLogic store;
	/**
	 * The manager of the GUI screens.
	 */
	private ScreenManager manager;
	/**
	 * The player's farm object.
	 */
	private Farm farm;

	/**
	 * Create the GUI screen.
	 * @param incomingManager The ScreenManager object that will launch and close this screen.
	 * @param gameInfo The logic class containing information about the current game.
	 * @param storeLogic The logic class allowing purchasing of animals, crops, and items.
	 */
	public StoreAnimalScreen(ScreenManager incomingManager, GameInformation gameInfo, StoreLogic storeLogic) {
		manager = incomingManager;
		store = storeLogic;
		game = gameInfo;
		farm = game.getFarm();
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
		manager.closeStoreAnimalScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		double farmMoney = game.getFarm().getMoney();
		
		JLabel animalsLabel = new JLabel("Animals");
		animalsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		animalsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		animalsLabel.setBounds(310, 20, 130, 59);
		frame.getContentPane().add(animalsLabel);
		
		/*
		 * Farms money label
		 */
		String money = String.format("Current Money: $%.2f", farmMoney);
		JLabel moneyLabel = new JLabel(money);
		moneyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		moneyLabel.setBounds(21, 42, 180, 16);
		frame.getContentPane().add(moneyLabel);
		
		// Create a ListModel for animals and populate
		DefaultListModel<Animal> animalListModel = new DefaultListModel<>();
		animalListModel.addAll(game.getStore().availableAnimals);
		
		/*
		 * Animal JList
		 */
		JList<Animal> animalList = new JList<Animal>(animalListModel);
		animalList.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		animalList.setCellRenderer(new AnimalListCellRenderer());
		animalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		animalList.setBounds(210, 85, 334, 225);
		frame.getContentPane().add(animalList);
		
		JTextPane infoBox = new JTextPane();
		infoBox.setEditable(false);
		String ownedInfo = "Currently owned animals:\n";
		//Create a dictionary object to count the number of animals currently owned
		Hashtable<String, Integer> animalDict = new Hashtable<String, Integer>();
		for (Animal animal: game.getFarm().getAnimals()) {
			String type = animal.getType();
			if (animalDict.get(type) == null) {
				animalDict.put(type, 1);
			} else {
				animalDict.put(type, animalDict.get(type)+1);
			}
		}
		for (String key: animalDict.keySet()) {
			int number = animalDict.get(key);
			ownedInfo += String.format("%d %s, ", number, key);
		}
		infoBox.setText(ownedInfo);
		infoBox.setBounds(125, 321, 500, 60);
		frame.getContentPane().add(infoBox);
		
		/*
		 * Back button
		 */
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
				
			}
		});
		backButton.setBounds(96, 411, 117, 29);
		frame.getContentPane().add(backButton);
		
		JButton buyButton = new JButton("Buy");
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Animal selectedAnimal = animalList.getSelectedValue();
				String name = selectedAnimal != null ? selectedAnimal.getType() : "";
				double price = selectedAnimal != null ? selectedAnimal.getPrice() : 0.00;

				if (selectedAnimal == null) {
					JOptionPane.showMessageDialog(frame, "You need to select an animal first!", "", JOptionPane.PLAIN_MESSAGE);
				} else if (selectedAnimal.getPrice() > farmMoney){
					JOptionPane.showMessageDialog(frame, "You don't have enough money to buy that!", "", JOptionPane.PLAIN_MESSAGE);
				} else if (farm.getAnimals().size() == farm.getAnimalLimit()) {
					JOptionPane.showMessageDialog(frame, "Your farm doesn't have room for any more animals! Tend to your land to make more room.", "No room on your farm.", JOptionPane.ERROR_MESSAGE);
				} else {
					String message = String.format("Are you sure you want to buy a %s for $%.2f?", name, price);
					int choice = JOptionPane.showConfirmDialog(frame, message,  "", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						store.purchaseAnimal(selectedAnimal);
						finishedWindow();
					} 
				}
				
			}
		});
		buyButton.setBounds(500, 411, 117, 29);
		frame.getContentPane().add(buyButton);
	}
}
