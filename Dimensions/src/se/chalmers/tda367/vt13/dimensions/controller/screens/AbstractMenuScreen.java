package se.chalmers.tda367.vt13.dimensions.controller.screens;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class AbstractMenuScreen implements Screen {

	private Dimensions game;
	private SpriteBatch batch;
	private BitmapFont font;
	private final Table table;
	private TextButtonStyle buttonStyle = new TextButtonStyle();
	private Stage stage;

	public AbstractMenuScreen(final Dimensions game) {
		this.game = game;
		batch = new SpriteBatch();
		font = new BitmapFont();
		table = new Table();
		stage = new Stage();
		buttonStyle.font = new BitmapFont();
		buttonStyle.fontColor = Color.WHITE;
		buttonStyle.overFontColor = Color.RED;
		buttonStyle.pressedOffsetY = 1f;
		buttonStyle.downFontColor = new Color(0.8f, 0.8f, 0.8f, 1f);
		stage = new Stage();
		table.setFillParent(true);

	}

	public void setStageInput() {
		stage.addActor(table);

		Gdx.input.setInputProcessor(stage);
	}

	public Stage getStage() {
		return this.stage;
	}

	public Table getTable() {
		return this.table;
	}

	public TextButtonStyle getButtonStyle() {
		return this.buttonStyle;
	}

	@Override
	public void render(float delta) {
		// Clear screen with white color
		Gdx.gl.glClearColor(0, 0, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		Table.drawDebug(stage);
		batch.begin();
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		// font.draw(batch, str, 380, 240);
		batch.end();

		/*
		 * if (Gdx.input.justTouched() || Gdx.input.isKeyPressed(Keys.UP)) {
		 * game.setScreen(game.getGameScreen()); }
		 */
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);

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

		table.clear();

	}

}
