package farmsAndFarmer;

import java.util.ArrayList;

import items.Item;
import items.ItemForAnimal;
import items.ItemForCrop;
import items.ItemWater;
import crops.Crop;
import animals.Animal;

/** Represents the farm
 */
public class Farm {
	
	/**
	 * Name of the farm.
	 */
	private String name;
	/**
	 * Type of the farm. 
	 * Affects bonuses granted during the game.
	 */
	private String type;
	/**
	 * User's created farmer assigned to the farm.
	 */
	private Farmer farmer;
	/**
	 * Initial money available to the user.
	 */
	private double startMoney;
	/**
	 * A running total of the farm's current money.
	 */
	private double money;
	/**
	 * ArrayList containing the farm's animals.
	 */
	protected ArrayList<Animal> animals = new ArrayList<Animal>();
	/**
	 * ArrayList containing the farm's crops.
	 */
	protected ArrayList<Crop> crops = new ArrayList<Crop>();
	/**
	 * ArrayList containing the farm's items.
	 */
	private ArrayList<Item> items = new ArrayList<Item>();
	/**
	 * Maximum amount of crops allowed on the farm.
	 */
	protected int cropLimit = 10;
	/**
	 * Maximum amount of animals allowed on the farm.
	 */
	protected int animalLimit = 10;
	
	/**
	 * The farms current bonus score.
	 */
	private int currentBonusScore = 0;
	
	
	/**
	 * Constructor using parameters.
	 * @param title The desired name for the farm.
	 * @param kind The type of farm to be made.
	 * @param owner The Farmer created by the player.
	 * @param cash The initial available money.
	 */
	public Farm(String title, String kind, Farmer owner, double cash) {
		name = title;
		type = kind;
		farmer = owner;
		money = cash;
		startMoney = cash;
	}
	
	/**
	 * Gets the name of the farm.
	 * @return name A String of the farm's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the farm's farmer object.
	 * @return farmer The farm's Farmer object.
	 */
	public Farmer getFarmer() {
		return farmer;
	}
	
	/**
	 * Gets the farm's type.
	 * @return type The farm's type.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Gets the current money available to the user.
	 * @return money The farm's current money.
	 */
	public double getMoney() {
		return money;
	}
	
	/**
	 * Returns the difference in current money and the money the player had at the start of the game.
	 * @return profit The total profit since the start of the game.
	 */
	public double getProfit() {
		return money - startMoney;
	}
	
	/**
	 * Gets the current animals on the farm.
	 * @return animals An ArrayList containing the farm's animals.
	 */
	public ArrayList<Animal> getAnimals() {
		return animals;
	}
	
	/**
	 * Gets the current crops growing on the farm.
	 * @return crops An ArrayList containing the farm's crops.
	 */
	public ArrayList<Crop> getCrops() {
		return crops;
	}
	
	/**
	 * Gets the current items owned by the player.
	 * @return items An ArrayList containing the farm's items.
	 */
	public ArrayList<Item> getItems() {
		return items;
	}
	
	/**
	 * Gets the crops on the player's farm that are currently ready for harvest.
	 * @return harvestableCrops An ArrayList containing all crops that can currently be harvested.
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
	 * @return animalItems An ArrayList that contains all items that could be used on an animal.
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
	 * @return cropItems An ArrayList that contains all items that could be used on a crop.
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
	 * Manipulates the total current money of the farm.
	 * Throws an illegal argument exception if the money goes below 0.
	 * @param change The desired change in money.
	 */
	public void changeMoney(double change) {
		if (money >= Math.abs(change) && change < 0 || change >= 0) {
			money += change;
			//Ensures that the total money is always rounded to 2 d.p.
			money = Math.round(money*100.0)/100.0;
		} else {
			throw new IllegalArgumentException("Not enough money");
		}
	}

	/**
	 * Adds an animal into the ArrayList animals.
	 * If the farm's type allows it, adds a bonus to the animal's happiness and health.
	 * @param animal The animal to be added to the ArrayList.
	 */
	public void addAnimal(Animal animal) {
		if (animals.size() == animalLimit) {
			throw new IllegalArgumentException("Farm is full of animals");
		}
		animals.add(animal);
	}
	
	/**
	 * Adds a crop into the ArrayList crops.
	 * If the farm's type allows it, decreases the crop's harvest time by a day.
	 * @param crop The crop to be added to the farm's current crops.
	 */
	public void addCrop(Crop crop) {
		if (crops.size() == cropLimit) {
			throw new IllegalArgumentException("Farm is full of crops");
		}
		crops.add(crop);
	}
	
	/**
	 * Adds an item into the ArrayList items.
	 * @param item The item to be added to the farm's current items.
	 */
	public void addItem(Item item) {
		items.add(item);
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
	 * @return currentbonusScore
	 */
	public int getBonusScore() {
		return currentBonusScore;
	}
	
	/**
	 * Increases the farms current bonus score.
	 * @param incr Amount to increase the farm's bonus score by.
	 */
	public void incrBonus(int incr) {
		currentBonusScore = currentBonusScore + incr;
	}
	
}
