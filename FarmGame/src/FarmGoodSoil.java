

import crops.Crop;

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
	
	/**
	 * Adds a crop into the ArrayList crops.
	 * Decreases the crop's harvest time by a day.
	 * @param crop - the crop to be added to the farm's current crops.
	 */
	@Override
	public void addCrop(Crop crop) {
		if (crops.size() == cropLimit) {
			throw new IllegalArgumentException("Farm is full of crops");
		} else if (crop.getHarvestTime() > 1) {
			crop.changeHarvestTime(1);
		}
		crops.add(crop);
	}
}
