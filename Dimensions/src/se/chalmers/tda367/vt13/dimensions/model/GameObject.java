package se.chalmers.tda367.vt13.dimensions.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.controller.SoundObserver;

/**
 * Abstract class for all objects within the game.
 * @author Carl Fredriksson
 */
public abstract class GameObject implements Serializable, SoundObservable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Instance variables
	private Vector3 position;
	private Vector3 size;
	private Vector3 speed;
	private List<SoundObserver> observers = new ArrayList<SoundObserver>();
	
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
		//observers = new ArrayList<SoundObserver>();
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
	
	/**
	 * Set method for instance variable speed.
	 * @param speed the speed of the GameObject
	 */
	public void setSpeed(Vector3 speed) {
		this.speed = speed;
	}
	
	/**
	 * Method to be implemented in sub classes.
	 * Is called by the controller, making all
	 * objects update their states every frame.
	 */
	public abstract void update();
	
	public abstract String getSoundFileAsString();
	
	@Override
	public void addObserver(SoundObserver s){
		observers.add(s);
	}
	
	@Override
	public void removeObserver(SoundObserver s){
		observers.remove(s);
	}
	
	@Override
	public List<SoundObserver> getObservers(){
		return observers;
	}
	
	// Private methods

}
