package se.chalmers.tda367.vt13.dimensions.model;

/**
 * Class describing a platform in the game.
 * @author Carl Fredriksson
 */
public class Platform extends GameObject {

	// Instance variables
	
	// Public methods
	public Platform(Vector3 position, Vector3 speed, double height, double width) {
		setPosition(position);
		setSpeed(speed);
		setHeight(height);
		setWidth(width);
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
