package se.chalmers.tda367.vt13.dimensions.model;

import java.io.Serializable;

/**
 * Abstract class for all objects within the game.
 * @author Carl Fredriksson
 */
public abstract class GameObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Instance variables
	private Vector3 position;
	private Vector3 size;
	private Vector3 speed;
	
	// Public methods
	/**
	 * Constructor to be used by sub classes.
	 * @param position the position of the GameObject
	 * @param size the size of the GameObject
	 * @param speed the speed of the GameObject
	 */
	public GameObject(Vector3 position, Vector3 size, Vector3 speed) {
		this.position = position;
		this.size = size;
		this.speed = speed;
	}
	
	public String toString(){
		return "Position : " + position + " Size : " + size + " Speed : " + speed;
	}
	
	/**
	 * Get method for instance variable position.
	 * @return the position of the GameObject
	 */
	public Vector3 getPosition() {
		return position;
	}
	
	/**
	 * Get method for instance variable volume.
	 * @return the volume of the GameObject
	 */
	public Vector3 getSize() {
		return size;
	}
	
	/**
	 * Get method for instance variable speed.
	 * @return the speed of the GameObject
	 */
	public Vector3 getSpeed() {
		return speed;
	}

	/**
	 * Set method for instance variable position.
	 * @param position the position of the GameObject
	 */
	public void setPosition(Vector3 position) {
		this.position = position;
	}
	
	/**
	 * Set method for instance variable size.
	 * @param size the size of the GameObject
	 */
	public void setSize(Vector3 size) {
		this.size = size;
	}
	
	// Private methods

}
