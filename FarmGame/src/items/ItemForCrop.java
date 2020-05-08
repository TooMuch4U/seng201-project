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
	public double getBenefit() {
		return(daysBenefit);
	}
	
	@Override
	public String toString() {
		return String.format("This %s decreases growing time by %d days", super.getName(), getBenefit());
	}

}
