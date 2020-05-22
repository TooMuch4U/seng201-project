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
	
	private GameInformation gameInfo;
	private AnimalLogic animalLogic;
	private CropLogic cropLogic;
	private RandomEvents random;
	private boolean activateRandom = false;
	private Farm farm;
	
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
	 * If the current days is the number of days the player requested, the game is over.
	 * Generates and displays the score, and prompts the user to play again.
	 * This function is called directly when the player chooses to advance time.
	 * @return event - The random event
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
	 * @return String - either textual representations of each of the player's items, or a prompt to buy items from the store.
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
	 * @return returnString - the string detailing the player's crops.
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
	 * @return returnString - the string detailing the player's animals.
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
	 * @return returnString - a string containing information about the user's farm.
	 */
	public String viewFarmStatus() {
		String farmName = farm.getName();
		String farmMoney = Double.toString(farm.getMoney());
		String farmerName = farm.getFarmer().getName();
		
		String returnString = "Your farm "+farmName+" has a farmer called "+farmerName+", with total money of "+farmMoney + ".\n";
		returnString += viewAnimalStatus() + viewCropStatus() + viewItems();
		return returnString;
	}
	
	/**
	 * Tending to land increases the crop and animal limits on the farm.
	 */
	public void tendToLand() {
		int cropIncrease = 5;
		int animalIncrease = 5;

		farm.changeCropLimit(cropIncrease);
		farm.changeAnimalLimit(animalIncrease);
		gameInfo.setDecreaseHappinessDays(gameInfo.getDecreaseHappinessDays() + 1);
		gameInfo.changeNumActions();		
	}
	
	/**
	 * Method that is run at the end of each day.
	 * Tallies up the farms score for the day.
	 */
	public void endOfDay() {
		int incr = 0;

		ArrayList<Animal> animals = farm.getAnimals();

		// Add score from animals
		for (Animal animal : animals) {
			int hap = (int) animal.getHappiness();
			incr = incr + hap;
		}

		farm.incrBonus(incr);

	}
	
	/**
	 * Returns the end screen
	 * @return EndScreen
	 */
	public EndScreen endGame() {
		EndScreen end = new EndScreen(farm);
		return end;
	}
	
}
