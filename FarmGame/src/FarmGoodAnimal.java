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
}
