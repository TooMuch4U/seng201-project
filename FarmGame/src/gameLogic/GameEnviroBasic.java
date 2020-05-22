package gameLogic;

import java.util.ArrayList;
import java.util.Random;

import animals.*;
import crops.*;
import items.*;
import farmsAndFarmer.Farm;
import farmsAndFarmer.Farmer;
import guiScreens.*;

/** Game environment class
 * Represents the games current state and handles most processes
 *
 */
public class GameEnviroBasic {
	
	/**
	 * The player's farm
	 */
	private Farm farm;
	/**
	 * The number of days the player wishes to play for
	 */
	private int requiredDays;
	/**
	 * A random number generator used to determine random events
	 */
	private Random rng = new Random();
	/**
	 * The current day the player is on
	 */
	private int currentDays = 0;
	/**
	 * The number of actions permittable in a day
	 */
	private int numActions = 2;
	/**
	 * The store object
	 */
	private Store store = new Store();
	/**
	 * A boolean to determine whether the player can continue playing
	 */
	public boolean gameEnded = false;
	/**
	 * An int that dictates how many days will pass before animal happiness decreases
	 */
	private int decreaseHappinessDays = 1;
	/**
	 * A boolean value dictating whether random events are turned on for the game
	 */
	private boolean randomEventsOn;
	
	/**
	 * Constructor without parameters.
	 * Used with GUI implementation of the application.
	 */
	public GameEnviroBasic() {}

	
}
