package se.chalmers.tda367.vt13.dimensions.model;

/**
 * Interface to be implemented by all PowerUps.
 * @author Carl Fredriksson
 */
public interface PowerUp  {

	/**
	 * Method called when collision is detected between
	 * the player and the PowerUp. Affects the player
	 * in different ways, depending on the implementing
	 * PowerUp class.
	 */
	public void use(GameModel gm);
	
}
