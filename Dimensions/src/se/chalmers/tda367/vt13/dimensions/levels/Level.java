package se.chalmers.tda367.vt13.dimensions.levels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMap;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.Platform;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.model.powerup.LowGravityPowerUp;
import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;
import se.chalmers.tda367.vt13.dimensions.model.powerup.SpeedPowerUp;

/**
 * Class for creating a level with platforms and powerups
 */
public class Level implements Serializable {

	private static final long serialVersionUID = 2122723720243818390L;
	protected List<GameObject> gameObjects = new ArrayList<GameObject>();
	protected float gravity = -0.05f;
	protected Dimension startingDimension = Dimension.XY;
	protected TiledMap mapXY;
	protected TiledMap mapXZ;
	protected String stringMapXY;
	protected String stringMapXZ;
	private String levelname;

	@Deprecated
	/**
	 * @param name
	 */
	public Level(String name){
		this(name, -0.05f);
	}
	
	@Deprecated
	public Level(String name, float gravity){
		this(name, new ArrayList<GameObject>(), gravity, Dimension.XY);
	}

	@Deprecated
	public Level(String levelName, String filepath) {
		this.levelname = levelName;
		//this.backGroundImagePath = filepath;
	}
	
	public Level(String name, List<GameObject> gameObjects, float gravity, Dimension start){
		this.levelname = name;
		this.gravity = gravity;
		this.startingDimension = start;
		this.gameObjects = gameObjects;
	}

	public List<GameObject> getGameObjects() {
		return gameObjects;
	}

	public TiledMap getMapXY() {
		return mapXY;
	}


	public Dimension getStartingDimension() {
		return startingDimension;
	}

	public float getGravity() {
		return gravity;
	}
	
	public String getName(){
		return levelname;
	}
	
	public String getStringMapXY(){
		return stringMapXY;
	}
	
	public String getStringMapXZ(){
		return stringMapXZ;
	}
	
	public void setGameObjects(List<GameObject> l){
		gameObjects = l;
	}
}
