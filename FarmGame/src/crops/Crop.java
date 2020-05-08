package crops;


public class Crop {
	
	/**
	 * The type of crop
	 */
	private String type;
	/**
	 * The purchase price of the crops
	 */
	private double purchasePrice;
	/**
	 * The income gained when selling the crops
	 */
	private double salePrice;
	/**
	 * The days until the crops can be harvested
	 */
	private int daysUntilHarvest;
	
	/**
	 * Constructor using parameters
	 * @param kind - the type of crop
	 * @param buying - the purchase price
	 * @param selling - the selling price
	 * @param time - the harvest time in days
	 */
	public Crop(String kind, double buying, double selling, int time) {
		type = kind;
		purchasePrice = buying;
		salePrice = selling;
		daysUntilHarvest = time;
	}
	
	/**
	 * Gets the type of crop
	 * @return type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Gets the purchasing price of the crop
	 * @return purchasePrice
	 */
	public double getPurchasePrice() {
		return purchasePrice;
	}
	
	/**
	 * Gets the selling price of the crop
	 * @return salePrice
	 */
	public double getSalePrice() {
		return salePrice;
	}
	
	/**
	 * Gets the current time until the crop can be harvested
	 * @return daysUntilHarvest
	 */
	public int getHarvestTime() {
		return daysUntilHarvest;
	}
	
	/**
	 * Changes the current harvest time of the crop
	 * Sets the harvest time to 0 if the harvest time will go below 0
	 * @param change - the desired change in harvest time
	 */
	public void changeHarvestTime(int change) {
		if (change < 0) {
			throw new IllegalArgumentException("Crops can't grow backwards");
		} else if (daysUntilHarvest < change) {
			change = daysUntilHarvest - 1; 
		}
		daysUntilHarvest -= change;
	}
	
	@Override
	public String toString() {
		return String.format("This %s has %d days left to grow", getType(), getHarvestTime());
	}
}
