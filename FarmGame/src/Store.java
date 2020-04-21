import java.util.ArrayList;
import items.*;

public class Store {
	
	/**
	 * ArrayList containing the stores available items
	 */
	public ArrayList<Item> availableItems = new ArrayList<Item>();
	
	/**
	 * ArrayList containing the stores available animals
	 */
	public ArrayList<Animal> availableAnimals = new ArrayList<Animal>();
	
	/**
	 * ArrayList containing the stores available crops
	 */
	public ArrayList<Crop> availableCrops = new ArrayList<Crop>();
	
	
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
}
