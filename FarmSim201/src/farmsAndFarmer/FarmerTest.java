package farmsAndFarmer;

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
		Farmer secondFarmer = new Farmer();
		assertEquals(secondFarmer.getName(), "Nameless Farmer");
	}
}
