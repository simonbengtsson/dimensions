package com.chalmers.dimensions.model;

/**
 * A class to describe a state of a checkpoint.
 * 
 * @author Kim
 * 
 */
public class CheckPoint {
	private Player player;

	public CheckPoint(GameWorld g) {
		player = g.getPlayer().clone();
	}

	public Player getPlayer() {
		return player.clone();
	}

}
