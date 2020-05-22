package guiScreens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import animals.Animal;
import crops.Crop;
import gameLogic.AnimalLogic;
import gameLogic.GameInformation;
import gameLogic.ScreenManager;

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
	/**
	 * The information about the game, which all functions can be accessed from.
	 */
	private GameInformation game;
	/**
	 * The manager of the GUI screens.
	 */
	private ScreenManager manager;
	/**
	 * Collection of logic pertaining to manipulation and interaction with animals.
	 */
	private AnimalLogic animalLogic;

	/**
	 * Create the application.
	 */
	public SelectAnimalScreen(ScreenManager incomingManager, GameInformation gameInfo, AnimalLogic animal) {
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
		manager.closeSelectAnimalScreen(this);
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
		animalList.setCellRenderer(new AnimalMainListCellRenderer());
		animalList.setBounds(140, 70, 470, 320);
		frame.getContentPane().add(animalList);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedAnimal = animalList.getSelectedIndex();
				if (selectedAnimal == -1) {
					JOptionPane.showMessageDialog(frame, "Please select an animal to play with.", "Error: No Animal Selected.", JOptionPane.ERROR_MESSAGE);
				} else {
					Animal animal = animals.get(selectedAnimal);
					String confirmString = "You have selected this animal:\n"+animal.toString()+"\nDo you want to play with it?";
					int choice = JOptionPane.showConfirmDialog(frame, confirmString,  "Confirmation", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						animalLogic.playWithAnimals(selectedAnimal);
						finishedWindow();
					}
				}
			}
		});
		confirmButton.setBounds(600, 400, 105, 35);
		frame.getContentPane().add(confirmButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		cancelButton.setBounds(45, 400, 105, 35);
		frame.getContentPane().add(cancelButton);
	}
}
