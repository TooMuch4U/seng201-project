
public class Item {
	
	/**
	 * Items name
	 */
	private String name;
	/**
	 * Items price
	 */
	private double price;
	/**
	 * Items benefit
	 */
	private double benefit;
	
	
	/**
	 * Item constructor
	 * 
	 * @param newName Name for the item.
	 * @param newPrice Price for the item.
	 * @param newBenefit Benefit of the item.
	 */
	public Item(String newName, double newPrice, double newBenefit) {
		name = newName;
		price = newPrice;
		benefit = newBenefit;
	}
	
	/**
	 * Returns the items name.
	 * 
	 * @return String
	 */
	public String getName() {
		return(name);
	}
	
	/**
	 * Returns the items price.
	 * 
	 * @return
	 */
	public double getPrice() {
		return(price);
	}
	
	/**
	 * Returns the items benefit.
	 * 
	 * @return double
	 */
	public double getBenefit() {
		return(benefit);
	}
	
	public static void main(String[] args) {

	}

}
