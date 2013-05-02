package se.chalmers.tda367.vt13.dimensions.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public abstract class GameObject implements Serializable, SoundObservable {

	private static final long serialVersionUID = 1L;

	private Vector3 position;
	private Vector3 size;
	private Vector3 speed;
	private String soundFile;
	private String imageFile;
	private List<SoundObserver> observers = new ArrayList<SoundObserver>();

	/**
	 * Constructor to be used by sub classes.
	 * 
	 * @param position
	 *            the position of the GameObject
	 * @param size
	 *            the size of the GameObject
	 * @param speed
	 *            the speed of the GameObject
	 */
	protected GameObject(Vector3 position, Vector3 size, Vector3 speed, String imageFile, String soundFile) {
		this.position = position;
		this.size = size;
		this.speed = speed;
		this.soundFile = soundFile;
		this.imageFile = imageFile;
	}

	public String toString() {
		return "Position : " + position + " Size : " + size + " Speed : "
				+ speed;
	}

	public Vector3 getPosition() {
		return position;
	}

	public Vector3 getSize() {
		return size;
	}

	public Vector3 getSpeed() {
		return speed;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public void setSize(Vector3 size) {
		this.size = size;
	}

	public void setSpeed(Vector3 speed) {
		this.speed = speed;
	}

	/**
	 * Makes it possible for the controller to store String's and the
	 * corresponding Sound() in a map for fast access
	 * 
	 * @return
	 */
	public String getSoundFileAsString(){
		return soundFile;
	}

	/**
	 * Makes it possible for the view to store String's and the corresponding
	 * Texture() in a map for fast access
	 * 
	 * @return
	 */
	public String getImageFileAsString(){
		return imageFile;
	}

	@Override
	public void addObserver(SoundObserver s) {
		observers.add(s);
	}

	@Override
	public void removeObserver(SoundObserver s) {
		observers.remove(s);
	}

	@Override
	public List<SoundObserver> getObservers() {
		return observers;
	}
	
	public void playSound(){
		for(SoundObserver s : observers){
			s.playSound(soundFile);
		}
	}
}
