import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import items.ItemForAnimal;
import animals.Animal;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class FeedAnimalScreen {

	private JFrame frame;
	private GameEnviroBasic game;

	/**
	 * Create the application.
	 */
	public FeedAnimalScreen(GameEnviroBasic incomingGame) {
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
		game.closeFeedAnimalScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		DefaultListModel<ItemForAnimal> itemListModel = new DefaultListModel<>();
		DefaultListModel<Animal> animalListModel = new DefaultListModel<>();
		itemListModel.addAll(game.getFarm().getAnimalItems());
		animalListModel.addAll(game.getFarm().getAnimals());
		
		JList<Animal> animalList = new JList<Animal>(animalListModel);
		animalList.setBounds(25, 67, 325, 300);
		frame.getContentPane().add(animalList);
		
		JList<ItemForAnimal> itemList = new JList<ItemForAnimal>(itemListModel);
		itemList.setBounds(400, 67, 325, 300);
		frame.getContentPane().add(itemList);
		
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
					game.feedAnimals(item, animalIndex);
					finishedWindow();
				}
			}
		});
		confirmSelectButton.setBounds(580, 400, 145, 35);
		frame.getContentPane().add(confirmSelectButton);
		
		JLabel greetingLabel = new JLabel("Please select which animal you would like to feed, with which item");
		greetingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		greetingLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		greetingLabel.setBounds(10, 11, 716, 45);
		frame.getContentPane().add(greetingLabel);
		
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
