package se.chalmers.tda367.vt13.dimensions.controller.screens;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LevelFinishedScreen extends AbstractMenuScreen {

	private final TextButton nextLevel;

	public LevelFinishedScreen(final Dimensions game) {
		super(game);
		nextLevel = new TextButton("NextLevel", getButtonStyle());

		getTable().add(nextLevel);
		nextLevel.addListener(new ClickListener() {
			public void clicked(InputEvent e, float x, float y) {
				dispose();
				game.setScreen(game.getGameScreen());

			}
		});
		setStageInput();

	}

	@Override
	public void render(float delta) {
		super.render(delta);

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

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
		super.dispose();

	}

}
