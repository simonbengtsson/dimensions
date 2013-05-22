package se.chalmers.tda367.vt13.dimensions.controller.screens;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;
import se.chalmers.tda367.vt13.dimensions.model.LevelHandler;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameOverScreen extends AbstractMenuScreen {

	public GameOverScreen(final Dimensions game) {
		super(game);
		init();
	}
	
	private void init(){
		TextButton playButton = new TextButton("Play again", getButtonStyle());
		playButton.addListener(new ClickListener() {
			public void clicked(InputEvent e, float x, float y) {
				game.setScreen(new GameScreen(game, LevelHandler.getInstance().getLastPlayed()));
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
