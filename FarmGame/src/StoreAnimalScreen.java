import java.awt.EventQueue;
import animals.*;
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
import java.awt.event.ActionEvent;

public class StoreAnimalScreen {

	private JFrame frame;
	private GameEnviroBasic game;
	
	
	

	/**
	 * Create the application.
	 */
	public StoreAnimalScreen(GameEnviroBasic incomingGame) {
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
		game.closeStoreAnimalScreen(this);
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
		for (Animal animal: game.getFarm().getAnimals()) {
			ownedInfo += animal.getType()+", ";
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
				} else {
					String message = String.format("Are you sure you want to buy a %s for $%.2f?", name, price);
					int choice = JOptionPane.showConfirmDialog(frame, message,  "", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						game.purchaseAnimal(selectedAnimal);
						finishedWindow();
					} 
				}
				
			}
		});
		buyButton.setBounds(500, 411, 117, 29);
		frame.getContentPane().add(buyButton);
	}
}
