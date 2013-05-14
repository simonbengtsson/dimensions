package se.chalmers.tda367.vt13.dimensions.controller.screens;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;
import se.chalmers.tda367.vt13.dimensions.model.levels.LevelHandler;

public class NewGameScreen extends AbstractMenuScreen {

	public NewGameScreen(final Dimensions game) {
		super(game);
		List<String> levels = LevelHandler.getInstance().getLevelsAsStrings();
		
		for (String s : levels) {
			TextButton level = new TextButton(s, getButtonStyle());
			level.addListener(new ClickListener() {
				public void clicked(InputEvent e, float x, float y) {
					//dispose();
					// Link to a new game method and provide the string for the
					// level

				}
			});
			
			getTable().add(level);
			getTable().row();
		}
		
		TextButton goBack = new TextButton("Back", getButtonStyle());
		goBack.addListener(new ClickListener(){
			public void clicked(InputEvent e, float x, float y){
				dispose();
				game.setScreen(new MainMenuScreen(game));
			}
		});
		
		getTable().row();
		getTable().add(goBack);
		
		setStageInput();

	}

}
