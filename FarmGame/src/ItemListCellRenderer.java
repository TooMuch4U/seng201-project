import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
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
	        	itemString = String.format("%-90s %s", String.format("%s decreases time until harvest by %s days", item.getName(), item.getBenefit()), "$" + item.getPrice());
	        } else {
	        	itemString = String.format("%-90s %s", String.format("%s increases the health of animals by %s", item.getName(), item.getBenefit()), "$" + item.getPrice());
	        }
	        
	        label.setText(itemString);

	        return label;

	    }

}