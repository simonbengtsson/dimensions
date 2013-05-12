package se.chalmers.tda367.vt13.dimensions.model.levels;

import se.chalmers.tda367.vt13.dimensions.model.Obstacle;
import se.chalmers.tda367.vt13.dimensions.model.Platform;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.model.powerup.DimensionChangePowerUp;
import se.chalmers.tda367.vt13.dimensions.model.powerup.SpeedPowerUp;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;

@SuppressWarnings("serial")
public class TiledLevel extends Level {

	public TiledLevel(String levelName, String filepath) {
		super(levelName, filepath);
		mapXY = new TmxMapLoader().load("data/tiledMaps/levelXY.tmx");
		mapXZ = new TmxMapLoader().load("data/tiledMaps/levelXZ.tmx");
		addGameObjects();
	}

	private void addGameObjects() {
		//Spawn a few original gameobjects. Everything else is in the tiled maps.
		//PowerUps
		gameobjects.add(new SpeedPowerUp(new Vector3(50, 16, 16), new Vector3(1, 1, 5), new Vector3()));
		gameobjects.add(new DimensionChangePowerUp(new Vector3(30, 5, 5), new Vector3(1, 1, 1), new Vector3()));
		//Obstacles
		for (int i = 0; i < 18; i++) {
			gameobjects.add(new Obstacle(new Vector3(35 + i*1.8f, 2, 0), new Vector3(2,
					1, 1), new Vector3(), "data/spikes.png", ""));
		}
		// Platform
		gameobjects.add(new Platform(new Vector3(40, 10, 10), new Vector3(30,
				2, 10), new Vector3()));
	}
}
