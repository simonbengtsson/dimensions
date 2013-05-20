package se.chalmers.tda367.vt13.dimensions.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.powerup.DimensionChangePowerUp;
import se.chalmers.tda367.vt13.dimensions.model.powerup.LowGravityPowerUp;
import se.chalmers.tda367.vt13.dimensions.model.progresshandler.ProgressLevel;

public class LevelHandler {
	private Deque<ProgressLevel> progressLevels;
	private static LevelHandler instance;

	private LevelHandler() {
		progressLevels = new ArrayDeque<ProgressLevel>();
	}

	/**
	 * Implements singleton pattern
	 * 
	 * @return
	 */
	public static synchronized LevelHandler getInstance() {
		if (instance == null) {
			instance = new LevelHandler();
		}
		return instance;
	}

	/**
	 * Adds a level to the handler.
	 * 
	 * @param l
	 */
	public void registerLevel(Level l) {
		progressLevels.addLast(new ProgressLevel(l));
	}
	
	public Collection<ProgressLevel> getProgressLevels(){
		return new ArrayDeque<ProgressLevel>(progressLevels);
	}

	/**
	 * Returns the next unfinished Level.
	 * 
	 * @return The next unfinished level. <code>null</code> if all levels are
	 *         finished.
	 */
	public Level getNextUnfinishedLevel() {
		Level nextLevel = null;
		Iterator<ProgressLevel> iter = progressLevels.iterator();
		while (iter.hasNext()) {
			ProgressLevel progress = iter.next();
			if (!progress.isCompleted()) {
				nextLevel = progress.getLevel();
				return nextLevel;
			}
		}
		return null;
	}

	/**
	 * Returns a list with all finished ProgressLevels, containing score and the
	 * Level itself.
	 * 
	 * @return
	 */
	public List<ProgressLevel> getAllFinishedLevels() {
		List<ProgressLevel> list = new ArrayList<ProgressLevel>();
		Iterator<ProgressLevel> iter = progressLevels.iterator();
		while (iter.hasNext()) {
			ProgressLevel pl = iter.next();
			if (pl.isCompleted()) {
				list.add(pl);
			}
		}
		return list;
	}

	public ProgressLevel getProgressFor(Level l) {
		ProgressLevel returning = null;
		Iterator<ProgressLevel> iter = progressLevels.iterator();
		while (iter.hasNext()) {
			ProgressLevel p = iter.next();
			if (p.getLevel() == l) {
				returning = p;
				return p;
			}
		}
		return returning;
	}

	public ProgressLevel getProgressLevel(int i) {
		return (ProgressLevel) progressLevels.toArray()[i];
	}

	public void gameFinished(Level l, int score, boolean completedLevel) {
		ProgressLevel pl = getProgressFor(l);
		pl.gameFinished(score, completedLevel);
	}

	/*
	 * If you want to a level to be dynamically created at start up, and not
	 * read or written to file, create and register it in the LevelHandler here.
	 * Use an instance of LevelCreator to create formations in your level.
	 */
	public void load() {
		// Level named Example
		List<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects.add(new DimensionChangePowerUp(new Vector3(30, 4, 10), new Vector3(
				1, 1, 1), new Vector3()));
		List<GameObject> exampleList = new ArrayList<GameObject>();
		exampleList.add(new LowGravityPowerUp(new Vector3(15, 4, 10),
				new Vector3(1, 1, 1), new Vector3()));
		exampleList.add(new DimensionChangePowerUp(new Vector3(25, 4, 10),
				new Vector3(1, 1, 1), new Vector3()));
		Level example = new Level("Example", -0.05f, exampleList, Dimension.XY, 
				"data/tiledMaps/levelXY.tmx", "data/tiledMaps/levelXZ.tmx", 205);
		registerLevel(example);
		// End of Example Level

		// Level named DimensionChange
		List<GameObject> dimensionChangeList = new ArrayList<GameObject>();
		dimensionChangeList.add(new DimensionChangePowerUp(new Vector3(30, 4,
				10), new Vector3(1, 1, 1), new Vector3()));
		dimensionChangeList.add(new DimensionChangePowerUp(new Vector3(50, 4,
				10), new Vector3(1, 1, 1), new Vector3()));
		Level dimensionChange = new Level("Dimension Change", -0.03f,
				dimensionChangeList, Dimension.XZ,
				"data/tiledMaps/levelXY.tmx", "data/tiledMaps/levelXZ.tmx", 150);
		registerLevel(dimensionChange);
	}
}
