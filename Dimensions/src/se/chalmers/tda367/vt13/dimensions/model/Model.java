package se.chalmers.tda367.vt13.dimensions.model;

import java.util.*;

import se.chalmers.tda367.vt13.dimensions.model.levels.Level;

public class Model {
	private float gravity = -9.82f;
	private float worldSpeed = 10;
	private Player player;
	private List<Platform> platforms;
	private Level level;
	
	public Model(Level level){
		this.level = level;
		player = new NormalPlayer(this);
		platforms = level.getListOfPlatforms();
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
	
	public void playerJump(){
		player.jump();
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
	
	public void setWorldSpeed(float speed, int time){
		setWorldSpeed(speed);
		new Timer().schedule(new TimerTask() {          
		    @Override
		    public void run() {
		    	resetWorldSpeed();
		    }
		}, time);
	}
	
	public void resetWorldSpeed(){
		worldSpeed = level.getStandardWorldSpeed();
	}
	
	public void addTempPlatform(Platform p){
		platforms.add(p);
	}
	
	public void removeTempPlatform(Platform p){
		platforms.remove(p);
	}
}
