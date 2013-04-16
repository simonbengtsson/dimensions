package se.chalmers.tda367.vt13.dimensions.model;

import java.util.*;

import se.chalmers.tda367.vt13.dimensions.model.levels.Level;

public class Model {
	private double gravity = -9.82;
	private Player player;
	private List<Platform> platforms;
	private List<PowerUp> powerUps;
	private boolean usingPowerUp = false;
	
	public Model(Level level, Player p){
		player = p;
		platforms = level.getPlatforms();
		powerUps = level.getPowerUps();
	}
	
	public double getGravity(){
		return gravity;
	}
	
	public boolean isUsingPowerUp(){
		return usingPowerUp;
	}
	
	public void setGravity(float gravity){
		this.gravity = gravity;
	}
	 
	public void update(float time){
		//player.calculateState(time);
	}
	
	public void setPlayerSpeed(double speed, int time){
		setPlayerSpeed(speed);
		new Timer().schedule(new TimerTask() {          
		    @Override
		    public void run() {
		    	resetPlayerSpeed();
		    }
		}, time);
	}
	
	public void resetPlayerSpeed(){
		player.setVelocityX(player.getStandardVelocityX());
	}
	
	//
	public void addTempPlatform(Platform p){
		platforms.add(p);
	}
	
	public void removeTempPlatform(Platform p){
		platforms.remove(p);
	}
}
