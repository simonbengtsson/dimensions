package se.chalmers.tda367.vt13.dimensions.levels;

import java.util.HashMap;

public class LevelHandler {
	private HashMap<String, Level> levels;
	private static LevelHandler instance;

	private LevelHandler() {}

	/**
	 * Implements singleton pattern
	 * @return
	 */
	public static synchronized LevelHandler getInstance() {
		if (instance == null) {
			instance = new LevelHandler();
		}
		return instance;
	}

	/**
	 * Will restore LevelHandler to the same state as when itwas shutdown.
	 */
	public void populateFromFile() {
		// TODO implement
	}

	/**
	 * Adds a level to the handler.
	 * @param l
	 */
	public void registerLevel(Level l) {
		levels.put("", l); // TODO Make sure levels have a name to catch
	}
	
	/**
	 * Returns the level associated with the string.
	 * @param s
	 * @return
	 */
	public Level getLevel(String s){
		return levels.get(s);
	}
}
