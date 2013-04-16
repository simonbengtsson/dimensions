package se.chalmers.tda367.vt13.dimensions.model;

import static org.junit.Assert.*;

import org.junit.Test;

import se.chalmers.tda367.vt13.dimensions.model.levels.Level;

public class NormalPlayerTest {

	@Test
	public void testOnPlatform() {
		Level lv = new Level("Level1");
		Player player = new Player(new Vector3(10,100,0), new Vector3(50, 50, 0), new Vector3(2, 0, 0)
			, 0.75f, 15f, true);
		GameModel model = new GameModel(lv.getList(), player);
		model.getPlayer().getIsGrounded();
		assertTrue(model.getPlayer().getSpeed().getY() == 0);
		assertTrue(model.getPlayer().getIsGrounded());
	}

}
