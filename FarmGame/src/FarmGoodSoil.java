/**
 * Farm type that grants an additional bonus to crop growing time
 */
public class FarmGoodSoil extends Farm {
	
	/**
	 * Constructor for the farm with parameters
	 * @param name - the name of the farm
	 * @param farmer - the farmer created by the user
	 */
	public FarmGoodSoil(String name, Farmer farmer) {
		super(name, "cropBonus", farmer, 1000.00);
	}
}
