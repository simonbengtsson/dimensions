package se.chalmers.tda367.vt13.dimensions.model;

/**
 * A class to describe a state of a checkpoint.
 * @author Kim
 *
 */
public class CheckPoint {
	private Player player;
	private int score;
	
	public CheckPoint(GameWorld g){
		player = g.getPlayer().clone();
	}
	
	public Player getPlayer(){
		return player.clone();
	}
	
	public int getScore(){
		return score;
	}
}
