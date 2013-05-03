package se.chalmers.tda367.vt13.dimensions.model.levels;

import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;

@SuppressWarnings("serial")
public class NormalLevel extends Level {
	public NormalLevel(String levelname, String backgroundfilepath) {
		super(levelname, backgroundfilepath);
		spawnSingleBlock(getList(), new Vector3(), new Vector3(20000, 50, 500));
		spawnPowerUp(gameobjects, PowerUp.SPEED, new Vector3(800, 400, 200));
		spawnPowerUp(gameobjects, PowerUp.LOW_GRAVITY, new Vector3(1600, 400, 200));

		WriteLevel rv = new WriteLevel();
		rv.saveToFile(levelname, this);
	}

}
