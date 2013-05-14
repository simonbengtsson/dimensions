package se.chalmers.tda367.vt13.dimensions.model;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.levels.Level;
import se.chalmers.tda367.vt13.dimensions.util.TiledMapHandler;

/**
 * Game model.
 * 
 * @author Carl Fredriksson
 */
public class GameWorld {

	public enum Dimension {
		XY, XZ, YZ
	}

	public enum WorldEvent {
		GAME_OVER, DIMENSION_CHANGED;
	}

	public enum State {
		GAME_RUNNING, GAME_PAUSED, GAME_OVER, GAME_LEVEL_END, DIMENSION_CHANGE;
	}

	private List<GameObject> gameObjects;
	private Player player;
	private CollisionHandler collisionHandler;
	private Dimension currentDimension;
	private float baseGravity;
	private float gravity;
	private List<WorldListener> listeners = new ArrayList<WorldListener>();
	private State currentState;
	private CheckPoint cp;
	private int score;
	private Level currentLevel;

	/**
	 * New GameWorld with given Level
	 * 
	 * @param gameObjects
	 */
	public GameWorld(Level level, TiledMapHandler tiledMapHandler) {
		this(new Player(), level, tiledMapHandler);
	}

	/**
	 * New GameWorld with given Level
	 * 
	 * @param gameObjects
	 */
	public GameWorld(Player player, Level level, TiledMapHandler tiledMapHandler) {
		this.player = player;
		this.gameObjects = level.getGameObjects();
		this.collisionHandler = new CollisionHandler(tiledMapHandler);
		this.gravity = level.getGravity();
		this.currentDimension = level.getStartingDimension();
		this.baseGravity = gravity;
		this.currentState = State.GAME_RUNNING;
		cp = new CheckPoint(this);
		currentLevel = level;
	}

	public void update() {
		switch (currentState) {
		case GAME_RUNNING:
			updateRunning();
			break;
		case GAME_PAUSED:
			updatePaused();
			break;
		case GAME_LEVEL_END:
			updateLevelEnd();
			break;
		case GAME_OVER:
			updateGameOver();
			break;
		case DIMENSION_CHANGE:
			updateDimensionChange();
			break;
		}
	}

	/**
	 * Update all the GameObjects in the gameObjects list, and update the
	 * player.
	 */
	public void updateRunning() {
		player.getPosition().add(player.getSpeed());
		collisionHandler.checkCollisions(this);
		if (currentDimension == Dimension.XY) {
			player.calculateYSpeed(this);
			// Reset the player's speed to MAX_VELOCITY if it's too fast, the
			// reason is to simulate drag
			if (Math.abs(player.getSpeed().getY()) > Player.MAX_VELOCITY) {
				player.getSpeed().setY(
						Math.signum(player.getSpeed().getY())
								* Player.MAX_VELOCITY);
			}
		} else if (currentDimension == Dimension.XZ) {
			player.setSpeed(new Vector3(player.getSpeed().getX(), 0, 0));
		}
		if (isGameOver()) {
			currentState = State.GAME_OVER;
		}
	}

	private void updatePaused() {
		// TODO show paused screen
	}

	private void updateLevelEnd() {
		// TODO show Level end screen
	}

	private void updateGameOver() {
		notifyWorldListeners(WorldEvent.GAME_OVER);
	}

	private void updateDimensionChange() {
		// Hint dimension change?
		notifyWorldListeners(WorldEvent.DIMENSION_CHANGED);
	}

	public void swapDimension() {
		if (currentDimension == Dimension.XY) {
			currentDimension = Dimension.XZ;
		} else {
			currentDimension = Dimension.XY;
		}
		currentState = State.DIMENSION_CHANGE;
	}

	/**
	 * Game over conditions, only if player is below 0 on the y-axis for now
	 * 
	 * @return if game over
	 */
	public boolean isGameOver() {
		return player.getPosition().getY() < 0;
	}

	public List<GameObject> getGameObjects() {
		return gameObjects;
	}

	public Level getCurrentLevel() {
		return currentLevel;
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

	public void notifyWorldListeners(WorldEvent worldEvent) {
		for (WorldListener wordListener : listeners) {
			wordListener.worldChange(worldEvent, this);
		}
	}

	public void addWorldListener(WorldListener newListener) {
		listeners.add(newListener);
	}

}
