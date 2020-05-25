package gameLogic;

import java.util.ArrayList;

import animals.Animal;
import crops.Crop;
import farmsAndFarmer.Farm;
import items.ItemForAnimal;
import items.ItemForCrop;

/**
 * Contains logic dictating time passing or farm information
 *
 */
public class BasicGameLogic {
	
	/**
	 * The logic class containing the vital information about the current game.
	 */
	private GameInformation gameInfo;
	/**
	 * Logic class containing logic involving the manipulation and interaction with animals.
	 */
	private AnimalLogic animalLogic;
	/**
	 * Logic class containing logic involving the manipulation and interaction with animals.
	 */
	private CropLogic cropLogic;
	/**
	 * Logic class that dictates the occurance of random events. 
	 * Null if the player chooses to deactivate random events for their game.
	 */
	private RandomEvents random = null;
	/**
	 * Boolean dictating whether the random events should be activated. 
	 * false unless otherwise specified by the player.
	 */
	private boolean activateRandom = false;
	/**
	 * The player's Farm object.
	 */
	private Farm farm;
	
	/**
	 * Constructor for BasicGameLogic with parameters. 
	 * @param info The GameInformation class containing information about the current game 
	 * @param animal The AnimalLogic class for the current game 
	 * @param crop The CropLogic class for the current game
	 */
	public BasicGameLogic(GameInformation info, AnimalLogic animal, CropLogic crop) {
		gameInfo = info;
		animalLogic = animal;
		cropLogic = crop;
		farm = gameInfo.getFarm();
		if (gameInfo.getRandomEventsOn()) {
			random = new RandomEvents(gameInfo);
			activateRandom = true;
		}
	}
	
	
	/**
	 * Advances the number of days by one, and resets the action counter. 
	 * If the current days is the number of days the player requested, the game is over, so don't carry out the operation. 
	 * This function is called directly when the player chooses to advance time. 
	 * @return event A String detailing what happened if anything.
	 */
	public String advanceDays() {
		int currentDays = gameInfo.getCurrentDays();
		int happinessDays = gameInfo.getDecreaseHappinessDays() - 1;
		int requiredDays = gameInfo.getRequiredDays();
		boolean decrease = false;
		
		gameInfo.addOneDay();
		if (happinessDays == 0) {
			decrease = true;
			happinessDays = 1;
		}
		gameInfo.setDecreaseHappinessDays(happinessDays);
		
		String event = "";
		if (currentDays != requiredDays) {
			gameInfo.setNumActions(2);
			cropLogic.dailyDecrease();
			animalLogic.dailyDecrease(decrease);
			if (activateRandom) {
				event = random.triggerRandomEvents();
			}
		}
		return event;
	}
	
	/**
	 * Allows the user to view what items they currently have on their farm. 
	 * Returns the String representation of each item owned by the player. 
	 * This does not decrease daily actions. 
	 * If the user has no items, the string prompts them to visit the store. 
	 * @return String Either textual representations of each of the player's items, or a prompt to buy items from the store.
	 */
	public String viewItems() {
		ArrayList<ItemForAnimal> animalItems = farm.getAnimalItems();
		ArrayList<ItemForCrop> cropItems = farm.getCropItems();
		if (animalItems.size() == 0 && cropItems.size() == 0) {
			return "You don't have any items right now. Please visit the store to buy some.\n";
		} else {
			String returnString = "Items for your animals:\n";
			for (ItemForAnimal item: animalItems) {
				returnString += item.toString() + "\n";
			}
			returnString += "\nItems for your crops:\n";
			for (ItemForCrop item: cropItems) {
				returnString += item.toString() + "\n";
			}
			return returnString+"\n";
		}
	}
	
	/**
	 * Allows the user to view the status of all of their crops. 
	 * Returns a string for each crop, outlining their type and the growing time left. 
	 * This does not count as a daily action; it can thus be called if all actions are performed. 
	 * If the user has no crops, the string prompts them to visit the store. 
	 * @return returnString The string detailing the player's crops. 
	 */
	public String viewCropStatus() {
		ArrayList<Crop> crops = farm.getCrops();
		if (crops.size() == 0) {
			return "You don't seem to have any crops right now. Visit the store to buy some.\n";
		} else {
			String returnString = "";
			for (Crop crop:crops) {
				returnString += crop.toString() + String.format(" Has been growing for %d days.\n", crop.getGrowingTime());
			}
			return returnString;
		}
	}
	
	/**
	 * Allows the user to view the status of all of their animals. 
	 * Returns a string for each animal outlining their type, happiness, and health. 
	 * This does not count as a daily action; it can thus be called if all actions are performed. 
	 * If the user has no animals, the string prompts them to visit the store. 
	 * @return returnString The string detailing the player's animals. 
	 */
	public String viewAnimalStatus() {
		ArrayList<Animal> animals = farm.getAnimals();
		if (animals.size() == 0) {
			return "You don't have any animals right now. Visit the store to buy some.\n";
		} else {
			String returnString = "";
			for (Animal animal: animals) {
				returnString += animal.toString() + "\n";
			}
			return returnString;
		}
	}
	
	
	/**
	 * Allows the player to view the status of their farm. 
	 * Returns a string containing their farm's name, farmer, and total current money. 
	 * Also returns strings containing information about the player's animals and crops. 
	 * This does not count towards daily actions. 
	 * @return returnString A string containing information about the user's farm. 
	 */
	public String viewFarmStatus() {
		String farmName = farm.getName();
		Double farmMoney = farm.getMoney();
		String farmerName = farm.getFarmer().getName();
		
		String returnString = String.format("Your farm %s has a farmer called %s, with total money of $%.2f.\n", farmName, farmerName, farmMoney);
		returnString += viewAnimalStatus() + viewCropStatus() + viewItems();
		return returnString;
	}
	
	/**
	 * Allows the player to tend to their farmland. 
	 * Tending to land increases the crop and animal limits on the farm. 
	 * Also increases the number of days before their animals become unhappy. 
	 * Counts towards the player's daily actions, and as such, can't be performed if the player has no more actions for the day.
	 */
	public void tendToLand() {
		if (gameInfo.getNumActions() == 0) {
			throw new ActionCountException("Sorry, you've performed all your actions for today.");
		} else {
			int cropIncrease = 5;
			int animalIncrease = 5;
	
			farm.changeCropLimit(cropIncrease);
			farm.changeAnimalLimit(animalIncrease);
			gameInfo.setDecreaseHappinessDays(gameInfo.getDecreaseHappinessDays() + 1);
			gameInfo.changeNumActions();
		}
	}
	
}
