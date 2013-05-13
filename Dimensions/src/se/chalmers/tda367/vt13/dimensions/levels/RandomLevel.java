package se.chalmers.tda367.vt13.dimensions.levels;

import java.util.List;
import java.util.Random;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;
import se.chalmers.tda367.vt13.dimensions.util.WriteLevel;

@SuppressWarnings("serial")
public class RandomLevel extends Level {

	public RandomLevel(String levelname, String pathname) {
		super(levelname, pathname);
		for (int i = 0; i < 5; i++) {
			randomGenerateLevel(gameObjects, 100, 50);
		}

		for (int i = 0; i < 5; i++) {
			randomGenerateLevel(gameObjects, 300, 50);
		}
		
		for (int i = 0; i < 5; i++) {
			randomGenerateLevel(gameObjects, 500, 50);
		}
		
		for (int i = 0; i < 5; i++) {
			randomGenerateLevel(gameObjects, 700, 50);
		}
		
		for (int i = 0; i < 5; i++) {
			randomGenerateLevel(gameObjects, 900, 50);
		}
		spawnPowerUp(gameObjects, PowerUp.LOW_GRAVITY, new Vector3(700, 200, 200));
		spawnPowerUp(gameObjects, PowerUp.SPEED, new Vector3(500, 300, 200));

		WriteLevel rv = new WriteLevel();
		rv.saveToFile(levelname, this);
	}

	public void randomGenerateLevel(List<GameObject> l, int high, int low) {
		Random rd = new Random();
		int i = rd.nextInt(4);

		int xval = rd.nextInt(high - low) + low;
		int yval = rd.nextInt(80 - low) + low;
		switch (i) {
		case 0:
			stairCase(l);
			break;
		case 1:
			dropDown(l);
			break;
		case 2:
			spawnSingleBlock(l, xval, yval, 120, 50);
			break;
		case 3:
			longPlatform(l);
			break;
		}
	}

}
