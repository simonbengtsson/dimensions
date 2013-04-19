package se.chalmers.tda367.vt13.dimensions.model.levels;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.*;
import java.io.Serializable;

/** 
 * Class for creating a level with platforms and powerups
 */
public class Level implements Serializable {
	
	// Instance variables
	private List<Platform> platforms;
	private List<PowerUp> powerUps;
	private double gravity;
	List <GameObject> gameobjects = new ArrayList <GameObject>();
	private float lastx = 0;
	private float lasty = 50;
	private String levelname;
	
	/** Constructor for creating Levels
	 * 
	 * @param s Level name
	 */
	public Level(String s){
		this.levelname = s;
		
		// This constructor is currently a mess
		// TODO: Clean up this mess
		
		spawnStartingPlatform(gameobjects);
		stairCase(gameobjects);
		//dropDown(gameobjects);
		//stairCase(gameobjects);
		for(int i = 0; i<20; i++){
			spawnSingleBlock(gameobjects,100,0,150,50);
		}
		// SvŒrt att veta vart man ska spawna powerups, bšr gšras via position frŒn listan
		//spawnPowerUp(gameobjects,1,0,50);
		//spawnPowerUp(gameobjects,1,50,100);
		spawnPowerUp(gameobjects,1,100,120);
		spawnPowerUp(gameobjects,1,400,300);
		
		// Save level to file
		WriteLevel rv = new WriteLevel();
		rv.saveToFile(this.levelname, this);
		
		
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
	 *  	height specifies the height of the platform
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
	
	/** Adds a staircase pattern of platforms to the list
	 * Pattern:	      -----
	 * 			 -----
	 *      -----
	 * @param l
	 */
	public void stairCase(List <GameObject> l){
		listAddShortPf(l,50,10);
		listAddShortPf(l,50,50);
		listAddShortPf(l,50,50);
		
	}
	
	/** Adds a dropDown pattern of platforms to the list 
	 * Pattern: ----_______-----
	 * 
	 * @param l
	 */
	public void dropDown(List<GameObject> l ){
		listAddShortPf(l,50,0);
		listAddLongPf(l,0,-70);
		listAddShortPf(l,0,70);
	}
	
	/** Adds a 'short' platform to the list with predefined values
	 * 
	 * @param l List
	 * @param x Offset x from previous platform
	 * @param y Offset y from previous platform
	 */
	public void listAddShortPf(List<GameObject> l, float x,float y){
		this.lastx = lastx + x;
		this.lasty = lasty + y;
		l.add(new Platform(new Vector3(lastx, lasty, 0), new Vector3(50, 50, 0), new Vector3()));
		this.lastx = lastx + 50;
		
	}
	
	/** Method for adding powerups to the List representing the level
	 * i = 1: Speed
	 * i = 2: Slow
	 * i = 3: ...
	 * @param l List
	 * @param i int value representing each powerup
	 * @param x x-coordinate of spawn
	 * @param y y-coordinate of spawn
	 */
	
	public void spawnPowerUp(List<GameObject>l,int i,float x,float y){
		switch(i){
		case 1:
			l.add(new SpeedPowerUp(new Vector3(x, y, 0), new Vector3(25, 25, 0), new Vector3()));
			
		case 2: 
			// todo
		}
		
	}
	/** Adds a 'long' platform to the list with predefined values
	 * 
	 * @param l List
	 * @param x x-coordinate offset from previous platform
	 * @param y y-coordinate offset from previous platform
	 */
		public void listAddLongPf(List<GameObject> l, float x,float y){
			this.lastx = lastx + x;
			this.lasty = lasty + y;
			l.add(new Platform(new Vector3(lastx, lasty, 0), new Vector3(130, 50, 0), new Vector3()));
			this.lastx = lastx + 130;
			
		}
		
	/** Adds a single block in a user(coder) defined position
	 * 
	 * @param l List
	 * @param x x-coordinate offset from previous platform
	 * @param y y-coordinate offset from previous platform
	 * @param length Specifies the length of the platform
	 * @param height Specifies the height of the platform
	 */
		public void spawnSingleBlock(List<GameObject> l , float x, float y,float length,float height){
			this.lastx= lastx + x;
			this.lasty = lasty+y;
			l.add(new Platform(new Vector3(lastx, lasty, 0), new Vector3(length, height, 0), new Vector3()));
			this.lastx = lastx+ length;
		}
	
	/** Returns the list of powerups
	 * 
	 * @return
	 */
	public List<PowerUp> getPowerUps(){
		return powerUps;
	}
	
	/** Returns the list of platforms
	 * 
	 * @return
	 */
	public List<Platform> getPlatforms(){
		return platforms;
	}
	
	/** Adding platform to the platforms list
	 * 
	 * @param p
	 */
	public void addPlatform(Platform p){
		System.out.println(platforms);
		platforms.add(p);
	}
	
	/** Removing platform from the platforms list
	 * 
	 * @param p
	 */
	public void removePlatform(Platform p){
		platforms.remove(p);
	}
	
	/** Returns the gravity of the level
	 * 
	 * @return
	 */
	public double getGravity(){
		return gravity;
	}
}
