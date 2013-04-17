package se.chalmers.tda367.vt13.dimensions.model;
import java.io.Serializable;
/**
 * Class describing a platform in the game.
 * @author Carl Fredriksson
 */
public class Platform extends GameObject implements Serializable {

	// Instance variables
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Public methods
	
	
	/**
	 * Constructor. Calls the super class constructor.
	 * @param position the position of the platform
	 * @param size the size of the platform
	 * @param speed the speed of the platform
	 */
	public Platform(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed);
	}
	
	@Override
	public void update() {
		move();
	}
	
	// Private methods
	/**
	 * Move the platform.
	 */
	private void move() {
		getPosition().add(getSpeed());
	}
}
