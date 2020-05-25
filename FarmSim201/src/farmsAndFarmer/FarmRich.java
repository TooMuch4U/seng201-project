package farmsAndFarmer;

/**
 * Farm type that grants an additional starting money bonus.
 */
public class FarmRich extends Farm {
	
	/**
	 * Constructor for the farm with parameters.
	 * @param name The name of the farm.
	 * @param farmer The farmer created by the user.
	 */
	public FarmRich(String name, Farmer farmer) {
		super(name, "rich", farmer, 1500.00);
	}
}
