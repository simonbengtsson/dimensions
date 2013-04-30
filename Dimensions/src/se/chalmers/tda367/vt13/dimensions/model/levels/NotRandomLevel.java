package se.chalmers.tda367.vt13.dimensions.model.levels;

@SuppressWarnings("serial")
public class NotRandomLevel extends Level {

	public NotRandomLevel(String levelname, String filepath) {
		super(levelname, filepath);
		for (int i = 0; i < 5; i++) {
			spawnSingleBlock(gameobjects, 150, 130, 400, 50);
		}
		spawnSingleBlock(gameobjects, 400, -600, 400, 50);
		spawnSingleBlock(gameobjects, 200, 100, 800, 50);
	}
}
