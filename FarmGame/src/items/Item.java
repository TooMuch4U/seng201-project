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
	 * Item's price
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
	 * Returns the item's name.
	 * 
	 * @return name A String of the name of the item
	 */
	public String getName() {
		return(name);
	}
	
	/**
	 * Returns the item's price.
	 * 
	 * @return price The price of the item
	 */
	public double getPrice() {
		return(price);
	}
	
	/**
	 * Returns the item's benefit.
	 * 
	 * @return double
	 */
	public abstract double getBenefit();

}
