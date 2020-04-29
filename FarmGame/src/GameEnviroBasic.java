import java.util.ArrayList;
import java.util.Random;

import animals.*;
import crops.*;
import items.*;

public class GameEnviroBasic {
	
	Farmer farmer = new Farmer();
	Farm farm = new FarmBasic("Test Farm", farmer);
	Random rng = new Random();
	
	/**
	 * Adds details of the farm
	 * Used as a basic debugging tool
	 */
	public void addDetails() {
		ArrayList<Animal> animals = new ArrayList<Animal>();
		ArrayList<Crop> crops = new ArrayList<Crop>();
		
		AnimalCow cow = new AnimalCow();
		AnimalPig pig = new AnimalPig();
		CropWheat wheat = new CropWheat();
		CropCorn corn = new CropCorn();
		
		for(int i = 0; i < 10; i += 1) {
			farm.addAnimal(cow);
			farm.addCrop(wheat);
			animals.add(pig);
			crops.add(corn);
		}
	}
	
	/**
	 * Allows the user to view the status of all of their crops
	 * Prints a string for each crop, outlining their type and the growing time left.
	 */
	public void viewCropStatus() {
		String returnString = "";
		ArrayList<Crop> crops = farm.getCrops();
		for (Crop crop:crops) {
			String cropTime = Integer.toString(crop.getHarvestTime());
			returnString += "The crop " + crop.getType() + " has " + cropTime + " days left.\n";
		}
		System.out.println(returnString);
	}
	
	/**
	 * Allows the user to view the status of all of their animals
	 * Prints a string for each animal outlining their type, happiness, and health
	 */
	public void viewAnimalStatus() {
		String returnString = "";
		ArrayList<Animal> animals = farm.getAnimals();
		for (Animal animal: animals) {
			String animalHealth = Double.toString(animal.getHealth());
			String animalHappy = Double.toString(animal.getHappiness());
			String animalType = animal.getType();
			returnString += "This " + animalType + " has a health of " + animalHealth + " and happiness of " + animalHappy + ".\n";
		}
		System.out.println(returnString);
	}
	
	/**
	 * Allows the player to view the status of their farm
	 * Prints a string containing their farm's name, farmer, and total current money
	 */
	public void viewFarmStatus() {
		String farmName = farm.getName();
		String farmMoney = Double.toString(farm.getMoney());
		String farmFarmer = farm.getFarmer().getName();
		
		String returnString = "Your farm "+farmName+" has a farmer called "+farmFarmer+", with total money of "+farmMoney;
		System.out.println(returnString);
	}
	
	/**
	 * Removes half of the animals from the farm using a random number generator
	 * @param farm - the non-static player's farm
	 * @param rng - the non_static random number generator used
	 */
	public static void removeHalfAnimals(Farm farm, Random rng) {
		ArrayList<Animal> animals = farm.getAnimals();
		int num_required = animals.size()/2;
		/**
		 * Utilises a while loop to ensure exactly half of the animals are removed
		 * If the rng generates an even number, or there are as many animals in the list as need to be removed, remove the animal
		 */
		while(num_required > 0) {
			for(int i = 0; i < animals.size(); i++) {
				if (num_required == (animals.size() - i) || rng.nextInt()%2 == 0) {
					animals.remove(i);
					num_required -= 1;
				}
			}
		}
		farm.setAnimals(animals);
	}
	
	/**
	 * Removes half of the crops from the farm using a random number generator
	 * @param farm - the non-static user's farm passed into a static function
	 * @param rng - the non_static number generator passed into a static function
	 */
	public static void removeHalfCrops(Farm farm, Random rng) {
		ArrayList<Crop> crops = farm.getCrops();
		int crop_num = crops.size();
		int num_required = crop_num/2;
		/**
		 * Uses a while loop to ensure that the number of crops removed is adequate
		 * If the rng generates an odd number, or the remaining crops in the list need to be removed, remove the next crop
		 */
		while(num_required > 0) {
			for(int i = 0; i < crop_num; i++) {
				if (num_required == (crop_num - i) || rng.nextInt()%2 == 1) {
					crops.remove(i);
					num_required -= 1;
				}
			}
		}
		farm.setCrops(crops);
	}
	
	/**
	 * The triggering function for the random events
	 * Utilises a random number generator to determine whether the events happen or not
	 * County Fair - 10% chance of occurring - player gets a bonus sum of money depending on the numbers of crops and animals they have
	 * Broken Fence - 5% chance of occurring - player loses half of their farm's animals. The animals are chose randomly using removeHalfAnimals()
	 * Drought - 5% chance of occurring - player loses half of their farm's crops. The crops are chose randomly using removeHalfCrops()
	 * 
	 */
	public void randomEvents() {
		int randNum = rng.nextInt();
		if(randNum%10 == 2) {
			//County fair: win a bonus amount of money 
			int animalNum = farm.getAnimals().size();
			int cropNum = farm.getCrops().size();
			double moneyGain = Math.round((25*animalNum + 10*cropNum)*100.0)/100.0;			
			
			System.out.println("Your farm won first prize at the county fair!\nYou gain an extra $"+ Double.toString(moneyGain)+".");
			farm.changeMoney(moneyGain);
			
		} else if (randNum%20 == 5) {
			//Broken fence: animals escape
			System.out.println("Your fence broke, and half of your animals escaped.");
			removeHalfAnimals(farm, rng);
		} else if (randNum%20 == 19 ) {
			//Drought: crops die
			System.out.println("A drought has struck, and your crops can't handle it.\nHalf of them die from lack of water.");
			removeHalfCrops(farm, rng);
		}
	}
	
	
	public static void main(String[] args) {
		GameEnviroBasic enviro = new GameEnviroBasic();
		enviro.addDetails();
		enviro.viewCropStatus();
		enviro.viewAnimalStatus();
		enviro.viewFarmStatus();
	}
}
