package guiScreens;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import animals.*;

public class AnimalMainListCellRenderer extends DefaultListCellRenderer {
	
	public Component getListCellRendererComponent(
	        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	    {
		
	        // super setups up all the defaults
	        JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

	        Animal animal = (Animal) value;
	        String animalString = animal.toString();
	        label.setText(animalString);
	        
	        java.net.URL imageURL = AnimalListCellRenderer.class.getResource(String.format("images/Animals/%s.png", animal.getType()));
	        ImageIcon image = new ImageIcon(imageURL);
	        label.setIcon(image);

	        return label;

	    }

}
