package crops;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CropTest {
	
	private Crop testCropA;
	private Crop testCropB;
	
	@BeforeEach
	void setUp() throws Exception {
		testCropA = new Crop("Test Crop", 12.21, 29.24, 4);
		testCropB = new Crop("Beans", 0.02, 100.00, 9);
	}
	
	@Test
	void testGetters() {
		assertEquals(testCropA.getHarvestTime(), 4);
		assertEquals(testCropA.getPrice(), 12.21);
		assertEquals(testCropA.getSalePrice(), 29.24);
		assertEquals(testCropA.getType(), "Test Crop");
		assertEquals(testCropB.getType(), "Beans");
		assertEquals(testCropB.getPrice(), 0.02);
	}
	
	@Test
	void testChangeHarvestTime() {
		testCropB.changeHarvestTime(4);
		assertEquals(testCropB.getHarvestTime(), 5);
		testCropB.changeHarvestTime(8);
		assertEquals(testCropB.getHarvestTime(), 1);
		try {
			testCropB.changeHarvestTime(-5);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}

}
