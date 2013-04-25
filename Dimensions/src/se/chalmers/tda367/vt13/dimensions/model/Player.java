package se.chalmers.tda367.vt13.dimensions.model;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class for the player in the game.
 * 
 * @author Carl Fredriksson
 */
public class Player extends GameObject {

	private float gravityConstant;
	private float jumpSpeed;
	private float zSpeed;
	private boolean isGrounded;
	private final float baseXSpeed;
	private final float baseGravity;
	private boolean usingDash;
	private boolean onPlatform;

	/**
	 * Constructor.
	 * 
	 * @param position
	 *            the position of the player within the game
	 * @param size
	 *            the size of the player
	 * @param speed
	 *            the speed of the player
	 * @param gravityConstant
	 *            the gravity constant affecting how fast the player falls
	 * @param jumpSpeed
	 *            the initial speed of the player when jumping
	 * @param isGrounded
	 *            the boolean for if the player is standing on a platform or not
	 */
	public Player(Vector3 position, Vector3 size, Vector3 speed,
			float gravityConstant, float jumpSpeed, boolean isGrounded) {
		super(position, size, speed, "data/PlayerImg.png", "");
		this.gravityConstant = gravityConstant;
		this.jumpSpeed = jumpSpeed;
		this.isGrounded = isGrounded;
		baseXSpeed = speed.getX();
		baseGravity = gravityConstant;
		usingDash = false;

		// Test
		this.zSpeed = 2f;
		getSpeed().setY(zSpeed);
		setCurrentDimension(Dimension.XY);
		onPlatform = true;
	}

	public float getBaseXSpeed() {
		return baseXSpeed;
	}

	public float getGravity() {
		return gravityConstant;
	}

	public void setGravity(float f) {
		gravityConstant = f;
	}

	/**
	 * Increase the players ySpeed with jumpSpeed.
	 */
	public void jump() {
		if (isGrounded) {
			getSpeed().setY(jumpSpeed);
			isGrounded = false;
		}
	}

	/**
	 * Moves the player up with z-speed;
	 */
	public void changeDirection() {
		// TODO Gets called more then one time each key press which makes it
		// more complicated then just change direction by multiplying with -1 or
		// similar
		getSpeed().setY(-zSpeed);
	}

	public void dash() {
		if (!isGrounded && !usingDash) {
			usingDash = true;
			getSpeed().setX(getSpeed().getX() + 20);

			Timer timer = new Timer();

			// Resets the speed 500ms after used dash
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					getSpeed().setX(getSpeed().getX() - 20);
				}
			}, 500);

			// Enables the player to use dash again after 5s
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					usingDash = false;
				}
			}, 5000);
		}
	}

	public void resetSpeed() {
		getSpeed().setX(baseXSpeed);
	}

	/**
	 * Get method for instance variable isGrounded.
	 * 
	 * @return if the player is standing on a platform or not
	 */
	public boolean getIsGrounded() {
		return isGrounded;
	}

	/**
	 * Method for setting the isGrounded field of the player. For example:
	 * isGrounded should be set to true if proper collision with a platform is
	 * detected in the GameController.
	 * 
	 * @param isGrounded
	 *            if the player is standing on a platform or not
	 */
	public void setIsGrounded(boolean isGrounded) {
		this.isGrounded = isGrounded;
	}

	/**
	 * Check if the the player is grounded. Adjust speed accordingly.
	 */
	public void calculateSpeed() {
		if (getCurrentDimension() == Dimension.XY) {
			if (!isGrounded) {
				getSpeed().setY(getSpeed().getY() + gravityConstant);
			} else {
				getSpeed().setY(0);
			}
		} else if (getCurrentDimension() == Dimension.XZ) {
			if (getPosition().getY() > 400 || getPosition().getY() <= 0) {
				getSpeed().setY(0);
			}
		}
	}

	/**
	 * Gameover if player slips below camera y position
	 */
	public boolean isGameOver() {
		if (getCurrentDimension() == Dimension.XY) {
			return getPosition().getY() < 0;
		} else { // Dimension XZ
			return !onPlatform;
		}

	}
}
