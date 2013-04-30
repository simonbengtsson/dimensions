package se.chalmers.tda367.vt13.dimensions.model.powerup;

import java.io.Serializable;

import se.chalmers.tda367.vt13.dimensions.model.GameModel;
import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;

/**
 * This powerup doubles the speed, if the players speed is equal or below 2 px/frame.
 * @author Carl Fredriksson
 */

public class SpeedPowerUp extends GameObject implements PowerUp,Serializable {


	// Instance variables
	private final float speedModifier = 2;
	private static final long serialVersionUID = 1L;

	// Public methods
	/**
	 * Constructor. Calls the super class constructor.
	 * @param position the position of the SpeedPowerUp
	 * @param size the size of the SpeedPowerUp
	 * @param speed the speed of the SpeedPowerUp
	 */
	public SpeedPowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed, "data/SpeedPowerUpImg.png", "sound/SpeedPowerUp.mp3");
	}
	
	@Override
	public void use(GameModel gm) {
		if (gm.getPlayer().getSpeed().getX() <= gm.getPlayer().getBaseXSpeed()) {
			gm.getPlayer().getSpeed().setX(gm.getPlayer().getSpeed().getX() * speedModifier);
		}
		playSound();
	}
	
	// Private methods
	
}
