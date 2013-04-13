package se.chalmers.tda367.vt13.dimensions.model;

import static org.junit.Assert.*;

import org.junit.Test;

import se.chalmers.tda367.vt13.dimensions.factories.ModelFactory;

public class NormalPlayerTest {

	@Test
	public void testOnPlatform() {
		Model m = ModelFactory.getModel();
		NormalPlayer np = new NormalPlayer(m);
		np.onPlatform();
		assertTrue(np.getVelocityY() == 0);
		assertTrue(!np.isFalling());
	}

}
