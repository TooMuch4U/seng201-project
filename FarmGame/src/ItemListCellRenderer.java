import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import items.*;

public class ItemListCellRenderer extends DefaultListCellRenderer {
	
	public Component getListCellRendererComponent(
	        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	    {
		
	        // super setups up all the defaults
	        JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

	        Item item = (Item) value;
	        String itemString;
	        
	        if (item instanceof ItemForCrop) {
	        	itemString = String.format("%-20s %s", "$" + item.getPrice(), String.format("%s decreases time until harvest by %s days", item.getName(), item.getBenefit()));
	        } else {
	        	itemString = String.format("%-20s %s", "$" + item.getPrice(), String.format("%s increases the health of animals by %s", item.getName(), item.getBenefit() + "%"));
	        }
	        
	        label.setText(itemString);
	        
	        java.net.URL imageURL = AnimalListCellRenderer.class.getResource(String.format("images/Items/%s.png", item.getName()));
	        ImageIcon image = new ImageIcon(imageURL);
	        label.setIcon(image);

	        return label;

	    }

}