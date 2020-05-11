package items;

public abstract class Item implements StoreItem{
	
	/**
	 * Items name
	 */
	private String name;
	/**
	 * Items price
	 */
	private double price;
	
	
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
	public abstract double getBenefit();
	

}
