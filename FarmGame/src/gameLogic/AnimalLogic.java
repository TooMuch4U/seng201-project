package gameLogic;

import java.util.ArrayList;

import animals.Animal;
import farmsAndFarmer.Farm;
import items.ItemForAnimal;

/**
 * Contains logic dictating the manipulation of animals and their attributes
 *
 */
public class AnimalLogic {
	
	private GameInformation gameInfo;
	private Farm farm;
	
	public AnimalLogic(GameInformation info) {
		gameInfo = info;
		farm = gameInfo.getFarm();
	}
	
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
	 * @param feedItem - the ItemForAnimal that will be used to increase the animal's health.
	 * @param animalIndex - the index of the animal within the list that the user wishes to feed.
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
	 * @param animalIndex takes the integer of the position in the arrayList to get the animal.
	 */
	public void playWithAnimals(int animalIndex) {
		double change = 5.0; // Change constant to increase animals happiness by
		ArrayList<Animal> Animals = farm.getAnimals();
		Animal animal = Animals.get(animalIndex);
		animal.changeHappiness(change);
		gameInfo.changeNumActions();
	}
	
}
