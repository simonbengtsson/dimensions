package se.chalmers.tda367.vt13.dimensions.model.levels;

import java.util.List;
import java.util.Random;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;

@SuppressWarnings("serial")
public class RandomLevel extends Level {

	public RandomLevel(String levelname, String pathname) {
		super(levelname, pathname);
		for (int i = 0; i < 5; i++) {
			randomGenerateLevel(gameobjects, 100, 50);
		}

		for (int i = 0; i < 5; i++) {
			randomGenerateLevel(gameobjects, 300, 50);
		}
		
		for (int i = 0; i < 5; i++) {
			randomGenerateLevel(gameobjects, 500, 50);
		}
		
		for (int i = 0; i < 5; i++) {
			randomGenerateLevel(gameobjects, 700, 50);
		}
		
		for (int i = 0; i < 5; i++) {
			randomGenerateLevel(gameobjects, 900, 50);
		}
		spawnPowerUp(gameobjects, 2, 700, 200);
		spawnPowerUp(gameobjects, 1, 500, 300);

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
