package farmsAndFarmer;

/**
 * Farm type that grants an additional starting money bonus.
 */
public class FarmRich extends Farm {
	
	/**
	 * Constructor for the farm with parameters.
	 * @param name - the name of the farm.
	 * @param farmer - the farmer created by the user.
	 */
	public FarmRich(String name, Farmer farmer) {
		super(name, "rich", farmer, 1500.00);
	}
}
