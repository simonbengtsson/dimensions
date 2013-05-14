package se.chalmers.tda367.vt13.dimensions.levels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;
import se.chalmers.tda367.vt13.dimensions.util.LevelCreator;
import se.chalmers.tda367.vt13.dimensions.util.WriteLevel;

@SuppressWarnings("serial")
public class RandomLevel extends Level {

	public RandomLevel() {
		super("RandomLevel", new ArrayList<GameObject>(), 0.05f, Dimension.XY);
		LevelCreator lc = new LevelCreator(gameObjects);
		
		for (int i = 0; i < 5; i++) {
			lc.randomGenerateLevel(100, 50);
		}

		for (int i = 0; i < 5; i++) {
			lc.randomGenerateLevel(300, 50);
		}
		
		for (int i = 0; i < 5; i++) {
			lc.randomGenerateLevel(500, 50);
		}
		
		for (int i = 0; i < 5; i++) {
			lc.randomGenerateLevel(700, 50);
		}
		
		for (int i = 0; i < 5; i++) {
			lc.randomGenerateLevel(900, 50);
		}

		lc.spawnPowerUp(PowerUp.LOW_GRAVITY, new Vector3(700, 200, 200));
		lc.spawnPowerUp(PowerUp.SPEED, new Vector3(500, 300, 200));
		
		List<GameObject> g = lc.creationCompleted();
		setGameObjects(g);
	}

	

}
