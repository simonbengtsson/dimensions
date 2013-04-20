package se.chalmers.tda367.vt13.dimensions.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreen implements Screen {

	Dimensions game;
	SpriteBatch batch;
	BitmapFont font;
	CharSequence str = "Game Over! Click to play again.";

	public GameOverScreen(Dimensions game) {
		this.game = game;
		batch = new SpriteBatch();
		font = new BitmapFont();
	}

	@Override
	public void render(float delta) {
		// Clear screen with white color
		Gdx.gl.glClearColor(0, 0, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		font.draw(batch, str, 380, 240);
		batch.end();

		if (Gdx.input.justTouched() || Gdx.input.isKeyPressed(Keys.UP)) {
			game.setScreen(game.getGameScreen());
		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

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
