package se.chalmers.tda367.vt13.dimensions.model.powerup;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;

@SuppressWarnings("serial")
public class SlowPowerUp extends GameObject implements PowerUp {
	
	public SlowPowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed, "", "");
	}

	@Override
	public void use(GameWorld gm) {
		Vector3 s = gm.getPlayer().getSpeed();
		s.setX(s.getX() * 0.5f);
	}
	
	@Override
	public SlowPowerUp clone() {
		return new SlowPowerUp(getPosition().clone(), getSize().clone(),
				getSpeed().clone());
	}
}
