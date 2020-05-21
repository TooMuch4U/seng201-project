

public class Farmer {
	
	/**
	 * Farmers name
	 * Must be at least of length 1
	 */
	private String name;
	/**
	 * Farmers age
	 * must be a positive integer
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
	 * @param newName Name the farmer will be initialised with.
	 * @param newAge Age the farmer will be initialised with.
	 */
	public Farmer(String newName, int newAge) {
		this.setName(newName);
		this.setAge(newAge);
	}
	
	/**
	 * Returns the farmers name.
	 */
	public String getName() {
		return(name);
	}
	
	/**
	 * Returns the farmers age.
	 */
	public int getAge() {
		return(age);
	}
	
	/**
	 * Sets the farmers name.
	 * 
	 * @param newName New name for the farmer. Must be at least 1 character.
	 */
	public void setName(String newName) {
		if (newName.length() > 0) {
			name = newName;
		}
		else {
			throw new IllegalArgumentException("newName String must atleast be of length 1.");				
		}
	}
	
	/**
	 * Sets the farmers age.
	 * 
	 * @param newAge age to set for farmer.
	 */
	private void setAge(int newAge) {
		if (newAge >= 0) {
			age = newAge;
		}
		else {
			throw new IllegalArgumentException("newAge String must not be negative.");
		}
	}

	public static void main(String[] args) {
		
		Farmer farmer1 = new Farmer("Tom", 18);
		Farmer farmer2 = new Farmer();
		
		
		System.out.println(farmer1.getName());
		System.out.println(farmer2.getName());
	}

}
