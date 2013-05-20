package se.chalmers.tda367.vt13.dimensions.model;

/**
 * Class for the agents chasing the player. Game over if the Chaser catches up
 * to the player.
 */
public class Chaser extends GameObject {

	/**
	 * Create a Chaser with default values.
	 */
	public Chaser() {
		this(new Vector3(-20, 2, 0), new Vector3(2, 2, 2),
				new Vector3(0.4f, 0, 0), "data/PlayerImg.png", "");
	}

	/**
	 * Constructor calling super class constructor. Sets the chaseSpeed variable
	 * to the speed variables x value.
	 * 
	 * @param position
	 *            the position of the Chaser
	 * @param size
	 *            the size of the Chaser
	 * @param speed
	 *            the speed of the Chaser
	 * @param imageFile
	 *            the file path to the chaser's image file
	 * @param soundFile
	 *            the file path to the chaser's sound file
	 */
	public Chaser(Vector3 position, Vector3 size, Vector3 speed,
			String imageFile, String soundFile) {
		super(position, size, speed, imageFile, soundFile);
	}

	@Override
	public GameObject clone() {
		return new Chaser(getPosition().clone(), getSize().clone(), getSpeed()
				.clone(), getImagePath(), getSoundFileAsString());
	}

}
