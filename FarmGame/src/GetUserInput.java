import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import animals.Animal;
import crops.Crop;
import items.*;

public class GetUserInput {
	
	private Scanner userInput = new Scanner(System.in);
	
	/**
	 * Ensures that the scanner stops asking for user input
	 * Thanks the player for playing the game
	 */
	public void endInput() {
		System.out.println("Play Again?");
	}
	
	/**
	 * Verifies that the value the user has input is an Integer, and is within bounds
	 * Prompts the user to enter another number if the input is not an integer, or if the Integer is not within bounds
	 * @return value - The first valid Integer value the player inputs
	 */
	public int verifyIntegerInput(int lowerLim, int upperLim) {
		boolean isType = false;
		boolean isBound = false;
		int value = 0;
		while (isType == false && isBound == false) {
			try {
				value = userInput.nextInt();
				isType = true;
				if (value > upperLim || value < lowerLim) {
					System.out.println("Please enter a number between " + lowerLim + " and " + upperLim + ".");
					isType = false;
				} else {
					isBound = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("That was not an integer. Please enter a positive whole number between " + lowerLim + " and " + upperLim +" inclusive:");
				userInput.nextLine(); //Eliminate whitespace
			}
		}
		return value;
	}
	
	
	/**
	 * Allows the user to create their own farmer
	 * Prompts the user to enter their name and age, and passes these to the Farmer constructor
	 * @return farmer - The Farmer object created by the player
	 */
	public Farmer createFarmer() {
		System.out.println("Please enter your name:");
		String farmerName = userInput.nextLine();
		System.out.println("Please enter your farmer's age");
		int farmerAge = verifyIntegerInput(0,100);
		Farmer farmer = new Farmer(farmerName, farmerAge);
		return farmer;
	}
	
	/**
	 * Allows the user to create their farm
	 * Prompts the user to input the farm's name and type
	 * Uses a switch function to determine what type of farm constructor is called
	 * @param farmer - The Farmer object created by the user
	 * @return farm - the Farm object created from the player's specifications
	 */
	public Farm createFarm(Farmer farmer) {
		userInput.nextLine(); //Strips trailing whitespace
		System.out.println("Please enter your farm's name:");
		
		String farmName = userInput.nextLine();
		
		System.out.println("Please enter the number of the kind of farm you would like to play as:\n1. Basic: no additional bonuses\n2. Trust Fund Farm: extra monetary bonus at the start of the game");
		System.out.println("3. Nutrient Soil: Crops have a faster growing time\n4. Livestock Land: Animals start happier and healthier");
		//int farmIndex = userInput.nextInt();
		int farmIndex = verifyIntegerInput(1, 4);
		Farm farm = null;
		
		switch(farmIndex)
		{
		case 1:
			farm = new FarmBasic(farmName, farmer);
			break;
		case 2:
			farm = new FarmRich(farmName, farmer);
			break;
		case 3:
			farm = new FarmGoodSoil(farmName, farmer);
			break;
		case 4:
			farm = new FarmGoodAnimal(farmName, farmer);
			break;
		}
		
		return farm;
	}
	
	/**
	 * Allows the user to feed their animals
	 * Prompts the user to select the animal they would like to feed, and the item they would like to feed them
	 * If the player does not have any animals, or any items that they can feed the animal, they are informed that they can't perform this action and prompted to choose another
	 * This does not count as a daily action
	 * @param animals - An ArrayList containing the animals currently on the player's farm
	 * @param items - An ArrayList containing the items currently owned by the player
	 * @param animalNum - An integer of animals currently on the player's farm
	 * @param itemNum - An integer of items currently owned by the player
	 * @param game - The game environment object
	 */
	public void activateFeedAnimals(ArrayList<Animal> animals, ArrayList<Item> items, int animalNum, int itemNum, GameEnviroBasic game) {
		System.out.println("Which animal would you like to feed?");
		for (int i = 0; i < animalNum; i++) {
			Animal animal = animals.get(i);
			System.out.println("Animal " + i + "has health of " + animal.getHealth());
		}
		int animalIndex = verifyIntegerInput(0, animalNum-1);
		
		System.out.println("Which item would you like to use?");
		for (int j = 0; j < itemNum; j++) {
			Item item = items.get(j);
			System.out.println("Item number " + j + ": " + item.getName() + " adds " + item.getBenefit() + " health");
		}
		int itemInput = verifyIntegerInput(0, itemNum-1);
		
		ItemForAnimal selectItem = (ItemForAnimal) items.get(itemInput);
		items.remove(itemInput);
		game.feedAnimals(selectItem, animalIndex);
	}
	
	/**
	 * Allows the user to attend to their crops
	 * Prompts the user to choose a specific crop and item, then applies that item to the crop
	 * If the user does not have any crops, they are asked to choose a different activity. This does not count as an action for the day
	 * If the user has no items or chooses not to use one, the free watering option is used
	 * @param crops - An ArrayList containing the crops on the player's farm
	 * @param items - An ArrayList containing the items owned by the player
	 * @param cropNum - An integer of crops on the player's farm
	 * @param itemNum - An integer of items owned by the player
	 * @param game - The game environment object
	 */
	public void activateTendToCrops(ArrayList<Crop> crops, ArrayList<Item> items, int cropNum, int itemNum, GameEnviroBasic game) {
		System.out.println("Which crop would you like to tend to?");
		for (int i = 0; i < cropNum; i++) {
			Crop crop = crops.get(i);
			System.out.println("Crop " + i + ", a " + crop.getType() + ", has remaining grow time of " + crop.getHarvestTime());
		}
		int cropIndex = verifyIntegerInput(0, cropNum-1);
		
		if (itemNum == 0) {
			game.tendToCrops(cropIndex);
		} else {
			System.out.println("Would you like to use an item?\nType 0 for No, or 1 for Yes.");
			int confirmation = verifyIntegerInput(0,1);
			if (confirmation == 0) {
				game.tendToCrops(cropIndex);
			} else {
				System.out.println("What item would you like to use?");
				for (int j = 0; j < itemNum; j++) {
					Item item = items.get(j);
					System.out.println("Item number " + j + ": " + item.getName() + " adds " + item.getBenefit() + " health");
				}
				int itemIndex = verifyIntegerInput(0, itemNum-1);
				ItemForCrop cropItem = (ItemForCrop) items.get(itemIndex);
				game.tendToCrops(cropItem, cropIndex);
			}
		}
	}
	
	/**
	 * Contains the main process for playing the game
	 * Uses a switch function to call different activities depending on user input
	 * Uses a while loop to ensure that the user can't interact with the game once it is over
	 * @param game - the game environment
	 * @param numDays - the number of days specified by the player
	 * @param farm - the farm created by the player
	 */
	public void playGame(GameEnviroBasic game, int numDays, Farm farm) {
		
		while (game.getCurrentDays() < numDays) {
			System.out.println("Please select an activity you would like to perform:");
			System.out.println("1. Go to the next day\n2. View the status of your crops\n3. View the status of your animals\n4. View the status of your farm");
			System.out.println("5. Feed your animals\n6. Tend to your crops");
			int activitySelect = verifyIntegerInput(1,6);
			
			switch(activitySelect)
			{
			case 1:
				game.advanceDays();
				break;
			case 2:
				game.viewCropStatus();
				break;
			case 3:
				game.viewAnimalStatus();
				break;
			case 4:
				game.viewFarmStatus();
				break;
			case 5:
				ArrayList<Animal> animals = farm.getAnimals();
				int animalNum = animals.size();
				ArrayList<Item> itemList = farm.items;
				int itemNum = itemList.size();
				if (animalNum == 0) {
					System.out.println("Sorry, you don't have any animals to feed");
					break;
				} else if (itemNum == 0) {
					System.out.println("Sorry, you don't have any food items");
					break;
				}
				
				activateFeedAnimals(animals, itemList, animalNum, itemNum, game);
				
				break;
			case 6:
				ArrayList<Crop> crops = farm.getCrops();
				int numCrops = crops.size();
				ArrayList<Item> itemsList = farm.items;
				int numItems = itemsList.size();
				if (numCrops == 0) {
					System.out.println("Sorry, you don't have any crops at the moment");
					break;
				}
				
				activateTendToCrops(crops, itemsList, numCrops, numItems, game);
				
				break;				
			} //Closes switch
			
		} //Closes while loop
		endInput();
	}
	
	/**
	 * Creates all objects needed for the user to play the game
	 * Passes these objects into playGame()
	 */
	public void startGame() {
		
		Farmer farmer = createFarmer();
		Farm farm = createFarm(farmer);
		
		System.out.println("Please enter how many in-game days you want to play for:");
		int numDays = verifyIntegerInput(5,100);
		
		GameEnviroBasic game = new GameEnviroBasic(numDays, farm);
		playGame(game, numDays, farm);
		
	}
	
	public static void main(String[] args) {
		GetUserInput newGame = new GetUserInput();
		newGame.startGame();
	}
}
