package se.chalmers.tda367.vt13.dimensions.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.State;
import se.chalmers.tda367.vt13.dimensions.model.powerup.DimensionChangePowerUp;
import se.chalmers.tda367.vt13.dimensions.util.TiledMapHandler;

public class ModelTests {
	private GameWorld w;
	private Level lev;
	private State TEST1;
	private State TEST2;
	private State TEST3;

	// @Test
	public void test() {
		fail("Not yet implemented");
	}

	public void create() {
		TiledMapHandler tiledMapHandler = new TiledMapHandler();
		CollisionHandler collisionHandler = new CollisionHandler(
				tiledMapHandler);
		LevelHandler l = LevelHandler.getInstance();

		l.load();

		List<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects.add(new DimensionChangePowerUp(new Vector3(20, 4, 10),
				new Vector3(1, 1, 1), new Vector3()));
		gameObjects.add(new DimensionChangePowerUp(new Vector3(30, 4, 10),
				new Vector3(1, 1, 1), new Vector3()));
		this.lev = new Level("Example", -0.05f, gameObjects, Dimension.XY,
				"data/tiledMaps/levelXY.tmx", "data/tiledMaps/levelXZ.tmx", 205);

		this.w = new GameWorld(this.lev, collisionHandler);
	}

	// @Test
	public void testDimensionChange() {
		create();

		// MŒste kommentera bort timern i Gameworld.swapDimension() fšr att det
		// ska fungera.

		assertTrue(w.getDimension() == Dimension.XY);
		w.swapDimension();
		// w.update();
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				assertTrue(w.getDimension() == Dimension.XZ);
			}
		}, 3500);
		// w.swapDimension();
		assertTrue(w.getDimension() == Dimension.XZ);
		w.swapDimension();
		w.swapDimension();
		assertTrue(w.getDimension() == Dimension.XZ);
	}

	@Test
	public void testGameOverOrCompleted() {
		create();

		assertFalse(w.isGameOver());
		w.getPlayer().setPosition(
				new Vector3(w.getLevel().getLength() + 1, 1, 1));
		assertTrue(w.isLevelFinished());

		w.getPlayer().setPosition(
				new Vector3(w.getLevel().getLength() - 1, 1, 1));
		assertFalse(w.isLevelFinished());

		w.getPlayer().setPosition(
				new Vector3(w.getLevel().getLength() + 1, -1, 1));
		assertTrue(w.isGameOver());
		assertTrue(w.isLevelFinished());

	}

	@Test
	public void testCheckpoint() {
		create();
		CheckPoint startCp = w.getCheckPoint();
		w.placeCheckPoint();
		CheckPoint newCp = w.getCheckPoint();
		assertTrue(startCp != newCp);

	}

	@Test
	public void testGravity() {
		create();
		assertTrue(w.getGravity() == w.getBaseGravity());
		w.setGravity(w.getGravity() + 0.1f);
		assertTrue(w.getGravity() != w.getBaseGravity());
		w.resetGravity();
		assertTrue(w.getGravity() == w.getBaseGravity());

	}

	@Test
	public void testListeners() {
		create();

		class Tests implements WorldListener {
			private State state;

			@Override
			public void worldChanged(State newWorldState) {
				this.state = newWorldState;
			}
		}

		List<WorldListener> list = w.getWorldListeners();
		assertTrue(list != null);
		int length = list.size();

		Tests t = new Tests();
		w.addWorldListener(t);
		assertTrue(w.getWorldListeners().size() > length);

		w.setCurrentState(TEST1);
		assertTrue(t.state == TEST1);
		w.setCurrentState(TEST2);
		w.setCurrentState(TEST3);
		assertTrue(t.state == TEST3);

	}

}
