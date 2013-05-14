package se.chalmers.tda367.vt13.dimensions.model.levels;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;
import se.chalmers.tda367.vt13.dimensions.util.Constants;
import se.chalmers.tda367.vt13.dimensions.util.LevelCreator;
import se.chalmers.tda367.vt13.dimensions.util.WriteLevel;

@SuppressWarnings("serial")
public class NormalLevel extends Level{
	public NormalLevel() {
		super("NormalLevel");
		LevelCreator lc = new LevelCreator(new ArrayList<GameObject>());

		lc.spawnSingleBlock(new Vector3(), new Vector3(20000, 50, 500));
		lc.spawnPowerUp(PowerUp.SPEED, new Vector3(700, 200, 200));
		lc.spawnPowerUp(PowerUp.LOW_GRAVITY, new Vector3(1600, 400, 200));
		// DIMENSION CHANGE TESTING WITH OBSTACLES
		
		List<GameObject> done = lc.creationCompleted();
		//setGameObjects(done);
	}

}
