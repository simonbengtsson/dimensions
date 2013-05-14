package se.chalmers.tda367.vt13.dimensions.util;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.levels.Level;
import se.chalmers.tda367.vt13.dimensions.model.levels.LevelHandler;

public class DynamicallyCreatedLevels {

	/*
	 * If you want to a level to be dynamically created at start up, and not
	 * read or written to file, create and register it in the LevelHandler here.
	 * Use an instance of LevelCreator to create formations in your level.
	 */
	public static void load() {
		// Example Level
		List<GameObject> gameObjects = new ArrayList<GameObject>();
		LevelCreator lc = new LevelCreator(gameObjects);
		lc.dropDown();
		lc.longPlatform();
		Level level = new Level("Example", 0.05f, lc.creationCompleted(),
				Dimension.XY);
		LevelHandler.getInstance().registerLevel(level);

	}
}
