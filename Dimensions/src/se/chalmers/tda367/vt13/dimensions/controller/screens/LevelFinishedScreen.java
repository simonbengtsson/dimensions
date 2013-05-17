package se.chalmers.tda367.vt13.dimensions.controller.screens;

import se.chalmers.tda367.vt13.dimensions.components.MenuButton;
import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;
import se.chalmers.tda367.vt13.dimensions.model.LevelHandler;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class LevelFinishedScreen extends AbstractMenuScreen {

	public LevelFinishedScreen(Dimensions game) {
		super(game);
		init();
	}
	
	private void init(){
		final MenuButton playButton = new MenuButton("data/play.png");
		playButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				playButton.setChecked(false);
				game.getGameScreen().nextLevel(
						LevelHandler.getInstance().getNextUnfinishedLevel());
				game.setScreen(game.getGameScreen());
			}
		});
		mainTable.add(playButton);		
	}
}
