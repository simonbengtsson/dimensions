package se.chalmers.tda367.vt13.dimensions.model;

/**
 * Class describing a platform in the game.
 * @author Carl Fredriksson
 */
public class Platform extends GameObject {

	// Instance variables
	
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
	public void update(float time) {
		move(time);
	}
	
	// Private methods
	/**
	 * Move the platform.
	 */
	private void move(float time) {
		getPosition().add(getSpeed());
	}
}
