/**
 * Farm type that grants no additional bonuses
 */
public class FarmBasic extends Farm {
	
	/**
	 * Constructor for the farm with parameters
	 * @param name - the name of the farm
	 * @param farmer - the farmer created by the user
	 */
	public FarmBasic(String name, Farmer farmer) {
		super(name, "basic", farmer, 1000.00);
	}
}
