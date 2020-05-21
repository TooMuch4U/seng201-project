package items;

/** Representation of an item
 * 
 */
public abstract class Item {
	
	/**
	 * Items name
	 */
	private String name;
	/**
	 * Items price
	 */
	public double price;
	
	
	/**
	 * Item constructor
	 * 
	 * @param newName Name for the item.
	 * @param newPrice Price for the item.
	 * @param newBenefit Benefit of the item
	 */
	public Item(String newName, double newPrice) {
		name = newName;
		price = newPrice;
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
	 * @return price - The price of the item
	 */
	public double getPrice() {
		return(price);
	}
	
	/**
	 * Returns the items benefit.
	 * 
	 * @return double
	 */
	public abstract double getBenefit();

}
