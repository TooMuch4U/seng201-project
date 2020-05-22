package gameLogic;

import guiScreens.*;

public class ScreenManager {
	
	private GameInformation gameInfo;
	private BasicGameLogic basicLogic;
	private AnimalLogic animalLogic;
	private CropLogic cropLogic;
	private StoreLogic storeLogic;
	/**
	 * Constructor of the ScreenManager
	 */
	public ScreenManager() {
		gameInfo = new GameInformation();
	}
	
	/**
	 * Builds the required logic classes after the information about the game has been filled in
	 */
	public void buildLogicClasses() {
		animalLogic = new AnimalLogic(gameInfo);
		cropLogic = new CropLogic(gameInfo);
		storeLogic = new StoreLogic(gameInfo);
		basicLogic = new BasicGameLogic(gameInfo, animalLogic, cropLogic);
	}
	
	
	/**
	 * Launches the main GUI screen of the game.
	 */
	public void launchMainScreen() {
		MainScreen mainWindow = new MainScreen(this, gameInfo, basicLogic);
	}
	
	/**
	 * Closes the main GUI screen of the game.
	 * @param mainWindow - the main screen of the GUI.
	 */
	public void closeMainScreen(MainScreen mainWindow) {
		mainWindow.closeWindow();
	}
	
	/**
	 * Closes the main game screen and opens the animal feeding GUI screen.
	 * @param mainWindow - the main screen of the GUI. Passed in to close the screen.
	 */
	public void launchFeedAnimalScreen(MainScreen mainWindow) {
		if (gameInfo.getNumActions() == 0) {
			throw new ActionCountException("Sorry, you've perfomed all actions for the day.");
		} else {
			closeMainScreen(mainWindow);
			FeedAnimalScreen animalWindow = new FeedAnimalScreen(this, gameInfo, animalLogic);
		}
	}
	
	/**
	 * Closes the animal feeding screen and opens the main screen of the GUI.
	 * @param animalWindow - the animal feeding screen of the GUI.
	 */
	public void closeFeedAnimalScreen(FeedAnimalScreen animalWindow) {
		animalWindow.closeWindow();
		launchMainScreen();
	}
	
	/**
	 * Closes the main game screen and opens the crop tending GUI screen.
	 * @param mainWindow - the main screen of the GUI. Passed in to close the screen.
	 */
	public void launchTendCropsScreen(MainScreen mainWindow) {
		if (gameInfo.getNumActions() == 0) {
			throw new ActionCountException("Sorry, you've perfomed all actions for the day.");
		} else {
			closeMainScreen(mainWindow);
			TendCropsScreen cropsWindow = new TendCropsScreen(this, gameInfo, cropLogic);
		}
	}
	
	/**
	 * Closes the crop tending screen and opens the main game screen.
	 * @param cropsWindow - the crop tending screen of the GUI.
	 */
	public void closeTendCropsScreen(TendCropsScreen cropsWindow) {
		cropsWindow.closeWindow();
		launchMainScreen();
	}
	
	/**
	 * Launches the setup screen of the GUI.
	 * @param game - Game environment variable
	 */
	public void launchSetupScreen(ScreenManager manager) {
		SetupScreen setupWindow = new SetupScreen(manager, gameInfo);
	}
	
	/**
	 * Closes the setup screen of the GUI.
	 * @param setupWindow - the setup screen of the GUI.
	 */
	public void closeSetupScreen(SetupScreen setupWindow) {
		setupWindow.closeWindow();
		buildLogicClasses();
		launchMainScreen();
	}
	
	/**
	 * Launches the stores main screen GUI.
	 */
	public void launchStoreMainScreen() {
		StoreMainScreen storeMain = new StoreMainScreen(this, gameInfo);
		
	}
	
	/**
	 * Closes the store main screen.
	 * @param storeMain - The store main screen.
	 */
	public void closeStoreMainScreen(StoreMainScreen storeMain) {
		storeMain.closeWindow();
		launchMainScreen();
	}
	
	/**
	 * Launches the stores animal screen GUI.
	 * @param storeMain - The store main screen GUI.
	 */
	public void launchStoreAnimalScreen(StoreMainScreen storeMain) {
		storeMain.closeWindow();
		StoreAnimalScreen storeAnimal = new StoreAnimalScreen(this, gameInfo, storeLogic);
	}
	
	/**
	 * Closes the store animal screen GUI.
	 * @param storeAnimal - Store animal GUI to be closed.
	 */
	public void closeStoreAnimalScreen(StoreAnimalScreen storeAnimal) {
		storeAnimal.closeWindow();
		launchStoreMainScreen();
	}
	
	/**
	 * Launches the store crop screen GUI.
	 */
	public void launchStoreCropScreen() {
		StoreCropScreen cropScreen = new StoreCropScreen(this, gameInfo, storeLogic);
	}
	
	/**
	 * Closes the store crop screen GUI.
	 * @param cropScreen - store crop screen GUI object to be closed.
	 */
	public void closeStoreCropScreen(StoreCropScreen cropScreen) {
		cropScreen.closeWindow();
		launchStoreMainScreen();
	}
	
	/**
	 * Launches the Store Item Screen of the GUI.
	 */
	public void launchStoreItemScreen() {
		StoreItemScreen itemScreen = new StoreItemScreen(this, gameInfo, storeLogic);
	}
	
	/**
	 * Closes the Store Item screen of the GUI.
	 * @param itemScreen - the item screen GUI object to be closed.
	 */
	public void closeStoreItemScreen(StoreItemScreen itemScreen) {
		itemScreen.closeWindow();
		launchStoreMainScreen();
	}
	
	/**
	 * Launches the select crop screen object of the GUI, and closes the main screen.
	 * Throws an action count exception if the player has no actions left for the day.
	 * @param main - the main screen GUI object that needs to be closed.
	 */
	public void launchSelectCropScreen(MainScreen main) {
		if (gameInfo.getNumActions() == 0) {
			throw new ActionCountException("Sorry, you've perfomed all actions for the day.");
		} else {
			main.closeWindow();
			SelectCropScreen cropScreen = new SelectCropScreen(this, cropLogic, gameInfo);
		}
	}
	
	/**.
	 * Closes the select crop screen object of the GUI, and relaunches the main screen.
	 * @param cropScreen - the crop select GUI object that needs to be closed.
	 */
	public void closeSelectCropScreen(SelectCropScreen cropScreen) {
		cropScreen.closeWindow();
		launchMainScreen();
	}
	
	/**
	 * Launches the select animal screen object of the GUI, and closes the main screen.
	 * Throws an ActionCountException if the player has performed all tasks for the day.
	 * @param main - the main screen GUI object that needs to be closed.
	 */
	public void launchSelectAnimalScreen(MainScreen main) {
		if (gameInfo.getNumActions() == 0) {
			throw new ActionCountException("Sorry, you've performed all actions for the day");
		} else {
			main.closeWindow();
			SelectAnimalScreen animalScreen = new SelectAnimalScreen(this, gameInfo, animalLogic);
		}
	}
	
	/**
	 * Closes the select animal screen object of the GUI, and relaunches the main screen of the game.
	 * @param animalScreen - the select animal screen GUI object that needs to be closed.
	 */
	public void closeSelectAnimalScreen(SelectAnimalScreen animalScreen) {
		animalScreen.closeWindow();
		launchMainScreen();
	}
	
	/**
	 * Closes the main screen of the game, and launches the final score screen.
	 * @param main - the main screen GUI object that needs to be closed.
	 */
	public void launchScoreScreen(MainScreen main) {
		main.closeWindow();
		ScoreScreen scoreScreen = new ScoreScreen(this, gameInfo);
	}
	
	/**
	 * Closes the final score screen, and launches a new set-up screen for the player.
	 * Called if the player chooses to start a new game.
	 * @param score - The score screen GUI object that needs to be closed.
	 */
	public void closeScoreScreen(ScoreScreen score) {
		score.closeWindow();
		ScreenManager newManager = new ScreenManager();
		newManager.launchSetupScreen(newManager);
	}
	
	/**
	 * Main method of the game object.
	 * Calls the setup screen launcher when triggered.
	 * @param args 
	 */
	public static void main(String[] args) {
		ScreenManager manager = new ScreenManager();
		manager.launchSetupScreen(manager);
	}
}
