package se.chalmers.tda367.vt13.dimensions.model.powerup;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.util.Assets;

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
	public void use(GameWorld world) {
		world.startDimensionTimer();
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
