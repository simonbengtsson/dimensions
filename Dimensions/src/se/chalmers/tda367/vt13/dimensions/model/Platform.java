package se.chalmers.tda367.vt13.dimensions.model;
import java.io.Serializable;
/**
 * Class describing a platform in the game.
 * @author Carl Fredriksson
 */
public class Platform extends GameObject implements Serializable {

	// Instance variables
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String textureFile;

	// Public methods
	
	
	/**
	 * Constructor. Calls the super class constructor.
	 * @param position the position of the platform
	 * @param size the size of the platform
	 * @param speed the speed of the platform
	 */
	public Platform(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed);
		textureFile = "data/PlatformImg.png";
	}
	
	/*
<<<<<<< HEAD

=======
*/

	@Override
	public void playSound() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSoundFileAsString() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getTextureFileAsString() {
		return textureFile;
	}
//>>>>>>> e26976cb22a7187a0b221b95dd071e28d9070b8d
	// Private methods
	
}
