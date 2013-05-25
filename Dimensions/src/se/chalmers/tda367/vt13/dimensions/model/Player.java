package se.chalmers.tda367.vt13.dimensions.model;

import java.util.Timer;
import java.util.TimerTask;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;

/**
 * Class for the player in the game.
 * 
 * @author Carl Fredriksson
 */
@SuppressWarnings("serial")
public class Player extends GameObject {

	public static final float MAX_VELOCITY_Y = 0.5f;
	private static final float DEFAULT_XSPEED = 0.5f;
	private static final float DEFAULT_JUMP_SPEED = 1.2f;
	private float jumpSpeed;
	private boolean isGrounded = false;
	private boolean isStuck;
	private final float baseXSpeed;
	private final float baseZSpeed;
	private boolean changingDirection;
	private static String imgpath = "data/PlayerImg.png";

	/**
	 * Creates player with default values
	 */
	public Player() {
		this(new Vector3(10, 10, 10), new Vector3(1f, 3f, 3f), new Vector3(
				DEFAULT_XSPEED, 0, 0), DEFAULT_JUMP_SPEED, false);
	}

	/**
	 * Creates player with specified values
	 * 
	 * @param position
	 * @param size
	 * @param speed
	 * @param jumpSpeed
	 * @param isGrounded
	 */
	public Player(Vector3 position, Vector3 size, Vector3 speed,
			float jumpSpeed, boolean isGrounded) {
		super(position, size, speed, imgpath, "");
		this.jumpSpeed = jumpSpeed;
		this.isGrounded = isGrounded;
		baseXSpeed = speed.getX();
		baseZSpeed = 0.2f;
		changingDirection = false;
	}

	/**
	 * Update when falling
	 * 
	 * @param gravity
	 */
	public void updateY(float gravity) {
		getSpeed().setY(getSpeed().getY() + gravity);
		getPosition().setY(getPosition().getY() + getSpeed().getY());
	}

	public void updateX() {
		getPosition().setX(getPosition().getX() + getSpeed().getX());
	}

	/**
	 * Increase the players ySpeed with jumpSpeed. Called when XY dimension is
	 * active.
	 */
	public void jump() {
		if (isGrounded) {
			getSpeed().setY(jumpSpeed);
			setGrounded(false);
		}
	}

	/**
	 * Change the players Z direction. Called when XZ dimension is active.
	 */
	public void changeDirection() {
		if (!changingDirection) {
			changingDirection = true;
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

	/**
	 * Should be set to true if proper collision with a platform is detected
	 * ahead of the player.
	 * 
	 * @param isStuck
	 *            if the player is running in to a platform or not
	 */
	public void setStuck(boolean isStuck) {
		this.isStuck = isStuck;
	}

	/**
	 * Should be set to true if proper collision with a platform is detected
	 * below the player.
	 * 
	 * @param isGrounded
	 *            If the player is standing on a platform or not
	 */
	public void setGrounded(boolean isGrounded) {
		this.isGrounded = isGrounded;
	}

	public boolean isStuck() {
		return isStuck;
	}

	public void resetXSpeed() {
		getSpeed().setX(baseXSpeed);
	}

	public boolean isGrounded() {
		return isGrounded;
	}

	public void setImagePath(String s) {
		// TODO findbugs gives error here.
		// "Write to static field from instance method"
		Player.imgpath = "data/" + s + "png";
	}

	public float getBaseXSpeed() {
		return baseXSpeed;
	}

	public float getBaseZSpeed() {
		return baseZSpeed;
	}

	public Player clone() {
		return new Player(getPosition().clone(), getSize().clone(), getSpeed()
				.clone(), jumpSpeed, isGrounded);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!(o instanceof Player) || o == null
				|| o.getClass() != this.getClass()) {
			return false;
		}
		Player p = (Player) o;
		return (this.getPosition().equals(p.getPosition())
				&& this.getSize().equals(p.getSize())
				&& this.getSpeed().equals(p.getSpeed())
				&& this.getBaseXSpeed() == p.getBaseXSpeed()
				&& this.isGrounded() == p.isGrounded()
				&& this.isStuck() == p.isStuck()
				&& this.jumpSpeed == p.jumpSpeed && this.baseZSpeed == p.baseZSpeed);
	}
}
