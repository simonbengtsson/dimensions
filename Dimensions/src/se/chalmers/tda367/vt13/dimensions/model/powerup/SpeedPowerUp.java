package se.chalmers.tda367.vt13.dimensions.model.powerup;

import java.io.Serializable;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld;
import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.util.Assets;

/**
 * This powerup doubles the speed, if the players speed is equal or below 2
 * px/frame.
 * 
 * @author Carl Fredriksson
 */

public class SpeedPowerUp extends GameObject implements PowerUp, Serializable {

	private final float speedModifier = 2;
	private static final long serialVersionUID = 1L;

	/**
	 * @param position
	 * @param size
	 * @param speed
	 */
	public SpeedPowerUp(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed, Assets.SPEEDPOWERUP_IMAGE, Assets.SPEEDPOWERUP_SOUND);
	}

	@Override
	public void use(GameWorld gm) {
		if (gm.getPlayer().getSpeed().getX() <= gm.getPlayer().getBaseXSpeed()) {
			gm.getPlayer().getSpeed()
					.setX(gm.getPlayer().getSpeed().getX() * speedModifier);
		}
		playSound();
	}
	
	@Override
	public SpeedPowerUp clone() {
		return new SpeedPowerUp(getPosition().clone(), getSize().clone(),
				getSpeed().clone());
	}

	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		
		if(!(o instanceof SpeedPowerUp) || o == null || o.getClass() != this.getClass()){
			return false;
		}
		SpeedPowerUp p = (SpeedPowerUp)o;
		if(this.getPosition().equals(p.getPosition()) && 
			this.getSize().equals(p.getSize()) && 
			this.getSpeed().equals(p.getSpeed())){
			return true;
		}
		else{
		return false;
		}
	}
}
