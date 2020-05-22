package gameLogic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import animals.*;
import crops.*;
import items.*;
import farmsAndFarmer.Farm;
import farmsAndFarmer.FarmBasic;
import farmsAndFarmer.Farmer;
import guiScreens.MainScreen;

class GameEnviroBasicTest {
	
	private Farm farm;
	private Farmer farmer;
	private GameEnviroBasic game;
	
	
	@BeforeEach
	void setUp() throws Exception {
		farmer = new Farmer();
		farm = new FarmBasic("test farm", farmer);
		game = new GameEnviroBasic();
		game.setFarm(farm);
		game.setRequiredDays(15);
		game.setRandomEventsOn(true);
		farm.changeAnimalLimit(15);
		farm.changeCropLimit(15);
		
	}
	
	@Test
	void testActionExceptions() {
		game.setNumActions(0);
		MainScreen main = new MainScreen(game);
		try {
			game.launchSelectAnimalScreen(main);
		} catch (ActionCountException e) {
			System.out.println("Exception caught successfully");
		}
		
		try {
			game.launchTendCropsScreen(main);
		} catch (ActionCountException e) {
			System.out.println("Exception caught successfully\n");
		}
	}
	
	@Test
	void testAdvanceDays() {
		game.setRandomEventsOn(false);
		game.advanceDays();
		assertEquals(1, game.getCurrentDays());
		assertEquals(2, game.getNumActions());
		
		//Check to see if crop attributes decrease properly
		CropPotato pot = new CropPotato();
		CropCabbage cab = new CropCabbage();
		int potHarvestDays = pot.getHarvestTime();
		int potGrowDays = pot.getGrowingTime();
		int cabHarvestDays = cab.getHarvestTime();
		int cabGrowDays = cab.getGrowingTime();
		
		farm.addCrop(pot);
		farm.addCrop(cab);
		game.advanceDays();
		
		assertEquals(potHarvestDays-1, pot.getHarvestTime());
		assertEquals(potGrowDays+1, pot.getGrowingTime());
		assertEquals(cabHarvestDays-1, cab.getHarvestTime());
		assertEquals(cabGrowDays+1, cab.getGrowingTime());
		
		//Check to see if money increases and animal attributes decrease properly
		AnimalCow cow = new AnimalCow();
		AnimalPig pig = new AnimalPig();
		double startMoney = farm.getMoney();
		double cowHealth = cow.getHealth();
		double cowHappiness = cow.getHappiness();
		double cowIncome = cow.getIncome();
		double pigHealth = pig.getHealth();
		double pigHappiness = pig.getHappiness();
		double pigIncome = pig.getIncome();
		
		farm.addAnimal(cow);
		farm.addAnimal(pig);
		game.advanceDays();
		
		assertEquals(cowHealth-2, cow.getHealth());
		assertEquals(cowHappiness-2, cow.getHappiness());
		assertEquals(pigHealth-2, pig.getHealth());
		assertEquals(pigHappiness-2, pig.getHappiness());
		assertEquals(startMoney+cowIncome+pigIncome, farm.getMoney());
	}
	
	@Test
	//Test to determine whether the functions decrease the numAction counter suitably
	void testNumActions() {
		farm.addAnimal(new AnimalCow());
		farm.addCrop(new CropCabbage());
		ItemForAnimal item = new ItemApple();
		ItemForCrop water = new ItemWater();
		
		game.feedAnimals(item, 0);
		game.tendToCrops(water, 0);
		
		assertEquals(game.getNumActions(), 0);
	}
	
	@Test
	void testFeedAnimals() {
		game.setNumActions(10);
		farm.addAnimal(new AnimalCow());
		farm.addAnimal(new AnimalChicken());
		ItemForAnimal feedItem = new ItemApple();
		farm.addItem(feedItem);
		farm.addItem(feedItem);
		
		game.feedAnimals(feedItem, 0);
		ArrayList<Animal> animals = farm.getAnimals();
		assertEquals(animals.get(0).getHealth(), 68.00);
		assertEquals(animals.get(1).getHealth(), 75.00);
		assertEquals(farm.getItems().size(), 1);
		assertEquals(game.getNumActions(), 9);
		
		game.feedAnimals(feedItem, 1);
		assertEquals(animals.get(0).getHealth(), 68.00);
		assertEquals(animals.get(1).getHealth(), 78.00);
		assertEquals(farm.getItems().size(), 0);
		assertEquals(game.getNumActions(), 8);
	}
	
	@Test
	void testTendToCrops() {
		game.setNumActions(10);
		
		farm.addCrop(new CropCauliflower());
		farm.addCrop(new CropCabbage());
		farm.addCrop(new CropCauliflower());
		ItemForCrop fert = new ItemFertilizer();
		ItemForCrop hoe = new ItemHoe();
		ItemForCrop water = new ItemWater();
		farm.addItem(fert);
		farm.addItem(hoe);
		farm.addItem(water);
		assertTrue(farm.getCropItems().size() == 3);
		
		game.tendToCrops(fert, 0);
		ArrayList<Crop> crops = farm.getCrops();
		assertEquals(crops.get(0).getHarvestTime(), 2);
		assertEquals(crops.get(1).getHarvestTime(), 3);
		assertEquals(farm.getCropItems().size(), 2);
		
		game.tendToCrops(hoe, 1);
		assertEquals(crops.get(0).getHarvestTime(), 2);
		assertEquals(crops.get(1).getHarvestTime(), 0);
		assertEquals(farm.getCropItems().size(), 1);
		
		game.tendToCrops(water, 2);
		assertEquals(crops.get(2).getHarvestTime(), 3);
		assertEquals(crops.get(0).getHarvestTime(), 2);
		assertEquals(crops.get(1).getHarvestTime(), 0);
		assertEquals(farm.getCropItems().size(), 1);
	}
	
	@Test
	void testRemoveRandomAnimals() {
		String status = game.removeRandomAnimals();
		assertTrue(status == "");
		
		for (int i = 0; i < 20; i += 1) {
			farm.addAnimal(new AnimalCow());
		}
		ArrayList<Animal> animals = farm.getAnimals();
		int size = animals.size();
		
		status = game.removeRandomAnimals();
		//Check if an animal has been removed. As is random, very slim chance that it has not
		assertTrue(size != animals.size());
		assertFalse(status == "");
	}
	
	@Test
	void testRemoveHalfCrops() {
		String status = game.removeHalfCrops();
		assertEquals(status, "");
		
		Farm secondFarm = new FarmBasic("test", farmer);
		GameEnviroBasic secondGame = new GameEnviroBasic();
		secondGame.setFarm(secondFarm);
		
		for (int i = 0; i < 10; i += 1) {
			farm.addCrop(new CropCabbage());
			secondFarm.addCrop(new CropCabbage());
		}
		farm.addCrop(new CropPotato());
		
		status = game.removeHalfCrops();
		String secondStatus = secondGame.removeHalfCrops();
		//Check if half the crops have been removed
		assertEquals(6, farm.getCrops().size());
		assertEquals(5, secondFarm.getCrops().size());
		assertTrue(status != "");
		assertTrue(secondStatus != "");
	}
	
	@Test
	void testRandomEvents() {
		assertEquals(game.randomEvents(2), "Your farm won first prize at the county fair!\nYou gain an extra $0.00 for your spectacular animals and crops.");
		assertEquals(game.randomEvents(12), "Your farm won first prize at the county fair!\nYou gain an extra $0.00 for your spectacular animals and crops.");
		//Check the farms money increases adequately
		farm.addAnimal(new AnimalCow());
		double currentMoney = farm.getMoney();
		game.randomEvents(2);
		assertEquals(currentMoney + 25, farm.getMoney());
		//Shouldn't trigger any event
		assertEquals(game.randomEvents(1323), "");
		assertEquals(game.randomEvents(136), "");
		
		//Add animals and crops so methods can remove some
		for (int i = 0; i < 20; i += 1) {
			farm.addAnimal(new AnimalCow());
			farm.addCrop(new CropCabbage());
		}
		//Check multiple true values for animals
		assertTrue(game.randomEvents(5) != "");
		assertTrue(game.randomEvents(45) != "");
		
		//Check multiple true values for crops
		assertTrue(game.randomEvents(19) != "");
		assertTrue(game.randomEvents(159) != "");
	}

	@Test
	void testPlayWithAnimals() {
		for (int i = 0; i < 5; i += 1) {
			farm.addAnimal(new AnimalCow());
		}
		
		double baseHappiness = farm.getAnimals().get(2).getHappiness();
		
		game.playWithAnimals(2);
		assertEquals(baseHappiness + 5, farm.getAnimals().get(2).getHappiness());
		assertEquals(baseHappiness, farm.getAnimals().get(1).getHappiness());
		
		game.playWithAnimals(4);
		game.playWithAnimals(2);
		assertEquals(baseHappiness + 10, farm.getAnimals().get(2).getHappiness());
		assertEquals(baseHappiness + 5, farm.getAnimals().get(4).getHappiness());
		assertEquals(baseHappiness, farm.getAnimals().get(0).getHappiness());
		
	}

	@Test
	void testHarvestCrops() {
		Crop wheat = new CropWheat();
		double profit = wheat.getSalePrice();
		double baseMoney = farm.getMoney();
		farm.addCrop(wheat);
		game.advanceDays();
		
		game.harvestCrops(0);
		assertEquals(farm.getMoney(), baseMoney+profit);
		assertEquals(farm.getCrops().size(), 0);
		assertEquals(game.getNumActions(), 1);
	}
	
	@Test
	void testTendToLand() {
		for (int i = 0; i < 25; i += 1) {
			farm.addAnimal(new AnimalCow());
			farm.addCrop(new CropWheat());
		}
		try {
			farm.addAnimal(new AnimalCow());
			System.out.println("No exception caught when adding too many animals");
		} catch (IllegalArgumentException e) {
			System.out.println("Exception successfully caught");
		}
		try {
			farm.addCrop(new CropWheat());
			System.out.println("No exception caught when adding too many crops");
		} catch (IllegalArgumentException e) {
			System.out.println("Exception successfully caught");
		}
		
		
		game.tendToLand();
		try {
			farm.addAnimal(new AnimalCow());
			System.out.println("No exception caught: adding animal test successful");
		} catch (IllegalArgumentException e) {
			System.out.println("Exception caught: adding animal test unsuccessful");
		}
		
		try {
			farm.addAnimal(new AnimalCow());
			System.out.println("No exception caught: adding crop test successful");
		} catch (IllegalArgumentException e) {
			System.out.println("Exception caught: adding crop test unsuccessful");
		}
		
		double happiness = farm.getAnimals().get(0).getHappiness();
		game.advanceDays();
		//Checks that the animals' happiness remains unchanged
		assertEquals(farm.getAnimals().get(0).getHappiness(), happiness);
		
	}
	
}
