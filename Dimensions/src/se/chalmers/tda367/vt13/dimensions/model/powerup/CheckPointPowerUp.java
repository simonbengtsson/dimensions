package se.chalmers.tda367.vt13.dimensions.model.powerup;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.util.Assets;

public class CheckPointPowerUp extends GameObject implements PowerUp {
	private static final long serialVersionUID = -3976535550976929865L;

	public CheckPointPowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed, Assets.CHECKPOINTPOWERUP_IMAGE,
				Assets.CHECKPOINTPOWERUP_SOUND);
	}

	@Override
	public void use(GameWorld gm) {
		gm.placeCheckPoint();
	}

	@Override
	public CheckPointPowerUp clone() {
		return new CheckPointPowerUp(getPosition().clone(), getSize().clone(),
				getSpeed().clone());
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!(o instanceof CheckPointPowerUp) || o == null
				|| o.getClass() != this.getClass()) {
			return false;
		}
		CheckPointPowerUp p = (CheckPointPowerUp) o;
		if (this.getPosition().equals(p.getPosition())
				&& this.getSize().equals(p.getSize())
				&& this.getSpeed().equals(p.getSpeed())) {
			return true;
		} else {
			return false;
		}
	}
}
