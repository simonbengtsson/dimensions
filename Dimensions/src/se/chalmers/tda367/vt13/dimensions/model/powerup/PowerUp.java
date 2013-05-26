package se.chalmers.tda367.vt13.dimensions.model.powerup;

import se.chalmers.tda367.vt13.dimensions.model.PowerUpHandler;

/**
 * Interface to be implemented by all PowerUps.
 * 
 * @author Carl Fredriksson
 */
public interface PowerUp {
	public static final int SPEED = 1;
	public static final int LOW_GRAVITY = 2;
	public static final int DIMENSION_CHANGE = 3;
	public static final int SLOW = 4;

	/**
	 * Method called when collision is detected between the player and the
	 * PowerUp. Affects the player in different ways, depending on the
	 * implementing PowerUp class.
	 * 
	 * @param powerUpHandler
	 *            The PowerUpHandler handling PowerUp usage.
	 */
	public void use(PowerUpHandler powerUpHandler);
}
