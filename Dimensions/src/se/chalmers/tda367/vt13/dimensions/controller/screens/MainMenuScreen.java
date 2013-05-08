package se.chalmers.tda367.vt13.dimensions.controller.screens;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuScreen extends AbstractMenuScreen {

	private final TextButton start;
	private final TextButton option;
	private final TextButton exit;

	public MainMenuScreen(final Dimensions game) {
		super(game);
		start = new TextButton("Start game", getButtonStyle());
		option = new TextButton("Options", getButtonStyle());
		exit = new TextButton("Exit game", getButtonStyle());

		getTable().add(start);
		start.addListener(new ClickListener() {
			public void clicked(InputEvent e, float x, float y) {
				dispose();

				game.setScreen(game.getGameScreen());

			}
		});

		option.addListener(new ClickListener() {
			public void clicked(InputEvent e, float x, float y) {
				dispose();
				game.setScreen(new OptionScreen(game));

			}
		});

		exit.addListener(new ClickListener() {
			public void clicked(InputEvent e, float x, float y) {

				System.exit(0);

			}
		});

		getTable().row();
		getTable().add(option);
		getTable().row();
		getTable().add(exit);
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
