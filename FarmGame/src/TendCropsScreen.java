import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import crops.Crop;
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

	private JFrame frame;
	private GameEnviroBasic game;

	/**
	 * Create the application.
	 */
	public TendCropsScreen(GameEnviroBasic incomingGame) {
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
		game.closeTendCropsScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		TendCropsScreen screen = this;
		
		DefaultListModel<ItemForCrop> itemListModel = new DefaultListModel<>();
		DefaultListModel<Crop> cropListModel = new DefaultListModel<>();
		itemListModel.addAll(game.getFarm().getCropItems());
		cropListModel.addAll(game.getFarm().getCrops());
		
		JList<Crop> cropList = new JList<Crop>(cropListModel);
		cropList.setBounds(25, 67, 325, 300);
		frame.getContentPane().add(cropList);
		
		JList<ItemForCrop> itemList = new JList<ItemForCrop>(itemListModel);
		itemList.setBounds(400, 67, 325, 300);
		frame.getContentPane().add(itemList);
		
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
					game.tendToCrops(item, cropIndex);
					game.closeTendCropsScreen(screen);
				}
			}
		});
		confirmSelectButton.setBounds(580, 400, 145, 35);
		frame.getContentPane().add(confirmSelectButton);
		
		JLabel greetingLabel = new JLabel("Please select which crop you would like to tend, with which item");
		greetingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		greetingLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		greetingLabel.setBounds(10, 11, 716, 45);
		frame.getContentPane().add(greetingLabel);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.closeTendCropsScreen(screen);
			}
		});
		cancelButton.setBounds(25, 400, 145, 35);
		frame.getContentPane().add(cancelButton);
	}
}
