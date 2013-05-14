package se.chalmers.tda367.vt13.dimensions.levels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.Platform;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.powerup.LowGravityPowerUp;
import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;
import se.chalmers.tda367.vt13.dimensions.model.powerup.SpeedPowerUp;

import com.badlogic.gdx.maps.tiled.TiledMap;

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
	private float lastx = 0;
	private float lasty = 50;
	private float lastz = 0;
	private String levelname;

	@Deprecated
	/**
	 * @param levelName
	 * @param filepath
	 */
	public Level(String levelName, String filepath) {
		this.levelname = levelName;
		//this.backGroundImagePath = filepath;
	}
	
	public Level(String name, List<GameObject> gameObjects, float gravity, Dimension start){
		this.levelname = name;
		this.gameObjects = gameObjects;
		this.gravity = gravity;
		this.startingDimension = start;
		
	}

	public List<GameObject> getGameObjects() {
		return gameObjects;
	}

	public TiledMap getMapXY() {
		return mapXY;
	}

	public TiledMap getMapXZ() {
		return mapXZ;
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
	
	public void setGameObjects(List<GameObject> l){
		gameObjects = l;
	}
}
