import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import crops.Crop;
import items.ItemForAnimal;
import items.ItemForCrop;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TendCropsScreen {

	private JFrame frame;
	private GameEnviroBasic game;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TendCropsScreen window = new TendCropsScreen();
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
	public TendCropsScreen(GameEnviroBasic incomingGame) {
		game = incomingGame;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		game.closeTendCropsScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		TendCropsScreen screen = this;
		
		DefaultListModel<ItemForCrop> itemListModel = new DefaultListModel<>();
		DefaultListModel<Crop> cropListModel = new DefaultListModel<>();
		itemListModel.addAll(game.getFarm().getCropItems());
		cropListModel.addAll(game.getFarm().getCrops());
		
		JList<Crop> cropList = new JList<>(cropListModel);
		cropList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cropList.setBounds(147, 218, -106, -138);
		frame.getContentPane().add(cropList);
		
		JList<ItemForCrop> itemList = new JList<>(itemListModel);
		itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemList.setBounds(255, 213, 137, -138);
		frame.getContentPane().add(itemList);
		
		JButton confirmSelectButton = new JButton("Confirm Selection");
		confirmSelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemForCrop item = itemList.getSelectedValue();
				int cropIndex = cropList.getSelectedIndex();
				game.tendToCrops(item, cropIndex);
				game.closeTendCropsScreen(screen);
			}
		});
		confirmSelectButton.setBounds(303, 279, 149, 23);
		frame.getContentPane().add(confirmSelectButton);
		
		JLabel greetingLabel = new JLabel("Please select which crop you would like to tend, with which item");
		greetingLabel.setBounds(10, 11, 466, 14);
		frame.getContentPane().add(greetingLabel);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.closeTendCropsScreen(screen);
			}
		});
		cancelButton.setBounds(42, 279, 127, 23);
		frame.getContentPane().add(cancelButton);
	}
}
