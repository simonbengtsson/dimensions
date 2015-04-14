package com.chalmers.dimensions.model.powerup;

import com.chalmers.dimensions.model.GameObject;
import com.chalmers.dimensions.model.PowerUpHandler;
import com.chalmers.dimensions.model.Vector3;
import com.chalmers.dimensions.util.Assets;

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
