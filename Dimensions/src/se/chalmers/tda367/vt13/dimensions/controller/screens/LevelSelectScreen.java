package se.chalmers.tda367.vt13.dimensions.controller.screens;

import java.util.List;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;
import se.chalmers.tda367.vt13.dimensions.model.Level;
import se.chalmers.tda367.vt13.dimensions.model.LevelHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LevelSelectScreen implements Screen {

	private Dimensions game;
	private Stage stage;
	private Table mainTable = new Table();
	private BitmapFont font;
	private TextButtonStyle buttonStyle = new TextButtonStyle();

	public LevelSelectScreen(final Dimensions game) {
		this.game = game;
		this.stage = new Stage();
		init();
		stage.addActor(mainTable);
	}

	private void init() {
		List<Level> levels = LevelHandler.getInstance().getLevels();

		for (final Level l : levels) {
			TextButton levelButton = new TextButton(l.getName(),
					getButtonStyle());
			levelButton.addListener(new ClickListener() {
				public void clicked(InputEvent e, float x, float y) {
					game.getGameScreen().nextLevel(l);
					game.setScreen(game.getGameScreen());
				}
			});

			mainTable.add(levelButton);
			mainTable.row();
		}

		TextButton goBack = new TextButton("Back", getButtonStyle());
		goBack.addListener(new ClickListener() {
			public void clicked(InputEvent e, float x, float y) {
				dispose();
				game.setScreen(new MainMenuScreen(game));
			}
		});

		mainTable.row();
		mainTable.add(goBack);
	}

	public TextButtonStyle getButtonStyle() {
		font = new BitmapFont();
		font.scale(2f);
		stage = new Stage();
		buttonStyle.font = font;
		buttonStyle.fontColor = Color.WHITE;
		buttonStyle.overFontColor = Color.RED;
		buttonStyle.pressedOffsetY = 1f;
		buttonStyle.downFontColor = new Color(0.8f, 0.8f, 0.8f, 1f);
		return this.buttonStyle;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, false);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
