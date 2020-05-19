import java.util.ArrayList;

import items.Item;
import items.ItemForAnimal;
import items.ItemForCrop;
import items.ItemWater;
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
	private double startMoney;
	/**
	 * A running total of the farm's current money
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
	 * The farms current bonus score
	 */
	private int currentBonusScore = 0;
	
	
	/**
	 * Constructor using parameters.
	 * @param title - the desired name for the farm, having already been checked.
	 * @param kind - the type of farm to be made.
	 * @param owner - the Farmer created by the player.
	 * @param cash - the initial available money.
	 */
	public Farm(String title, String kind, Farmer owner, double cash) {
		name = title;
		type = kind;
		farmer = owner;
		money = cash;
		startMoney = cash;
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
	 * Gets the current money available to the user.
	 * @return money
	 */
	public double getMoney() {
		return money;
	}
	
	/**
	 * Returns the difference in current money and the money the player had at the start of the game.
	 * @return profit - a double: the difference in money and startMoney
	 */
	public double getProfit() {
		return money - startMoney;
	}
	
	/**
	 * Gets the current animals on the farm.
	 * @return animals - ArrayList containing the farm's animals.
	 */
	public ArrayList<Animal> getAnimals() {
		return animals;
	}
	
	/**
	 * Gets the current crops growing on the farm.
	 * @return crops - ArrayList containing the farm's crops.
	 */
	public ArrayList<Crop> getCrops() {
		return crops;
	}
	
	/**
	 * Gets the crops on the player's farm that are currently ready for harvest.
	 * @return harvestableCrops - an ArrayList containing all crops that can currently be harvested
	 */
	public ArrayList<Crop> getHarvestableCrops() {
		ArrayList<Crop> harvestableCrops = new ArrayList<Crop>();
		for (Crop crop: crops) {
			if (crop.getHarvestTime() == 0) {
				harvestableCrops.add(crop);
			}
		}
		return harvestableCrops;
	}
	
	/**
	 * Returns a list containing all of the items owned by the player that can be used on animals.
	 * @return animalItems - an ArrayList that contains all items that could be used on an animal.
	 */
	public ArrayList<ItemForAnimal> getAnimalItems() {
		ArrayList<ItemForAnimal> animalItems = new ArrayList<>();
		for (Item item:items) {
			String name = item.getName();
			if (name == "Apple" || name == "Steroids" || name == "Milk" ) {
				animalItems.add((ItemForAnimal) item);
			}
		}
		return animalItems;
	}
	
	/**
	 * Returns a list containing all of the items owned by the player that could be used on crops.
	 * @return cropItems - an ArrayList that contains all items that could be used on a crop.
	 */
	public ArrayList<ItemForCrop> getCropItems() {
		ArrayList<ItemForCrop> cropItems = new ArrayList<>();
		cropItems.add(new ItemWater());
		for (Item item:items) {
			String name = item.getName();
			if (name == "Hoe" || name == "Hydroponic Grow Lights" || name == "Fertilizer" ) {
				cropItems.add((ItemForCrop) item);
			}
		}
		return cropItems;
	}
	
	/**
	 * Sets the ArrayList animals to a new or modified list of Animals.
	 * @param newAnimals - the ArrayList animals with some animals removed.
	 */
	public void setAnimals(ArrayList<Animal> newAnimals) {
		animals = newAnimals;
	}
	
	/**
	 * Sets the ArrayList crops to a new or modified list of Crops.
	 * @param newCrops - the ArrayList crops with one or more crops removed or manipulated.
	 */
	public void setCrops(ArrayList<Crop> newCrops) {
		crops = newCrops;
	}
	
	/**
	 * Manipulates the total current money of the farm.
	 * Throws an illegal argument exception if the money goes below 0.
	 * @param change - the desired change in money.
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
	 * Adds an animal into the ArrayList animals.
	 * If the farm's type allows it, adds a bonus to the animal's happiness and health.
	 * @param animal - the animal to be added to the ArrayList.
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
	 * Adds a crop into the ArrayList crops.
	 * If the farm's type allows it, decreases the crop's harvest time by a day.
	 * @param crop - the crop to be added to the farm's current crops.
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
	 * Changes the farms crop limit.
	 * @param incr Integer to change the crop limit by.
	 */
	public void changeCropLimit(int incr) {
		if ((cropLimit + incr) < 0) {
			cropLimit = 0;
		} else {
			cropLimit = cropLimit + incr;
		}
	}
	
	/**
	 * Changes the farms animal limit.
	 * @param incr Integer to change the animal limit by.
	 */
	public void changeAnimalLimit(int incr) {
		if ((animalLimit + incr) < 0) {
			animalLimit = 0;
		} else {
			animalLimit = animalLimit + incr;
		}
	}
	
	/**
	 * Returns the farms current bonus score.
	 * @return currentbonusScore.
	 */
	public int getBonusScore() {
		return currentBonusScore;
	}
	
	/**
	 * Increases the farms current bonus score.
	 * @param incr Amount to increase the farms bonus score by.
	 */
	public void incrBonus(int incr) {
		currentBonusScore = currentBonusScore + incr;
	}
	
}
