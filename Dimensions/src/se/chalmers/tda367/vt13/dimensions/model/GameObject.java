package se.chalmers.tda367.vt13.dimensions.model;

/**
 * Abstract class for all objects within the game.
 * @author Carl Fredriksson
 */
public abstract class GameObject {
	
	// Instance variables
	private Vector3 position;
	private Vector3 speed;
	private double height;
	private double width;
	
	// Public methods
	/**
	 * Get method for instance variable position.
	 * @return the position of the GameObject as a Vector3
	 */
	public Vector3 getPosition() {
		return position;
	}
	
	/**
	 * Get method for instance variable speed.
	 * @return the speed of the GameObject as a Vector3
	 */
	public Vector3 getSpeed() {
		return speed;
	}
	
	/**
	 * Get method for instance variable height.
	 * @return the height of the GameObject
	 */
	public double getHeight() {
		return height;
	}
	
	/**
	 * Get method for instance variable width.
	 * @return the width of the GameObject
	 */
	public double getWidth() {
		return width;
	}
	
	/**
	 * Set method for instance variable position.
	 * @param position the position of the GameObject as a Vector3
	 */
	public void setPosition(Vector3 position) {
		this.position = position;
	}
	
	/**
	 * Set method for instance variable speed.
	 * @param speed the speed of the GameObject as a Vector3
	 */
	public void setSpeed(Vector3 speed) {
		this.speed = speed;
	}
	
	/**
	 * Set method for instance variable height.
	 * @param height the height of the GameObject
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	/**
	 * Method to be implemented in sub classes.
	 * Is called by the controller, making all
	 * objects update their states every frame.
	 */
	public abstract void update();
	
	// Private methods

}
