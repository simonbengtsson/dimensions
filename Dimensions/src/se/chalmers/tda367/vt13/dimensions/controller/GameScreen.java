package se.chalmers.tda367.vt13.dimensions.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.chalmers.tda367.vt13.dimensions.model.GameModel;
import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.Player;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.model.levels.Level3D;
import se.chalmers.tda367.vt13.dimensions.view.GameViewXY;
import se.chalmers.tda367.vt13.dimensions.view.GameViewXZ;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;


/**
 * Game controller.
 * 
 * @author Carl Fredriksson
 */
public class GameScreen implements Screen, SoundObserver {
	static final int DIMENSION_XY = 0;
	static final int DIMENSION_XZ = 1;
	private int activeDimension;

	GameModel model;
	GameViewXY viewXY;
	GameViewXZ viewXZ;
	List<GameObject> ls;
	Map<String, Sound> files;
	Dimensions game;

	public GameScreen(Dimensions game) {
		this.game = game;
		Level3D lv = new Level3D("Level3D", null);
		ls = lv.getList();
		Player player = new Player(new Vector3(10, 150, 0), new Vector3(50, 50,
				50), new Vector3(4, 0, 0), -0.75f, 15f, false);
		loadSoundFiles();
		model = new GameModel(ls, player);
		viewXY = new GameViewXY(model);
		viewXZ = new GameViewXZ(model);
		activeDimension = DIMENSION_XZ;
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void render(float delta) {
		if (model.getPlayer().isGameOver()) {
			game.newGame();
			game.setScreen(new GameOverScreen(game));
		}
		getInput();
		model.updateModel();
		viewXY.draw();
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

	private void loadSoundFiles() {
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
	
	public int getDimension(){
		return activeDimension;
	}
	
	public void setDimension(int newDimension){
		activeDimension = newDimension;
	}

	@Override
	public void show() {}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}

}
