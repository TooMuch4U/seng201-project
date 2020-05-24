package animals;

/** 
 * Represents an animal on the player's farm.
 */
public class Animal {
	
	/**
	 * The type of animal.
	 */
	private String type;
	/**
	 * The purchase price of the animal.
	 */
	private double price;
	/**
	 * The maximum income the animal will give the farm.
	 * Maximum value is declared, then affected by health and happiness.
	 */
	private double income;
	/**
	 * The health of the animal.
	 * Affects the daily income generated by the animal.
	 * Maximum of 100, minimum of 0.
	 */
	private double health;
	/**
	 * The happiness of the animal.
	 * Affects the daily income generated by the animal.
	 * Maximum of 100, minimum of 0.
	 */
	private double happiness;
	
	
	/**
	 * Constructor using parameters.
	 * Ensures the animals happiness and health will be between 0 and 100 inclusive.
	 * @param kind The type of animal.
	 * @param cost The desired price of the animal.
	 * @param earnings The base income of the animal.
	 * @param fitness The starting health of the animal.
	 * @param mood The starting happiness of the animal.
	 */
	public Animal(String kind, double cost, double earnings, double fitness, double mood) {
		if (fitness > 100) {
			fitness = 100.00;
		} else if (fitness < 0) {
			fitness = 0.00;
		}
		
		if (mood > 100) {
			mood = 100.00;
		} else if (mood < 0) {
			mood = 0.00;
		}
		
		type = kind;
		price = cost;
		income = earnings;
		health = fitness;
		happiness = mood;
	}
	
	/**
	 * Gets the type of the animal.
	 * @return type Type of the animal.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Gets the price of the animal
	 * @return price Purchase price of the animal.
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Gets the current income generated by the animal.
	 * Calculates the income based off base income, and health and happiness attributes.
	 * @return The current calculated income generated by the animal.
	 */
	public double getIncome() {
		double healthPercent = health/100;
		double happyPercent = happiness/100;
		return Math.round(income*healthPercent*happyPercent*100.0)/100.0;
	}
	
	/**
	 * Gets the current health of the animal.
	 * @return health The current health of the animal.
	 */
	public double getHealth() {
		return health;
	}
	
	/**
	 * Gets the current happiness of the animal.
	 * @return happiness Current happiness of the animal.
	 */
	public double getHappiness() {
		return happiness;
	}
	
	/**
	 * Manipulates the current health of the animal.
	 * Sets health to 100 if change causes it to go above, and to 0 if change causes it to go below the boundaries.
	 * @param change The change in health to occur.
	 */
	public void changeHealth(double change) {
		double newHealth = (health += change);
		if(newHealth < 0)
		{
			health = 0;
		}
		else if(newHealth > 100)
		{
			health = 100;
		}
		else
		{
			health = Math.round(newHealth*100.0)/100.0;
		}
	}
	
	/**
	 * Manipulates the current happiness of the animal.
	 * If change causes happiness to go above or below the boundaries, sets happiness to 100 or 0 respectively.
	 * @param change The change in happiness to occur.
	 */
	public void changeHappiness(double change) {
		double newHappy = (happiness += change);
		if(newHappy < 0)
		{
			happiness = 0;
		}
		else if(newHappy > 100)
		{
			happiness = 100;
		}
		else
		{
			happiness = Math.round(newHappy*100.0)/100.0;
		}
	}
	
	
	@Override
	/**
	 * Displays the animal's type, happiness, and healthiness in a String form.
	 */
	public String toString() {
		return String.format("A %s: %.1f%% happy and %.1f%% healthy", type, happiness, health);
	}
}
