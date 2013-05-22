package se.chalmers.tda367.vt13.dimensions.model;

import static org.junit.Assert.*;

import org.junit.Test;



public class PlayerTest {

	
	@Test
	public void testEquals(){
		Player p = new Player();
		Player p2 = new Player();
		Player p3 = new Player(new Vector3(10, 10, 10), new Vector3(2, 2, 2), new Vector3(0.5f,
				0, 0), 1f, false);
		
		assertTrue(p!=p2);
		assertTrue(p.equals(p2));
		assertFalse(p.equals(p3));
		
		p2.setSpeed(new Vector3(0.5f,
				0, 0));
		assertFalse(p.equals(p2));
	}
//	@Test
//	public void testJump() {
//		Player p = new Player(new Vector3(10, 100, 0), new Vector3(50, 50, 0),
//				new Vector3(2, 2, 2), 15f, false);
//		p.jump();
//		assertEquals(2f, p.getSpeed().getY(), 0.1);
//		p.setIsGrounded(true);
//		p.jump();
//		assertEquals(17f, p.getSpeed().getY(), 0.1);
//		assertFalse(p.getIsGrounded());
//	}
//
//	@Test
//	public void testOnPlatform() {
//		NormalLevel lv = new NormalLevel();
//		Player player = new Player(new Vector3(10, 100, 0), new Vector3(50, 50,
//				0), new Vector3(2, 0, 0), 15f, true);
//		GameWorld world = new GameWorld(new TiledLevel("Test", null), null);
//		world.getPlayer().getIsGrounded();
//		assertTrue(world.getPlayer().getSpeed().getY() == 0);
//		assertTrue(world.getPlayer().getIsGrounded());
//	}
}
