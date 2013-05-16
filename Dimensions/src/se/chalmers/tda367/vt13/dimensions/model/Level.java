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
	protected float levelFinishedPosition;
	private String levelname;

	@Deprecated
	/**
	 * 
	 * @param name
	 * @param gameObjects
	 * @param gravity
	 */
	public Level(String name, float gravity) {
		this.levelname = name;
		this.gravity = gravity;
	}

	@Deprecated
	/**
	 * @param name
	 */
	public Level(String name) {
		this(name, -0.05f);
	}

	@Deprecated
	public Level(String name, float gravity, List<GameObject> gameObjects,
			Dimension start) {
		this.levelname = name;
		this.gravity = gravity;
		this.gameObjects = gameObjects;
		this.startingDimension = start;
	}

	public Level(String name, float gravity, List<GameObject> gameObjects,
			Dimension start, String TMXFileXY, String TMXFileXZ,
			float finishedPosition) {
		this.levelname = name;
		this.gravity = gravity;
		this.gameObjects = gameObjects;
		this.startingDimension = start;
		this.mapXYPath = TMXFileXY;
		this.mapXZPath = TMXFileXZ;
		this.levelFinishedPosition = finishedPosition;
	}

	public List<GameObject> getGameObjects() {
		return gameObjects;
	}

	public float getLevelFinishedPosition() {
		return levelFinishedPosition;
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
		return levelname;
	}
}
