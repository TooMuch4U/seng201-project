import java.util.ArrayList;
import java.util.HashMap;

import java.util.InputMismatchException;
import java.util.Map;
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
	 * Allows the user to play with an animal
	 * Prompts the user to choose a specific animal and plays with that animal
	 * @param animals ArrayList of the farms animals
	 * @param game Game environment object
	 */
	public void activatePlayWithAnimals(ArrayList<Animal> animals, GameEnviroBasic game) {
		System.out.println("Which animal would you like to play with?");
		int i = 0;
		for (Animal animal : animals) {
			System.out.printf("%s. %s with a happiness of %s\n", i, animal.getType(), animal.getHappiness());
			i++;
		}
		System.out.printf("%s. Back/Cancel", i);
		
		int userChoice = verifyIntegerInput(0, i);
		
		if (userChoice < i) {
			// User doesn't want to exit
			game.playWithAnimals(userChoice);
			Animal animal = animals.get(userChoice);
			System.out.printf("%s %s now has a happiness of %s.\n", animal.getType(), userChoice, animal.getHappiness());
		}
		
		System.out.println();
		
	}
	
	/**
	 * Allows the user to visit the store
	 * Prompts the user with a list of item types to buy
	 * Then lists all available items of that type
	 * @param game Games environment object
	 * @param farm The games Farm object
	 */
	public void activateVisitStore(GameEnviroBasic game, Farm farm) {
		
		Store store = game.getStore();
		
		System.out.println("What are you looking to buy?");
		System.out.println("0. Items");
		System.out.println("1. Animals");
		System.out.println("2. Crops");
		System.out.println("3. Back/Exit");
		
		int mainCat = verifyIntegerInput(0, 3);
		
		System.out.println();
		
		
		
		if (mainCat == 0) {
			// Buying items
			Item purchaseItem;
			ArrayList<Item> items = store.availableItems;
			
			System.out.println("What item would you like to buy?");
			int i = 0;
			String printString = "";
			
			for (Item item : items) {
				if (item instanceof ItemForCrop) {
					// Item is for crops
					printString = "%s. %s decreases the time until harvest of a crop by %s days.   $%s\n";
				} else {
					// Item is for animals
					printString = "%s. %s increases the health of animals by %s.     $%s\n";
				}
				
				System.out.printf(printString, i, item.getName(), item.getBenefit(), item.getPrice());
				i++;
			}
			System.out.printf("%s. Back/Cancel", i);
			
			int itemIndex = verifyIntegerInput(0, i);
			
			if (itemIndex != i) {
				purchaseItem = items.get(itemIndex);
				System.out.println();
				
				try {
					game.purchaseItem(purchaseItem);
					System.out.printf("You have sucessfully purchased a %s!\n", purchaseItem.getName());
					System.out.printf("Your farm now has a balance of: $%s\n", farm.getMoney());
					
				} catch (IllegalStateException e) {
					System.out.printf("You don't have enough money! A %s costs $%s and you only have $%s!\n", purchaseItem.getName(), purchaseItem.getPrice(), farm.getMoney());
				}	
			}
			
			
		}
		
		if (mainCat == 1) {
			// Buying Animals
			ArrayList<Animal> animals = store.availableAnimals;
			Animal purchaseAnimal;
			
			System.out.println("What animal would you like to buy?");
			int i = 0;
			
			for (Animal animal : animals) {
				System.out.printf("%s. %s. Earns up to $%s per day.    $%s\n", i, animal.getType(), animal.getIncome(), animal.getPrice());
				i++;
			}
			System.out.printf("%s. Back/Cancel\n", i);
			
			int itemIndex = verifyIntegerInput(0, i);
			
			if (itemIndex != i) {
				purchaseAnimal = animals.get(itemIndex);
				System.out.println();
				
				try {
					game.purchaseAnimal(purchaseAnimal);
					System.out.printf("You have sucessfully purchased a %s!\n", purchaseAnimal.getType());
					System.out.printf("Your farm now has a balance of: $%s\n", farm.getMoney());
					
				} catch (IllegalStateException e) {
					System.out.printf("You don't have enough money! A %s costs $%s and you only have $%s!\n", purchaseAnimal.getType(), purchaseAnimal.getPrice(), farm.getMoney());
				} catch (IllegalArgumentException e) {
					System.out.println("Your farm has the maximum number of animals!");
				}
			}
			
			
		}
		
		if (mainCat == 2) {
			// Buying crops
			ArrayList<Crop> crops = store.availableCrops;
			Crop purchaseCrop;
			
			System.out.println("What crop would you like to buy?");
			int i = 0;
			
			for (Crop crop : crops) {
				System.out.printf("%s. %s. Sells for $%s.   $%s\n", i, crop.getType(), crop.getSalePrice(), crop.getPrice());
				i++;
			}
			System.out.printf("%s. Back/Cancel\n", i);
			
			int itemIndex = verifyIntegerInput(0, i);
			
			if (itemIndex != i) {
				purchaseCrop = crops.get(itemIndex);
				System.out.println();
				
				try {
					game.purchaseCrop(purchaseCrop);
					System.out.printf("You have sucessfully purchased a %s!\n", purchaseCrop.getType());
					System.out.printf("Your farm now has a balance of: $%s\n", farm.getMoney());
					
				} catch (IllegalStateException e) {
					System.out.printf("You don't have enough money! A %s costs $%s and you only have $%s!\n", purchaseCrop.getType(), purchaseCrop.getPrice(), farm.getMoney());
				} catch (IllegalArgumentException e) {
					System.out.println("Your farm has the maximum number of crops!");
				}
			} 
			
		}
		
		System.out.println();
	}
	
	/**
	 * Allows the user to harvest crops that are ready to be harvested
	 * Prompts the user with a list of crops
	 * @param crops ArrayList of crops in the farm
	 * @param game Game environment object
	 */
	public void activateHarvestCrops(ArrayList<Crop> crops, GameEnviroBasic game) {
		
		ArrayList<Crop> cropsReady = new ArrayList<Crop>();
		Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
		int i = 0;
		for (Crop crop : crops) {
			if (crop.getHarvestTime() == 0) {
				cropsReady.add(crop);
				indexMap.put(cropsReady.size() - 1, i);
			}
			i++;
		}
		
		if (cropsReady.size() > 0) {
			System.out.println("What crop would you like to harvest?");
			
			i = 0;
			for (Crop crop : cropsReady) {
				System.out.printf("%s. %-15s  +$%s\n", i, crop.getType(), crop.getSalePrice());
				i++;
			}
			
			System.out.printf("%s. Back/Cancel\n", i);
			int choice = verifyIntegerInput(0, i);
			
			if (choice != i) {
				int cropIndex = indexMap.get(choice);
				Crop harvestCrop = crops.get(cropIndex);
				game.harvestCrops(cropIndex);
				System.out.printf("Harvested %s for $%s.\n", harvestCrop.getType(), harvestCrop.getSalePrice());
			}
			
		} else {
			System.out.println("Your farm doesn't have any crops that are ready to be harvested!");
		}
		
		System.out.println();
		
	}
	
	public void endGame(GameEnviroBasic game) {
		EndScreen endScreen = game.endGame();
		System.out.printf("Finished with a score of %s\n", endScreen.displayScore());
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
		
		while (game.getCurrentDays() < numDays && !(game.gameEnded)) {
			System.out.println("Please select an activity you would like to perform:");
			System.out.println("1. Go to the next day\n2. View the status of your crops\n3. View the status of your animals\n4. View the status of your farm");
			System.out.println("5. Feed your animals\n6. Tend to your crops");
			System.out.println("7. Play with an animal\n8. Harvest your crops\n9. Tend to your land\n10. Visit the store\n11. End game");
			int activitySelect = verifyIntegerInput(1,11);
			
			ArrayList<Animal> animals = farm.getAnimals();
			int animalNum = animals.size();
			ArrayList<Crop> crops = farm.getCrops();
			int numCrops = crops.size();
			
			switch(activitySelect)
			{
			case 1:
				game.advanceDays();
				game.endOfDay();
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
				ArrayList<Item> itemsList = farm.items;
				int numItems = itemsList.size();
				if (numCrops == 0) {
					System.out.println("Sorry, you don't have any crops at the moment");
					break;
				}
				
				activateTendToCrops(crops, itemsList, numCrops, numItems, game);
				
				break;
			case 7:
				if (animalNum == 0) {
					System.out.println("Sorry, you don't have any animals to play with");
					break;
				} 
				
				activatePlayWithAnimals(animals, game);
				break;
			case 8:
				if (numCrops == 0) {
					System.out.println("Sorry, you don't have any crops at the moment");
					break;
				}
				
				activateHarvestCrops(crops, game);
				
				break;
			case 9:
				game.tendToLand();
			case 10:
				activateVisitStore(game, farm);
				break;
			case 11:
				endGame(game);
				break;
			} //Closes switch
			
			if (game.getCurrentDays() >= numDays) {
				endGame(game);
			}
			
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
