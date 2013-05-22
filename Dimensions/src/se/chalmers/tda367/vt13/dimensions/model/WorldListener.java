package se.chalmers.tda367.vt13.dimensions.model;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld.State;

/**
 * Listening to game over events only atm
 */
public interface WorldListener {
	public void worldChanged(State newWorldState);
}
