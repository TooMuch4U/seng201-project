package guiScreens;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import animals.Animal;
import crops.Crop;
import farmsAndFarmer.Farm;
import gameLogic.GameInformation;
import gameLogic.ScreenManager;
import gameLogic.StoreLogic;

public class StoreCropScreen {

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
	public StoreCropScreen(ScreenManager incomingManager, GameInformation gameInfo, StoreLogic storeLogic) {
		manager = incomingManager;
		game = gameInfo;
		store = storeLogic;
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
		manager.closeStoreCropScreen(this);
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
		
		JLabel cropLabel = new JLabel("Crops");
		cropLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cropLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		cropLabel.setBounds(310, 20, 130, 59);
		frame.getContentPane().add(cropLabel);
		
		
		/*
		 * Farms money label
		 */
		String money = String.format("Current Money: $%.2f", farmMoney);
		JLabel moneyLabel = new JLabel(money);
		moneyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		moneyLabel.setBounds(21, 42, 180, 16);
		frame.getContentPane().add(moneyLabel);
		
		// Create a ListModel for animals and populate
		DefaultListModel<Crop> cropListModel = new DefaultListModel<>();
		cropListModel.addAll(game.getStore().availableCrops);
		
		/*
		 * Animal JList
		 */
		JList<Crop> cropList = new JList<>(cropListModel);
		cropList.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		cropList.setCellRenderer(new CropListCellRenderer());
		cropList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cropList.setBounds(210, 85, 334, 225);
		frame.getContentPane().add(cropList);
		
		JTextPane infoBox = new JTextPane();
		infoBox.setEditable(false);
		String ownedInfo = "Currently owned crops:\n";
		//Create a dictionary object to count the number of crops currently owned
		Hashtable<String, Integer> cropDict = new Hashtable<String, Integer>();
		for (Crop crop: game.getFarm().getCrops()) {
			String type = crop.getType();
			if (cropDict.get(type) == null) {
				cropDict.put(type, 1);
			} else {
				cropDict.put(type, cropDict.get(type)+1);
			}
		}
		for (String key: cropDict.keySet()) {
			int number = cropDict.get(key);
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
				Crop selectedCrop = cropList.getSelectedValue();
				String name = selectedCrop != null ? selectedCrop.getType() : "";
				double price = selectedCrop != null ? selectedCrop.getPrice() : 0.0;

				if (selectedCrop == null) {
					JOptionPane.showMessageDialog(frame, "You need to select a crop first!", "", JOptionPane.PLAIN_MESSAGE);
				} else if (selectedCrop.getPrice() > farmMoney){
					JOptionPane.showMessageDialog(frame, "You don't have enough money to buy that!", "", JOptionPane.PLAIN_MESSAGE);
				} else if (farm.getCrops().size() == farm.getCropLimit()) {
					JOptionPane.showMessageDialog(frame, "Your farm doesn't have room for any more crops! Tend to your land to make more room.", "No room on your farm.", JOptionPane.ERROR_MESSAGE);
				} else {
					String message = String.format("Are you sure you want to buy a %s for $%.2f?", name, price);
					int choice = JOptionPane.showConfirmDialog(frame, message,  "", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						store.purchaseCrop(selectedCrop);
						finishedWindow();
					} 
				}
				
			}
		});
		buyButton.setBounds(500, 411, 117, 29);
		frame.getContentPane().add(buyButton);
	}

}
