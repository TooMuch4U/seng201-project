package gameLogic;

import java.util.ArrayList;

import crops.Crop;
import farmsAndFarmer.Farm;
import items.ItemForCrop;

/**
 * Contains game logic dictating manipulation of crops and their attributes
 *
 */
public class CropLogic {
	
	/**
	 * The logic class containing the vital information about the current game.
	 */
	private GameInformation gameInfo;
	/**
	 * The player's farm object.
	 */
	private Farm farm;
	
	/**
	 * Constructor with paramters for CropLogic.
	 * @param info - the GameInformation class for the current game.
	 */
	public CropLogic(GameInformation info) {
		gameInfo = info;
		farm = gameInfo.getFarm();
	}
	
	/**
	 * Decreases the harvest time and increases the growing time of the farm's crops. 
	 * Called at the end of each day.
	 */
	public void dailyDecrease() {
		ArrayList<Crop> crops = farm.getCrops();
		for (Crop crop: crops) {
			crop.changeHarvestTime(1);
			crop.increaseGrowTime();
		}
	}
	
	/**
	 * Allows the player to tend to the crops on their farm, making them grow quicker. 
	 * Counts towards the player's daily actions, and as such, can't be performed if the player has no more actions for the day. 
	 * @param cropItem - the ItemForCrop to be used on the crops. 
	 * @param cropIndex - the index of the crop within the list that the player wishes to tend to.
	 */
	public void tendToCrops(ItemForCrop cropItem, int cropIndex) {
		ArrayList<Crop> crops = farm.getCrops();
		//Cast the given double to an integer
		int growBonus = (int) cropItem.getBenefit();
		Crop chosenCrop = crops.get(cropIndex);
		chosenCrop.changeHarvestTime(growBonus);
		if (cropItem.getName() != "Water") {
			farm.getItems().remove(cropItem);
		}
		gameInfo.changeNumActions();
	}
	
	
	/**
	 * Harvests one crop in the farm and adds the money to the farm. 
	 * @param cropIndex index of the crop to harvest.
	 */
	public void harvestCrops(int cropIndex) {
		ArrayList<Crop> crops = farm.getCrops();
		Crop crop = crops.get(cropIndex);
		double income = crop.getSalePrice(); 
		crops.remove(crop); // Remove the crop from the farm

		farm.changeMoney(income); // Add the money to the farm
		gameInfo.changeNumActions();		
	}
	
}
