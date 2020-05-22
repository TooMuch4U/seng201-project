package farmsAndFarmer;

/**
 * Represents the player's farmer
 */
public class Farmer {
	
	/**
	 * The farmer's name.
	 * Must be at least of length 3, and less than length 15.
	 * Checked when exting the set-up screen.
	 */
	private String name;
	/**
	 * The farmer's age.
	 * Must be a non-negative integer.
	 */
	private int age;
	
	/**
	 * Constructor for farmer.
	 */
	public Farmer() {
		name = "Nameless Farmer";
		age = 30;
	}
	
	/**
	 * Constructor with params.
	 * 
	 * @param newName - the name the farmer will be initialised with.
	 * @param newAge - the age the farmer will be initialised with.
	 */
	public Farmer(String newName, int newAge) {
		name = newName;
		age = newAge;
	}
	
	/**
	 * Returns the farmers name.
	 * @return name - The name of the farmer.
	 */
	public String getName() {
		return(name);
	}
	
	/**
	 * Returns the farmers age.
	 * @return age - The age of the farmer.
	 */
	public int getAge() {
		return(age);
	}

}
