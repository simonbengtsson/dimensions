package se.chalmers.tda367.vt13.dimensions.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ParallaxLayer {

	private TextureRegion region;
	private float xRatio;

	public ParallaxLayer(TextureRegion region, float xRatio) {
		super();
		this.region = region;
		this.xRatio = xRatio;

	}

	public TextureRegion getRegion() {
		return region;
	}

	public float getxRatio() {
		return xRatio;
	}

	public void render(float xPos, float yPos, float width, float height,
			SpriteBatch sprite) {
		sprite.draw(region, xPos, yPos, width, height);
	}
}
