package se.chalmers.tda367.vt13.dimensions.controller.screens;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class CreditsScreen extends AbstractMenuScreen{

	public CreditsScreen(Dimensions game) {
		super(game);
		TextButton ke = new TextButton("Kim Egenvall",getButtonStyle());
		TextButton kk = new TextButton("Kim Kling",getButtonStyle());
		TextButton s = new TextButton("Carl Fredriksson",getButtonStyle());
		TextButton kf = new TextButton("Simon Bengtsson",getButtonStyle());
		mainTable.add(ke);
		mainTable.row();
		mainTable.add(kk);
		mainTable.row();
		mainTable.add(s);
		mainTable.row();
		mainTable.add(kf);
		
	}

	
}
