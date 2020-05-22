package gameLogic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import animals.AnimalCow;
import animals.AnimalPig;
import crops.CropCabbage;
import crops.CropPotato;
import crops.CropWheat;
import farmsAndFarmer.Farm;
import farmsAndFarmer.FarmBasic;
import farmsAndFarmer.Farmer;

class BasicGameLogicTest {
	
	private FarmBasic farm;
	private Farmer farmer;
	private GameInformation gameInfo;
	private AnimalLogic animal;
	private CropLogic crop;
	private BasicGameLogic game;
	
	@BeforeEach
	void setUp() throws Exception {
		gameInfo = new GameInformation();
		animal = new AnimalLogic(gameInfo);
		crop = new CropLogic(gameInfo);
		farmer = new Farmer();
		farm = new FarmBasic("TEST", farmer);
		gameInfo.setFarm(farm);
		game = new BasicGameLogic(gameInfo, animal, crop);
	}

	@Test
	void testAdvanceDays() {
		gameInfo.setRandomEventsOn(false);		
		game.advanceDays();
		assertEquals(1, gameInfo.getCurrentDays());
		assertEquals(2, gameInfo.getNumActions());
	}
	
	@Test
	void testTendToLand() {
		
		//Ensure the exceptions are thrown when too many animals and crops are added
		for (int i = 0; i < 10; i += 1) {
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
		
		//Now enxure no exceptions are caught after land has been tended to
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
		
		//Check the functon can't be called when the number of exceptions is too low
		gameInfo.setNumActions(0);
		try {
			game.tendToLand();
			System.out.println("Exception not caught; tending land action test unsuccessful");
		} catch (ActionCountException e) {
			System.out.println("Exception successfully caught");
			assertEquals(e.getMessage(), "Sorry, you've performed all your actions for today.");
		}
		
	}
	
	@Test
	void testReturnStrings() {
		String cropString = game.viewCropStatus();
		assertEquals(cropString, "You don't seem to have any crops right now. Visit the store to buy some.\n");
		
		String animalString = game.viewAnimalStatus();
		assertEquals(animalString, "You don't have any animals right now. Visit the store to buy some.\n");
	}

}
