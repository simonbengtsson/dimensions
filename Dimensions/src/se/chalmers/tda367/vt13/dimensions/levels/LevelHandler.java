package se.chalmers.tda367.vt13.dimensions.levels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
	
	/**
	 * Returns a list of Strings, representing all levels.
	 * @return
	 */
	public List<String> getListOfLevelsAsStrings(){
		List<String> list = new ArrayList<String>();
		Iterator<String> iter = levels.keySet().iterator();
		while(iter.hasNext()){
			String s = iter.next();
			list.add(s);
		}
		return list;
	}
	
	/**
	 * Returns a list of all Levels.
	 * @return
	 */
	public List<Level> getListOfLevels(){
		List<Level> list = new ArrayList<Level>();
		Iterator<Level> iter = levels.values().iterator();
		while(iter.hasNext()){
			Level l = iter.next();
			list.add(l);
		}
		return list;
	}
}
