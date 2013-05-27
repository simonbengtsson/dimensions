package se.chalmers.tda367.vt13.dimensions.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import se.chalmers.tda367.vt13.dimensions.model.powerup.DimensionChangePowerUp;
import se.chalmers.tda367.vt13.dimensions.model.powerup.LowGravityPowerUp;
import se.chalmers.tda367.vt13.dimensions.model.powerup.SpeedPowerUp;

public class LevelHandler {
	private Deque<ProgressLevel> progressLevels;
	private Map<String, Level> levels;
	private static LevelHandler instance;
	private String lastPlayed;

	private LevelHandler() {
		progressLevels = new ArrayDeque<ProgressLevel>();
		levels = new HashMap<String, Level>();
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
		levels.put(l.getName(), l);
		if (getProgressFor(l) == null) {
			progressLevels.addLast(new ProgressLevel(l.getName()));
		}

	}

	/**
	 * Run after adding levels in case of a progress have been saved for a level not
	 * in use.
	 */
	private void cleanUpProgressLevels() {
		Iterator<ProgressLevel> iter = progressLevels.iterator();
		while (iter.hasNext()) {
			ProgressLevel p = iter.next();
			if (levels.get(p.getLevel()) == null) {
				iter.remove();
				System.out.println("Deleted '" + p.getLevel() + "' from list.");
			}
		}
	}

	public Collection<ProgressLevel> getProgressLevels() {
		return new ArrayDeque<ProgressLevel>(progressLevels);
	}

	/**
	 * Returns the next unfinished Level.
	 * 
	 * @return The next unfinished level. <code>null</code> if all levels are
	 *         finished.
	 */
	public Level getNextUnfinishedLevel() {
		Iterator<ProgressLevel> iter = progressLevels.iterator();
		while (iter.hasNext()) {
			ProgressLevel progress = iter.next();
			if (!progress.isCompleted()) {
				Level l = levels.get(progress.getLevel());
				return l;
			}
		}
		return null;
	}

	public Level getLastPlayed() {
		return levels.get(lastPlayed).clone();
	}

	public void setLastPlayed(String s) {
		lastPlayed = s;
	}

	public boolean loadProgressFromFile(Collection<ProgressLevel> p) {
		if (p != null && !p.isEmpty()) {
			progressLevels = new ArrayDeque<ProgressLevel>(p);
			System.out.println("Progress file found for "
					+ progressLevels.size() + " levels.");
			return true;
		}
		return false;
	}

	public ProgressLevel getProgressFor(Level l) {
		ProgressLevel returning = null;
		Iterator<ProgressLevel> iter = progressLevels.iterator();
		while (iter.hasNext()) {
			ProgressLevel p = iter.next();
			if (p.getLevel().equals(l.getName())) {
				returning = p;
				return p;
			}
		}
		return returning;
	}

	public Level getLevel(String s) {
		return levels.get(s).clone();
	}

	public ProgressLevel getProgressLevel(int i) {
		return (ProgressLevel) progressLevels.toArray()[i];
	}

	public Level getLevel(int i) {
		return levels.get(getProgressLevel(i).getLevel()).clone();
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
		// Old level
		List<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects.add(new DimensionChangePowerUp(new Vector3(20, 4, 10),
				new Vector3(1, 1, 1), new Vector3()));
		List<GameObject> exampleList = new ArrayList<GameObject>();
		exampleList.add(new DimensionChangePowerUp(new Vector3(25, 8, 10),
				new Vector3(1, 1, 1), new Vector3()));
		Level example = new Level("Old", exampleList, "tiled_maps/levelXY.tmx",
				"tiled_maps/levelXZ.tmx", 205);
		registerLevel(example);
		// End of Example Level

		// Nature Level
		List<GameObject> natureList = new ArrayList<GameObject>();
		natureList.add(new DimensionChangePowerUp(new Vector3(85, 15, 10),
				new Vector3(1, 1, 1), new Vector3()));
		Level natureLevel = new Level("Nature", natureList,
				"tiled_maps/natureXY.tmx", "tiled_maps/natureXZ.tmx", 205);
		registerLevel(natureLevel);

		// Demo Level
		List<GameObject> demoList = new ArrayList<GameObject>();
		demoList.add(new DimensionChangePowerUp(new Vector3(60, 15, 10),
				new Vector3(1, 1, 1), new Vector3()));
		demoList.add(new DimensionChangePowerUp(new Vector3(110, 15, 10),
				new Vector3(1, 1, 1), new Vector3()));
		demoList.add(new DimensionChangePowerUp(new Vector3(220, 15, 10),
				new Vector3(1, 1, 1), new Vector3()));

		demoList.add(new LowGravityPowerUp(new Vector3(166, 15, 10),
				new Vector3(1, 1, 1), new Vector3()));
		demoList.add(new LowGravityPowerUp(new Vector3(293, 15, 10),
				new Vector3(1, 1, 1), new Vector3()));
		Level demoLevel = new Level("Demo", demoList, "tiled_maps/demoXY.tmx",
				"tiled_maps/demoXZ.tmx", 308);
		registerLevel(demoLevel);


		// Remove faulty progressfiles. Run last.
		cleanUpProgressLevels();
	}
}
