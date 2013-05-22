package se.chalmers.tda367.vt13.dimensions.model;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class for the player in the game.
 * 
 * @author Carl Fredriksson
 */
@SuppressWarnings("serial")
public class Player extends GameObject {

	public static float MAX_VELOCITY = 1f;
	private float jumpSpeed;
	private boolean isGrounded;
	private boolean isStuck;
	private final float baseXSpeed;
	private final float baseZSpeed;
	private boolean changingDirection;
	private static String imgpath = "data/PlayerImg.png";

	/**
	 * Creates player with default values
	 */
	public Player() {
		this(new Vector3(10, 10, 10), new Vector3(2, 2, 2), new Vector3(0.3f,
				0, 0), 1f, false);
	}

	/**
	 * Creates player with specified values
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
			float jumpSpeed, boolean isGrounded) {
		super(position, size, speed, imgpath, "");
		this.jumpSpeed = jumpSpeed;
		this.isGrounded = isGrounded;
		baseXSpeed = speed.getX();
		baseZSpeed = 0.5f;
		changingDirection = false;
	}

	public void setImagePath(String s) {
		Player.imgpath = "data/" + s + "png";
	}

	public float getBaseXSpeed() {
		return baseXSpeed;
	}

	public float getBaseZSpeed() {
		return baseZSpeed;
	}

	/**
	 * Increase the players ySpeed with jumpSpeed. Called when XY dimension is
	 * active.
	 */
	public void jump() {
		if (isGrounded) {
			getSpeed().setY(jumpSpeed);
			isGrounded = false;
		}
	}

	/**
	 * Change the players Z direction. Called when XZ dimension is active.
	 */
	public void changeDirection() {
		if (!changingDirection) {
			changingDirection = true;
			System.out.println(getSpeed().getZ());
			if (getSpeed().getZ() < 0) {
				getSpeed().setZ(baseZSpeed);
			} else {
				getSpeed().setZ(-baseZSpeed);
			}
			// Timer to avoid several direction changes with one keypress
			Timer timer = new Timer();
			long delay = 200;
			timer.schedule(new TimerTask() { 
				@Override
				public void run() {
					changingDirection = false;
				}
			}, delay);
		}
	}

	public void resetXSpeed() {
		getSpeed().setX(baseXSpeed);
	}

	public boolean getIsGrounded() {
		return isGrounded;
	}

	/**
	 * Method for setting the isGrounded field of the player. For example:
	 * isGrounded should be set to true if proper collision with a platform is
	 * detected.
	 * 
	 * @param isGrounded
	 *            if the player is standing on a platform or not
	 */
	public void setIsGrounded(boolean isGrounded) {
		this.isGrounded = isGrounded;
	}

	public boolean getIsStuck() {
		return isStuck;
	}

	/**
	 * Method for setting the isStuck field of the player. For example: isStuck
	 * should be set to true if proper collision with a platform is detected.
	 * 
	 * @param isStuck
	 *            if the player is running in to a platform or not
	 */
	public void setIsStuck(boolean isStuck) {
		this.isStuck = isStuck;
	}

	/**
	 * Check if the the player is grounded. Adjust speed accordingly.
	 */
	public void calculateYSpeed(float gravity) {
		if (isGrounded) {
			getSpeed().setY(0);
		} else {
			getSpeed().setY(getSpeed().getY() + gravity);
		}
	}

	public void calculateXSpeed() {
		if (isStuck) {
			getSpeed().setX(0);
		} else {
			resetXSpeed();
		}
	}

	public Player clone() {
		return new Player(getPosition().clone(), getSize().clone(), getSpeed()
				.clone(), jumpSpeed, isGrounded);
	}
}
