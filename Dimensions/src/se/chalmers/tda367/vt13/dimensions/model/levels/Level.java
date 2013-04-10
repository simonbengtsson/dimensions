package se.chalmers.tda367.vt13.dimensions.model.levels;

import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.Platform;

public class Level {
	private List<Platform> platforms;
	private double gravity;
	
	public Level(){
		
	}
	public List<Platform> getPlatforms(){
		return platforms;
	}
	public void addPlatform(Platform p){
		platforms.add(p);
	}
	public void removePlatform(Platform p){
		platforms.remove(p);
	}
	
	public double getGravity(){
		return gravity;
	}
}
