package se.chalmers.tda367.vt13.dimensions.model;

import java.io.Serializable;

/**
 * Class for the speed PowerUp. Increases
 * the players speed when used.
 * @author Carl Fredriksson
 */

public class SpeedPowerUp extends GameObject implements PowerUp,Serializable {


	// Instance variables
	final float speedModifier = 2;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Public methods
	/**
	 * Constructor. Calls the super class constructor.
	 * @param position the position of the SpeedPowerUp
	 * @param size the size of the SpeedPowerUp
	 * @param speed the speed of the SpeedPowerUp
	 */
	public SpeedPowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed);
	}
	
	@Override
	public void use(GameModel gm) {
		if (gm.getPlayer().getSpeed().getX() <= gm.getPlayer().getBaseXSpeed()) {
			gm.getPlayer().getSpeed().setX(gm.getPlayer().getSpeed().getX() * speedModifier);
		}
	}

	// Private methods
	
}
