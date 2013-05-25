package se.chalmers.tda367.vt13.dimensions.model.powerup;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.PowerUpHandler;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;

/**
 * This PowerUp decreases the player's speed.
 */
@SuppressWarnings("serial")
public class SlowPowerUp extends GameObject implements PowerUp {

	public SlowPowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed, "", "");
	}

	@Override
	public void use(PowerUpHandler powerUpHandler) {
		powerUpHandler.useSlowPowerUp();
	}

	@Override
	public SlowPowerUp clone() {
		return new SlowPowerUp(getPosition().clone(), getSize().clone(),
				getSpeed().clone());
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!(o instanceof SlowPowerUp) || o == null
				|| o.getClass() != this.getClass()) {
			return false;
		}
		SlowPowerUp p = (SlowPowerUp) o;
		if (this.getPosition().equals(p.getPosition())
				&& this.getSize().equals(p.getSize())
				&& this.getSpeed().equals(p.getSpeed())) {
			return true;
		} else {
			return false;
		}
	}
}
