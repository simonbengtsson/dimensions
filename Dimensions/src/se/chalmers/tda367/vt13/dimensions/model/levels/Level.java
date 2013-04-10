package se.chalmers.tda367.vt13.dimensions.model.levels;

import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.Platform;
import se.chalmers.tda367.vt13.dimensions.model.PowerUp;

public class Level {
	private List<Platform> platforms;
	private List<PowerUp> powerUps;
	private double gravity;
	
	public Level(){
		
	}
	
	public List<PowerUp> getPowerUps(){
		return powerUps;
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
