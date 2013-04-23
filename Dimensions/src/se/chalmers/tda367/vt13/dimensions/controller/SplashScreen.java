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
	long createTime;

	public SplashScreen(Dimensions game) {
		this.game = game;

		// The picture to show on splashscreen
		splashTexture = new Texture(Gdx.files.internal("data/boat.png"));
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		batch = new SpriteBatch();

		createTime = System.currentTimeMillis();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(splashTexture, 10, 10);

		batch.end();

		// If splashscreen has been shown longer then 2s, tell master controller
		// that the splashscreen is done
		if ((System.currentTimeMillis() - createTime) > 2000) {
			game.splashScreenDone();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// new Thread(new LoadStuff()).start();
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
		batch.dispose();
		splashTexture.dispose();
		// batch.dispose();
	}

	// A private class for loading everything into the master controller.
	// Currently not in use
	private class LoadStuff implements Runnable {

		@Override
		public void run() {
			// game.loadGame();
		}

	}

}
