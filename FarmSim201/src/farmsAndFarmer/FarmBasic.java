package farmsAndFarmer;

/**
 * Farm type that grants no additional bonuses.
 */
public class FarmBasic extends Farm {
	
	/**
	 * Constructor for the farm with parameters. 
	 * @param name The name of the farm.
	 * @param farmer The farmer created by the user.
	 */
	public FarmBasic(String name, Farmer farmer) {
		super(name, "basic", farmer, 1000.00);
	}
}
