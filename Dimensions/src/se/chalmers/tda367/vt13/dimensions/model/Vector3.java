package se.chalmers.tda367.vt13.dimensions.model;

/**
 * Class describing a three dimensional vector.
 * @author Carl Fredriksson
 */
public class Vector3 {

	// Instance variables
	private double x, y, z;
	
	// Public methods
	/**
	 * Default constructor.
	 */
	public Vector3() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	/**
	 * Constructor.
	 * @param x the Vector's x value
	 * @param y the Vector's y value
	 * @param z the Vector's z value
	 */
	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Copy constructor.
	 * @param vector3 the vector3 to copy from
	 */
	public Vector3(Vector3 vector3) {
		this.x = vector3.getX();
		this.y = vector3.getY();
		this.z = vector3.getZ();
	}
	
	/**
	 * Get method for instance variable x.
	 * @return the vector's x value
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Get method for instance variable y.
	 * @return the vector's y value
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Get method for instance variable z.
	 * @return the vector's y value
	 */
	public double getZ() {
		return z;
	}
	
	/**
	 * Set method for instance variable x.
	 * @param x the x value for the vector
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Set method for instance variable y.
	 * @param y the y value for the vector
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Set method for instance variable z.
	 * @param z the z value for the vector
	 */
	public void setZ(double z) {
		this.z = z;
	}
	
	/**
	 * Vector addition.
	 * @param otherVector the vector to add to this vector
	 */
	public void add(Vector3 otherVector) {
		this.x = this.x + otherVector.getX();
		this.y = this.y + otherVector.getY();
		this.z = this.z + otherVector.getZ();
	}
	
	/**
	 * Vector subtraction.
	 * @param otherVector the vector to subtract from this vector
	 */
	public void subtract(Vector3 otherVector) {
		this.x = this.x - otherVector.getX();
		this.y = this.y - otherVector.getY();
		this.z = this.z - otherVector.getZ();
	}
	
	/**
	 * Scale the vector with a scalar.
	 * @param scalar the scalar to scale the vector with
	 */
	public void scale(double scalar) {
		x = x * scalar;
		y = y * scalar;
		z = z * scalar;
	}
	
	// Private methods
	
}
