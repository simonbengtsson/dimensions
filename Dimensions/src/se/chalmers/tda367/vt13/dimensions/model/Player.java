package se.chalmers.tda367.vt13.dimensions.model;

/**
 * Class for the player in the game
 * 
 * @author Carl Fredriksson
 */
public class Player extends GameObject {

	// Instance variables
	private float gravityConstant;
	private float jumpSpeed;
	private boolean isGrounded;
	private final float standardSpeedX;

	// Public methods
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
		super(position, size, speed);
		standardSpeedX = speed.getX();
		this.gravityConstant = gravityConstant;
		this.jumpSpeed = jumpSpeed;
		this.isGrounded = isGrounded;
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
	
	public void resetSpeed(){
		getSpeed().setX(standardSpeedX);
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

	@Override
	public void update() {
		moveX();
		moveY();
	}

	// Private methods
	/**
	 * Move the player
	 */
	private void moveY() {
		if (!isGrounded) {
			Vector3 p = getPosition();
			Vector3 s = getSpeed();
			p.setY(p.getY() + s.getY());
			s.setY(s.getY() + gravityConstant);
		} else {
			getSpeed().setY(0);
		}
	}
	
	private void moveX(){
		getPosition().setX(getPosition().getX() + getSpeed().getX());
	}

	@Override
	public void playSound() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSoundFileAsString() {
		// TODO Auto-generated method stub
		return "";
	}

}
