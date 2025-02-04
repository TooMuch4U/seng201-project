package crops;

/**
 * Represents a crop on the player's farm.
 */
public class Crop {
	
	/**
	 * The type of crop.
	 */
	private String type;
	/**
	 * The purchase price of the crop.
	 */
	private double purchasePrice;
	/**
	 * The income gained when selling the crop.
	 */
	private double salePrice;
	/**
	 * The days until the crops can be harvested.
	 */
	private int daysUntilHarvest;
	/**
	 * The days the crop has been growing for.
	 * If an item is tended to, this does not affect growing time.
	 * Always 0 when crop is created.
	 */
	private int growingTime = 0;
	
	/**
	 * Constructor using parameters.
	 * @param kind The type of crop.
	 * @param buying The purchase price.
	 * @param selling The selling price.
	 * @param time The harvest time in days.
	 */
	public Crop(String kind, double buying, double selling, int time) {
		type = kind;
		purchasePrice = buying;
		salePrice = selling;
		daysUntilHarvest = time;
	}
	
	/**
	 * Gets the type of crop.
	 * @return type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Gets the purchasing price of the crop.
	 * @return purchasePrice
	 */
	public double getPrice() {
		return purchasePrice;
	}
	
	/**
	 * Gets the selling price of the crop.
	 * @return salePrice
	 */
	public double getSalePrice() {
		return salePrice;
	}
	
	/**
	 * Gets the current time until the crop can be harvested.
	 * @return daysUntilHarvest
	 */
	public int getHarvestTime() {
		return daysUntilHarvest;
	}
	
	/**
	 * Gets the total time the crop has been growing for.
	 * Does not count days taken away by using an item on the crop.
	 * @return growingTime
	 */
	public int getGrowingTime() {
		return growingTime;
	}
	
	/**
	 * Changes the current harvest time of the crop.
	 * Sets the harvest time to 0 if the harvest time will go below 0.
	 * @param change The desired change in harvest time.
	 */
	public void changeHarvestTime(int change) {
		if (change < 0) {
			throw new IllegalArgumentException("Crops can't grow backwards");
		} else if (daysUntilHarvest < change) {
			change = daysUntilHarvest; 
		}
		daysUntilHarvest -= change;
	}
	
	/**
	 * Increases the growing time of the crop by one. 
	 * Is not called when an item is used on the crop.
	 */
	public void increaseGrowTime() {
		growingTime += 1;
	}
	
	
	@Override
	/**
	 * Displays the crop's types, growing time, and time until harvest. If the crop is ready to be harvested, it display the sale price.
	 */
	public String toString() {
		if (daysUntilHarvest == 0) {
			return String.format("This %s is ready, and will sell for $%.2f", getType(), getSalePrice());
		} else {
			return String.format("A %s, with %d days until harvest.", type, daysUntilHarvest);
		}
	}
}
