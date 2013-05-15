package se.chalmers.tda367.vt13.dimensions.model.levels;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.progresshandler.ProgressLevel;

public class LevelHandler {
	private HashSet<Level> levels;
	private Deque<ProgressLevel> progressLevels;
	private static LevelHandler instance;

	private LevelHandler() {
		levels = new HashSet<Level>();
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
	 * Will restore LevelHandler to the same state as when it shutdown.
	 */
	public void populateFromFile() {
		// TODO implement
	}

	/**
	 * Adds a level to the handler.
	 * 
	 * @param l
	 */
	public void registerLevel(Level l) {
		levels.add(l);
		progressLevels.addLast(new ProgressLevel(l));
	}

	/**
	 * Returns the first level named to the string.
	 * 
	 * @param s
	 * @return
	 */
	public Level getLevel(String s) {
		Level level = null;
		Iterator<Level> iter = levels.iterator();
		while (iter.hasNext()) {
			Level l = iter.next();
			if (l.getName().equals(s)) {
				level = l;
				break;
			}
		}
		return level;
	}

	/**
	 * Returns a list of Strings, representing all levels.
	 * 
	 * @return
	 */
	public List<String> getLevelsAsStrings() {
		List<String> list = new ArrayList<String>();
		Iterator<Level> iter = levels.iterator();
		while (iter.hasNext()) {
			String s = iter.next().getName();
			list.add(s);
		}
		return list;
	}

	/**
	 * Returns a list of all Levels.
	 * 
	 * @return
	 */
	public List<Level> getLevels() {
		List<Level> list = new ArrayList<Level>();
		Iterator<Level> iter = levels.iterator();
		while (iter.hasNext()) {
			Level l = iter.next();
			list.add(l);
		}
		return list;
	}

	/**
	 * Returns the next unfinished Level.
	 * 
	 * @return
	 */
	public Level getNextUnfinishedLevel() {
		Level returning = null;
		Iterator<ProgressLevel> iter = progressLevels.iterator();
		while (iter.hasNext()) {
			ProgressLevel progress = iter.next();
			if (!progress.isCompleted()) {
				returning = progress.getLevel();
				break;
			}
		}
		return returning;
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
				p = returning;
				return p;
			}
		}
		return returning;
	}
}
