package se.chalmers.tda367.vt13.dimensions.model.powerup;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;

@SuppressWarnings("serial")
public class DimensionChangePowerUp extends GameObject implements PowerUp {

	/**
	 * @param position
	 * @param size
	 * @param speed
	 */
	public DimensionChangePowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed, "data/SpeedPowerUpMini.png", "sound/SpeedPowerUp.mp3");
	}

	@Override
	public void use(GameWorld gw) {
		gw.swapDimension();
	}
	
	@Override
	public DimensionChangePowerUp clone() {
		return new DimensionChangePowerUp(getPosition().clone(), getSize().clone(),
				getSpeed().clone());
	}
}
