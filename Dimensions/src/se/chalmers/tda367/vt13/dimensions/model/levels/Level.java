package se.chalmers.tda367.vt13.dimensions.model.levels;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.Platform;
import se.chalmers.tda367.vt13.dimensions.model.PowerUp;
import se.chalmers.tda367.vt13.dimensions.model.SlowPowerUp;
import se.chalmers.tda367.vt13.dimensions.model.SpeedPowerUp;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;

public class Level {
	private List<Platform> platforms;
	private List<PowerUp> powerUps;
	private double gravity;
	List <GameObject> gameobjects = new ArrayList <GameObject>();
	private float lastx = 0;
	private float lasty = 50;
	
	public Level(String s){
		spawnStartingPlatform(gameobjects);
		stairCase(gameobjects);
		dropDown(gameobjects);
		stairCase(gameobjects);
		spawnSingleBlock(gameobjects,50,0,150,50);
		// Svårt att veta vart man ska spawna powerups, bör göras via position från listan
		spawnPowerUp(gameobjects,1,200,100);
		
		
	}
	
	public List<GameObject> getList(){
		return this.gameobjects;
	}
	/*####################### READ ###########################*/
	/*~~~~~~~INSTRUCTIONS FOR PLACING PLATFORMS ~~~~~~~~~~/
	 * Parameters:
	 * 	first value: offset from the end of the previous platform in X
	 * 		Example: 0 will place it edge to edge of previous platform (in x-led)
	 * 				50 will place it 50 units away from the end of the previous platform
	 * 
	 *  second value: offset from the previous platform in Y
	 *  	Example: 0 will place it at the same height
	 *  			50 will place it 50 units higher than the previous platform
	 *  			-50 will place it 50 units lower than the previous platform
	 *  
	 *  
	 *  For spawnSingleBlock(l,x,y,width,height):
	 *  	x as first value above
	 *  	y as second value above
	 *  	width specifies the length of the platform
	 *  	height specifiec the height of the platform
	 * 
	 * 
	 *  NOTE: 
	 *  	Patterns such as stairCase() and dropDown() has predefined values that should not be changed. 
	 */
	
	
	/** Spawns the platform where the player will start.
	 * @param l
	 */
	public void spawnStartingPlatform(List<GameObject> l ){
		spawnSingleBlock(l,0,10,100,50);
		
	}

	public void stairCase(List <GameObject> l){
		listAddShortPf(l,50,10);
		listAddShortPf(l,50,50);
		listAddShortPf(l,50,50);
		
	}
	
	
	public void dropDown(List<GameObject> l ){
		listAddShortPf(l,50,0);
		listAddLongPf(l,0,-70);
		listAddShortPf(l,0,70);
	}
	
	
	public void listAddShortPf(List<GameObject> l, float x,float y){
		this.lastx = lastx + x;
		this.lasty = lasty + y;
		l.add(new Platform(new Vector3(lastx, lasty, 0), new Vector3(50, 50, 0), new Vector3()));
		this.lastx = lastx + 50;
		
	}
	
	// 1 = Speed, 2 = Slow 3 = ...
	public void spawnPowerUp(List<GameObject>l,int i,float x,float y){
		switch(i){
		case 1:
			l.add(new SpeedPowerUp(new Vector3(x, y, 0), new Vector3(25, 25, 0), new Vector3()));
			
		case 2: 
			// todo
		}
		
	}
	
		public void listAddLongPf(List<GameObject> l, float x,float y){
			this.lastx = lastx + x;
			this.lasty = lasty + y;
			l.add(new Platform(new Vector3(lastx, lasty, 0), new Vector3(130, 50, 0), new Vector3()));
			this.lastx = lastx + 130;
			
		}
		
	
		public void spawnSingleBlock(List<GameObject> l , float x, float y,float length,float height){
			this.lastx= lastx + x;
			this.lasty = lasty+y;
			l.add(new Platform(new Vector3(lastx, lasty, 0), new Vector3(length, height, 0), new Vector3()));
			this.lastx = lastx+ length;
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
