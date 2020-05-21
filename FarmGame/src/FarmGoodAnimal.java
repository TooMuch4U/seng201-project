

import animals.Animal;

/**
 * Farm type that grants bonus animal happiness and health
 */
public class FarmGoodAnimal extends Farm {
	
	/**
	 * Constructor for the farm with parameters
	 * @param name - the name of the farm
	 * @param farmer - the farmer created by the user
	 */
	public FarmGoodAnimal(String name, Farmer farmer) {
		super(name, "animalBonus", farmer, 1000.00);
	}
	
	/**
	 * Adds an animal into the ArrayList animals.
	 * If the farm's type allows it, adds a bonus to the animal's happiness and health.
	 * @param animal - the animal to be added to the ArrayList.
	 */
	public void addAnimal(Animal animal) {
		if (animals.size() == animalLimit) {
			throw new IllegalArgumentException("Farm is full of animals");
		}
		animal.changeHealth(10.00);
		animal.changeHappiness(10.00);
		animals.add(animal);
	}
}
