package se.chalmers.tda367.vt13.dimensions.model.powerup;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.PowerUpHandler;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.util.Assets;

/**
 * This powerup lowers the gravity.
 * 
 * @author Kim Kling
 */

@SuppressWarnings("serial")
public class LowGravityPowerUp extends GameObject implements PowerUp {

	public LowGravityPowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed, Assets.LOWGRAVITYPOWERUP_IMAGE,
				Assets.LOWGRAVITYPOWERUP_SOUND);
	}

	@Override
	public void use(PowerUpHandler powerUpHandler) {
		powerUpHandler.useLowGravityPowerUp();
	}

	@Override
	public LowGravityPowerUp clone() {
		return new LowGravityPowerUp(getPosition().clone(), getSize().clone(),
				getSpeed().clone());
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!(o instanceof LowGravityPowerUp) || o == null
				|| o.getClass() != this.getClass()) {
			return false;
		}
		LowGravityPowerUp p = (LowGravityPowerUp) o;
		if (this.getPosition().equals(p.getPosition())
				&& this.getSize().equals(p.getSize())
				&& this.getSpeed().equals(p.getSpeed())) {
			return true;
		} else {
			return false;
		}
	}
}
