import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import items.*;

public class StoreItemScreen {

	private JFrame frame;
	private GameEnviroBasic game;
	
	/**
	 * Create the application.
	 */
	public StoreItemScreen(GameEnviroBasic incomingGame) {
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
		game.closeStoreItemScreen(this);
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
		
		JLabel itemLabel = new JLabel("Items");
		itemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		itemLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		itemLabel.setBounds(310, 20, 130, 59);
		frame.getContentPane().add(itemLabel);
		
		/*
		 * Farms money label
		 */
		String money = String.format("Current Money: $%.2f", farmMoney);
		JLabel moneyLabel = new JLabel(money);
		moneyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		moneyLabel.setBounds(21, 42, 180, 16);
		frame.getContentPane().add(moneyLabel);
		
		// Create a ListModel for animals and populate
		DefaultListModel<Item> itemListModel = new DefaultListModel<>();
		itemListModel.addAll(game.getStore().availableItems);
		
		/*
		 * Item JList
		 */
		JList<Item> itemList = new JList<>(itemListModel);
		itemList.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		itemList.setCellRenderer(new ItemListCellRenderer());
		itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemList.setBounds(71, 85, 607, 225);
		frame.getContentPane().add(itemList);
		
		JTextPane infoBox = new JTextPane();
		infoBox.setEditable(false);
		String ownedInfo = "Currently owned items:\n";
		//Create a dicitonary object to count the number of items currently owned
		Hashtable<String, Integer> itemDict = new Hashtable<String, Integer>();
		for (Item item: game.getFarm().items) {
			String name = item.getName();
			if (itemDict.get(name) == null) {
				itemDict.put(name, 1);
			} else {
				itemDict.put(name, itemDict.get(name)+1);
			}
		}
		for (String key: itemDict.keySet()) {
			int number = itemDict.get(key);
			ownedInfo += String.format("%d %s, ", number, key);
		}
		infoBox.setText(ownedInfo);
		infoBox.setBounds(71, 321, 607, 60);
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
				Item selectedItem = itemList.getSelectedValue();
				String name = selectedItem != null ? selectedItem.getName() : "";
				double price = selectedItem != null ? selectedItem.getPrice() : 0.0;

				if (selectedItem == null) {
					JOptionPane.showMessageDialog(frame, "You need to select a crop first!", "", JOptionPane.PLAIN_MESSAGE);
				} else if (selectedItem.getPrice() > farmMoney){
					JOptionPane.showMessageDialog(frame, "You don't have enough money to buy that!", "", JOptionPane.PLAIN_MESSAGE);
				} else {
					String message = String.format("Are you sure you want to buy a %s for $%.2f?", name, price);
					int choice = JOptionPane.showConfirmDialog(frame, message,  "", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						game.purchaseItem(selectedItem);
						finishedWindow();
					} 
				}
				
			}
		});
		buyButton.setBounds(500, 411, 117, 29);
		frame.getContentPane().add(buyButton);
	}

}
