package gameLogic;

import java.util.ArrayList;

import items.*;
import animals.*;
import crops.*;
import farmsAndFarmer.Farm;

public class EndScreen {
	
	/**
	 * The game's score.
	 */
	private double baseScore = 0.0;
	
	/**
	 * Constructor for EndScreen.
	 * @param farm Farm to calculate score for.
	 */
	public EndScreen(Farm farm) {
		baseScore = calculateScore(farm);
	}
	
	/**
	 * Calculates the score of the farm.
	 * 
	 * @param farm The Farm to calculate score of.
	 * @return score The total score of the game.
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
		
		return score;
	}
	
	/**
	 * Returns the farm's score as a string.
	 * @return A String representation of baseScore.
	 */
	public String displayScore() {
		return("" + baseScore);
	}
	
}
