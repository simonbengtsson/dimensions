package se.chalmers.tda367.vt13.dimensions.model;

import java.io.Serializable;

/**
 * Class describing a platform in the game.
 * 
 * @author Carl Fredriksson
 */
public class Platform extends GameObject implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor. Calls the super class constructor.
	 * 
	 * @param position
	 *            the position of the platform
	 * @param size
	 *            the size of the platform
	 * @param speed
	 *            the speed of the platform
	 */
	public Platform(Vector3 position, Vector3 size, Vector3 speed) {
		super(position, size, speed, "data/PlatformMini.png", "");
	}

	@Override
	public Platform clone() {
		return new Platform(getPosition().clone(), getSize().clone(),
				getSpeed().clone());
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!(o instanceof Platform) || o == null
				|| o.getClass() != this.getClass()) {
			return false;
		}
		Platform p = (Platform) o;
		if (this.getPosition().equals(p.getPosition())
				&& this.getSize().equals(p.getSize())
				&& this.getSpeed().equals(p.getSpeed())) {
			return true;
		} else {
			return false;
		}
	}

}