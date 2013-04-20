package se.chalmers.tda367.vt13.dimensions.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.chalmers.tda367.vt13.dimensions.model.GameModel;
import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.Player;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.model.levels.RandomLevel;
import se.chalmers.tda367.vt13.dimensions.view.GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;

//import org.lwjgl.opengl.Display;

/**
 * Game controller.
 * 
 * @author Carl Fredriksson
 */
public class GameScreen implements Screen, SoundObserver {

	GameModel model;
	GameView view;
	List<GameObject> ls;
	Map<String, Sound> files;
	Dimensions game;

	public GameScreen(Dimensions game) {
		this.game = game;
		// Reads a level, either by creating one or read from a file.
		RandomLevel lv = new RandomLevel("RandomLevel2",null);
		ls = lv.getList();

		//ReadLevel rl = new ReadLevel();
		//ls = rl.readLevelName("RandomLevel1"+".dat");

		Player player = new Player(new Vector3(10, 150, 0), new Vector3(50, 50,
				0), new Vector3(4, 0, 0), -0.75f, 15f, false);

		// Load all soundfiles & add Controller as observer
		loadSoundFiles();
		
		model = new GameModel(ls, player);
		view = new GameView(model);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		
		if(view.isGameOver()){
			game.newGame();
			game.setScreen(game.getGameOverScreen());
		}
		getInput();
		model.updateModel();
		view.draw();
	}

	/**
	 * Get input from the user, do different stuff depending on what input was
	 * given.
	 */
	private void getInput() {
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			model.getPlayer().jump();
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			model.getPlayer().dash();
		}
		
		if (Gdx.input.isTouched()) {
			model.getPlayer().jump();
		}
	}

	public GameModel getGameModel() {
		return model;
	}

	@Override
	public void playSound(String s) {
		files.get(s).play(0.5f);
	}
	
	private void loadSoundFiles(){
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
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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
	}

}
