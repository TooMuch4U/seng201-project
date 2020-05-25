package guiScreens;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import crops.Crop;
import gameLogic.CropLogic;
import gameLogic.GameInformation;
import gameLogic.ScreenManager;
import items.ItemForAnimal;
import items.ItemForCrop;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class TendCropsScreen {

	/**
	 * The frame for the GUI screen.
	 */
	private JFrame frame;
	/**
	 * The information about the game, which all functions can be accessed from.
	 */
	private GameInformation game;
	/**
	 * The manager of the GUI screens.
	 */
	private ScreenManager manager;
	/**
	 * All logic pertaining to the manipulation and interaction with crops.
	 */
	private CropLogic cropLogic;
	
	
	/**
	 * Create the GUI screen.
	 * @param incomingManager The ScreenManager object that will launch and close this screen.
	 * @param gameInfo The logic class containing information about the current game.
	 * @param crop The logic class allowing manipulation and interaction with crops.
	 */
	public TendCropsScreen(ScreenManager incomingManager, GameInformation gameInfo, CropLogic crop) {
		manager = incomingManager;
		game = gameInfo;
		cropLogic = crop;
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
		manager.closeTendCropsScreen(this);
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
		 * Create a list model for the JLists, then create the JLists themselves.
		 */
		DefaultListModel<ItemForCrop> itemListModel = new DefaultListModel<>();
		DefaultListModel<Crop> cropListModel = new DefaultListModel<>();
		itemListModel.addAll(game.getFarm().getCropItems());
		cropListModel.addAll(game.getFarm().getCrops());
		
		JList<Crop> cropList = new JList<Crop>(cropListModel);
		cropList.setCellRenderer(new CropMainListCellRenderer());
		cropList.setBounds(25, 67, 325, 300);
		frame.getContentPane().add(cropList);
		
		JList<ItemForCrop> itemList = new JList<ItemForCrop>(itemListModel);
		itemList.setCellRenderer(new ItemMainListCellRenderer());
		itemList.setBounds(400, 67, 325, 300);
		frame.getContentPane().add(itemList);
		
		/**
		 * Button to confirm user selection. Checks the user has selected values, then enquires whether the player is sure.
		 */
		JButton confirmSelectButton = new JButton("Confirm Selection");
		confirmSelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemForCrop item = itemList.getSelectedValue();
				int cropIndex = cropList.getSelectedIndex();
				if (item == null) {
					JOptionPane.showMessageDialog(frame, "Please select an item to use.", "Warning: No Item Selected", JOptionPane.ERROR_MESSAGE);
				} else if (cropIndex == -1) {
					JOptionPane.showMessageDialog(frame, "Please select a crop to tend to.", "Warning: No Crop Selected", JOptionPane.ERROR_MESSAGE);
				} else {
					cropLogic.tendToCrops(item, cropIndex);
					finishedWindow();
				}
			}
		});
		confirmSelectButton.setBounds(580, 400, 145, 35);
		frame.getContentPane().add(confirmSelectButton);
		
		/**
		 * Label prompting the user to make selections.
		 */
		JLabel greetingLabel = new JLabel("Please select which crop you would like to tend, with which item");
		greetingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		greetingLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		greetingLabel.setBounds(10, 11, 716, 45);
		frame.getContentPane().add(greetingLabel);
		
		/**
		 * Button to go back to the previous screen
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
