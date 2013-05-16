package se.chalmers.tda367.vt13.dimensions.controller.screens;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class OptionScreen extends AbstractMenuScreen {
//	private CheckBox soundCheckbox;
//	private CheckBoxStyle cbs = new CheckBoxStyle();
	private TextButton gomenu;
	
	
	public OptionScreen(final Dimensions game){
		super(game);
		this.gomenu = new TextButton("Go to menu",getButtonStyle());
		
		this.gomenu.addListener(new ClickListener(){
			public void clicked(InputEvent e, float x, float y){
				game.setScreen(game.getMenuScreen());
				
			}
		});
		/*
		cbs.font = new BitmapFont();
		cbs.checkedFontColor = Color.RED;
		
		this.soundCheckbox = new CheckBox("Test",cbs);
		this.soundCheckbox.addListener(new ClickListener(){
			public void clicked(InputEvent e, float x, float y){
				if(soundCheckbox.isChecked()){
					// Aktivera ljud
				}
				
				else{
					//inaktivera ljud
				}
			}
		});
		
		getTable().add(soundCheckbox);
		*/
		getTable().add(gomenu);
		setStageInput();
	
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
	}
	
	@Override
	public void show(){
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
	}

	

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		
		super.dispose();
		
	}

}
