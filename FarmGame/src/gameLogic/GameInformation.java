package gameLogic;

import java.util.Random;

import farmsAndFarmer.Farm;

public class GameInformation {
	
	/**
	 * The player's farm.
	 */
	private Farm farm;
	/**
	 * The number of in-game days the player wishes to play for.
	 */
	private int requiredDays;
	/**
	 * The current day the player is on.
	 */
	private int currentDays = 0;
	/**
	 * The number of major actions permittable in a day.
	 */
	private int numActions = 2;
	/**
	 * The store object.
	 */
	private Store store = new Store();
	/**
	 * An int that dictates how many days will pass before animal happiness decreases.
	 */
	private int decreaseHappinessDays = 1;
	/**
	 * A boolean value dictating whether random events are turned on for the game.
	 */
	private boolean randomEventsOn;
	
	/**
	 * Constructor for the game's information
	 */
	public GameInformation() {
	}
	
	
	/**
	 * Sets the value of the player's farm.
	 * Used only in the GUI implementation.
	 * @param playerFarm The player's farm, created using information on the set-up screen.
	 */
	public void setFarm(Farm playerFarm) {
		farm = playerFarm;
	}
	
	/**
	 * Sets the desired amount of days for the player.
	 * Used only in the GUI implementation.
	 * @param desiredDays The amount of days the player wants to play for.
	 */
	public void setRequiredDays(int desiredDays) {
		requiredDays = desiredDays;
	}
	
	/**
	 * Sets the number of actions permittable in a single day.
	 * @param number The number of actions that can be completed in one day.
	 */
	public void setNumActions(int number) {
		numActions = number;
	}
	
	/**
	 * Sets whether the random events occur during gameplay or not.
	 * @param accept true if random events will occur.
	 */
	public void setRandomEventsOn(boolean accept) {
		randomEventsOn = accept;
	}
	
	/**
	 * Sets the amount of days until animal happiness decreases.
	 * @param days The required days until a decrease in happiness.
	 */
	public void setDecreaseHappinessDays(int days) {
		decreaseHappinessDays = days;
	}
	
	/**
	 * Adds one day to the current day counter.
	 */
	public void addOneDay() {
		currentDays += 1;
	}
	
	/**
	 * Subtracts one from the current value of numActions. Used when a daily activity is performed.
	 */
	public void changeNumActions() {
		numActions -= 1;
	}
	
	/**
	 * Returns the store object.
	 * @return store The store object.
	 */
	public Store getStore() {
		return store;
	}
	
	/**
	 * Returns the current value of numActions.
	 * @return numActions The number of actions left in the day.
	 */
	public int getNumActions() {
		return numActions;
	}
	
	/**
	 * Returns the number of days currently surpassed.
	 * @return currentDays The number of days the game has gone for so far.
	 */
	public int getCurrentDays() {
		return currentDays;
	}
	
	/**
	 * Returns the number of days the game must continue for.
	 * @return requiredDays The number of days the player wanted the game to go for.
	 */
	public int getRequiredDays() {
		return requiredDays;
	}
	
	/**
	 * Returns the player's farm object.
	 * @return farm The player's farm.
	 */
	public Farm getFarm() {
		return farm;
	}
	
	/**
	 * Returns whether the random events are turned on for the game.
	 * @return randomEventsOn true if random events are enabled.
	 */
	public boolean getRandomEventsOn() {
		return randomEventsOn;
	}
	
	/**
	 * Returns the number of days left until animal happiness decreases.
	 * @return decreaseHappinessDays Days until animals lose happiness.
	 */
	public int getDecreaseHappinessDays() {
		return decreaseHappinessDays;
	}
	
	
}
