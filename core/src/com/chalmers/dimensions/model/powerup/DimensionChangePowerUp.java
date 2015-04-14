package com.chalmers.dimensions.model.powerup;

import com.chalmers.dimensions.model.GameObject;
import com.chalmers.dimensions.model.PowerUpHandler;
import com.chalmers.dimensions.model.Vector3;
import com.chalmers.dimensions.util.Assets;

/**
 * This PowerUp changes the active dimension.
 */
@SuppressWarnings("serial")
public class DimensionChangePowerUp extends GameObject implements PowerUp {

	/**
	 * @param position
	 * @param size
	 * @param speed
	 */
	public DimensionChangePowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed, Assets.DIMENSIONCHANGE_IMAGE,
				Assets.DIMENSIONCHANGE_SOUND);
	}

	@Override
	public void use(PowerUpHandler powerUpHandler) {
		powerUpHandler.useDimensionChangePowerUp();
	}

	@Override
	public DimensionChangePowerUp clone() {
		return new DimensionChangePowerUp(getPosition().clone(), getSize()
				.clone(), getSpeed().clone());
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!(o instanceof DimensionChangePowerUp) || o == null
				|| o.getClass() != this.getClass()) {
			return false;
		}
		DimensionChangePowerUp p = (DimensionChangePowerUp) o;
		if (this.getPosition().equals(p.getPosition())
				&& this.getSize().equals(p.getSize())
				&& this.getSpeed().equals(p.getSpeed())) {
			return true;
		} else {
			return false;
		}
	}
}
