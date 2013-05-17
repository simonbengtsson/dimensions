package se.chalmers.tda367.vt13.dimensions.model.powerup;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.util.Constants;

/**
 * This powerup sets the gravity to half of the current.
 * 
 * @author Kim Kling
 */

@SuppressWarnings("serial")
public class LowGravityPowerUp extends GameObject implements PowerUp {

	public LowGravityPowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed, Constants.LOWGRAVITYPOWERUP_IMAGE,
				Constants.LOWGRAVITYPOWERUP_SOUND);
	}

	@Override
	public void use(GameWorld gm) {
		gm.setGravity(gm.getGravity() * 0.9f);
	}

	@Override
	public LowGravityPowerUp clone() {
		return new LowGravityPowerUp(getPosition().clone(), getSize().clone(),
				getSpeed().clone());
	}
}
