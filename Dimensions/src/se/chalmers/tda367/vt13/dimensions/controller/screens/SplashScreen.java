package se.chalmers.tda367.vt13.dimensions.controller.screens;

import java.util.Timer;
import java.util.TimerTask;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SplashScreen implements Screen{
	private Table mainTable = new Table();
	private Stage mainStage = new Stage();
	private Dimensions game;

	public SplashScreen(final Dimensions game) {
		this.game = game;
		initMainStage();
		Timer t = new Timer();
		t.schedule(new TimerTask(){

			@Override
			public void run() {
				game.setScreen(game.getMenuScreen());
			}
			
		}, 1000);
	}
	
	private void initMainStage(){
		Texture bgTexture = new Texture(Gdx.files.internal("data/xp.jpg"));
		mainTable.setBackground(new TextureRegionDrawable(new TextureRegion(
				bgTexture)));
		mainTable.setFillParent(true);
		mainStage.addActor(mainTable);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		mainStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		mainStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		mainStage.setViewport(width, height, false);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(mainStage);
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
	}
}
