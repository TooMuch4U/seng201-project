
public class ItemForAnimal extends Item {
	
	/**
	 * ItemForAnimal's health benefit
	 */
	private double healthBenefit;
	
	/**
	 * ItemForCrop constructor
	 * 
	 * @param newName Name for the item.
	 * @param newPrice Price for the item.
	 * @param newBenefit Health benefit of the item.
	 */
	public ItemForAnimal(String newName, double newPrice, double newBenefit) {
		super(newName, newPrice);
		healthBenefit = newBenefit;
	}

	@Override
	public double getBenefit() {
		return(healthBenefit);
	}

}
