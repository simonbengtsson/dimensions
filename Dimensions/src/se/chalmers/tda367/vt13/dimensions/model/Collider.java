package se.chalmers.tda367.vt13.dimensions.model;

/**
 * Class for collision testing.
 * @author Carl Fredriksson
 */
public class Collider {

	// Instance variables
	private Vector3 position;
	private Vector3 size;
	
	// Public methods
	/**
	 * Constructor.
	 * @param position the position of the Collider.
	 * @param size the size position of the Collider.
	 */
	public Collider(Vector3 position, Vector3 size) {
		this.position = position;
		this.size = size;
	}
	
	/**
	 * Method testing if a vector is within the Collider.
	 * @param vector the vector to test
	 * @return true if the vector is within the Collider, otherwise false
	 */
	public boolean hitTest(Vector3 vector) {
		if (vector.getY() >= position.getY() && vector.getY() <= position.getY() + size.getY() &&
				vector.getX() >= position.getX() && vector.getX() <= position.getX() + size.getX() &&
				vector.getZ() >= position.getZ() && vector.getZ() <= position.getZ() + size.getZ()) {
			return true;
		}
		return false;
	}
	
	public Vector3 getPosition() {
		return position;
	}
	
	// Private methods
	
}
