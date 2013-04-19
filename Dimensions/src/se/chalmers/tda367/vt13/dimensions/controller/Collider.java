package se.chalmers.tda367.vt13.dimensions.controller;

import com.badlogic.gdx.math.Rectangle;

/**
 * Class for collision testing. Uses
 * libGDX rectangles.
 * @author Carl Fredriksson
 */
public class Collider {

	// Instance variables
	private Rectangle boundingRect;
	private Rectangle topRect, botRect, leftRect, rightRect;
	
	// Public methods
	/**
	 * Constructor.
	 * @param x the x position of the Collider.
	 * @param y the y position of the Collider.
	 * @param width the width of the Collider.
	 * @param height the height of the Collider.
	 */
	public Collider(float x, float y, float width, float height) {
		boundingRect = new Rectangle(x, y, width, height);
		topRect = new Rectangle(x, y+height-20, width, 20);
		botRect = new Rectangle(x, y, width, 20);
		leftRect = new Rectangle(x, y, 20, height);
		rightRect = new Rectangle(x+width-20, y, 20, height);
	}
	
	/**
	 * Get method for instance variable boundingRect.
	 * @return the bounding rectangle of the Collider
	 */
	public Rectangle getBoundingRect() {
		return boundingRect;
	}
	
	/**
	 * Get method for instance variable topRect.
	 * @return the top rectangle of the Collider
	 */
	public Rectangle getTopRect() {
		return topRect;
	}
	
	/**
	 * Get method for instance variable botRect.
	 * @return the bottom rectangle of the Collider
	 */
	public Rectangle getBotRect() {
		return botRect;
	}
	
	/**
	 * Get method for instance variable leftRect.
	 * @return the left rectangle of the Collider
	 */
	public Rectangle getLeftRect() {
		return leftRect;
	}
	
	/**
	 * Get method for instance variable rightRect.
	 * @return the right rectangle of the Collider
	 */
	public Rectangle getRightRect() {
		return rightRect;
	}
	
	/**
	 * Method that tests if this Collider's boundingRect
	 * collides with the argument Collider.
	 * @param collider the Collider to test collision with
	 * @return true if collision, otherwise false
	 */
	public boolean hit(Collider collider) {
		if (boundingRect.overlaps(collider.getBoundingRect())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method that tests if this Collider's boundingRect
	 * collides with the argument rectangle.
	 * @param rect the Rectangle to test collision with
	 * @return true if collision, otherwise false
	 */
	public boolean hit(Rectangle rect) {
		if (boundingRect.overlaps(rect)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method that test if this Collider's topRect
	 * collides with the argument Collider.
	 * @param collider the Collider to test collision with
	 * @return true if collision, otherwise false
	 */
	public boolean topHit(Collider collider) {
		if (topRect.overlaps(collider.getBoundingRect())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method that tests if this Collider's topRect
	 * collides with the argument rectangle.
	 * @param rect the Rectangle to test collision with
	 * @return true if collision, otherwise false
	 */
	public boolean topHit(Rectangle rect) {
		if (topRect.overlaps(rect)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method that test if this Collider's botRect
	 * collides with the argument Collider.
	 * @param collider the Collider to test collision with
	 * @return true if collision, otherwise false
	 */
	public boolean botHit(Collider collider) {
		if (botRect.overlaps(collider.getBoundingRect())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method that tests if this Collider's botRect
	 * collides with the argument rectangle.
	 * @param rect the Rectangle to test collision with
	 * @return true if collision, otherwise false
	 */
	public boolean botHit(Rectangle rect) {
		if (botRect.overlaps(rect)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method that test if this Collider's leftRect
	 * collides with the argument Collider.
	 * @param collider the Collider to test collision with
	 * @return true if collision, otherwise false
	 */
	public boolean leftHit(Collider collider) {
		if (leftRect.overlaps(collider.getBoundingRect())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method that tests if this Collider's leftRect
	 * collides with the argument rectangle.
	 * @param rect the Rectangle to test collision with
	 * @return true if collision, otherwise false
	 */
	public boolean leftHit(Rectangle rect) {
		if (leftRect.overlaps(rect)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method that test if this Collider's rightRect
	 * collides with the argument Collider.
	 * @param collider the Collider to test collision with
	 * @return true if collision, otherwise false
	 */
	public boolean rightHit(Collider collider) {
		if (rightRect.overlaps(collider.getBoundingRect())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method that tests if this Collider's rightRect
	 * collides with the argument rectangle.
	 * @param rect the Rectangle to test collision with
	 * @return true if collision, otherwise false
	 */
	public boolean rightHit(Rectangle rect) {
		if (rightRect.overlaps(rect)) {
			return true;
		}
		return false;
	}
	
	// Private methods
	
}
