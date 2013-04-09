package se.chalmers.tda367.vt13.dimensions.model;

public class Model {
	private float gravity = -9.82f;
	private float worldSpeed = 10;
	private Player player;
	
	
	public Model(){
		player = new NormalPlayer(this);
	}
	
	public float getGravity(){
		return gravity;
	}
	 
	public void update(float time){
		player.calculateState(time);
	}
	
	public Player getPlayer(){
		return player;
	}
}
