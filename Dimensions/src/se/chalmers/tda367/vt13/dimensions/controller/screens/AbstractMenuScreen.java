package se.chalmers.tda367.vt13.dimensions.controller.screens;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class AbstractMenuScreen implements Screen {

	protected Dimensions game;
	protected Stage mainStage = new Stage();
	protected Table mainTable = new Table();
	private TextButtonStyle textButtonStyle = new TextButtonStyle();

	public AbstractMenuScreen(Dimensions game) {
		this.game = game;
		initTextButtonStyle();
		mainStage.addActor(mainTable);
	}

	private void initTextButtonStyle() {
		BitmapFont font = new BitmapFont();
		font.scale(2f);
		textButtonStyle.font = font;
		textButtonStyle.fontColor = Color.WHITE;
		textButtonStyle.overFontColor = Color.RED;
		textButtonStyle.downFontColor = new Color(0.8f, 0.8f, 0.8f, 1f);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		mainStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		mainStage.draw();
	}

	protected TextButtonStyle getButtonStyle() {
		return textButtonStyle;
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
		// TODO Auto-generated method stub

	}

}
