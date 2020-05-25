package items;

public class ItemForCrop extends Item {
	
	/**
	 * ItemForCrop's day benefit
	 */
	private double daysBenefit;
	
	/**
	 * ItemForCrop constructor
	 * 
	 * @param newName Name for the item.
	 * @param newPrice Price for the item.
	 * @param newBenefit Benefit of the item
	 */
	public ItemForCrop(String newName, double newPrice, double newBenefit) {
		super(newName, newPrice);
		daysBenefit = newBenefit;
	}
	
	@Override
	/**
	 * Returns the item's growing benefit.
	 * 
	 * @return daysBenefit The number of days the item will decrease growing time by.
	 */
	public double getBenefit() {
		return(daysBenefit);
	}
	
	@Override
	/**
	 * Returns a String representation of the item
	 */
	public String toString() {
		return String.format("%s: decreases growing time by %s days", super.getName(), getBenefit());
	}

}
