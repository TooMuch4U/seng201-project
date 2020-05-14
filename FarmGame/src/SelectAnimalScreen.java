import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import animals.Animal;
import crops.Crop;

import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SelectAnimalScreen {

	private JFrame frame;
	private GameEnviroBasic game;

	/**
	 * Create the application.
	 */
	public SelectAnimalScreen(GameEnviroBasic incomingGame) {
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
		game.closeSelectAnimalScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel promptLabel = new JLabel("Please select the animal you would like to play with");
		promptLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		promptLabel.setHorizontalAlignment(SwingConstants.CENTER);
		promptLabel.setBounds(10, 11, 716, 45);
		frame.getContentPane().add(promptLabel);
		
		DefaultListModel<Animal> animalListModel = new DefaultListModel<Animal>();
		ArrayList<Animal> animals = game.getFarm().getAnimals();
		animalListModel.addAll(animals);
		
		JList<Animal> animalList = new JList<Animal>(animalListModel);
		animalList.setBounds(215, 74, 320, 320);
		frame.getContentPane().add(animalList);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedAnimal = animalList.getSelectedIndex();
				if (selectedAnimal == -1) {
					JOptionPane.showMessageDialog(frame, "Please select an animal to play with.", "Error: No Animal Selected.", JOptionPane.ERROR_MESSAGE);
				} else {
					Animal animal = animals.get(selectedAnimal);
					String confirmString = String.format("You have selected this animal:\nA %s with %d\\% health and %d\\% happiness", animal.getType(), animal.getHealth(), animal.getHappiness());
					int choice = JOptionPane.showConfirmDialog(frame, confirmString,  "Confirmation", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						game.playWithAnimals(selectedAnimal);
						finishedWindow();
					}
				}
			}
		});
		confirmButton.setBounds(591, 359, 105, 35);
		frame.getContentPane().add(confirmButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		cancelButton.setBounds(591, 300, 105, 35);
		frame.getContentPane().add(cancelButton);
	}
}
