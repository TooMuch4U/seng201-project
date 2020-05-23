package gameLogic;

/**
 * Exception thrown when the user has performed all of their actions for the day.
 */
public class ActionCountException extends IllegalArgumentException {
	/**
	 * Constructor without parameters.
	 */
	public ActionCountException() {}
	
	/**
	 * Constructor with parameters.
	 * @param message The desired error message to display.
	 */
	public ActionCountException(String message) {
		super(message);
	}
}
