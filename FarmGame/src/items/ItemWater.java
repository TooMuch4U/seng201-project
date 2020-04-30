package items;

/**
 * Default item used if no item is specified when tending to crops
 * Cannot be purchased from the shop
 */
public class ItemWater extends ItemForCrop {
	
	/**
	 * Constructor for ItemWater
	 */
	public ItemWater() {
		super("Water", 0.00, 1.0);
	}

}
