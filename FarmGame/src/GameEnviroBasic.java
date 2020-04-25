import java.util.ArrayList;

public class GameEnviroBasic {
	
	static Farmer farmer = new Farmer();
	static Farm farm = new FarmBasic("Test Farm", farmer);
	
	public static void addDetails() {
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
	
	public static void viewCropStatus() {
		String returnString = "";
		ArrayList<Crop> crops = farm.getCrops();
		for (Crop crop:crops) {
			String cropTime = Integer.toString(crop.getHarvestTime());
			returnString += "The crop " + crop.getType() + " has " + cropTime + " days left.\n";
		}
		System.out.println(returnString);
	}
	
	public static void viewAnimalStatus() {
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
	
	public static void viewFarmStatus() {
		String farmName = farm.getName();
		String farmMoney = Double.toString(farm.getMoney());
		String farmFarmer = farm.getFarmer().getName();
		
		String returnString = "Your farm "+farmName+" has a farmer called "+farmFarmer+", with total money of "+farmMoney;
		System.out.println(returnString);
	}
	
	
	public static void main(String[] args) {
		addDetails();
		viewCropStatus();
		viewAnimalStatus();
		viewFarmStatus();
	}
}
