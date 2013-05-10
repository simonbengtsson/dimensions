package se.chalmers.tda367.vt13.dimensions.model.levels;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.Obstacle;
import se.chalmers.tda367.vt13.dimensions.model.Platform;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

@SuppressWarnings("serial")
public class TiledLevel extends Level {
	
	public TiledLevel(String levelName, String filepath, Dimension dimension) {
		super(levelName, filepath);
		if(dimension == Dimension.XY){
			map = new TmxMapLoader().load("data/tiledMaps/levelXY.tmx");
		} else if (dimension == Dimension.XZ) {
			map = new TmxMapLoader().load("data/tiledMaps/levelXZ.tmx");
		}
		spawnPowerUp(gameobjects, PowerUp.SPEED, new Vector3(30, 10, 0));
		spawnPowerUp(gameobjects, PowerUp.LOW_GRAVITY, new Vector3(90, 5, 0));
		Obstacle spikes = new Obstacle(new Vector3(50, 2, 0), new Vector3(2, 1, 1), 
				new Vector3(), "data/spikes.png", "");
		gameobjects.add(spikes);
		Platform testPlatform = new Platform(new Vector3(40, 10, 10), new Vector3(30, 2, 10), new Vector3());
		gameobjects.add(testPlatform);
		spawnSingleBlock(gameobjects, new Vector3(), new Vector3(10, 50, 500));
	}
}
