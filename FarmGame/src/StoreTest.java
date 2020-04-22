import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import items.Item;

class StoreTest {
	
	Store store = new Store();
	
	
	@Test
	void testItemPurchase() {
		int itemIndex = store.availableItems.size() - 1;
		assertEquals(store.purchaseItem(0), store.availableItems.get(0));
		assertEquals(store.purchaseItem(itemIndex), store.availableItems.get(itemIndex));
		assertThrows(IllegalArgumentException.class, () -> store.purchaseItem(itemIndex + 1));
		assertTrue(store.purchaseItem(0) instanceof Item);
	}
	
	@Test
	void testAnimalPurchase() {
		int lastIndex = store.availableAnimals.size() - 1;
		assertEquals(store.purchaseAnimal(0), store.availableAnimals.get(0));
		assertEquals(store.purchaseAnimal(lastIndex), store.availableAnimals.get(lastIndex));
		assertThrows(IllegalArgumentException.class, () -> store.purchaseAnimal(lastIndex + 1));
		assertTrue(store.purchaseAnimal(0) instanceof Animal);
	}
	
	@Test
	void testCropPurchase() {
		int lastIndex = store.availableCrops.size() - 1;
		assertEquals(store.purchaseCrop(0), store.availableCrops.get(0));
		assertEquals(store.purchaseCrop(lastIndex), store.availableCrops.get(lastIndex));
		assertThrows(IllegalArgumentException.class, () -> store.purchaseCrop(lastIndex + 1));
		assertTrue(store.purchaseCrop(0) instanceof Crop);
	}

}
