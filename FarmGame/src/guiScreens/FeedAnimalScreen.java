package guiScreens;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import items.ItemForAnimal;
import animals.Animal;
import gameLogic.AnimalLogic;
import gameLogic.GameInformation;
import gameLogic.ScreenManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class FeedAnimalScreen {
	
	/**
	 * The frame of the GUI screen
	 */
	private JFrame frame;
	/**
	 * The game logic responsible for managing the GUI screens.
	 */
	private ScreenManager manager;
	/**
	 * The logic class containing important informationa about the current game.
	 */
	private GameInformation game;
	/**
	 * Collection of logic pertaining to manipulation and interaction with animals.
	 */
	private AnimalLogic animalLogic;

	/**
	 * Create the GUI screen.
	 * @param incomingManager The ScreenManager object that will launch and close this screen.
	 * @param gameInfo The logic class containing information about the current game.
	 * @param animal The logic class allowing interaction with and manipulation of animals.
	 */
	public FeedAnimalScreen(ScreenManager incomingManager, GameInformation gameInfo, AnimalLogic animal) {
		manager = incomingManager;
		game = gameInfo;
		animalLogic = animal;
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
		manager.closeFeedAnimalScreen(this);
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
		 * Create the list models for the JList
		 */
		DefaultListModel<ItemForAnimal> itemListModel = new DefaultListModel<>();
		DefaultListModel<Animal> animalListModel = new DefaultListModel<>();
		itemListModel.addAll(game.getFarm().getAnimalItems());
		animalListModel.addAll(game.getFarm().getAnimals());
		
		/**
		 * Create the JLists, and add them to ScrollPanes
		 */
		JList<Animal> animalList = new JList<Animal>(animalListModel);
		animalList.setCellRenderer(new AnimalMainListCellRenderer());
		JScrollPane animalScroll = new JScrollPane();
		animalScroll.setViewportView(animalList);
		animalScroll.setBounds(25, 67, 325, 300);
		frame.getContentPane().add(animalScroll);
		
		JList<ItemForAnimal> itemList = new JList<ItemForAnimal>(itemListModel);
		itemList.setCellRenderer(new ItemMainListCellRenderer());
		JScrollPane itemScroll = new JScrollPane();
		itemScroll.setViewportView(itemList);
		itemScroll.setBounds(400, 67, 325, 300);
		frame.getContentPane().add(itemScroll);
		
		/**
		 * Confirm Button - check they have been selected, then move to next screen.
		 */
		JButton confirmSelectButton = new JButton("Confirm Selection");
		confirmSelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemForAnimal item = itemList.getSelectedValue();
				int animalIndex = animalList.getSelectedIndex();
				if (item == null) {
					JOptionPane.showMessageDialog(frame, "Please select an item to use.", "Warning: No Item Selected", JOptionPane.ERROR_MESSAGE);
				} else if (animalIndex == -1) {
					JOptionPane.showMessageDialog(frame, "Please select an animal to feed.", "Warning: No Animal Selected", JOptionPane.ERROR_MESSAGE);
				} else {
					animalLogic.feedAnimals(item, animalIndex);
					finishedWindow();
				}
			}
		});
		confirmSelectButton.setBounds(580, 400, 145, 35);
		frame.getContentPane().add(confirmSelectButton);
		
		/**
		 * Label to prompt the player
		 */
		JLabel greetingLabel = new JLabel("Please select which animal you would like to feed, with which item");
		greetingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		greetingLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		greetingLabel.setBounds(10, 11, 716, 45);
		frame.getContentPane().add(greetingLabel);
		
		/**
		 * Cancel button - go to previous screen
		 */
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		cancelButton.setBounds(25, 400, 145, 35);
		frame.getContentPane().add(cancelButton);
		
	}

}
