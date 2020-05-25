package gameLogic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import animals.Animal;
import animals.AnimalChicken;
import animals.AnimalCow;
import animals.AnimalPig;
import farmsAndFarmer.FarmBasic;
import farmsAndFarmer.Farmer;
import items.ItemApple;
import items.ItemForAnimal;

class AnimalLogicTest {

	private AnimalLogic animalLogic;
	private GameInformation gameInfo;
	private FarmBasic farm;
	private Farmer farmer;
	
	@BeforeEach
	void setUp() throws Exception {
		farmer = new Farmer();
		farm = new FarmBasic("test", farmer);
		gameInfo = new GameInformation();
		gameInfo.setFarm(farm);
		animalLogic = new AnimalLogic(gameInfo);
	}

	@Test
	void testDailyDecrease() {
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
		animalLogic.dailyDecrease(false);
		
		assertEquals(cowHealth-2, cow.getHealth());
		assertEquals(cowHappiness, cow.getHappiness());
		assertEquals(pigHealth-2, pig.getHealth());
		assertEquals(pigHappiness, pig.getHappiness());
		assertEquals(startMoney+cowIncome+pigIncome, farm.getMoney());
		
		animalLogic.dailyDecrease(true);
		assertEquals(cowHappiness-2, cow.getHappiness());
		assertEquals(pigHappiness-2, pig.getHappiness());
		assertEquals(cowHealth-4, cow.getHealth());
		assertEquals(pigHealth-4, pig.getHealth());
	}
	
	@Test
	void testFeedAnimals() {
		gameInfo.setNumActions(10);
		farm.addAnimal(new AnimalCow());
		farm.addAnimal(new AnimalChicken());
		ItemForAnimal feedItem = new ItemApple();
		farm.addItem(feedItem);
		farm.addItem(feedItem);
		
		animalLogic.feedAnimals(feedItem, 0);
		ArrayList<Animal> animals = farm.getAnimals();
		assertEquals(animals.get(0).getHealth(), 68.00);
		assertEquals(animals.get(1).getHealth(), 75.00);
		assertEquals(farm.getItems().size(), 1);
		assertEquals(gameInfo.getNumActions(), 9);
		
		animalLogic.feedAnimals(feedItem, 1);
		assertEquals(animals.get(0).getHealth(), 68.00);
		assertEquals(animals.get(1).getHealth(), 78.00);
		assertEquals(farm.getItems().size(), 0);
		assertEquals(gameInfo.getNumActions(), 8);
	}
	
	@Test
	void testPlayWithAnimals() {
		gameInfo.setNumActions(5);
		for (int i = 0; i < 5; i += 1) {
			farm.addAnimal(new AnimalCow());
		}
		
		double baseHappiness = farm.getAnimals().get(2).getHappiness();
		
		animalLogic.playWithAnimals(2);
		assertEquals(baseHappiness + 5, farm.getAnimals().get(2).getHappiness());
		assertEquals(baseHappiness, farm.getAnimals().get(1).getHappiness());
		
		animalLogic.playWithAnimals(4);
		animalLogic.playWithAnimals(2);
		assertEquals(baseHappiness + 10, farm.getAnimals().get(2).getHappiness());
		assertEquals(baseHappiness + 5, farm.getAnimals().get(4).getHappiness());
		assertEquals(baseHappiness, farm.getAnimals().get(0).getHappiness());
		
		assertEquals(gameInfo.getNumActions(), 2);
	}
	
	@Test
	void testCalculateBonusScore() {
		farm.changeAnimalLimit(25);
		Animal lump = new Animal("lump", 10.00, 10.00, 10.00, 10.00);
		for (int i = 0; i < 5; i += 1) {
			farm.addAnimal(lump);
		}
		animalLogic.calculateBonusScore();
		assertEquals(farm.getBonusScore(), 50.0);
		
		for (int j = 0; j < 20; j += 1) {
			farm.addAnimal(lump);
		}
		animalLogic.calculateBonusScore();
		assertEquals(farm.getBonusScore(), 300.0);
	}
}
