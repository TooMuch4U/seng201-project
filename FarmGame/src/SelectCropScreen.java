import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import crops.Crop;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SelectCropScreen {

	private JFrame frame;
	private GameEnviroBasic game;

	/**
	 * Create the application.
	 */
	public SelectCropScreen(GameEnviroBasic incomingGame) {
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
	 * Passes in the index of the selected Crop.
	 * @param cropIndex - an integer index that specifies what crop was selected
	 */
	public void finishedWindow() {
		game.closeSelectCropScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel promptLabel = new JLabel("Please select the crop you would like to harvest");
		promptLabel.setHorizontalAlignment(SwingConstants.CENTER);
		promptLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		promptLabel.setBounds(10, 11, 716, 45);
		frame.getContentPane().add(promptLabel);
		
		DefaultListModel<Crop> cropListModel = new DefaultListModel<Crop>();
		ArrayList<Crop> crops = game.getFarm().getHarvestableCrops();
		cropListModel.addAll(crops);
		
		JList<Crop> cropList = new JList<Crop>(cropListModel);
		cropList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cropList.setBounds(215, 75, 320, 320);
		frame.getContentPane().add(cropList);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		cancelButton.setBounds(590, 300, 105, 35);
		frame.getContentPane().add(cancelButton);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedCrop = cropList.getSelectedIndex();
				String confirmString = "You have selected this crop:\n" + crops.get(selectedCrop);
				int choice = JOptionPane.showConfirmDialog(frame, confirmString,  "Confirmation", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					game.harvestCrops(selectedCrop);
					finishedWindow();
				}
			}
		});
		confirmButton.setBounds(590, 360, 105, 35);
		frame.getContentPane().add(confirmButton);
	}

}
