import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import animals.*;

public class AnimalListCellRenderer extends DefaultListCellRenderer {
	
	public Component getListCellRendererComponent(
	        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	    {
		
	        // super setups up all the defaults
	        JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

	        Animal animal = (Animal) value;
	        String animalString = String.format("%-10s %10s", animal.getType(), "$" + animal.getPrice());
	        label.setText(animalString);

	        return label;

	    }

}
