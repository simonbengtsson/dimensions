package se.chalmers.tda367.vt13.dimensions.model;

import java.util.*;

import se.chalmers.tda367.vt13.dimensions.model.levels.Level;

public class Model {
	private double gravity = -9.82;
	private List<Platform> platforms;
	private boolean usingPowerUp = false;
	
	public Model(Level level, Player p){
		platforms = level.getPlatforms();
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
	 

	
	public void setPlayerSpeed(double speed, int time){
		new Timer().schedule(new TimerTask() {          
		    @Override
		    public void run() {
		    	
		    }
		}, time);
	}
	
	//
	public void addTempPlatform(Platform p){
		platforms.add(p);
	}
	
	public void removeTempPlatform(Platform p){
		platforms.remove(p);
	}
}
