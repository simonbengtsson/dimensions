package se.chalmers.tda367.vt13.dimensions.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChaserTest {
	private Chaser chaser1, chaser2;

	public void init() {
		chaser1 = new Chaser();
		chaser2 = chaser1.clone();
	}

	@Test
	public void cloneTest() {
		init();
		assertTrue(chaser1 != chaser2);

	}

	@Test
	public void equalsTest() {
		init();
		assertTrue(chaser1.equals(chaser2));
		Chaser chaser3 = new Chaser(new Vector3(8, 2, 0), new Vector3(2, 2, 2),
				new Vector3(0.4f, 0, 0), "data/PlayerImg.png", "");
		Chaser chaser4 = new Chaser(new Vector3(8, 2, 0), new Vector3(2, 2, 2),
				new Vector3(0.4f, 0, 0), "data/PlayerImg.png", "");

		assertTrue(chaser3 != chaser4);
		assertTrue(chaser3.equals(chaser4));
		assertTrue(chaser3.hashCode() == chaser4.hashCode());
		assertFalse(chaser3.equals(chaser1));

	}

}
