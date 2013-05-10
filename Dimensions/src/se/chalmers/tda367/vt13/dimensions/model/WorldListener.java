package se.chalmers.tda367.vt13.dimensions.model;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld.WorldEvent;

/**
 * Listening to game over events only atm
 */
public interface WorldListener {
	public void worldChange(WorldEvent worldEvent, Object value);
}
