import java.util.ArrayList;
import java.util.Random;

import animals.*;
import crops.*;
import items.*;

public class GameEnviroBasic {
	
	Farmer farmer = new Farmer();
	Farm farm = new FarmBasic("Test Farm", farmer);
	Random rng = new Random();
	
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
	
	public void viewCropStatus() {
		String returnString = "";
		ArrayList<Crop> crops = farm.getCrops();
		for (Crop crop:crops) {
			String cropTime = Integer.toString(crop.getHarvestTime());
			returnString += "The crop " + crop.getType() + " has " + cropTime + " days left.\n";
		}
		System.out.println(returnString);
	}
	
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
	
	public void viewFarmStatus() {
		String farmName = farm.getName();
		String farmMoney = Double.toString(farm.getMoney());
		String farmFarmer = farm.getFarmer().getName();
		
		String returnString = "Your farm "+farmName+" has a farmer called "+farmFarmer+", with total money of "+farmMoney;
		System.out.println(returnString);
	}
	
	public static void removeHalfAnimals(Farm farm, Random rng) {
		ArrayList<Animal> animals = farm.getAnimals();
		int num_required = animals.size()/2;
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
	
	public static void removeHalfCrops(Farm farm, Random rng) {
		ArrayList<Crop> crops = farm.getCrops();
		int crop_num = crops.size();
		int num_required = crop_num/2;
		while(num_required > 0) {
			for(int i = 0; i < crop_num; i++) {
				if (num_required == (crop_num - i) || rng.nextInt()%2 == 0) {
					crops.remove(i);
					num_required -= 1;
				}
			}
		}
		farm.setCrops(crops);
	}
	
	public void randomEvents() {
		int randNum = rng.nextInt();
		if(randNum%10 == 2) {
			//County fair: win a bonus amount of money 
			System.out.println("Your farm won first prize at the county fair!\nYou gain an extra $500.");
			farm.changeMoney(500);
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
