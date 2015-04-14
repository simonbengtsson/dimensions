package com.chalmers.dimensions.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating a level with platforms and powerups
 */
public class Level implements Serializable, Cloneable {

	private final static long serialVersionUID = 2122723720243818390L;
	private final static float DEFAULT_GRAVITY = -0.05f;
	private final static Dimension DEFAULT_STARTING_DIMENSION = Dimension.XY;
	private List<GameObject> gameObjects = new ArrayList<GameObject>();
	private float gravity;
	private Dimension startingDimension = Dimension.XY;
	private String mapXYPath;
	private String mapXZPath;
	private float length;
	private String levelName;

	/**
	 * Empty level. Later the two maps or the gameobject list has to be set.
	 */
	public Level() {
		this("Empty Level", DEFAULT_GRAVITY, null, DEFAULT_STARTING_DIMENSION,
				null, null, 100);
	}

	/**
	 * Creates a level with some default values.
	 * 
	 * @param name
	 * @param gameObjects
	 * @param TMXFileXY
	 * @param TMXFileXZ
	 * @param length
	 */
	public Level(String name, List<GameObject> gameObjects, String mapXYPath,
			String mapXZPath, float length) {
		this(name, DEFAULT_GRAVITY, gameObjects, DEFAULT_STARTING_DIMENSION,
				mapXYPath, mapXZPath, length);
	}

	/**
	 * Creates a level from scratch.
	 * 
	 * @param name
	 * @param gravity
	 * @param gameObjects
	 * @param start
	 * @param TMXFileXY
	 * @param TMXFileXZ
	 * @param length
	 */
	public Level(String name, float gravity, List<GameObject> gameObjects,
			Dimension start, String mapXYPath, String mapXZPath, float length) {
		this.levelName = name;
		this.gravity = gravity;
		this.gameObjects = gameObjects;
		this.startingDimension = start;
		this.mapXYPath = mapXYPath;
		this.mapXZPath = mapXZPath;
		this.length = length;
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

	@Override
	public Level clone() {
		ArrayList<GameObject> clonedGameObjects = new ArrayList<GameObject>(gameObjects);
		return new Level(levelName, gravity, clonedGameObjects,
				startingDimension, mapXYPath, mapXZPath, length);
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

	// TODO change it so that it is only one return statement
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

}
