package se.chalmers.tda367.vt13.dimensions.model;

import static org.junit.Assert.*;

import org.junit.Test;

import se.chalmers.tda367.vt13.dimensions.model.levels.Level;

public class PlayerTest {

	@Test
	public void testJump() {
		Player p = new Player(new Vector3(10, 100, 0), new Vector3(50, 50, 0),
				new Vector3(2, 2, 2), 0.75f, 15f, false);
		p.jump();
		assertEquals(2f, p.getSpeed().getY(), 0.1);
		p.setIsGrounded(true);
		p.jump();
		assertEquals(17f, p.getSpeed().getY(), 0.1);
		assertFalse(p.getIsGrounded());
	}

//	@Test
//	public void testOnPlatform() {
//		Level lv = new Level("Level1");
//		Player player = new Player(new Vector3(10, 100, 0), new Vector3(50, 50,
//				0), new Vector3(2, 0, 0), 0.75f, 15f, true);
//		GameModel model = new GameModel(lv.getList(), player);
//		model.getPlayer().getIsGrounded();
//		assertTrue(model.getPlayer().getSpeed().getY() == 0);
//		assertTrue(model.getPlayer().getIsGrounded());
//	}

}
