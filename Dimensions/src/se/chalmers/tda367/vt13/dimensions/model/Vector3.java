package se.chalmers.tda367.vt13.dimensions.model;

import java.io.Serializable;

/**
 * Class describing a three dimensional vector.
 * 
 * @author Carl Fredriksson
 */
public class Vector3 implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private float x, y, z;

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
	 * 
	 * @param x
	 *            the Vector's x value
	 * @param y
	 *            the Vector's y value
	 * @param z
	 *            the Vector's z value
	 */
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Copy constructor.
	 * 
	 * @param vector3
	 *            the vector3 to copy from
	 */
	public Vector3(Vector3 vector3) {
		this.x = vector3.getX();
		this.y = vector3.getY();
		this.z = vector3.getZ();
	}

	/**
	 * Get method for instance variable x.
	 * 
	 * @return the vector's x value
	 */
	public float getX() {
		return x;
	}

	/**
	 * Get method for instance variable y.
	 * 
	 * @return the vector's y value
	 */
	public float getY() {
		return y;
	}

	/**
	 * Get method for instance variable z.
	 * 
	 * @return the vector's y value
	 */
	public float getZ() {
		return z;
	}

	/**
	 * Set method for instance variable x.
	 * 
	 * @param x
	 *            the x value for the vector
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Set method for instance variable y.
	 * 
	 * @param y
	 *            the y value for the vector
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Set method for instance variable z.
	 * 
	 * @param z
	 *            the z value for the vector
	 */
	public void setZ(float z) {
		this.z = z;
	}

	/**
	 * Vector addition.
	 * 
	 * @param otherVector
	 *            the vector to add to this vector
	 */
	public void add(Vector3 otherVector) {
		this.x = this.x + otherVector.getX();
		this.y = this.y + otherVector.getY();
		this.z = this.z + otherVector.getZ();
	}

	/**
	 * Vector subtraction.
	 * 
	 * @param otherVector
	 *            the vector to subtract from this vector
	 */
	public void subtract(Vector3 otherVector) {
		this.x = this.x - otherVector.getX();
		this.y = this.y - otherVector.getY();
		this.z = this.z - otherVector.getZ();
	}

	/**
	 * Scale the vector with a scalar.
	 * 
	 * @param scalar
	 *            the scalar to scale the vector with
	 */
	public void scale(float scalar) {
		x = x * scalar;
		y = y * scalar;
		z = z * scalar;
	}

	/**
	 * Returns the vector as an array
	 * 
	 * @return an array
	 */
	public double[] getArray() {
		return new double[] { x, y, z };
	}

	/**
	 * Sets x, y & z to the specified values.
	 * 
	 * @param x
	 *            Sets x-value
	 * @param y
	 *            Sets y-value
	 * @param z
	 *            Sets z-value
	 */
	public void setXYZ(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public Vector3 clone() {
		return new Vector3(x, y, z);
	}

	public int hashCode() {
		double hash = 0;
		hash = +x * 9;
		hash = +y * 13;
		hash = +z * 17;
		return (int) hash;
	}

	public boolean equals(Object o) {
		if (o instanceof Vector3) {
			Vector3 v = (Vector3) o;
			if (Math.abs(v.getX() - x) < 0.00001
					&& Math.abs(v.getY() - y) < 0.00001
					&& Math.abs(v.getZ() - z) < 0.00001) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String toString() {
		return "x=" + x + " y=" + y + " z=" + z;
	}

}
