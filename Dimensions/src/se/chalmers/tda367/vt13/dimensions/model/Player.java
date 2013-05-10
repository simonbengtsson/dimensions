package se.chalmers.tda367.vt13.dimensions.model;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class for the player in the game.
 * 
 * @author Carl Fredriksson
 */
public class Player extends GameObject {

	public static float MAX_VELOCITY = 1f;
	private float jumpSpeed;
	private boolean isGrounded;
	private final float baseXSpeed;
	private final float baseZSpeed;
	private boolean usingDash;

	/**
	 * Creates player with default values
	 */
	public Player() {
		this(new Vector3(10, 10, 10), new Vector3(2.1f, 2.1f, 2.1f),
				new Vector3(0.3f, 0, 0), 1f, false);
	}

	/**
	 * Creates player with spcified values
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
		super(position, size, speed, "data/PlayerImg.png", "");
		this.jumpSpeed = jumpSpeed;
		this.isGrounded = isGrounded;
		baseXSpeed = speed.getX();
		baseZSpeed = 10;
		usingDash = false;
	}

	public float getBaseXSpeed() {
		return baseXSpeed;
	}

	/**
	 * Increase the players ySpeed with jumpSpeed.
	 */
	public void jump() {
		if (isGrounded) {
			getSpeed().setY(jumpSpeed);
			isGrounded = false;
			System.out.println("jump");
		}
	}

	/**
	 * Moves the player up with z-speed;
	 */
	public void moveUp() {
		getSpeed().setZ(baseZSpeed);
	}

	/**
	 * Moves the player down with z-speed;
	 */
	public void moveDown() {
		getSpeed().setZ(-baseZSpeed);
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

	public void resetXSpeed() {
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
	public void calculateYSpeed(GameWorld world) {
		if (isGrounded) {
			getSpeed().setY(0);
		} else {
			world.getPlayer().getSpeed()
					.setY(getSpeed().getY() + world.getGravity());
		}
	}

	public Player clone() {
		return new Player(getPosition().clone(), getSize().clone(), getSpeed()
				.clone(), jumpSpeed, isGrounded);
	}

	/**
	 * Gameover if player slips below screens 0 coordinate
	 */
	public boolean isGameOver() {
		return getPosition().getY() < 0;
	}
}
