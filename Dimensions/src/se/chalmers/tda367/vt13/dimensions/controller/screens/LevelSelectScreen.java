package se.chalmers.tda367.vt13.dimensions.controller.screens;

import java.util.Collection;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;
import se.chalmers.tda367.vt13.dimensions.model.LevelHandler;
import se.chalmers.tda367.vt13.dimensions.model.progresshandler.ProgressLevel;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LevelSelectScreen extends AbstractMenuScreen {

	public LevelSelectScreen(final Dimensions game) {
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
					+ pl.getLevel().getName(), getButtonStyle());
			levelButton.addListener(new ClickListener() {
				public void clicked(InputEvent e, float x, float y) {
					game.setScreen(new GameScreen(game, pl.getLevel()));
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
