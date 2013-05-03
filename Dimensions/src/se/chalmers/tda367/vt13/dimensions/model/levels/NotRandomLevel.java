package se.chalmers.tda367.vt13.dimensions.model.levels;

import se.chalmers.tda367.vt13.dimensions.model.Obstacle;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.model.powerup.*;

@SuppressWarnings("serial")
public class NotRandomLevel extends Level {

	public NotRandomLevel(String levelname, String filepath) {
		super(levelname, filepath);
		spawnSingleBlock(gameobjects, new Vector3(10, 100, 10), new Vector3(500, 50, 300));
		spawnSingleBlock(gameobjects, new Vector3(300, 10, 10), new Vector3(500, 50, 300));
		spawnSingleBlock(gameobjects, new Vector3(150, 140, 10), new Vector3(500, 50, 300));
		spawnSingleBlock(gameobjects, new Vector3(430, -250, 10), new Vector3(500, 50, 300));
		spawnPowerUp(gameobjects, PowerUp.SPEED, new Vector3(270, 110, 200));
		Obstacle spikes = new Obstacle(new Vector3(1250, 210, 100), new Vector3(100, 30, 50), new Vector3(), "data/spikes.png", "");
		gameobjects.add(spikes);
	}
}
