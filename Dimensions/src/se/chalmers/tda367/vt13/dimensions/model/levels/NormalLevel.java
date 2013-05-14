package se.chalmers.tda367.vt13.dimensions.model.levels;

import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;
import se.chalmers.tda367.vt13.dimensions.util.WriteLevel;

@SuppressWarnings("serial")
public class NormalLevel extends Level {
	public NormalLevel() {
		super("Normal");
		spawnSingleBlock(getGameObjects(), new Vector3(), new Vector3(20000, 50, 500));
		spawnPowerUp(gameObjects, PowerUp.SPEED, new Vector3(700, 200, 200));
		spawnPowerUp(gameObjects, PowerUp.LOW_GRAVITY, new Vector3(1600, 400,
				200));
		WriteLevel rv = new WriteLevel();
		rv.saveToFile("Normal", this);
	}

}
