package items;

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
	/**
	 * Returns the item's health benefit.
	 * 
	 * @return healthBenefit The health the item will grant an animal.
	 */
	public double getBenefit() {
		return(healthBenefit);
	}
	
	
	@Override
	/**
	 * Returns a String representation of the item
	 */
	public String toString() {
		return String.format("%s: increases animal health by %s", super.getName(), getBenefit());
	}

}
