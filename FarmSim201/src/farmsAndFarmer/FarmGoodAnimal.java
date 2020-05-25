package farmsAndFarmer;

import animals.Animal;

/**
 * Farm type that grants bonus animal happiness and health.
 */
public class FarmGoodAnimal extends Farm {
	
	/**
	 * Constructor for the farm with parameters.
	 * @param name The name of the farm.
	 * @param farmer The farmer created by the user.
	 */
	public FarmGoodAnimal(String name, Farmer farmer) {
		super(name, "animalBonus", farmer, 1000.00);
	}
	
	/**
	 * Adds an animal into the ArrayList animals. 
	 * Due to farm-type bonuses, adds an additional bonus to the animal's happiness and health.
	 * @param animal The animal to be added to the ArrayList.
	 */
	@Override
	public void addAnimal(Animal animal) {
		if (animals.size() == animalLimit) {
			throw new IllegalArgumentException("Farm is full of animals");
		}
		animal.changeHealth(10.00);
		animal.changeHappiness(10.00);
		animals.add(animal);
	}
}
