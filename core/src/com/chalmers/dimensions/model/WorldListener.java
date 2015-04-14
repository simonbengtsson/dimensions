package com.chalmers.dimensions.model;

import com.chalmers.dimensions.model.GameWorld.State;

/**
 * Listening to game over events only atm
 */
public interface WorldListener {
	public void worldChanged(State newWorldState);
}
