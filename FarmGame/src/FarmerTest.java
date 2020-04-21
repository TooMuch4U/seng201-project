import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FarmerTest {

	@Test
	void testGet() {
		Farmer farmer = new Farmer("Tom", 19);
		
		assertEquals(farmer.getAge(), 19);
		assertEquals(farmer.getName(), "Tom");
	}
	
	@Test
	void testSet() {
		Farmer farmer = new Farmer("Tom", 19);
		assertEquals(farmer.getName(), "Tom");
		farmer.setName("Test");
		assertEquals(farmer.getName(), "Test");
	}
	
	@Test
	void testError() {
		assertThrows(IllegalArgumentException.class, () -> new Farmer("Tom", -1));
		assertThrows(IllegalArgumentException.class, () -> new Farmer("", 19));
		
		Farmer farmer = new Farmer("Tom", 19);
		
		assertThrows(IllegalArgumentException.class, () -> farmer.setName(""));
		
	}

}
