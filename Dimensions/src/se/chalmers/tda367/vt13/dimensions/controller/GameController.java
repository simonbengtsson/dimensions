package se.chalmers.tda367.vt13.dimensions.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import se.chalmers.tda367.vt13.dimensions.model.*;
import se.chalmers.tda367.vt13.dimensions.model.levels.*;
import se.chalmers.tda367.vt13.dimensions.view.*;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;

//import org.lwjgl.opengl.Display;

/**
 * Game controller.
 * @author Carl Fredriksson
 */
public class GameController implements ApplicationListener, SoundObserver {

	// Instance variables
	GameModel model;
	GameView view;
	List<GameObject> ls;
	Map<String, Sound> files;

	// Public methods
	@Override
	public void create() {
		
		// Reads a level, either by creating one or read from a file.

		Level lv = new Level("Level1");
		ls = lv.getList();

		// ReadLevel rl = new ReadLevel();
		// ls = rl.readLevelName("Level2"+".dat");

		// Creates a player
		Player player = new Player(new Vector3(10, 150, 0), new Vector3(50, 50,0),
				new Vector3(2, 0, 0), -0.75f, 15f, false);

		// Load all soundfiles & add Controller as observer
		//initalizeKim();
		
		model = new GameModel(ls, player);
		view = new GameView(model);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// Display.sync(200);
		//checkCollisions();
		getInput();
		model.updateModel();
		view.draw();
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
		// TODO Auto-generated method stub

	}

	// Private methods
	/**
	 * Get input from the user, do different stuff depending on what input was
	 * given.
	 */
	private void getInput() {
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			model.getPlayer().jump();
		}
	}

	/**
	 * Pretty obvious :)
	 * 
	 * @return
	 */
	public GameModel getGameModel() {
		return model;
	}

	@Override
	public void playSound(String s) {
		files.get(s).play();
	}
	
	private void initalizeKim(){
		files = new HashMap<String, Sound>();
		for (GameObject g : ls) {
			g.addObserver(this);
			String file = g.getSoundFileAsString();

			if (!files.containsKey(file) && !file.equals("")) {
				Sound sound = Gdx.audio.newSound(Gdx.files.internal(file));
				files.put(file, sound);
			}
		}
	}

}
