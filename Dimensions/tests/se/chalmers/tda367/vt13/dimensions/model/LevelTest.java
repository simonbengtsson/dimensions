package se.chalmers.tda367.vt13.dimensions.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.powerup.DimensionChangePowerUp;
import se.chalmers.tda367.vt13.dimensions.model.powerup.LowGravityPowerUp;

public class LevelTest {
	private Level example, dimensionChange;
	private LevelHandler l;

	public void init() {
		l = LevelHandler.getInstance();
		// Level named Example
		List<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects.add(new DimensionChangePowerUp(new Vector3(30, 4, 10),
				new Vector3(1, 1, 1), new Vector3()));
		List<GameObject> exampleList = new ArrayList<GameObject>();
		exampleList.add(new LowGravityPowerUp(new Vector3(15, 4, 10),
				new Vector3(1, 1, 1), new Vector3()));
		exampleList.add(new DimensionChangePowerUp(new Vector3(25, 4, 10),
				new Vector3(1, 1, 1), new Vector3()));
		this.example = new Level("Example", -0.05f, exampleList, Dimension.XY,
				"data/tiledMaps/levelXY.tmx", "data/tiledMaps/levelXZ.tmx", 205);
		l.registerLevel(example);
		// End of Example Level

		// Level named DimensionChange
		List<GameObject> dimensionChangeList = new ArrayList<GameObject>();
		dimensionChangeList.add(new DimensionChangePowerUp(new Vector3(30, 4,
				10), new Vector3(1, 1, 1), new Vector3()));
		dimensionChangeList.add(new DimensionChangePowerUp(new Vector3(50, 4,
				10), new Vector3(1, 1, 1), new Vector3()));
		this.dimensionChange = new Level("Dimension Change", -0.03f,
				dimensionChangeList, Dimension.XZ,
				"data/tiledMaps/levelXY.tmx", "data/tiledMaps/levelXZ.tmx", 150);
		l.registerLevel(dimensionChange);
	}

	@Test
	public void equalsAndCloneTest() {
		init();
		l.load();

		Level exampleclone = this.example.clone();
		assertTrue(exampleclone != example);
		assertTrue(exampleclone.equals(example));
		assertTrue(example.hashCode() == exampleclone.hashCode());
		assertFalse(example.equals(dimensionChange));
		assertFalse(example.hashCode() == dimensionChange.hashCode());
	}

}
