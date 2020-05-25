package gameLogic;

import java.util.ArrayList;

import animals.Animal;
import farmsAndFarmer.Farm;
import items.ItemForAnimal;

/**
 * Contains logic dictating the manipulation of animals and their attributes.
 *
 */
public class AnimalLogic {
	
	/**
	 * The logic class containing the vital information about the current game.
	 */
	private GameInformation gameInfo;
	/**
	 * The player's farm object.
	 */
	private Farm farm;
	
	/**
	 * Constructor for AnimalLogic with parameters. 
	 * @param info The GameInformation class containing the information about the current game.
	 */
	public AnimalLogic(GameInformation info) {
		gameInfo = info;
		farm = gameInfo.getFarm();
	}
	
	/**
	 * Decreases each animal's health by a set amount. 
	 * If the number of days until animal happiness decreases is zero, decrease happiness by a set amount too. 
	 * @param happinessChange A boolean dictating whether happiness should decrease.
	 */
	public void dailyDecrease(boolean happinessChange) {
		ArrayList<Animal> animals = farm.getAnimals();
		for (Animal animal: animals) {
			double income = animal.getIncome();
			farm.changeMoney(income);
			animal.changeHealth(-2);
			if (happinessChange) {
				animal.changeHappiness(-2);
			}
		}
	}
	
	/**
	 * Allows the user to feed an animal on their farm. 
	 * Counts as a daily action, and as such can't be performed if all actions are completed. 
	 * @param feedItem The ItemForAnimal that will be used to increase the animal's health. 
	 * @param animalIndex The index of the animal within the list that the user wishes to feed.
	 */
	public void feedAnimals(ItemForAnimal feedItem, int animalIndex) {
		ArrayList<Animal> animals = farm.getAnimals();
		double healthBonus = feedItem.getBenefit();
		Animal chosenAnimal = animals.get(animalIndex);
		chosenAnimal.changeHealth(healthBonus);
		farm.getItems().remove(feedItem);
		gameInfo.changeNumActions();
	}
	
	/**
	 * Play with animals, increases the happiness of an animal in the farm. 
	 * @param animalIndex Takes the integer of the position in the arrayList to get the animal.
	 */
	public void playWithAnimals(int animalIndex) {
		double change = 5.0; // Change constant to increase animals happiness by
		ArrayList<Animal> Animals = farm.getAnimals();
		Animal animal = Animals.get(animalIndex);
		animal.changeHappiness(change);
		gameInfo.changeNumActions();
	}
	
	/**
	 * Method that is run at the end of each day. 
	 * Calculates the total bonus score generated today by all of the farm's animals.
	 */
	public void calculateBonusScore() {
		int incr = 0;
		ArrayList<Animal> animals = farm.getAnimals();
		// Add score from animals
		for (Animal animal : animals) {
			int hap = (int) animal.getHappiness();
			incr = incr + hap;
		}

		farm.incrBonus(incr);

	}
	
}
