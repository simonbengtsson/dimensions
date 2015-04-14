package com.chalmers.dimensions.model;

/**
 * Class for handling PowerUp usage. Uses the Singleton pattern.
 */
public class NormalPowerUpHandler implements PowerUpHandler {

	private GameWorld world;
	private final float lowGravityModifier = 0.9f;
	private final float slowModifier = 0.5f;
	private final float speedModifier = 2f;

	public NormalPowerUpHandler(GameWorld world) {
		this.world = world;
	}

	@Override
	public void useCheckPointPowerUp() {
		world.placeCheckPoint();
	}

	@Override
	public void useDimensionChangePowerUp() {
		world.startDimensionTimer();
	}

	@Override
	public void useLowGravityPowerUp() {
		world.setGravity(world.getGravity() * lowGravityModifier);
	}

	@Override
	public void useSlowPowerUp() {
		Vector3 s = world.getPlayer().getSpeed();
		s.setX(s.getX() * slowModifier);
	}

	@Override
	public void useSpeedPowerUp() {
		if (world.getPlayer().getSpeed().getX() <= world.getPlayer()
				.getBaseXSpeed()) {
			world.getPlayer().getSpeed()
					.setX(world.getPlayer().getSpeed().getX() * speedModifier);
		}
	}

}
