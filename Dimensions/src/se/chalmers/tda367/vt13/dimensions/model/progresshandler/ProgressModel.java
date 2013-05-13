package se.chalmers.tda367.vt13.dimensions.model.progresshandler;

import java.util.HashSet;
import java.util.Iterator;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.WorldEvent;
import se.chalmers.tda367.vt13.dimensions.model.WorldListener;
import se.chalmers.tda367.vt13.dimensions.levels.Level;

/**
 * This class handles the progress for a device. Later implementations may
 * handle profiles.
 * 
 * @author Kim
 * 
 */
public class ProgressModel implements WorldListener {
	private static ProgressModel instance;
	private HashSet<ProgressLevel> allLevels;
	private HashSet<ProgressLevel> completedLevels;
	private HashSet<ProgressLevel> notCompletedLevels;

	private ProgressModel() {

	}

	public static synchronized ProgressModel getInstance() {
		if (instance == null) {
			instance = new ProgressModel();
		}
		return instance;
	}

	@Override
	public void worldChange(WorldEvent worldEvent, GameWorld w) {
		if (worldEvent == WorldEvent.GAME_OVER) {
			// new ProgressLevel(w.getCurrentLevel(), w.getScore());
		}

	}

	/**
	 * Searches for all levels and returns the first who mathes the string
	 * provided.
	 * 
	 * @param s
	 * @return
	 */
	public ProgressLevel getWhereLevelIs(String s) {
		ProgressLevel returning = null;
		Iterator<ProgressLevel> iter = allLevels.iterator();
		while (iter.hasNext()) {
			ProgressLevel p = iter.next();
			if (p.getName().equals(s)) {
				p = returning;
				return p;
			}
		}
		return returning;
	}

	/**
	 * Searches for all levels and return the first that matches the containing
	 * level.
	 * 
	 * @param l
	 * @return
	 */
	public ProgressLevel getWhereLevelIs(Level l) {
		ProgressLevel returning = null;
		Iterator<ProgressLevel> iter = allLevels.iterator();
		while (iter.hasNext()) {
			ProgressLevel p = iter.next();
			if (p.getLevel() == l) {
				p = returning;
				return p;
			}
		}
		return returning;
	}

	/**
	 * Updates the score for a level.
	 * @param l
	 * @param score
	 */
	public void updateScore(Level l, int score) {
		getWhereLevelIs(l).evaluateNewScore(score);
	}

	public void updateScore(ProgressLevel p, int score) {

	}
}
