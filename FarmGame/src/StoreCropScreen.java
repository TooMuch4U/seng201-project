import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import animals.Animal;
import crops.Crop;

public class StoreCropScreen {

	private JFrame frame;
	private GameEnviroBasic game;
	
	/**
	 * Create the application.
	 */
	public StoreCropScreen(GameEnviroBasic incomingGame) {
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
		game.closeStoreCropScreen(this);
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
		cropLabel.setBounds(310, 56, 130, 59);
		frame.getContentPane().add(cropLabel);
		
		/*
		 * Farms money label
		 */
		String money = String.format("Current Money: $%.2f", farmMoney);
		JLabel moneyLabel = new JLabel(money);
		moneyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		moneyLabel.setBounds(21, 20, 180, 16);
		frame.getContentPane().add(moneyLabel);
		
		// Create a ListModel for animals and populate
		DefaultListModel<Crop> cropListModel = new DefaultListModel<>();
		cropListModel.addAll(game.getStore().availableCrops);
		
		/*
		 * Animal JList
		 */
		JList<Crop> cropList = new JList<>(cropListModel);
		cropList.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		cropList.setCellRenderer(new CropListCellRenderer());
		cropList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cropList.setBounds(210, 127, 334, 225);
		frame.getContentPane().add(cropList);
		
		/*
		 * Back button
		 */
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.launchStoreMainScreen();
				closeWindow();
				
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
				} else {
					String message = String.format("Are you sure you want to buy a %s for $%.2f?", name, price);
					int choice = JOptionPane.showConfirmDialog(frame, message,  "", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						game.purchaseCrop(selectedCrop);
						closeWindow();
						game.launchMainScreen();
					} 
				}
				
			}
		});
		buyButton.setBounds(500, 411, 117, 29);
		frame.getContentPane().add(buyButton);
	}

}
