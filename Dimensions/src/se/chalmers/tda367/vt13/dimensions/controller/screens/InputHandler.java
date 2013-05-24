package se.chalmers.tda367.vt13.dimensions.controller.screens;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class InputHandler extends InputAdapter {
	
	private GameScreen gameScreen;
	public InputHandler(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.SPACE || keycode == Keys.UP) {
			gameScreen.inputRecieved();
		}
		return false;
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		gameScreen.inputRecieved();
		return false;
	}
}