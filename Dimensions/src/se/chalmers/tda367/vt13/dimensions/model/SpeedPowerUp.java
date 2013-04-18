package se.chalmers.tda367.vt13.dimensions.model;

import java.io.Serializable;
import java.util.List;
import se.chalmers.tda367.vt13.dimensions.controller.SoundObserver;

/**
 * Class for the speed PowerUp. Increases
 * the players speed when used.
 * @author Carl Fredriksson
 */

public class SpeedPowerUp extends GameObject implements PowerUp,Serializable {


	// Instance variables
	private String soundFile;
	private String textureFile;
	private static final long serialVersionUID = 1L;

	// Public methods
	/**
	 * Constructor. Calls the super class constructor.
	 * @param position the position of the SpeedPowerUp
	 * @param size the size of the SpeedPowerUp
	 * @param speed the speed of the SpeedPowerUp
	 */
	public SpeedPowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed);
		soundFile = "sound/SpeedPowerUp.mp3";
		textureFile = "data/SpeedPowerUpImg.png";
	}
	
	@Override
	public void use(GameModel gm) {
		if (gm.getPlayer().getSpeed().getX() <= 2) {
			gm.getPlayer().getSpeed().setX(gm.getPlayer().getSpeed().getX() * 2);
		}
		playSound();
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
		
	}

	@Override
	public void playSound() {
		List<SoundObserver> observers = getObservers();
		for(SoundObserver s : observers){
			s.playSound(soundFile);
		}
	}

	@Override
	public String getSoundFileAsString() {
		return soundFile;
	}

	@Override
	public String getTextureFileAsString() {
		return textureFile;
	}
	
}
