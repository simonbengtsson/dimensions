package com.chalmers.dimensions.screens;

import com.chalmers.dimensions.model.LevelHandler;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.chalmers.dimensions.DimensionsGdxGame;

public class GameOverScreen extends AbstractMenuScreen {

	public GameOverScreen(final DimensionsGdxGame game) {
		super(game);
		init();
	}

	private void init() {
		TextButton playButton = new TextButton("Play again", getButtonStyle());
		playButton.addListener(new ClickListener() {
			public void clicked(InputEvent e, float x, float y) {
				game.setScreen(new GameScreen(game, LevelHandler.getInstance()
						.getLastPlayed()));
				dispose();

			}
		});
		mainTable.add(playButton);
		mainTable.row();

		TextButton menuButton = new TextButton("Go to menu", getButtonStyle());
		menuButton.addListener(new ClickListener() {
			public void clicked(InputEvent e, float x, float y) {
				setScreen(new MainMenuScreen(game));
			}
		});
		mainTable.add(menuButton);
	}
}
