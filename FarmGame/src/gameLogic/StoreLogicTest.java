package gameLogic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import animals.*;
import crops.*;
import farmsAndFarmer.Farm;
import farmsAndFarmer.FarmBasic;
import farmsAndFarmer.Farmer;
import items.*;

/** Test for StoreLogic class
 * 
 */
class StoreLogicTest {
	
	private StoreLogic storeLogic;
	private GameInformation gameInfo;
	private Farm farm;
	private Farmer farmer;
	private Store store;
	
	@BeforeEach
	void setUp() throws Exception {
		farmer = new Farmer();
		farm = new FarmBasic("test", farmer);
		gameInfo = new GameInformation();
		gameInfo.setFarm(farm);
		storeLogic = new StoreLogic(gameInfo);
		store = gameInfo.getStore();
	}
	
	@Test
	void testPurchaseAnimal() {
		// Blue sky scenario
		Animal testAnimal = new AnimalPig();
		double moneyBefore = farm.getMoney();
		storeLogic.purchaseAnimal(testAnimal);
		double moneyAfter = farm.getMoney();
		assertEquals(moneyBefore - testAnimal.getPrice(), moneyAfter);
		assertEquals(farm.getAnimals().size(), 1);
		assertEquals(farm.getAnimals().get(0).getType(), testAnimal.getType());
		
		// Boundary
		storeLogic.purchaseAnimal(testAnimal);
		storeLogic.purchaseAnimal(testAnimal);
		try {
			storeLogic.purchaseAnimal(testAnimal);
		}
		catch (IllegalStateException e){
			fail("Purchasing animal failed. It shouldn't have");
		}
		moneyAfter = farm.getMoney();
		assertEquals(moneyAfter, moneyBefore - (testAnimal.getPrice() * 4));
		assertEquals(farm.getAnimals().size(), 4);
		
		// Exception
		assertThrows(IllegalStateException.class, () -> storeLogic.purchaseAnimal(testAnimal));
	}
	
	@Test
	void testPurchaseCrop() {
		// Blue sky scenario
		Crop testCrop = new CropWheat();
		double moneyBefore = farm.getMoney();
		storeLogic.purchaseCrop(testCrop);
		double moneyAfter = farm.getMoney();
		assertEquals(moneyBefore - testCrop.getPrice(), moneyAfter);
		assertEquals(farm.getCrops().size(), 1);
		assertEquals(farm.getCrops().get(0).getType(), testCrop.getType());
		
		// Test all crops can be bought
		double totalCost = 0.0;
		int newCropCount = 0;
		int beforeCropCount = farm.getCrops().size();
		moneyBefore = farm.getMoney();
		for (Crop crop : store.availableCrops) {
			storeLogic.purchaseCrop(crop);
			totalCost = totalCost + crop.getPrice();
			newCropCount++;
		}
		moneyAfter = Math.round((moneyBefore - totalCost)*100.0)/100.0;
		assertEquals(moneyAfter, farm.getMoney());
		assertEquals(farm.getCrops().size(), beforeCropCount + newCropCount);
		
		// Boundary
		farm = new Farm("Test farm", "test", new Farmer(), testCrop.getPrice());
		gameInfo.setFarm(farm);
		storeLogic = new StoreLogic(gameInfo);
		try {
			storeLogic.purchaseCrop(testCrop);
		}
		catch (IllegalStateException e){
			fail("Purchasing crop failed. It shouldn't have");
		}
		moneyAfter = farm.getMoney();
		assertEquals(moneyAfter, 0.0);
		assertEquals(farm.getCrops().size(), 1);
		
		// Exception
		assertThrows(IllegalStateException.class, () -> storeLogic.purchaseCrop(testCrop));
	}
	
	@Test
	void testPurchaseItem() {
		// Blue sky scenario
		Item testItem = new ItemHoe();
		double moneyBefore = farm.getMoney();
		storeLogic.purchaseItem(testItem);
		double moneyAfter = farm.getMoney();
		assertEquals(moneyBefore - testItem.getPrice(), moneyAfter);
		assertEquals(farm.getItems().size(), 1);
		assertEquals(farm.getItems().get(0).getName(), testItem.getName());
		
		// Test all items can be bought
		double totalCost = 0.0;
		int newItemCount = 0;
		int beforeItemCount = farm.getItems().size();
		moneyBefore = farm.getMoney();
		for (Item item : store.availableItems) {
			storeLogic.purchaseItem(item);
			totalCost = totalCost + item.getPrice();
			newItemCount++;
		}
		moneyAfter = Math.round((moneyBefore - totalCost)*100.0)/100.0;
		assertEquals(moneyAfter, farm.getMoney());
		assertEquals(farm.getItems().size(), beforeItemCount + newItemCount);
		
		// Boundary
		farm = new Farm("Test farm", "test", new Farmer(), testItem.getPrice());
		gameInfo.setFarm(farm);
		storeLogic = new StoreLogic(gameInfo);
		try {
			storeLogic.purchaseItem(testItem);
		}
		catch (IllegalStateException e){
			fail("Purchasing item failed. It shouldn't have");
		}
		moneyAfter = farm.getMoney();
		assertEquals(moneyAfter, 0.0);
		assertEquals(farm.getItems().size(), 1);
		
		// Exception
		assertThrows(IllegalStateException.class, () -> storeLogic.purchaseItem(testItem));
	}
	

}
