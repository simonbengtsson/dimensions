package se.chalmers.tda367.vt13.dimensions.controller.screens;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;

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

public class GameOverScreen implements Screen {

	private TextButton playagain;
	private TextButton menu;
	private Stage stage = new Stage();
	private Table mainTable = new Table();

	public GameOverScreen(final Dimensions game) {
		playagain = new TextButton("Play again", getButtonStyle());
		menu = new TextButton("Go to menu", getButtonStyle());

		playagain.addListener(new ClickListener() {
			public void clicked(InputEvent e, float x, float y) {
				dispose();
				game.setScreen(game.getGameScreen());

			}
		});

		menu.addListener(new ClickListener() {
			public void clicked(InputEvent e, float x, float y) {
				dispose();
				game.setScreen(game.getMenuScreen());

			}
		});

		mainTable.add(playagain);
		mainTable.row();
		mainTable.add(menu);
	}
	
	public TextButtonStyle getButtonStyle() {
		BitmapFont font = new BitmapFont();
		font.scale(2f);
		TextButtonStyle buttonStyle = new TextButtonStyle();
		buttonStyle.font = font;
		buttonStyle.fontColor = Color.WHITE;
		buttonStyle.overFontColor = Color.RED;
		buttonStyle.pressedOffsetY = 1f;
		buttonStyle.downFontColor = new Color(0.8f, 0.8f, 0.8f, 1f);
		return buttonStyle;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, false);
	}

	@Override
	public void show() {
		stage.addActor(mainTable);
		Gdx.input.setInputProcessor(stage);
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
