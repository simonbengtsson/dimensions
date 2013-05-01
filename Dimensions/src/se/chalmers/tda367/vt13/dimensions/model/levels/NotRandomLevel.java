package se.chalmers.tda367.vt13.dimensions.model.levels;

import se.chalmers.tda367.vt13.dimensions.model.Vector3;

@SuppressWarnings("serial")
public class NotRandomLevel extends Level {

	public NotRandomLevel(String levelname, String filepath) {
		super(levelname, filepath);
		spawnSingleBlock(gameobjects, new Vector3(), new Vector3(500, 50, 300));
		spawnSingleBlock(gameobjects, new Vector3(), new Vector3(500, 50, 300));
		spawnSingleBlock(gameobjects, new Vector3(500, 10, 10), new Vector3(500, 50, 300));
	}
}
