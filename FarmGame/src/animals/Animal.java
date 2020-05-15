package animals;
import items.StoreItem;


public class Animal implements StoreItem{
	
	/**
	 * The type of animal
	 */
	private String type;
	/**
	 * The purchase price of the animal
	 */
	private double price;
	/**
	 * The income the animal will give the farm
	 * Maximum value is declared, then affected by health and happiness
	 */
	private double income;
	/**
	 * The health of the animal
	 * Affects the daily income generated by the animal
	 * Maximum of 100, minimum of 0
	 */
	private double health;
	/**
	 * The happiness of the animal
	 * Affects the daily income generated by the animal
	 * Maximum of 100, minimum of 0
	 */
	private double happiness;
	
	
	/**
	 * Constructor using parameters
	 * Every animal will likely be different, so no need for basic constructor
	 * Ensures the animals happiness and health will be between 0 and 100 inclusive
	 * @param kind - the type of animal
	 * @param cost - the desired price of the animal
	 * @param earnings - the base income of the animal
	 * @param fitness - the starting health of the animal
	 * @param mood - the starting happiness of the animal
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
	 * Gets the type of the animal
	 * @return type - type of the animal
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Gets the price of the animal
	 * @return price - purchase price of the animal
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Gets the current income generated by the animal
	 * Calculates the income based off base income, and health and happiness attributes
	 * @return income*health_percent*happy_percent - the current income generated by the animal
	 */
	public double getIncome() {
		double health_percent = health/100;
		double happy_percent = happiness/100;
		return Math.round(income*health_percent*happy_percent*100.0)/100.0;
	}
	
	/**
	 * Gets the current health of the animal
	 * @return health - the current health of the animal
	 */
	public double getHealth() {
		return health;
	}
	
	/**
	 * Gets the current happiness of the animal
	 * @return happiness - current happiness of the animal
	 */
	public double getHappiness() {
		return happiness;
	}
	
	/**
	 * Manipulates the current health of the animal
	 * Sets health to 100 if change causes it to go above, and to 0 if change causes it to go below the boundaries
	 * @param change - the change in health to occur
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
	 * Manipulates the current happiness of the animal
	 * If change causes happiness to go above or below the boundaries, sets happiness to 100 or 0 respectively
	 * @param change - the change in happiness to occur
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
	public String toString() {
		return "A "+getType()+": currently "+getHappiness()+" percent happy and "+getHealth()+" percent healthy";
	}
}
