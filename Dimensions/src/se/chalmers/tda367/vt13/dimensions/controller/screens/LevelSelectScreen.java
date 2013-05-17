package se.chalmers.tda367.vt13.dimensions.controller.screens;

import java.util.List;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;
import se.chalmers.tda367.vt13.dimensions.model.Level;
import se.chalmers.tda367.vt13.dimensions.model.LevelHandler;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LevelSelectScreen extends AbstractMenuScreen {
	
	public LevelSelectScreen(final Dimensions game) {
		super(game);
		init();
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
				game.setScreen(game.getMenuScreen());
			}
		});

		mainTable.row();
		mainTable.add(goBack);
	}
}
