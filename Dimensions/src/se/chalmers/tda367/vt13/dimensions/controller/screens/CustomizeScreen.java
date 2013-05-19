package se.chalmers.tda367.vt13.dimensions.controller.screens;

import javax.swing.JTextField;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import se.chalmers.tda367.vt13.dimensions.components.MenuButton;
import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;

public class CustomizeScreen extends AbstractMenuScreen{
	private String charname = "";
	private String characterTexture = "texture1";
	public CustomizeScreen(Dimensions game) {
		super(game);
		init();
	}


	public void init(){
		TextButton name = new TextButton("Enter character name",getButtonStyle());
		name.setDisabled(true);
		name.clearListeners();
		TextFieldStyle style = new TextFieldStyle();
		style.focusedFontColor = Color.BLUE;
		style.messageFontColor = Color.RED;
		style.fontColor = Color.BLACK;
		//TextField nametext = new TextField(this.characterTexture, style);
		//nametext.setMessageText("Enter Character name");






		final MenuButton char1 = new MenuButton("data/level_select.png");
		char1.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				charname = char1.getName();
			}
		});


		final MenuButton char2 = new MenuButton("data/level_select.png");
		char2.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// Tell texturehandler that player should have this texture
				charname = char2.getName();
			}
		});


		TextButton back = new TextButton("Back", getButtonStyle());
		back.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new MainMenuScreen(game));
			}
		});
		
		TextButton save = new TextButton("Save",getButtonStyle());
		save.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// Do saving stuff
			}
		});
		
		mainTable.add(name).fill();
		mainTable.row();
		//mainTable.add(nametext);
		mainTable.row();
		mainTable.add(char1);
		mainTable.row();
		mainTable.add(char2);
		mainTable.row();
		mainTable.add(back).expandX();
		mainTable.add(save).expandX();

	}
}
