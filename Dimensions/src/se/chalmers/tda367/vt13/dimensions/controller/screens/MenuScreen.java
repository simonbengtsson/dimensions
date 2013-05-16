package se.chalmers.tda367.vt13.dimensions.controller.screens;

import se.chalmers.tda367.vt13.dimensions.components.PlayButton;
import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;
import se.chalmers.tda367.vt13.dimensions.model.LevelHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuScreen implements Screen {

	private Dimensions game;
	private Stage stage;
	private SpriteBatch batch;
	private Table table = new Table();

	public MenuScreen(final Dimensions game) {
		this.game = game;
		stage = new Stage();
		init();
	}

	private void init() {

		Texture bgTexture = new Texture(Gdx.files.internal("data/bg.jpg"));
		table.setBackground(new TextureRegionDrawable(new TextureRegion(
				bgTexture)));
		table.setFillParent(true);
		table.debug();
		stage.addActor(table);

		final PlayButton playButton = new PlayButton();
		playButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				dispose();
				game.getGameScreen().nextLevel(
						LevelHandler.getInstance().getNextUnfinishedLevel());
				game.setScreen(game.getGameScreen());
			}
		});
		Table newTable = new Table();
		newTable.debug();
		table.add(playButton).expandX();

		ButtonStyle optionButtonStyle = new ButtonStyle();
		optionButtonStyle.up = new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("data/settings.png")), 128, 128));
		final Button optionButton = new Button(optionButtonStyle);
		playButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(game.getGameScreen());
			}
		});

		TextButtonStyle buttonStyle = new TextButtonStyle();
		buttonStyle.font = new BitmapFont();
		buttonStyle.fontColor = Color.WHITE;
		buttonStyle.overFontColor = Color.RED;
		buttonStyle.pressedOffsetY = 1f;
		buttonStyle.downFontColor = new Color(0.8f, 0.8f, 0.8f, 1f);

		newTable.add(new TextButton("Select level", buttonStyle)).width(200);
		newTable.row();
		newTable.add(optionButton).bottom();
		newTable.row();
		newTable.add(new TextButton("Exit", buttonStyle));
		table.add(newTable);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		// Table.drawDebug(stage);
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
		stage.dispose();
	}

}
