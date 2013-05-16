package se.chalmers.tda367.vt13.dimensions.model.powerup;

import se.chalmers.tda367.vt13.dimensions.model.*;

public class CheckPointPowerUp extends GameObject implements PowerUp{

	protected CheckPointPowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed, "", "");
	}

	@Override
	public void use(GameWorld gm) {
		gm.placeCheckPoint();
	}

	@Override
	public boolean isUsed() {
		// TODO Auto-generated method stub
		return false;
	}

}
