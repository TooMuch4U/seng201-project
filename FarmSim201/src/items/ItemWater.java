package items;

/**
 * Free item that can be used if the player has no ItemForCrops. 
 * Cannot be purchased from the shop.
 */
public class ItemWater extends ItemForCrop {
	
	/**
	 * Constructor for ItemWater
	 */
	public ItemWater() {
		super("Water", 0.00, 1.0);
	}

}
