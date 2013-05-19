package se.chalmers.tda367.vt13.dimensions.model.powerup;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;

public class CheckPointPowerUp extends GameObject implements PowerUp{
	private static final long serialVersionUID = -3976535550976929865L;

	protected CheckPointPowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed, "", "");
	}

	@Override
	public void use(GameWorld gm) {
		gm.placeCheckPoint();
	}
	
	@Override
	public CheckPointPowerUp clone() {
		return new CheckPointPowerUp(getPosition().clone(), getSize().clone(), getSpeed().clone());
	}
}
