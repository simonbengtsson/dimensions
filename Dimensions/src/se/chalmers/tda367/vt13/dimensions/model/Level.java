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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((gameObjects == null) ? 0 : gameObjects.hashCode());
		result = prime * result + Float.floatToIntBits(gravity);
		result = prime * result + Float.floatToIntBits(length);
		result = prime * result
				+ ((levelName == null) ? 0 : levelName.hashCode());
		result = prime * result
				+ ((mapXYPath == null) ? 0 : mapXYPath.hashCode());
		result = prime * result
				+ ((mapXZPath == null) ? 0 : mapXZPath.hashCode());
		result = prime
				* result
				+ ((startingDimension == null) ? 0 : startingDimension
						.hashCode());
		return result;
	}

	//Needed for Level Testing
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Level other = (Level) obj;
		if (gameObjects == null) {
			if (other.gameObjects != null)
				return false;
		} else if (!gameObjects.equals(other.gameObjects))
			return false;
		if (Float.floatToIntBits(gravity) != Float
				.floatToIntBits(other.gravity))
			return false;
		if (Float.floatToIntBits(length) != Float.floatToIntBits(other.length))
			return false;
		if (levelName == null) {
			if (other.levelName != null)
				return false;
		} else if (!levelName.equals(other.levelName))
			return false;
		if (mapXYPath == null) {
			if (other.mapXYPath != null)
				return false;
		} else if (!mapXYPath.equals(other.mapXYPath))
			return false;
		if (mapXZPath == null) {
			if (other.mapXZPath != null)
				return false;
		} else if (!mapXZPath.equals(other.mapXZPath))
			return false;
		if (startingDimension != other.startingDimension)
			return false;
		return true;
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
