package gameLogic;

import java.util.ArrayList;
import java.util.Random;

import animals.Animal;
import crops.Crop;
import farmsAndFarmer.Farm;

/**
 * Contains logic dictating the function of random events
 */
public class RandomEvents {
	
	/**
	 * The random number generator used to determine when an event occurs.
	 */
	private Random rng = new Random();
	/**
	 * The logic class containing the vital information about the current game.
	 */
	private GameInformation gameInfo;
	/**
	 * The player's farm.
	 */
	private Farm farm;
	
	public RandomEvents(GameInformation info) {
		gameInfo = info;
		farm = gameInfo.getFarm();
	}
	
	/**
	 * Removes a random number of the animals from the farm using the game environment's random number generator.
	 * If an animal doesn't escape, it loses a substantial amount of happiness.
	 * A maximum of half of the player's animals can escape. The exact number is generated using the rng.
	 * Returns a string detailing the event.
	 * If the player has one or no animals, nothing happens, and the returned string contains nothing.
	 * @return A string detailing the event. Returns an empty string if the player doesn't have enough animals.
	 */
	public String removeRandomAnimals() {
		ArrayList<Animal> animals = farm.getAnimals();
		if (animals.size() < 2) {
			return "";
		} else {
			int maxNum = animals.size()/2;
			int numRequired = (rng.nextInt()%maxNum)+1;
			int i = 0;
			ArrayList<Integer> needRemoving = new ArrayList<Integer>();
			while(numRequired > 0 && i < animals.size()) {
				if (rng.nextInt()%2 == 0 || numRequired == animals.size()-(i+1)) {
					//Keep track of the indices that need removing. Stored from highest index to lowest.
					needRemoving.add(0, i);
					numRequired -= 1;
				}
				i += 1;
			}
			//Remove indices in top-down order
			for (int num: needRemoving) {
				animals.remove(num);
			}
			for (Animal animal: animals) {
				animal.changeHappiness(-20);
			}
			return "Overnight, your fence broke, and some of your animals escaped.\nThe ones that didn't are now sad that their friends are gone.";
		}
	}
	
	/**
	 * Removes half of the crops from the farm using a random number generator.
	 * Uses a while loop to ensure that the number of crops removed is adequate.
	 * If the rng generates an odd number, or the remaining crops in the list need to be removed, the next crop will be removed.
	 * @return A String detailing the event. If the player has one or no crops, returns an empty string.
	 * 
	 */
	public String removeHalfCrops() {
		ArrayList<Crop> crops = farm.getCrops();
		int cropNum = crops.size();
		int numRequired = cropNum/2;
		int i = 0;
		ArrayList<Integer> needRemoving = new ArrayList<Integer>();
		while(numRequired > 0) {
			if (numRequired == (cropNum - (i+1)) || rng.nextInt()%2 == 1) {
				//Keep track of the indices that need removing. Stored from highest index to lowest.
				needRemoving.add(0, i);
				numRequired -= 1;
			}
			i += 1;
		}
		//Remove indices in top-down order
		for (int num: needRemoving) {
			crops.remove(num);
		}
		if (cropNum < 2) {
			return "";
		} else {
			return "A drought has struck, and your crops are thirsty.\nHalf of them die from lack of water.";
		}
	}
	
	/**
	 * The triggering function for the random events.
	 * Utilizes a random number generator to determine whether the events happen or not.
	 * County Fair - 10% chance of occurring - player gets a bonus sum of money depending on the numbers of crops and animals they have.
	 * Broken Fence - 5% chance of occurring - player loses half of their farm's animals. The animals are chose randomly using removeHalfAnimals().
	 * Drought - 5% chance of occurring - player loses half of their farm's crops. The crops are chose randomly using removeHalfCrops().
	 * @param randNum Number that determines if a random event occurs
	 * @return eventInfo A string detailing what event happened, if any.
	 */
	public String randomEvents(int randNum) {
		String eventInfo = "";
		if(randNum%10 == 2) {
			//County fair: win a bonus amount of money 
			int animalNum = farm.getAnimals().size();
			int cropNum = farm.getCrops().size();
			double moneyGain = 25*animalNum + 10*cropNum;		
			
			eventInfo = String.format("Your farm won first prize at the county fair!\nYou gain an extra $%.2f for your spectacular animals and crops.", moneyGain);
			farm.changeMoney(moneyGain);
			
		} else if (randNum%20 == 5) {
			//Broken fence: animals escape
			eventInfo = removeRandomAnimals();
		} else if (randNum%20 == 19 ) {
			//Drought: crops die
			eventInfo = removeHalfCrops();
		}
		return eventInfo;
	}
	
	/**
	 * Calls the randomEvents method to potentially trigger a random event
	 * @return event A String detailing what, if any, random event occured
	 */
	public String triggerRandomEvents() {
		int randNum = rng.nextInt();
		String event = randomEvents(randNum);
		return event;
	}
}
