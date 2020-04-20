import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FarmTest {
	
	private Farmer farmer;
	private Farm farm;
	
	@BeforeEach
	void setUp() throws Exception {
		farmer = new Farmer();
		farm = new FarmBasic("Test Farm", farmer);
	}
	
	
	@Test
	void testBasicGetters() {
		assertEquals(farm.getName(), "Test Farm");
		assertEquals(farm.getType(), "basic");
		assertEquals(farm.getMoney(), 1000.00);
		assertEquals(farm.getFarmer(), farmer);
	}
	
	@Test
	void testListIsEmpty() {
		assertTrue(farm.getAnimals().isEmpty());
		assertTrue(farm.getCrops().isEmpty());
		assertTrue(farm.items.isEmpty());
	}
	
	@Test
	void testChangeMoney() {
		farm.changeMoney(100);
		assertEquals(farm.getMoney(), 1100.00);
		farm.changeMoney(-200.50);
		assertEquals(farm.getMoney(), 899.50);
		farm.changeMoney(900.00);
		assertEquals(farm.getMoney(), 1799.50);
		farm.changeMoney(-900.00);
		double currentMoney = farm.getMoney();
		try {
			farm.changeMoney(-1000);
		} catch(IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(currentMoney, farm.getMoney());
		farm.changeMoney(-farm.getMoney());
		assertEquals(farm.getMoney(), 0.00);
	}
	
	@Test
	void testAddAnimals() {
		AnimalCow animal = new AnimalCow();
		farm.addAnimal(animal);
		assertFalse(farm.getAnimals().isEmpty());
		assertEquals(farm.getAnimals().get(0), animal);
		for(int i = 1; i < 10; i += 1) {
			farm.addAnimal(animal);
		}
		assertEquals(farm.getAnimals().get(9), animal);
		assertEquals(farm.getAnimals().size(), 10);
		try {
			farm.addAnimal(animal);
		} catch(IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	void testAddCrops() {
		CropWheat crop = new CropWheat();
		farm.addCrop(crop);
		assertFalse(farm.getCrops().isEmpty());
		assertEquals(farm.getCrops().get(0), crop);
		for(int i = 1; i < 10; i += 1) {
			farm.addCrop(crop);
		}
		assertEquals(farm.getCrops().get(9), crop);
		assertEquals(farm.getCrops().size(), 10);
		try {
			farm.addCrop(crop);
		} catch(IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	void testItems() {
		Item apple = new ItemApple();
		Item hoe = new ItemHoe();
		
		farm.items.add(apple);
		farm.items.add(hoe);
		assertTrue(farm.items.contains(apple));
		assertTrue(farm.items.contains(hoe));
		
		for (int i = 1; i < 10; i += 1) {
			farm.items.add(apple);
			farm.items.add(hoe);
		}
		assertTrue(farm.items.size() == 20);
		
		farm.items.remove(hoe);
		assertTrue(farm.items.size() == 19);
		
	}
	
	@Test
	void testListSet() {
		ArrayList<Animal> animals = new ArrayList<Animal>();
		ArrayList<Crop> crops = new ArrayList<Crop>();
		
		AnimalCow cow = new AnimalCow();
		AnimalPig pig = new AnimalPig();
		CropWheat wheat = new CropWheat();
		CropCorn corn = new CropCorn();
		
		for(int i = 0; i < 10; i += 1) {
			farm.addAnimal(cow);
			farm.addCrop(wheat);
			animals.add(pig);
			crops.add(corn);
		}
		
		ArrayList<Animal> oldAnimals = farm.getAnimals();
		ArrayList<Crop> oldCrops = farm.getCrops();
		farm.setAnimals(animals);
		farm.setCrops(crops);
		
		assertFalse(farm.getAnimals().equals(oldAnimals));
		assertFalse(farm.getCrops().equals(oldCrops));
		assertEquals(farm.getAnimals(), animals);
		assertEquals(farm.getCrops(), crops);
	}
	
	@Test
	void testAnimalBonus() {
		Farm farm = new FarmGoodAnimal("Test Animal Farm", farmer);
		assertEquals(farm.getType(), "animalBonus");
		Animal chicken = new AnimalChicken();
		Animal cow = new AnimalCow();
		Animal pig = new AnimalPig();
		double chickHappy = chicken.getHappiness();
		double chickHealth = chicken.getHealth();
		double cowHappy = cow.getHappiness();
		double cowHealth = cow.getHealth();
		double pigHappy = pig.getHappiness();
		double pigHealth = pig.getHealth();
		
		//Just adding the animals to the list should change their attributes
		farm.addAnimal(chicken);
		farm.addAnimal(cow);
		farm.addAnimal(pig);
		
		assertEquals(chickHappy+10, chicken.getHappiness());
		assertEquals(cowHappy+10, cow.getHappiness());
		assertEquals(pigHappy+10, pig.getHappiness());
		
		assertEquals(chickHealth+10, chicken.getHealth());
		assertEquals(cowHealth+10, cow.getHealth());
		assertEquals(pigHealth+10, pig.getHealth());		
	}
	
	@Test
	void testCropBonus() {
		Farm farm = new FarmGoodSoil("Test Crop Farm", farmer);
		assertEquals(farm.getType(), "cropBonus");
		Crop wheat = new CropWheat();
		Crop corn = new CropCorn();
		Crop cauliflower = new CropCauliflower();
		
		int wheatTime = wheat.getHarvestTime();
		int cornTime = corn.getHarvestTime();
		int cauliTime = cauliflower.getHarvestTime();
		
		//Only adding the crops should be enough to change their attributes
		farm.addCrop(wheat);
		farm.addCrop(cauliflower);
		farm.addCrop(corn);
		
		assertEquals(wheatTime, wheat.getHarvestTime());
		assertEquals(cornTime-1, corn.getHarvestTime());
		assertEquals(cauliTime-1, cauliflower.getHarvestTime());
	}
	
	@Test
	void testMoneyBonus() {
		Farm moneyFarm = new FarmRich("Test Farm", farmer);
		assertEquals("rich", moneyFarm.getType());
		assertFalse(farm.getMoney() == moneyFarm.getMoney());
		assertEquals(1500.00, moneyFarm.getMoney());
	}
	
}
