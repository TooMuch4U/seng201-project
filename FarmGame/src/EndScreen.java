
import java.util.ArrayList;

import items.*;
import animals.*;
import crops.*;

public class EndScreen {
	
	/**
	 * The games score
	 */
	private double baseScore = 0.0;
	
	/**
	 * Constructor for EndScreen
	 * @param farm farm to calculate score for.
	 */
	public EndScreen(Farm farm) {
		baseScore = this.calculateScore(farm);
	}
	
	/**
	 * Calculates the score of the farm.
	 * 
	 * @param farm Farm to calculate score of
	 * @return
	 */
	private double calculateScore(Farm farm) {
		double score = 0.0;
		ArrayList<Animal> animals = farm.getAnimals();
		ArrayList<Crop> crops = farm.getCrops();
		ArrayList<Item> items = farm.getItems();
		
		// Add score from animals
		for (Animal animal : animals) {
			score = score + animal.getHappiness() + animal.getHealth();
		}
		
		// Add score from crops
		for (Crop crop : crops) {
			score = score + crop.getPrice();
		}
		
		// Add score from items
		for (Item item : items) {
			score = score + item.getPrice();
		}
		
		score = score + farm.getMoney();
		score = score + farm.getBonusScore();
		
		return(score);
	}
	
	/**
	 * Returns the farms score as a string.
	 * @return
	 */
	public String displayScore() {
		return("" + baseScore);
	}
	
}
