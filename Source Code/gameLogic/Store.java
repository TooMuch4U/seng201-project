package gameLogic;

import java.util.ArrayList;
import items.*;
import animals.*;
import crops.*;

/** Store class represents the farms store
 * 
 */
public class Store {
	
	/**
	 * ArrayList containing the stores available items.
	 */
	public ArrayList<Item> availableItems = new ArrayList<Item>();
	
	/**
	 * ArrayList containing the stores available animals.
	 */
	public ArrayList<Animal> availableAnimals = new ArrayList<Animal>();
	
	/**
	 * ArrayList containing the stores available crops.
	 */
	public ArrayList<Crop> availableCrops = new ArrayList<Crop>();
	
	
	/**
	 * Constructor for store. 
	 * Initialises all available items.
	 */
	public Store() {
		// Add all items to availableItems ArrayList
		availableItems.add(new ItemHoe());
		availableItems.add(new ItemFertilizer());
		availableItems.add(new ItemGrowLight());
		availableItems.add(new ItemApple());
		availableItems.add(new ItemMilk());
		availableItems.add(new ItemSteroids());
		
		// Add all animals to availableAnimals ArrayList
		availableAnimals.add(new AnimalChicken());
		availableAnimals.add(new AnimalCow());
		availableAnimals.add(new AnimalPig());
		
		// Add all crops to availableCrops ArrayList
		availableCrops.add(new CropCabbage());
		availableCrops.add(new CropCauliflower());
		availableCrops.add(new CropCorn());
		availableCrops.add(new CropPotato());
		availableCrops.add(new CropTomato());
		availableCrops.add(new CropWheat());
	}
	
	/**
	 * Returns the item from the availableItems array at the given index.
	 * 
	 * @param index Index of the item to retrieve. Must not be larger than the ArrayList size
	 * @return returnItem The item that was chosen.
	 */
	public Item purchaseItem(int index) {
		int arraySize = availableItems.size();
		Item returnItem;
		
		// Check that the index is valid
		if (index < arraySize && index >= 0) {
			returnItem = availableItems.get(index);
			
		}
		else {
			throw new IllegalArgumentException("That item doesn't exist");
		}
		
		return(returnItem);
	}
	
	/**
	 * Returns the Animal from the availableAnimals array at the given index
	 * 
	 * @param index Index of the animal wanted.
	 * @return returnItem The animal that was purchased
	 */
	public Animal purchaseAnimal(int index) {
		
		int arraySize = availableAnimals.size();
		Animal returnItem;
		
		// Check that the index is valid
		if (index < arraySize && index >= 0) {
			returnItem = availableAnimals.get(index);
		}
		else {
			throw new IllegalArgumentException("That animal doesn't exist");
		}
		
		return(returnItem);
	}
	
	/**
	 * Returns the Crop from the availableCrops array at the given index
	 * 
	 * @param index Index of the crop wanted.
	 * @return returnItem The crop that was purchased
	 */
	public Crop purchaseCrop(int index) {
		
		int arraySize = availableCrops.size();
		Crop returnItem;
		
		// Check that the index is valid
		if (index < arraySize && index >= 0) {
			returnItem = availableCrops.get(index);
		}
		else {
			throw new IllegalArgumentException("That crop doesn't exist");
		}
		
		return(returnItem);
	}
	
	
}
