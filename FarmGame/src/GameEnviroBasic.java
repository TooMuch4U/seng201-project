
import java.util.ArrayList;
import java.util.Random;

import animals.*;
import crops.*;
import items.*;

public class GameEnviroBasic {
	
	/**
	 * The player's farm
	 */
	private Farm farm;
	/**
	 * The number of days the player wishes to play for
	 */
	private int requiredDays;
	/**
	 * A random number generator used to determine random events
	 */
	private Random rng = new Random();
	/**
	 * The current day the player is on
	 */
	private int currentDays = 0;
	/**
	 * The number of actions permittable in a day
	 */
	private int numActions = 2;
	/**
	 * The store object
	 */
	private Store store = new Store();
	/**
	 * A boolean to determine whether the player can continue playing
	 */
	public boolean gameEnded = false;
	/**
	 * An int that dictates how many days will pass before animal happiness decreases
	 */
	private int decreaseHappinessDays = 1;
	/**
	 * A boolean value dictating whether random events are turned on for the game
	 */
	private boolean randomEventsOn;
	
	/**
	 * Constructor without parameters.
	 * Used with GUI implementation of the application.
	 */
	public GameEnviroBasic() {}
	
	/**
	 * Sets the value of the player's farm.
	 * Used only in the GUI implementation.
	 * @param playerFarm - the player's farm, created using information on the set-up screen.
	 */
	public void setFarm(Farm playerFarm) {
		farm = playerFarm;
	}
	
	/**
	 * Sets the desired amount of days for the player.
	 * Used only in the GUI implementation.
	 * @param desiredDays - the amount of days the player wants to play for.
	 */
	public void setRequiredDays(int desiredDays) {
		requiredDays = desiredDays;
	}
	
	/**
	 * Returns the store object.
	 * @return store - the store object
	 */
	public Store getStore() {
		return store;
	}
	
	/**
	 * Returns the current value of numActions.
	 * @return numActions - the number of actions left in the day.
	 */
	public int getNumActions() {
		return numActions;
	}
	
	/**
	 * Returns the number of days currently surpassed.
	 * @return currentDays - the number of days the game has gone for so far.
	 */
	public int getCurrentDays() {
		return currentDays;
	}
	
	/**
	 * Returns the number of days the game must continue for.
	 * @return requiredDays - the number of days the player wanted the game to go for.
	 */
	public int getRequiredDays() {
		return requiredDays;
	}
	
	/**
	 * Returns the player's farm object.
	 * @return farm - the player's farm.
	 */
	public Farm getFarm() {
		return farm;
	}
	
	/**
	 * Sets the number of actions permittable in a single day.
	 * @param number - the number of actions that can be completed in one day.
	 */
	public void setNumActions(int number) {
		numActions = number;
	}
	
	/**
	 * Sets whether the random events occur during gameplay or not
	 * @param accept - true if random events will occur
	 */
	public void setRandomEventsOn(boolean accept) {
		randomEventsOn = accept;
	}
	
	/**
	 * Launches the main GUI screen of the game.
	 */
	public void launchMainScreen() {
		MainScreen mainWindow = new MainScreen(this);
	}
	
	/**
	 * Closes the main GUI screen of the game.
	 * @param mainWindow - the main screen of the GUI.
	 */
	public void closeMainScreen(MainScreen mainWindow) {
		mainWindow.closeWindow();
	}
	
	/**
	 * Closes the main game screen and opens the animal feeding GUI screen.
	 * @param mainWindow - the main screen of the GUI. Passed in to close the screen.
	 */
	public void launchFeedAnimalScreen(MainScreen mainWindow) {
		if (numActions == 0) {
			throw new ActionCountException("All actions performed for the day");
		} else {
			closeMainScreen(mainWindow);
			FeedAnimalScreen animalWindow = new FeedAnimalScreen(this);
		}
	}
	
	/**
	 * Closes the animal feeding screen and opens the main screen of the GUI.
	 * @param animalWindow - the animal feeding screen of the GUI.
	 */
	public void closeFeedAnimalScreen(FeedAnimalScreen animalWindow) {
		animalWindow.closeWindow();
		launchMainScreen();
	}
	
	/**
	 * Closes the main game screen and opens the crop tending GUI screen.
	 * @param mainWindow - the main screen of the GUI. Passed in to close the screen.
	 */
	public void launchTendCropsScreen(MainScreen mainWindow) {
		if (numActions == 0) {
			throw new ActionCountException("All actions performed for the day");
		} else {
			closeMainScreen(mainWindow);
			TendCropsScreen cropsWindow = new TendCropsScreen(this);
		}
	}
	
	/**
	 * Closes the crop tending screen and opens the main game screen.
	 * @param cropsWindow - the crop tending screen of the GUI.
	 */
	public void closeTendCropsScreen(TendCropsScreen cropsWindow) {
		cropsWindow.closeWindow();
		launchMainScreen();
	}
	
	/**
	 * Launches the setup screen of the GUI.
	 */
	public void launchSetupScreen(GameEnviroBasic game) {
		SetupScreen setupWindow = new SetupScreen(game);
	}
	
	/**
	 * Closes the setup screen of the GUI.
	 * @param setupWindow - the setup screen of the GUI.
	 */
	public void closeSetupScreen(SetupScreen setupWindow) {
		setupWindow.closeWindow();
		launchMainScreen();
	}
	
	/**
	 * Launches the stores main screen GUI.
	 */
	public void launchStoreMainScreen() {
		StoreMainScreen storeMain = new StoreMainScreen(this);
		
	}
	
	/**
	 * Closes the store main screen.
	 * @param storeMain - The store main screen.
	 */
	public void closeStoreMainScreen(StoreMainScreen storeMain) {
		storeMain.closeWindow();
		launchMainScreen();
	}
	
	/**
	 * Launches the stores animal screen GUI.
	 * @param storeMain - The store main screen GUI.
	 */
	public void launchStoreAnimalScreen(StoreMainScreen storeMain) {
		storeMain.closeWindow();
		StoreAnimalScreen storeAnimal = new StoreAnimalScreen(this);
	}
	
	/**
	 * Closes the store animal screen GUI.
	 * @param storeAnimal - Store animal GUI to be closed.
	 */
	public void closeStoreAnimalScreen(StoreAnimalScreen storeAnimal) {
		storeAnimal.closeWindow();
		launchStoreMainScreen();
	}
	
	/**
	 * Launches the store crop screen GUI.
	 */
	public void launchStoreCropScreen() {
		StoreCropScreen cropScreen = new StoreCropScreen(this);
	}
	
	/**
	 * Closes the store crop screen GUI.
	 * @param cropScreen - store crop screen GUI object to be closed.
	 */
	public void closeStoreCropScreen(StoreCropScreen cropScreen) {
		cropScreen.closeWindow();
		launchStoreMainScreen();
	}
	
	/**
	 * Launches the Store Item Screen of the GUI.
	 */
	public void launchStoreItemScreen() {
		StoreItemScreen itemScreen = new StoreItemScreen(this);
	}
	
	/**
	 * Closes the Store Item screen of the GUI.
	 * @param itemScreen - the item screen GUI object to be closed.
	 */
	public void closeStoreItemScreen(StoreItemScreen itemScreen) {
		itemScreen.closeWindow();
		launchStoreMainScreen();
	}
	
	/**
	 * Launches the select crop screen object of the GUI, and closes the main screen.
	 * Throws an action count exception if the player has no actions left for the day.
	 * @param main - the main screen GUI object that needs to be closed.
	 */
	public void launchSelectCropScreen(MainScreen main) {
		if (numActions == 0) {
			throw new ActionCountException("All actions performed for the day");
		} else {
			main.closeWindow();
			SelectCropScreen cropScreen = new SelectCropScreen(this);
		}
	}
	
	/**.
	 * Closes the select crop screen object of the GUI, and relaunches the main screen.
	 * @param cropScreen - the crop select GUI object that needs to be closed.
	 */
	public void closeSelectCropScreen(SelectCropScreen cropScreen) {
		cropScreen.closeWindow();
		launchMainScreen();
	}
	
	/**
	 * Launches the select animal screen object of the GUI, and closes the main screen.
	 * Throws an ActionCountException if the player has performed all tasks for the day.
	 * @param main - the main screen GUI object that needs to be closed.
	 */
	public void launchSelectAnimalScreen(MainScreen main) {
		if (numActions == 0) {
			throw new ActionCountException("Sorry, you've performed all actions for the day");
		} else {
			main.closeWindow();
			SelectAnimalScreen animalScreen = new SelectAnimalScreen(this);
		}
	}
	
	/**
	 * Closes the select animal screen object of the GUI, and relaunches the main screen of the game.
	 * @param animalScreen - the select animal screen GUI object that needs to be closed.
	 */
	public void closeSelectAnimalScreen(SelectAnimalScreen animalScreen) {
		animalScreen.closeWindow();
		launchMainScreen();
	}
	
	/**
	 * Closes the main screen of the game, and launches the final score screen.
	 * @param main - the main screen GUI object that needs to be closed.
	 */
	public void launchScoreScreen(MainScreen main) {
		main.closeWindow();
		ScoreScreen scoreScreen = new ScoreScreen(this);
	}
	
	/**
	 * Closes the final score screen, and launches a new set-up screen for the player.
	 * Called if the player chooses to start a new game.
	 * @param score - The score screen GUI object that needs to be closed.
	 */
	public void closeScoreScreen(ScoreScreen score) {
		score.closeWindow();
		launchSetupScreen(new GameEnviroBasic());
	}
	
	
	/**
	 * Advances the number of days by one, and resets the action counter.
	 * If the current days is the number of days the player requested, the game is over.
	 * Generates and displays the score, and prompts the user to play again.
	 * This function is called directly when the player chooses to advance time.
	 */
	public String advanceDays() {
		boolean decrease = false;
		currentDays += 1;
		decreaseHappinessDays -= 1;
		
		if (decreaseHappinessDays == 0) {
			decrease = true;
			decreaseHappinessDays = 1;
		}
		
		String event = "";
		if (currentDays != requiredDays) {
			setNumActions(2);
			ArrayList<Crop> crops = farm.getCrops();
			ArrayList<Animal> animals = farm.getAnimals();
			for (Crop crop: crops) {
				crop.changeHarvestTime(1);
				crop.increaseGrowTime();
			}
			for (Animal animal: animals) {
				double income = animal.getIncome();
				farm.changeMoney(income);
				animal.changeHealth(-2);
				if (decrease) {
					animal.changeHappiness(-2);
				}
			}
			if (randomEventsOn) {
				int randNum = rng.nextInt();
				event = randomEvents(randNum);
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
	 * Removes a random number of the animals from the farm using the game environment's random number generator.
	 * If an animal doesn't escape, it loses a substantial amount of happiness.
	 * A maximum of half of the player's animals can escape. The exact number is generated using the rng.
	 * Returns a string detailing the event.
	 * If the player has one or no animals, nothing happens, and the returned string contains nothing.
	 * @return - a string detailing the event. Returns an empty string if the player doesn't have enough animals.
	 */
	public String removeRandomAnimals() {
		ArrayList<Animal> animals = farm.getAnimals();
		if (animals.size() < 2) {
			return "";
		} else {
			int maxNum = animals.size()/2;
			int numRequired = rng.nextInt()%maxNum;
			int i = 0;
			ArrayList<Integer> needRemoving = new ArrayList<Integer>();
			while(numRequired > 0 && i < animals.size()) {
				if (rng.nextInt()%2 == 0) {
					//Keep track of the indices that need removing. Stored from highest index to lowest.
					needRemoving.add(0, i);
					numRequired -= 1;
				} else {
					Animal animal = animals.get(i);
					animal.changeHappiness(-20);
				}
				i += 1;
			}
			//Remove indices in top-down order
			for (int num: needRemoving) {
				animals.remove(num);
			}
			return "Overnight, your fence broke, and some of your animals escaped.\nThe ones that didn't are now sad that their friends are gone.";
		}
	}
	
	/**
	 * Removes half of the crops from the farm using a random number generator.
	 * Uses a while loop to ensure that the number of crops removed is adequate.
	 * If the rng generates an odd number, or the remaining crops in the list need to be removed, the next crop will be removed.
	 * @return - A String detailing the event. If the player has one or no crops, returns an empty string.
	 * 
	 */
	public String removeHalfCrops() {
		ArrayList<Crop> crops = farm.getCrops();
		int cropNum = crops.size();
		int numRequired = cropNum/2;
		int i = 0;
		ArrayList<Integer> needRemoving = new ArrayList<Integer>();
		while(numRequired > 0) {
			if (numRequired == (cropNum - (i+1)) || rng.nextInt()%2 == 1) {
				//Keep track of the indices that need removing. Stored from highest index to lowest.
				needRemoving.add(0, i);
				numRequired -= 1;
			}
			i += 1;
		}
		//Remove indices in top-down order
		for (int num: needRemoving) {
			crops.remove(num);
		}
		if (cropNum < 2) {
			return "";
		} else {
			return "A drought has struck, and your crops are thirsty.\nHalf of them die from lack of water.";
		}
	}
	
	/**
	 * The triggering function for the random events.
	 * Utilizes a random number generator to determine whether the events happen or not.
	 * County Fair - 10% chance of occurring - player gets a bonus sum of money depending on the numbers of crops and animals they have.
	 * Broken Fence - 5% chance of occurring - player loses half of their farm's animals. The animals are chose randomly using removeHalfAnimals().
	 * Drought - 5% chance of occurring - player loses half of their farm's crops. The crops are chose randomly using removeHalfCrops().
	 * @return eventInfo - a string detailing what event happened, if any.
	 */
	public String randomEvents(int randNum) {
		String eventInfo = "";
		if(randNum%10 == 2) {
			//County fair: win a bonus amount of money 
			int animalNum = farm.getAnimals().size();
			int cropNum = farm.getCrops().size();
			double moneyGain = 25*animalNum + 10*cropNum;		
			
			eventInfo = String.format("Your farm won first prize at the county fair!\nYou gain an extra $%.2f for your spectacular animals and crops.", moneyGain);
			farm.changeMoney(moneyGain);
			
		} else if (randNum%20 == 5) {
			//Broken fence: animals escape
			eventInfo = removeRandomAnimals();
		} else if (randNum%20 == 19 ) {
			//Drought: crops die
			eventInfo = removeHalfCrops();
		}
		return eventInfo;
	}
	
	
	/**
	 * Allows the user to feed an animal on their farm.
	 * Counts as a daily action, and as such can't be performed if all actions are completed.
	 * @param feedItem - the ItemForAnimal that will be used to increase the animal's health.
	 * @param animalIndex - the index of the animal within the list that the user wishes to feed.
	 */
	public void feedAnimals(ItemForAnimal feedItem, int animalIndex) {
		ArrayList<Animal> animals = farm.getAnimals();
		double healthBonus = feedItem.getBenefit();
		Animal chosenAnimal = animals.get(animalIndex);
		chosenAnimal.changeHealth(healthBonus);
		farm.getItems().remove(feedItem);
		numActions -= 1;
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
		numActions -= 1;
	}
	
	/**
	 * Play with animals, increases the happiness of an animal in the farm.
	 * @param animalIndex takes the integer of the position in the arrayList to get the animal.
	 */
	public void playWithAnimals(int animalIndex) {

		double change = 5.0; // Change constant to increase animals happiness by
		ArrayList<Animal> Animals = farm.getAnimals();
		Animal animal = Animals.get(animalIndex);
		animal.changeHappiness(change);
		numActions -= 1;

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
		numActions -= 1;		
	}
	
	/**
	 * Tending to land increases the crop and animal limits on the farm.
	 */
	public void tendToLand() {
		int cropIncrease = 5;
		int animalIncrease = 5;

		farm.changeCropLimit(cropIncrease);
		farm.changeAnimalLimit(animalIncrease);
		decreaseHappinessDays += 1;
		numActions -= 1;		
	}
	
	/**
	 * Processes the payment of a store item.
	 * The farm must have enough money to make the purchase price.
	 * @param price The price of the item being purchased.
	 */
	private void processStoreItem(double price) {
		if (farm.getMoney() >= price) {
			// Farm can afford the item
			farm.changeMoney(- price);

		} else {
			throw new IllegalStateException("There is not enough money to purchase this");
		}
	}
	
	/**
	 * Purchases an item from the store if the farm has enough money.
	 * @param item The item the store would like to purchase.
	 */
	public void purchaseItem(Item item) {
		double itemPrice = item.getPrice();
		processStoreItem(itemPrice);
		if (item instanceof ItemHoe) {
			farm.addItem(new ItemHoe());
		} else if (item instanceof ItemFertilizer) {
			farm.addItem(new ItemFertilizer());
		} else if (item instanceof ItemGrowLight) {
			farm.addItem(new ItemGrowLight());
		} else if (item instanceof ItemApple) {
			farm.addItem(new ItemApple());
		} else if (item instanceof ItemMilk) {
			farm.addItem(new ItemMilk());
		} else if (item instanceof ItemSteroids) {
			farm.addItem(new ItemSteroids());
		}

	}
	
	/**
	 * Purchases an animal from the store if the farm has enough money.
	 * @param animal The animal that is to be purchased.
	 */
	public void purchaseAnimal(Animal animal) {
		double price = animal.getPrice();
		processStoreItem(price);
		if (animal instanceof AnimalPig) {
			farm.addAnimal(new AnimalPig());
		} else if (animal instanceof AnimalCow) {
			farm.addAnimal(new AnimalCow());
		} else if (animal instanceof AnimalChicken) {
			farm.addAnimal(new AnimalChicken());
		}
	}

	/**
	 * Purchases a crop from the store if the farm has enough money.
	 * @param crop The crop that is to be purchased.
	 */
	public void purchaseCrop(Crop crop) {
		double price = crop.getPrice();
		processStoreItem(price);
		if (crop instanceof CropCorn) {
			farm.addCrop(new CropCorn());
		} else if (crop instanceof CropCabbage) {
			farm.addCrop(new CropCabbage());
		} else if (crop instanceof CropCauliflower) {
			farm.addCrop(new CropCauliflower());
		} else if (crop instanceof CropPotato) {
			farm.addCrop(new CropPotato());
		} else if (crop instanceof CropTomato) {
			farm.addCrop(new CropTomato());
		} else if (crop instanceof CropWheat) {
			farm.addCrop(new CropWheat());
		}
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
		gameEnded = true;
		EndScreen end = new EndScreen(farm);
		return end;
	}
	
	/**
	 * Main method of the game object.
	 * Calls the setup screen launcher when triggered.
	 */
	public static void main(String[] args) {
		GameEnviroBasic game = new GameEnviroBasic();
		game.launchSetupScreen(game);
	}
	
}
