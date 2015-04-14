package com.chalmers.dimensions.screens;

import java.util.Collection;

import com.chalmers.dimensions.DimensionsGdxGame;
import com.chalmers.dimensions.model.LevelHandler;
import com.chalmers.dimensions.model.ProgressLevel;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LevelSelectScreen extends AbstractMenuScreen {

	public LevelSelectScreen(final DimensionsGdxGame game) {
		super(game);
		init();
	}

	private void init() {
		Collection<ProgressLevel> levels = LevelHandler.getInstance()
				.getProgressLevels();

		for (final ProgressLevel pl : levels) {
			String prefix = "";
			if (pl.isCompleted()) {
				prefix = "Completed - ";
			}
			TextButton levelButton = new TextButton(prefix
					+ pl.getLevel(), getButtonStyle());
			levelButton.addListener(new ClickListener() {
				public void clicked(InputEvent e, float x, float y) {
					game.setScreen(new GameScreen(game, LevelHandler.getInstance().getLevel(pl.getLevel())));
					dispose();
				}
			});

			mainTable.add(levelButton);
			mainTable.row();
		}

		TextButton goBack = new TextButton("Back", getButtonStyle());
		goBack.addListener(new ClickListener() {
			public void clicked(InputEvent e, float x, float y) {
				game.setScreen(new MainMenuScreen(game));
			}
		});

		mainTable.row();
		mainTable.add(goBack);
	}
}
