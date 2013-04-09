package se.chalmers.tda367.vt13.dimensions.model;

import java.util.*;

public class Model {
	private float gravity = -9.82f;
	private float worldSpeed = 10;
	private Player player;
	private List<Platform> platforms;
	
	
	public Model(){
		player = new NormalPlayer(this);
		platforms = new ArrayList<Platform>();
	}
	
	public float getGravity(){
		return gravity;
	}
	
	public void setGravity(float gravity){
		this.gravity = gravity;
	}
	 
	public void update(float time){
		player.calculateState(time);
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public float getWorldSpeed(){
		return worldSpeed;
	}
	
	public void setWorldSpeed(float speed){
		worldSpeed = speed;
	}
	
	public void addPlatform(Platform p){
		platforms.add(p);
	}
	
	public void removePlatform(Platform p){
		platforms.remove(p);
	}
}
