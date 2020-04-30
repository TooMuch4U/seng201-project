import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import animals.*;
import crops.*;
import items.*;

class GameEnviroBasicTest {
	
	private Farm farm;
	private Farmer farmer;
	private GameEnviroBasic enviro;
	
	
	@BeforeEach
	void setUp() throws Exception {
		farmer = new Farmer();
		farm = new FarmBasic("test farm", farmer);
		enviro = new GameEnviroBasic(8, farm);
		
	}
	
	@Test
	void testAdvanceDays() {
		GameEnviroBasic secondEnviro = new GameEnviroBasic(1, farm);
		enviro.advanceDays();
		assertEquals(1, enviro.getCurrentDays());
		assertEquals(2, enviro.getNumActions());
		secondEnviro.advanceDays();
		//System should output the score and end-game prompts
	}
	
	@Test
	//Test to determine whether the functions decrease the numAction counter suitably
	void testNumActions() {
		farm.addAnimal(new AnimalCow());
		farm.addCrop(new CropCabbage());
		ItemForAnimal item = new ItemApple();
		
		enviro.feedAnimals(item, 0);
		enviro.tendToCrops(0);
		
		assertEquals(enviro.getNumActions(), 0);
	}
	
	@Test
	//Test to determine whether functions execute when the action count is 0
	void testActionExceptions() {
		enviro.setNumActions(0);
		try {
			enviro.feedAnimals(new ItemApple(), 0);
		} catch (ActionCountException e) {
			System.out.print("Error successfully caught:");
			System.err.print(e.getMessage()+'\n');
		}
		
		try {
			enviro.tendToCrops(0);
		} catch (ActionCountException e) {
			System.out.print("Error successfully caught:");
			System.err.print(e.getMessage()+'\n');
		}
	}
	
	@Test
	void testFeedAnimals() {
		enviro.setNumActions(10);
		farm.addAnimal(new AnimalCow());
		farm.addAnimal(new AnimalChicken());
		ItemForAnimal feedItem = new ItemApple();
		
		enviro.feedAnimals(feedItem, 0);
		ArrayList<Animal> animals = farm.getAnimals();
		assertEquals(animals.get(0).getHealth(), 68.00);
		assertEquals(animals.get(1).getHealth(), 75.00);
		
		farm.setAnimals(animals);
		enviro.feedAnimals(feedItem, 1);
		ArrayList<Animal> moreAnimals = farm.getAnimals();
		assertEquals(moreAnimals.get(0).getHealth(), 68.00);
		assertEquals(moreAnimals.get(1).getHealth(), 78.00);
		
	}
	
	@Test
	void testTendToCrops() {
		enviro.setNumActions(10);
		
		farm.addCrop(new CropCauliflower());
		farm.addCrop(new CropCabbage());
		ItemForCrop fert = new ItemFertilizer();
		
		enviro.tendToCrops(fert, 0);
		ArrayList<Crop> crops = farm.getCrops();
		assertEquals(crops.get(0).getHarvestTime(), 2);
		assertEquals(crops.get(1).getHarvestTime(), 3);
		
		farm.setCrops(crops);
		enviro.tendToCrops(fert, 1);
		ArrayList<Crop> moreCrops = farm.getCrops();
		assertEquals(moreCrops.get(0).getHarvestTime(), 2);
		assertEquals(moreCrops.get(1).getHarvestTime(), 1);
		
		farm.setCrops(moreCrops);
		farm.addCrop(new CropPotato());
		enviro.tendToCrops(2);
		ArrayList<Crop> newCrops = farm.getCrops();
		assertEquals(newCrops.get(2).getHarvestTime(), 1);
		assertEquals(newCrops.get(0).getHarvestTime(), 2);
		assertEquals(newCrops.get(1).getHarvestTime(), 1);
	}

}
