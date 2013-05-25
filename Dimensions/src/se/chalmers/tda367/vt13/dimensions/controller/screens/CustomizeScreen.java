package se.chalmers.tda367.vt13.dimensions.controller.screens;

import se.chalmers.tda367.vt13.dimensions.components.MenuButton;
import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;
import se.chalmers.tda367.vt13.dimensions.model.LevelHandler;
import se.chalmers.tda367.vt13.dimensions.util.Assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


/** Class for customizing player.
 * Skins, character name etc for further use. 
 * 
 * @author kimegenvall
 *
 */
public class CustomizeScreen extends AbstractMenuScreen{
//	private String charname = "";
//	private String characterTexture = "texture1";
	private GameScreen g;
	public CustomizeScreen(Dimensions game) {
		super(game);
		init();
	}


	public void init(){
		final TextButton name = new TextButton("Choose your hero",getButtonStyle());
		name.setDisabled(true);
		name.clearListeners();
		TextFieldStyle style = new TextFieldStyle();
		style.focusedFontColor = Color.WHITE;
		style.messageFontColor = Color.YELLOW;
		style.fontColor = Color.BLACK;
		BitmapFont font = Assets.getFontStandard();
		style.font = font;
		final TextField nametext = new TextField("", style);
		nametext.setMessageText("Enter Character name");



		nametext.setTextFieldListener(new TextFieldListener(){

			@Override
			public void keyTyped(TextField textField, char key) {
				name.setText(nametext.getText());
				//charname = nametext.getText();
				
			}
			
		});



		final MenuButton char1 = new MenuButton("data/level_select.png");
		char1.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
//				characterTexture = "level_select";
//				System.out.println(characterTexture);
				// TODO What's this?
			}
		});


		final MenuButton char2 = new MenuButton("data/level_select.png");
		char2.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
//				characterTexture = "PlayerImg";
//				System.out.println(characterTexture);
//				//TODO Debug?
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
				g =new GameScreen(game, LevelHandler.getInstance().getNextUnfinishedLevel());
				// Does not work. Nullpointer
				//g.getGameModel().getPlayer().setImagePath(characterTexture);
			}
		});
		
		mainTable.add(name).center();
		mainTable.row();
		//mainTable.add(nametext).center();
		mainTable.row();
		mainTable.add(char1).left();
		mainTable.add(char2).right();
		mainTable.row();
		mainTable.add(back).left();
		mainTable.add(save).right();

	}
}
