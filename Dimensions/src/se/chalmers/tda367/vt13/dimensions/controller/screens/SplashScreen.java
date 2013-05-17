package se.chalmers.tda367.vt13.dimensions.controller.screens;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;

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
	SpriteBatch batch = new SpriteBatch();
	long createTime;

	public SplashScreen(Dimensions game) {
		this.game = game;
		splashTexture = new Texture(Gdx.files.internal("data/boat.png"));
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		//Assets.load();
		createTime = System.currentTimeMillis();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(splashTexture, 10, 10);
		batch.end();
		if ((System.currentTimeMillis() - createTime) > 1000) {
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
}
