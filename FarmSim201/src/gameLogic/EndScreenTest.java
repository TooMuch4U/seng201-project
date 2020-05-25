package gameLogic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import animals.Animal;
import crops.Crop;
import farmsAndFarmer.FarmBasic;
import farmsAndFarmer.Farmer;

class EndScreenTest {
	
	/**
	 * A test Farm object.
	 */
	private FarmBasic farm;
	/**
	 * A second test Farm object.
	 */
	private FarmBasic secondFarm;

	@BeforeEach
	void setUp() throws Exception {
		Animal animal = new Animal("animal", 10, 10, 10, 10);
		Farmer farmer = new Farmer();
		farm = new FarmBasic("farm", farmer);
		secondFarm = new FarmBasic("second farm", farmer);
		Crop crop = new Crop("crop", 10, 10, 10);
		farm.addAnimal(animal);
		farm.addCrop(crop);
		for (int i = 0; i < 7; i += 1) {
			secondFarm.addAnimal(animal);
			secondFarm.addCrop(crop);
		}
	}

	@Test
	void testCalculateScore() {
		EndScreen end = new EndScreen(farm);
		assertEquals(end.displayScore(), "1030.0");
		EndScreen endTwo = new EndScreen(secondFarm);
		assertEquals(endTwo.displayScore(), "1210.0");
	}

}
