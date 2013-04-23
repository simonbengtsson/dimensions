package se.chalmers.tda367.vt13.dimensions.controller;

// ----------------------------------------------------
// ----------------------------------------------------
// NOT YET USED 
//----------------------------------------------------
//----------------------------------------------------
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen {

	Dimensions game;
	Texture splashTexture;
	Sprite splashSprite;
	SpriteBatch batch;
	

	public SplashScreen(Dimensions game) {
		this.game = game;
		
//		splashTexture = new Texture("path");
//		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//		
//		splashSprite = new Sprite();
//		splashSprite.setOrigin(splashSprite.getWidth() / 2, splashSprite.getHeight() / 2);
//		splashSprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
//		
//		batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.justTouched()) {
			game.setScreen(game.getMainMenuScreen());
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		splashSprite.draw(batch);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
