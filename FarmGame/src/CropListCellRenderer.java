import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import crops.*;

public class CropListCellRenderer extends DefaultListCellRenderer {
	
	public Component getListCellRendererComponent(
	        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	    {
		
	        // super setups up all the defaults
	        JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

	        Crop crop = (Crop) value;
	        String cropString = String.format("$%-10.2f %10s", crop.getPrice(), String.format("%s, takes %d days to grow", crop.getType(), crop.getHarvestTime()));
	        label.setText(cropString);
	        
	        java.net.URL imageURL = AnimalListCellRenderer.class.getResource(String.format("images/Crops/%s.png", crop.getType()));
	        ImageIcon image = new ImageIcon(imageURL);
	        label.setIcon(image);

	        return label;

	    }

}
