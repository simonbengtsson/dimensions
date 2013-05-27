package se.chalmers.tda367.vt13.dimensions.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import se.chalmers.tda367.vt13.dimensions.model.powerup.DimensionChangePowerUp;
import se.chalmers.tda367.vt13.dimensions.model.powerup.LowGravityPowerUp;
import se.chalmers.tda367.vt13.dimensions.model.powerup.SpeedPowerUp;
import se.chalmers.tda367.vt13.dimensions.util.TiledMapHandler;

public class PowerUpTest {
	private GameWorld w;
	private Level lev;

	public void create() {
		TiledMapHandler tiledMapHandler = new TiledMapHandler();
		LevelHandler l = LevelHandler.getInstance();

		l.load();

		List<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects.add(new DimensionChangePowerUp(new Vector3(20, 4, 10),
				new Vector3(1, 1, 1), new Vector3()));
		gameObjects.add(new DimensionChangePowerUp(new Vector3(30, 4, 10),
				new Vector3(1, 1, 1), new Vector3()));
		this.lev = new Level("Example", -0.05f, gameObjects, Dimension.XY,
				"data/tiledMaps/levelXY.tmx", "data/tiledMaps/levelXZ.tmx", 205);

		this.w = new GameWorld(this.lev, tiledMapHandler);
	}

	@Test
	public void testEquals() {
		SpeedPowerUp sp = new SpeedPowerUp(new Vector3(30, 4, 10), new Vector3(1,
				1, 1), new Vector3());
		SpeedPowerUp sp2 = new SpeedPowerUp(new Vector3(30, 4, 10), new Vector3(
				1, 1, 1), new Vector3());
		LowGravityPowerUp sp3 = new LowGravityPowerUp(new Vector3(20, 4, 10), new Vector3(
				1, 1, 1), new Vector3());

		DimensionChangePowerUp dim = new DimensionChangePowerUp(new Vector3(30, 4, 10),
				new Vector3(1, 1, 1), new Vector3());

		assertTrue(sp != sp2);
		assertTrue(sp.equals(sp2));
		assertFalse(sp.equals(dim));
		assertFalse(sp3.equals(sp));
	}

	@Test
	public void testClone() {
		SpeedPowerUp sp = new SpeedPowerUp(new Vector3(30, 4, 10), new Vector3(1,
				1, 1), new Vector3());
		SpeedPowerUp sp2 = sp.clone();
		SpeedPowerUp sp3 = sp;

		assertTrue(sp3 == sp);
		assertTrue(sp2 != sp && sp2 != sp3);
		assertTrue(sp2.equals(sp) && sp2.equals(sp3));
		assertTrue(sp3.equals(sp));
	}

	@Test
	public void testUse() {
		create();
		

		DimensionChangePowerUp dcp = new DimensionChangePowerUp(new Vector3(30,
				4, 10), new Vector3(1, 1, 1), new Vector3());

		LowGravityPowerUp lgp = new LowGravityPowerUp(new Vector3(30, 4, 10),
				new Vector3(1, 1, 1), new Vector3());

		

		SpeedPowerUp speed = new SpeedPowerUp(new Vector3(30, 4, 10),
				new Vector3(1, 1, 1), new Vector3());
		PowerUpHandler powerUpHandler = new NormalPowerUpHandler(w);

	

		// dcp test. Expected outcome: Dimension changed in Gameworld caused by
		// w.swapDimension()
		// Dimension swapping w.swapDimension() is tested in ModelTests.java,
		// further info about
		// the requirements for this test to be done can be found there.

		dcp.use(powerUpHandler); // Note: Must meet requirements in
									// ModelTests.java -> testDimensionChange()

		// lgp test. Expected outcome: GameWorld Gravity will be higher before
		// use than after
		// Note: Since gravity is negative, this means preuse < postuse after
		// use

		float preuse = w.getGravity();
		lgp.use(powerUpHandler);
		float postuse = w.getGravity();
		assertTrue(preuse < postuse);

		

		// speed test: Expected outcome: Player speed is higer post use than pre
		// use
		float prespeeduse = w.getPlayer().getSpeed().getX();
		speed.use(powerUpHandler);
		float postspeeduse = w.getPlayer().getSpeed().getX();
		assertTrue(prespeeduse < postspeeduse);

	}

}
