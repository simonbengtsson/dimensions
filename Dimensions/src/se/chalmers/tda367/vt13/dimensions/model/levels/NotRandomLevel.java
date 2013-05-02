package se.chalmers.tda367.vt13.dimensions.model.levels;

import se.chalmers.tda367.vt13.dimensions.model.Obstacle;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;

@SuppressWarnings("serial")
public class NotRandomLevel extends Level {

	public NotRandomLevel(String levelname, String filepath) {
		super(levelname, filepath);
		spawnSingleBlock(gameobjects, new Vector3(), new Vector3(500, 50, 300));
		spawnSingleBlock(gameobjects, new Vector3(), new Vector3(500, 50, 300));
		spawnSingleBlock(gameobjects, new Vector3(500, 10, 10), new Vector3(500, 50, 300));
		Obstacle spikes = new Obstacle(new Vector3(1000, 110, 100), new Vector3(100, 30, 50), new Vector3(), "data/spikes.png", "");
		gameobjects.add(spikes);
	}
}
