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

	public static final float MAX_VELOCITY_Y = 1f;
	private static final float DEFAULT_XSPEED = 0.5f;
	private static final float DEFAULT_JUMP_SPEED = 1.2f;
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
		this(new Vector3(10, 10, 10), new Vector3(2, 2, 2), new Vector3(
				DEFAULT_XSPEED, 0, 0), DEFAULT_JUMP_SPEED, false);
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
		baseZSpeed = 0.2f;
		changingDirection = false;
	}

	/**
	 * Update the player. Method should be called each frame.
	 */
	public void update(Dimension currentDimension, float gravity) {
		getPosition().add(getSpeed());
		if (currentDimension == Dimension.XY) {
			calculateYSpeed(gravity);
			calculateXSpeed();
		}
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
	 * Adjust Y speed according to gravity.
	 */
	public void calculateYSpeed(float gravity) {
		if (isGrounded) {
			getSpeed().setY(0);
		} else {
			getSpeed().setY(getSpeed().getY() + gravity);
		}
		// simulates drag
		if (Math.abs(getSpeed().getY()) > MAX_VELOCITY_Y) {
			getSpeed().setY(
					Math.signum(getSpeed().getY()) * Player.MAX_VELOCITY_Y);
		}
	}

	public void calculateXSpeed() {
		if (isStuck) {
			getSpeed().setX(0);
		} else {
			resetXSpeed();
		}
	}

	/**
	 * Should be set to true if proper collision with a platform is detected
	 * ahead of the player.
	 * 
	 * @param isStuck
	 *            if the player is running in to a platform or not
	 */
	public void setIsStuck(boolean isStuck) {
		this.isStuck = isStuck;
	}

	/**
	 * Should be set to true if proper collision with a platform is detected
	 * below the player.
	 * 
	 * @param isGrounded
	 *            If the player is standing on a platform or not
	 */
	public void setIsGrounded(boolean isGrounded) {
		this.isGrounded = isGrounded;
	}

	public boolean getIsStuck() {
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
				&& this.getIsStuck() == p.getIsStuck()
				&& this.jumpSpeed == p.jumpSpeed && this.baseZSpeed == p.baseZSpeed);
	}

	public float getScreenY(Vector3 vector3, Dimension currentDimension) {
		switch (currentDimension) {
		case XY:
			return vector3.getY();
		case XZ:
			return vector3.getZ();
		default:
			break;
		}
		return 0;
	}
}
