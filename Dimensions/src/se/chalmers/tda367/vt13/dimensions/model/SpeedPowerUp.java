package se.chalmers.tda367.vt13.dimensions.model;

/**
 * Class for the speed PowerUp. Increases
 * the players speed when used.
 * @author Carl Fredriksson
 */
public class SpeedPowerUp extends GameObject implements PowerUp {

	// Instance variables
	
	// Public methods
	/**
	 * Constructor. Calls the super class constructor.
	 * @param position the position of the SpeedPowerUp
	 * @param size the size of the SpeedPowerUp
	 * @param speed the speed of the SpeedPowerUp
	 */
	public SpeedPowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed);
	}
	
	@Override
	public void use(Player player) {
		if (player.getSpeed().getX() <= 2) {
			player.getSpeed().setX(player.getSpeed().getX() * 2);
		}
	}

	@Override
	public void update() {
		move();
	}

	// Private methods
	/**
	 * Move the PowerUp.
	 */
	private void move() {
		getPosition().add(getSpeed());
	}
	
}
