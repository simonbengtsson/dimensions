package se.chalmers.tda367.vt13.dimensions.controller.screens;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class OptionScreen extends AbstractMenuScreen {
	
	public OptionScreen(final Dimensions game){
		super(game);
		TextButton backButton;
		backButton = new TextButton("Back", getButtonStyle());
		backButton.addListener(new ClickListener(){
			public void clicked(InputEvent e, float x, float y){
				game.setScreen(game.getMenuScreen());
				
			}
		});
		mainTable.add(backButton);	
	}
}
