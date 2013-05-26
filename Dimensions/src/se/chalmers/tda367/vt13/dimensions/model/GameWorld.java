package se.chalmers.tda367.vt13.dimensions.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Game model.
 * 
 * @author Carl Fredriksson
 */
public class GameWorld {

	public enum Dimension {
		XY, XZ, YZ
	}

	public enum State {
		GAME_RUNNING, GAME_PAUSED, GAME_OVER, LEVEL_FINISHED, DIMENSION_CHANGED, DIMENSION_CHANGING;
	}

	private List<GameObject> gameObjects;
	private Player player;
	private Chaser chaser;
	private CollisionHandler collisionHandler;
	private TileCollisionHandler tileCollisionHandler;
	private Dimension currentDimension;
	private float baseGravity;
	private float gravity;
	private List<WorldListener> listeners = new ArrayList<WorldListener>();
	private State currentState;
	private CheckPoint cp;
	private int score;
	private Level level;

	/**
	 * New GameWorld with given Level
	 * 
	 * @param gameObjects
	 */
	public GameWorld(Level level, MapHandler mapHandler) {
		this(new Player(), level, mapHandler);
	}

	/**
	 * New GameWorld with given Level
	 * 
	 * @param gameObjects
	 */
	public GameWorld(Player player, Level level, MapHandler mapHandler) {
		this.level = level;
		this.player = player;
		this.collisionHandler = new CollisionHandler();
		this.tileCollisionHandler = new TileCollisionHandler(this, mapHandler);
		this.chaser = new Chaser();
		this.gameObjects = level.getGameObjects();
		gameObjects.add(chaser);
		this.gravity = level.getGravity();
		this.currentDimension = level.getStartingDimension();
		this.baseGravity = level.getGravity();
		this.currentState = State.GAME_RUNNING;
		cp = new CheckPoint(this);
	}

	public void update() {
		switch (currentState) {
		case GAME_RUNNING:
			updateRunning();
			break;
		case GAME_PAUSED:
			notifyWorldListeners(State.GAME_PAUSED);
			break;
		case DIMENSION_CHANGING:
			notifyWorldListeners(State.DIMENSION_CHANGING);
			updateRunning();
			break;
		default:
			break;
		}

	}

	/**
	 * Update all the GameObjects in the gameObjects list, and update the
	 * player.
	 */
	public void updateRunning() {
		updatePlayer();
		chaser.update();
		collisionHandler.checkCollisions(this);
		checkGameOver();
		checkLevelFinished();
	}
	
	private void updatePlayer(){
		if(!tileCollisionHandler.isGroundBelow()){
			player.updateY(gravity);
		}
		if(!tileCollisionHandler.isGroundLeft()){
			player.updateX();
		}
	}

	/**
	 * If dimension XY, change it to XZ and the opposite
	 */
	public void swapDimension() {
		if (currentDimension == Dimension.XY) {
			currentDimension = Dimension.XZ;
			player.prepareForXZ();
			
		} else {
			currentDimension = Dimension.XY;
			player.prepareForXY();
		}
		notifyWorldListeners(State.DIMENSION_CHANGED);
		currentState = State.GAME_RUNNING;
	}

	public void startDimensionTimer() {
		currentState = State.DIMENSION_CHANGING;
		Timer t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				swapDimension();
			}

		}, 2000);
	}

	/**
	 * Game over conditions
	 * 
	 * @return if game over
	 */
	public void checkGameOver() {
		if (player.getPosition().getY() < 0
				|| chaser.getPosition().getX() >= player.getPosition().getX()
				|| (currentDimension == Dimension.XZ && !player.isGrounded())) {
			notifyWorldListeners(State.GAME_OVER);
		}
	}

	/**
	 * Notify listeners if the player has reached the end of the level
	 */
	public void checkLevelFinished() {
		if (player.getPosition().getX() >= level.getLength()) {
			notifyWorldListeners(State.LEVEL_FINISHED);
		}
	}

	public CheckPoint getCheckPoint() {
		return this.cp;
	}

	public List<GameObject> getGameObjects() {
		return gameObjects;
	}

	public Level getLevel() {
		return level;
	}

	public Player getPlayer() {
		return player;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int i) {
		score = i;
	}

	public float getBaseGravity() {
		return this.baseGravity;
	}

	public void setCurrentState(State newState) {
		currentState = newState;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void resetToCheckPoint() {
		player = cp.getPlayer();
	}

	public void placeCheckPoint() {
		cp = new CheckPoint(this);
	}

	public void setDimension(Dimension dimension) {
		currentDimension = dimension;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float g) {
		gravity = g;
	}

	public void resetGravity() {
		gravity = baseGravity;
	}

	public Dimension getDimension() {
		return currentDimension;
	}

	/**
	 * Notify world listeners the the World's state has changed
	 * 
	 * @param newWorldState
	 */
	public void notifyWorldListeners(State newWorldState) {
		for (WorldListener wordListener : listeners) {
			wordListener.worldChanged(newWorldState);
		}
	}

	public void addWorldListener(WorldListener newListener) {
		listeners.add(newListener);
	}

	public List<WorldListener> getWorldListeners() {
		return listeners;
	}

	/**
	 * Calculates the current progress
	 * 
	 * @return The progress in percentage
	 */
	public float getProgress() {
		return player.getPosition().getX() / level.getLength();
	}

	public float getChaserProgress() {
		return chaser.getPosition().getX() / level.getLength();
	}

}
