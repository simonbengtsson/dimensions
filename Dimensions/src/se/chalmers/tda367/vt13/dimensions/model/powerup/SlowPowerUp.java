package se.chalmers.tda367.vt13.dimensions.model.powerup;

import se.chalmers.tda367.vt13.dimensions.model.GameModel;
import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;

public class SlowPowerUp extends GameObject implements PowerUp {
	
	public SlowPowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed, "", "");
	}

	@Override
	public void playSound() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void use(GameModel gm) {
		Vector3 s = gm.getPlayer().getSpeed();
		s.setX(s.getX() * 0.5f);
		
	}

}
