import java.util.ArrayList;

import items.Item;
import crops.Crop;
import animals.Animal;

public class Farm {
	
	/**
	 * Name of the farm
	 */
	private String name;
	/**
	 * Type of the farm
	 * Affects bonuses granted during the game
	 */
	private String type;
	/**
	 * User's created farmer assigned to the farm
	 */
	private Farmer farmer;
	/**
	 * Initial money available to the user
	 */
	private double money;
	/**
	 * ArrayList containing the farm's animals
	 */
	private ArrayList<Animal> animals = new ArrayList<Animal>();
	/**
	 * ArrayList containing the farm's crops
	 */
	private ArrayList<Crop> crops = new ArrayList<Crop>();
	/**
	 * ArrayList containing the farm's items
	 */
	public ArrayList<Item> items = new ArrayList<Item>();
	/**
	 * Maximum amount of crops allowed on the farm
	 */
	private int cropLimit = 10;
	/**
	 * Maximum amount of animals allowed on the farm
	 */
	private int animalLimit = 10;
	
	
	/**
	 * Constructor using parameters
	 * @param title - the desired name for the farm, having already been checked
	 * @param kind - the type of farm to be made
	 * @param owner - the Farmer created by the User
	 * @param cash - the initial available money
	 */
	public Farm(String title, String kind, Farmer owner, double cash) {
		name = title;
		type = kind;
		farmer = owner;
		money = cash;
	}
	
	/**
	 * Gets the name of the farm
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the farm's farmer's name and age
	 * @return farmer
	 */
	public Farmer getFarmer() {
		return farmer;
	}
	
	/**
	 * Get's the farm's type
	 * @return type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Gets the current money available to the user
	 * @return money
	 */
	public double getMoney() {
		return money;
	}
	
	/**
	 * Gets the current animals on the farm
	 * @return animals - ArrayList containing the farm's animals
	 */
	public ArrayList<Animal> getAnimals() {
		return animals;
	}
	
	/**
	 * Gets the current crops growing on the farm
	 * @return crops - ArrayList containing the farm's crops
	 */
	public ArrayList<Crop> getCrops() {
		return crops;
	}
	
	/**
	 * Sets the ArrayList animals to a new list of Animals
	 * Intended to be used only when animals are removed from the farm by an event
	 * @param newAnimals - the ArrayList animals with some animals removed
	 */
	public void setAnimals(ArrayList<Animal> newAnimals) {
		animals = newAnimals;
	}
	
	/**
	 * Sets the ArrayList crops to a new list of Crops
	 * Intended to be used only when crops are harvested, or removed from the farm by an event
	 * @param newCrops - the ArrayList crops with one or more crops removed or manipulated
	 */
	public void setCrops(ArrayList<Crop> newCrops) {
		crops = newCrops;
	}
	
	/**
	 * Manipulates the total current money of the farm
	 * Throws an illegal argument exception if the money goes below 0
	 * @param change - the desired change in money
	 */
	public void changeMoney(double change) {
		if (money >= Math.abs(change) && change < 0 || change >= 0) {
			money += change;
			//Ensures that the total money is always displayed to 2 d.p.
			money = Math.round(money*100.0)/100.0;
		} else {
			throw new IllegalArgumentException("Not enough money");
		}
	}

	/**
	 * Adds an animal into the ArrayList animals
	 * If the farm's type allows it, adds a bonus to the animal's happiness and health
	 * @param animal - the animal to be added to the ArrayList
	 */
	public void addAnimal(Animal animal) {
		if (animals.size() == animalLimit) {
			throw new IllegalArgumentException("Farm is full of animals");
		} else if (type == "animalBonus") {
			animal.changeHealth(10.00);
			animal.changeHappiness(10.00);
		}
		animals.add(animal);
	}
	
	/**
	 * Adds a crop into the ArrayList crops
	 * If the farm's type allows it, decreases the crop's harvest time by a day
	 * @param crop
	 */
	public void addCrop(Crop crop) {
		if (crops.size() == cropLimit) {
			throw new IllegalArgumentException("Farm is full of crops");
		} else if (type == "cropBonus" && crop.getHarvestTime() > 1) {
			crop.changeHarvestTime(1);
		}
		crops.add(crop);
	}
	
	/**
	 * Changes the farms crop limit
	 * @param incr Integer to change the crop limit by
	 */
	public void changeCropLimit(int incr) {
		if ((cropLimit + incr) < 0) {
			cropLimit = 0;
		} else {
			cropLimit = cropLimit + incr;
		}
	}
	
	/**
	 * Changes the farms animal limit
	 * @param incr Integer to change the animal limit by
	 */
	public void changeAnimalLimit(int incr) {
		if ((animalLimit + incr) < 0) {
			animalLimit = 0;
		} else {
			animalLimit = animalLimit + incr;
		}
	}
	
}
