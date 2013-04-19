package se.chalmers.tda367.vt13.dimensions.model.powerup;

import se.chalmers.tda367.vt13.dimensions.model.*;

/**
 * This powerup sets the gravity to half of the current.
 * @author Kim Kling
 */

public class LowGravityPowerUp extends GameObject implements PowerUp {

	public LowGravityPowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed, "data/SpeedPowerUpImg.png", "sound/SpeedPowerUp.mp3");
	}

	@Override
	public void use(GameModel gm) {
		Player p = gm.getPlayer();
		p.setGravity(p.getGravity() * 0.5f);
	}

}
