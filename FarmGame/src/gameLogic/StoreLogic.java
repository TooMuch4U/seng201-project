package gameLogic;

import animals.*;
import crops.*;
import farmsAndFarmer.Farm;
import items.*;

public class StoreLogic {
	
	/**
	 * A logic class containing the vital information about the current game.
	 */
	private GameInformation gameInfo;
	/**
	 * The player's farm object.
	 */
	private Farm farm;
	
	/**
	 * Constructor with parameters. 
	 * @param info The logic class containing information about the current game.
	 */
	public StoreLogic(GameInformation info) {
		gameInfo = info;
		farm = gameInfo.getFarm();
	}
	
	/**
	 * Processes the payment of a store item. 
	 * The farm must have enough money to make the purchase price. 
	 * @param price The price of the item being purchased.
	 */
	private void processStoreItem(double price) {
		farm.changeMoney(-price);
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
	
}
