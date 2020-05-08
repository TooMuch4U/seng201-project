import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import items.ItemForAnimal;
import animals.Animal;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FeedAnimalScreen {

	private JFrame frame;
	private GameEnviroBasic game;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FeedAnimalScreen window = new FeedAnimalScreen();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public FeedAnimalScreen(GameEnviroBasic incomingGame) {
		game = incomingGame;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		game.closeFeedAnimalScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		FeedAnimalScreen screen = this;
		
		DefaultListModel<ItemForAnimal> itemListModel = new DefaultListModel<>();
		DefaultListModel<Animal> animalListModel = new DefaultListModel<>();
		itemListModel.addAll(game.getFarm().getAnimalItems());
		animalListModel.addAll(game.getFarm().getAnimals());
		
		JList<Animal> animalList = new JList<>(animalListModel);
		animalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		animalList.setBounds(147, 218, -106, -138);
		frame.getContentPane().add(animalList);
		
		JList<ItemForAnimal> itemList = new JList<>(itemListModel);
		itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemList.setBounds(255, 213, 137, -138);
		frame.getContentPane().add(itemList);
		
		JButton confirmSelectButton = new JButton("Confirm Selection");
		confirmSelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemForAnimal item = itemList.getSelectedValue();
				int animalIndex = animalList.getSelectedIndex();
				game.feedAnimals(item, animalIndex);
				game.closeFeedAnimalScreen(screen);
			}
		});
		confirmSelectButton.setBounds(288, 268, 154, 23);
		frame.getContentPane().add(confirmSelectButton);
		
		JLabel greetingLabel = new JLabel("Please select which animal you would like to feed, with which item");
		greetingLabel.setBounds(53, 11, 443, 14);
		frame.getContentPane().add(greetingLabel);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.closeFeedAnimalScreen(screen);
			}
		});
		cancelButton.setBounds(53, 268, 137, 23);
		frame.getContentPane().add(cancelButton);
	}

}
