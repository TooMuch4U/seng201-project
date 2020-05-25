package gameLogic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import animals.Animal;
import animals.AnimalCow;
import crops.CropCabbage;
import crops.CropPotato;
import farmsAndFarmer.Farm;
import farmsAndFarmer.FarmBasic;
import farmsAndFarmer.Farmer;

class RandomEventsTest {

	private RandomEvents random;
	private GameInformation gameInfo;
	private FarmBasic farm;
	private Farmer farmer;
	
	
	@BeforeEach
	void setUp() throws Exception {
		farmer = new Farmer();
		farm = new FarmBasic("test", farmer);
		gameInfo = new GameInformation();
		gameInfo.setFarm(farm);
		random = new RandomEvents(gameInfo);
	}

	@Test
	void testRemoveRandomAnimals() {
		String status = random.removeRandomAnimals();
		assertTrue(status == "");
		for (int i = 0; i < 10; i += 1) {
			farm.addAnimal(new Animal("animal", 100, 100, 100, 100));
		}
		int size = 10;
		
		status = random.removeRandomAnimals();
		//Check that an animal has been removed
		assertFalse(size == farm.getAnimals().size());
		for (int i = 0; i < farm.getAnimals().size(); i += 1) {
			assertEquals(farm.getAnimals().get(i).getHappiness(), 80.0);
		}
		assertFalse(status == "");
	}
	
	@Test
	void testRemoveHalfCrops() {
		String status = random.removeHalfCrops();
		assertEquals(status, "");
		
		Farm secondFarm = new FarmBasic("test", farmer);
		GameInformation secondGame = new GameInformation();
		secondGame.setFarm(secondFarm);
		RandomEvents secondRandom = new RandomEvents(secondGame);
		
		for (int i = 0; i < 8; i += 1) {
			farm.addCrop(new CropCabbage());
			secondFarm.addCrop(new CropCabbage());
		}
		farm.addCrop(new CropPotato());
		
		status = random.removeHalfCrops();
		String secondStatus = secondRandom.removeHalfCrops();
		//Check if half the crops have been removed
		assertEquals(5, farm.getCrops().size());
		assertEquals(4, secondFarm.getCrops().size());
		assertTrue(status != "");
		assertTrue(secondStatus != "");
	}
	
	@Test
	void testRandomEvents() {
		assertEquals(random.randomEvents(2), "Your farm won first prize at the county fair!\nYou gain an extra $0.00 for your spectacular animals and crops.");
		assertEquals(random.randomEvents(12), "Your farm won first prize at the county fair!\nYou gain an extra $0.00 for your spectacular animals and crops.");
		//Check the farms money increases adequately
		farm.addAnimal(new AnimalCow());
		double currentMoney = farm.getMoney();
		random.randomEvents(2);
		assertEquals(currentMoney + 25, farm.getMoney());
		//Shouldn't trigger any event
		assertEquals(random.randomEvents(1323), "");
		assertEquals(random.randomEvents(136), "");
		
		//Add animals and crops so methods can remove some
		for (int i = 0; i < 8; i += 1) {
			farm.addAnimal(new AnimalCow());
			farm.addCrop(new CropCabbage());
		}
		//Check multiple true values for animals
		assertTrue(random.randomEvents(5) != "");
		assertTrue(random.randomEvents(45) != "");
		
		//Check multiple true values for crops
		assertTrue(random.randomEvents(19) != "");
		assertTrue(random.randomEvents(159) != "");
	}

}
