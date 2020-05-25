package items;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItemTest {

	@Test
	void testAnimal() {
		ItemForAnimal item1 = new ItemForAnimal("Item1", 9.99, 4.99);
		ItemForAnimal item2 = new ItemForAnimal("Item2", 20.0, 0.00);
		
		assertEquals(item1.getName(), "Item1");
		assertEquals(item2.getName(), "Item2");
		assertEquals(item1.getBenefit(), 4.99);
		assertEquals(item2.getBenefit(), 0.00);
		assertEquals(item1.getPrice(), 9.99);
		assertEquals(item2.getPrice(), 20.0);
		
		Item item = item1;
		
		assertTrue(item instanceof ItemForAnimal);
		assertFalse(item instanceof ItemForCrop);
	}
	
	@Test
	void testCrop() {
		ItemForCrop item1 = new ItemForCrop("Item1", 2.99, 1.0);
		
		assertEquals(item1.getName(), "Item1");
		assertEquals(item1.getBenefit(), 1.0);
		assertEquals(item1.getPrice(), 2.99);
		
		Item item = item1;
		
		assertFalse(item instanceof ItemForAnimal);
		assertTrue(item instanceof ItemForCrop);
		
	}
	
	

}
