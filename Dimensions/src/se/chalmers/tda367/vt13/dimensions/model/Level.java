package se.chalmers.tda367.vt13.dimensions.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;

/**
 * Class for creating a level with platforms and powerups
 */
public class Level implements Serializable {

	private static final long serialVersionUID = 2122723720243818390L;
	protected List<GameObject> gameObjects = new ArrayList<GameObject>();
	protected float gravity = -0.05f;
	protected Dimension startingDimension = Dimension.XY;
	protected String mapXYPath;
	protected String mapXZPath;
	protected float length;
	private String levelName;

	public Level() {
		this("Empty Level", -0.05f, null, Dimension.XY, null, null, 100);
	}

	public Level(String name, float gravity, List<GameObject> gameObjects,
			Dimension start, String TMXFileXY, String TMXFileXZ,
			float length) {
		this.levelName = name;
		this.gravity = gravity;
		this.gameObjects = gameObjects;
		this.startingDimension = start;
		this.mapXYPath = TMXFileXY;
		this.mapXZPath = TMXFileXZ;
		this.length = length;
	}
	
	@Override
	public Level clone(){
		ArrayList<GameObject> clonedGameObjects = new ArrayList<GameObject>();
		for(GameObject gameObject : gameObjects) {
		    clonedGameObjects.add(gameObject.clone());
		}
		return new Level(levelName, gravity, clonedGameObjects, startingDimension, mapXYPath, mapXZPath, length);
	}

	public List<GameObject> getGameObjects() {
		return new ArrayList<GameObject>(gameObjects);
	}

	public float getLength() {
		return length;
	}

	public String getMapXYPath() {
		return mapXYPath;
	}

	public String getMapXZPath() {
		return mapXZPath;
	}

	public Dimension getStartingDimension() {
		return startingDimension;
	}

	public float getGravity() {
		return gravity;
	}

	public String getName() {
		return levelName;
	}
}
