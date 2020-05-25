package animals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnimalTest {
	
	private Animal testAnimal;
	
	@BeforeEach
	void setUp() throws Exception {
		testAnimal = new Animal("Test Animal", 16.18, 122.15, 13.00, 64.00);
	}
	
	@Test
	void testGetters() {
		assertEquals(testAnimal.getType(), "Test Animal");
		assertEquals(testAnimal.getHappiness(), 64.00);
		assertEquals(testAnimal.getHealth(), 13.00);
		assertEquals(testAnimal.getIncome(), 10.16);
		assertEquals(testAnimal.getPrice(), 16.18);
	}
	
	@Test
	//Tests that happiness and health will not be set above 100 or below 0 initially
	void testConstructor() {
		Animal justTooHigh = new Animal("Test", 10, 10, 100.001, 100.01);
		Animal farTooHigh = new Animal("Test", 10, 10, 1000.00, 1000.00);
		Animal justTooLow = new Animal("Test", 1, 1, -0.001, -0.001);
		Animal farTooLow = new Animal("Test", 1, 1, -100, -100);
		
		assertEquals(justTooHigh.getHappiness(), 100.00);
		assertEquals(justTooHigh.getHealth(), 100.00);
		assertEquals(farTooHigh.getHappiness(), 100.00);
		assertEquals(farTooHigh.getHealth(), 100.00);
		assertEquals(justTooLow.getHappiness(), 0.00);
		assertEquals(justTooLow.getHealth(), 0.00);
		assertEquals(farTooLow.getHappiness(), 0.00);
		assertEquals(farTooLow.getHealth(), 0.00);
	}
	
	@Test
	void testChangeHealth() {
		testAnimal.changeHealth(20);
		assertEquals(testAnimal.getHealth(), 33.00);
		testAnimal.changeHealth(-21);
		assertEquals(testAnimal.getHealth(), 12.00);
		testAnimal.changeHealth(-11.99);
		assertEquals(testAnimal.getHealth(), 0.01);
		testAnimal.changeHealth(39.46);
		assertEquals(testAnimal.getHealth(), 39.47);
		testAnimal.changeHealth(-19.38);
		assertEquals(testAnimal.getHealth(), 20.09);
		
		testAnimal.changeHealth(100.00);
		assertEquals(testAnimal.getHealth(), 100.00);
		testAnimal.changeHealth(30);
		assertEquals(testAnimal.getHealth(), 100.00);
		
		testAnimal.changeHealth(-150.00);
		assertEquals(testAnimal.getHealth(), 0.00);
		testAnimal.changeHealth(-10.00);
		assertEquals(testAnimal.getHealth(), 0.00);
	}
	
	@Test
	void testChangeHappiness() {
		testAnimal.changeHappiness(15);
		assertEquals(testAnimal.getHappiness(), 79.00);
		testAnimal.changeHappiness(-48);
		assertEquals(testAnimal.getHappiness(), 31.00);
		testAnimal.changeHappiness(-30.99);
		assertEquals(testAnimal.getHappiness(), 0.01);
		testAnimal.changeHappiness(82.39);
		assertEquals(testAnimal.getHappiness(), 82.40);
		testAnimal.changeHappiness(-38.57);
		assertEquals(testAnimal.getHappiness(), 43.83);
		
		testAnimal.changeHappiness(100.00);
		assertEquals(testAnimal.getHappiness(), 100.00);
		testAnimal.changeHappiness(45);
		assertEquals(testAnimal.getHappiness(), 100.00);
		
		testAnimal.changeHappiness(-101.00);
		assertEquals(testAnimal.getHappiness(), 0.00);
		testAnimal.changeHappiness(-16.00);
		assertEquals(testAnimal.getHappiness(), 0.00);
	}
	
	@Test
	void testGetIncome() {
		testAnimal.changeHealth(100);
		testAnimal.changeHappiness(100);
		assertEquals(testAnimal.getIncome(), 122.15);
		
		testAnimal.changeHealth(-100);
		testAnimal.changeHappiness(-100);
		assertEquals(testAnimal.getIncome(), 0.00);
		
		testAnimal.changeHealth(13.23);
		testAnimal.changeHappiness(84.37);
		assertEquals(testAnimal.getIncome(), 13.63);
	}

}
