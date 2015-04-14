package com.chalmers.dimensions.screens;

import com.chalmers.dimensions.DimensionsGdxGame;
import com.chalmers.dimensions.util.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AbstractMenuScreen implements Screen {

	protected DimensionsGdxGame game;
	protected Stage mainStage = new Stage();
	protected Table mainTable = new Table();
	private TextButtonStyle textButtonStyle = new TextButtonStyle();

	public AbstractMenuScreen(DimensionsGdxGame game) {
		this.game = game;
		initTextButtonStyle();
		initMainStage();
	}

	private void initMainStage() {
		mainTable.setBackground(new TextureRegionDrawable(Assets
				.getTextureRegion("bg")));
		mainTable.setFillParent(true);
		mainTable.debug();
		mainStage.addActor(mainTable);
	}

	private void initTextButtonStyle() {
		textButtonStyle.font = Assets.getFontImpact50();
		textButtonStyle.downFontColor = Color.RED;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mainStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		mainStage.draw();
	}

	public void setScreen(Screen screen) {
		game.setScreen(screen);
	}

	public Stage getStage() {
		return this.mainStage;
	}

	protected TextButtonStyle getButtonStyle() {
		return textButtonStyle;
	}

	@Override
	public void resize(int width, int height) {
		mainStage.getViewport().update(width, height);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(mainStage);
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		mainStage.dispose();
	}

}
