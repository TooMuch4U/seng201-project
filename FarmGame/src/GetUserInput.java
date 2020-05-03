import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import animals.Animal;
import crops.Crop;
import items.*;

public class GetUserInput {
	
	private static Scanner userInput = new Scanner(System.in);
	
	public static void endInput() {
		System.out.println("Thank you for playing!");
	}
	
	public static void main(String[] args) {
		System.out.println("Please enter your name:");
		String farmerName = userInput.nextLine();
		System.out.println("You named your farmer " +farmerName);
		System.out.println("Please enter your farm's name:");
		String farmName = userInput.nextLine();
		System.out.println("Please enter your farmer's age");
		int farmerAge = userInput.nextInt();
		System.out.println("Please enter how many days you want to play for:");
		int numDays = userInput.nextInt();
		System.out.println(farmName);
		System.out.println(numDays);
		System.out.println("Please enter the number of the kind of farm you would like to play as:\n1. Basic: no additional bonuses\n2. Trust Fund Farm: extra monetary bonus at the start of the game");
		System.out.println("3. Nutrient Soil: Crops have a faster growing time\n4. Livestock Land: Animals start happier and healthier");
		int farmIndex = userInput.nextInt();
		
		Farmer farmer = new Farmer(farmerName, farmerAge);
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
		
		GameEnviroBasic game = new GameEnviroBasic(numDays, farm);
		while (game.getCurrentDays() < numDays) {
			System.out.println("Please select an activity you would like to perform:");
			System.out.println("1. Go to the next day\n2. View the status of your crops\n3. View the status of your animals\n4. View the status of your farm");
			System.out.println("5. Feed your animals\n6. Tend to your crops");
			int activitySelect = userInput.nextInt();
			
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
				System.out.println("Which animal would you like to feed?");
				for (int i = 0; i < animalNum; i++) {
					Animal animal = animals.get(i);
					System.out.println("Animal " + i + "has health of " + animal.getHealth());
				}
				int animalIndex = userInput.nextInt();
				System.out.println("Which item would you like to use?");
				for (int j = 0; j < itemNum; j++) {
					Item item = itemList.get(j);
					System.out.println("Item number " + j + ": " + item.getName() + " adds " + item.getBenefit() + " health");
				}
				int itemInput = userInput.nextInt();
				ItemForAnimal selectItem = (ItemForAnimal) itemList.get(itemInput);
				itemList.remove(itemInput);
				game.feedAnimals(selectItem, animalIndex);
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
				System.out.println("Which crop would you like to tend to?");
				for (int i = 0; i < numCrops; i++) {
					Crop crop = crops.get(i);
					System.out.println("Crop " + i + ", a " + crop.getType() + ", has remaining grow time of " + crop.getHarvestTime());
				}
				int cropIndex = userInput.nextInt();
				
				System.out.println("Would you like to use an item?\nType 0 for No, or 1 for Yes.");
				int confirmation = userInput.nextInt();
				if (confirmation == 0 || numItems == 0) {
					game.tendToCrops(cropIndex);
				} else {
					System.out.println("What item would you like to use?");
					for (int j = 0; j < numItems; j++) {
						Item item = itemsList.get(j);
						System.out.println("Item number " + j + ": " + item.getName() + " adds " + item.getBenefit() + " health");
					}
					int chosenCropItem = userInput.nextInt();
					ItemForCrop cropItem = (ItemForCrop) itemsList.get(chosenCropItem);
					game.tendToCrops(cropItem, cropIndex);
				} //Closes if gate
				break;				
			} //Closes switch
			
		} //Closes while loop
	
	} //Closes main
	
	
}
