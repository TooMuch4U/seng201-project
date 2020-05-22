package gameLogic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import crops.Crop;
import crops.CropCabbage;
import crops.CropCauliflower;
import crops.CropPotato;
import crops.CropWheat;
import farmsAndFarmer.FarmBasic;
import farmsAndFarmer.Farmer;
import items.ItemFertilizer;
import items.ItemForCrop;
import items.ItemHoe;
import items.ItemWater;

class CropLogicTest {

	private CropLogic cropLogic;
	private GameInformation gameInfo;
	private FarmBasic farm;
	private Farmer farmer;
	
	@BeforeEach
	void setUp() throws Exception {
		farmer = new Farmer();
		farm = new FarmBasic("test", farmer);
		gameInfo = new GameInformation();
		gameInfo.setFarm(farm);
		cropLogic = new CropLogic(gameInfo);
	}

	@Test
	void testDailyDecrease() {
		//Check to see if crop attributes decrease properly
		CropPotato pot = new CropPotato();
		CropCabbage cab = new CropCabbage();
		int potHarvestDays = pot.getHarvestTime();
		int potGrowDays = pot.getGrowingTime();
		int cabHarvestDays = cab.getHarvestTime();
		int cabGrowDays = cab.getGrowingTime();

		farm.addCrop(pot);
		farm.addCrop(cab);
		cropLogic.dailyDecrease();

		assertEquals(potHarvestDays-1, pot.getHarvestTime());
		assertEquals(potGrowDays+1, pot.getGrowingTime());
		assertEquals(cabHarvestDays-1, cab.getHarvestTime());
		assertEquals(cabGrowDays+1, cab.getGrowingTime());
	}

	@Test
	void testHarvestCrops() {
		Crop plant = new Crop("plant", 0, 10, 10);
		double profit = plant.getSalePrice();
		double baseMoney = farm.getMoney();
		farm.addCrop(plant);
		
		cropLogic.harvestCrops(0);
		assertEquals(farm.getMoney(), baseMoney+profit);
		assertEquals(farm.getCrops().size(), 0);
		assertEquals(gameInfo.getNumActions(), 1);
	}
	
	@Test
	void testTendToCrops() {
		gameInfo.setNumActions(10);

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

		cropLogic.tendToCrops(fert, 0);
		ArrayList<Crop> crops = farm.getCrops();
		assertEquals(crops.get(0).getHarvestTime(), 2);
		assertEquals(crops.get(1).getHarvestTime(), 3);
		assertEquals(farm.getCropItems().size(), 2);
		
		cropLogic.tendToCrops(hoe, 1);
		assertEquals(crops.get(0).getHarvestTime(), 2);
		assertEquals(crops.get(1).getHarvestTime(), 0);
		assertEquals(farm.getCropItems().size(), 1);
		
		cropLogic.tendToCrops(water, 2);
		assertEquals(crops.get(2).getHarvestTime(), 3);
		assertEquals(crops.get(0).getHarvestTime(), 2);
		assertEquals(crops.get(1).getHarvestTime(), 0);
		assertEquals(farm.getCropItems().size(), 1);
		
		//Check the numActions decreases properly.
		assertEquals(gameInfo.getNumActions(), 7);
	}
}
