package se.chalmers.tda367.vt13.dimensions.model.powerup;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld;
import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.Player;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;


/**
 * This powerup sets the gravity to half of the current.
 * @author Kim Kling
 */

public class LowGravityPowerUp extends GameObject implements PowerUp {

	public LowGravityPowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed, "data/SpeedPowerUpImg.png", "sound/SpeedPowerUp.mp3");
	}

	@Override
	public void use(GameWorld gm) {
		gm.setGravity(gm.getGravity() * 0.5f);
	}

}
