package se.chalmers.tda367.vt13.dimensions.controller.screens;

import se.chalmers.tda367.vt13.dimensions.components.MenuButton;
import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;
import se.chalmers.tda367.vt13.dimensions.model.LevelHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuScreen implements Screen {

	private Dimensions game;
	private Stage stage;
	private Table mainTable = new Table();

	public MenuScreen(final Dimensions game) {
		this.game = game;
		stage = new Stage();
		init();
	}

	private void init() {

		Texture bgTexture = new Texture(Gdx.files.internal("data/bg.jpg"));
		mainTable.setBackground(new TextureRegionDrawable(new TextureRegion(
				bgTexture)));
		mainTable.setFillParent(true);
		mainTable.debug();
		stage.addActor(mainTable);

		final MenuButton playButton = new MenuButton("data/play.png");
		playButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				playButton.setChecked(false);
				game.getGameScreen().nextLevel(
						LevelHandler.getInstance().getNextUnfinishedLevel());
				game.setScreen(game.getGameScreen());
			}
		});
		mainTable.add(playButton).expandX();
		Table sidebarTable = new Table();
		
		final Button levelSelectButton = new MenuButton("data/level_select.png");
		levelSelectButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				levelSelectButton.setChecked(false);
				game.setScreen(game.getLevelSelectScreen());
			}
		});
		
		final Button optionButton = new MenuButton("data/settings.png");
		optionButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				optionButton.setChecked(false);
				game.setScreen(game.getOptionScreen(game));
			}
		});
		
		final Button exitButton = new MenuButton("data/exit.png");
		exitButton.addListener(new ClickListener() {
			public void clicked(InputEvent e, float x, float y) {
				System.out.println("exit");
				System.exit(0);
			}
		});

		sidebarTable.add().width(200);
		sidebarTable.row();
		sidebarTable.add(levelSelectButton);
		sidebarTable.row();
		sidebarTable.add(optionButton);
		sidebarTable.row();
		sidebarTable.add(exitButton);
		
		mainTable.add(sidebarTable);
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
