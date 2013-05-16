package se.chalmers.tda367.vt13.dimensions.model.levels;

import se.chalmers.tda367.vt13.dimensions.model.Platform;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.model.powerup.DimensionChangePowerUp;
import se.chalmers.tda367.vt13.dimensions.model.powerup.SpeedPowerUp;

@SuppressWarnings("serial")
public class TiledLevel extends Level {

	public TiledLevel() {
		this("Tiled", null);
	}
	
	public TiledLevel(String levelName, String filepath) {
		super(levelName);
		mapXYPath = "data/tiledMaps/levelXY.tmx";
		mapXZPath = "data/tiledMaps/levelXZ.tmx";
		levelFinishedPosition = 205;
		addGameObjects();
	}

	private void addGameObjects() {
		gameObjects.add(new SpeedPowerUp(new Vector3(50, 16, 16), new Vector3(1, 1, 1), new Vector3()));
		gameObjects.add(new DimensionChangePowerUp(new Vector3(30, 5, 5), new Vector3(1, 1, 1), new Vector3()));
		gameObjects.add(new DimensionChangePowerUp(new Vector3(120, 5, 5), new Vector3(1, 1, 1), new Vector3()));
		gameObjects.add(new DimensionChangePowerUp(new Vector3(120, 5, 5), new Vector3(1, 1, 1), new Vector3()));
		gameObjects.add(new Platform(new Vector3(46, 5, 5), new Vector3(2, 2, 2), new Vector3()));
		gameObjects.add(new Platform(new Vector3(48, 5, 5), new Vector3(2, 2, 2), new Vector3()));
		gameObjects.add(new Platform(new Vector3(50, 5, 5), new Vector3(2, 2, 2), new Vector3()));
		gameObjects.add(new Platform(new Vector3(52, 5, 5), new Vector3(2, 2, 2), new Vector3()));
		gameObjects.add(new Platform(new Vector3(54, 5, 5), new Vector3(2, 2, 2), new Vector3()));
	}
}
