package se.chalmers.tda367.vt13.dimensions.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MenuButton extends Button {

	public MenuButton(String imagePath) {
		super(getButtonStyle(imagePath));
	}

	private static ButtonStyle getButtonStyle(String imagePath) {
		Skin skin = new Skin();
		skin.add("play", new Texture(new Pixmap(Gdx.files.internal(imagePath))));
		ButtonStyle buttonStyle = new ButtonStyle();
		buttonStyle.up = skin.newDrawable("play", Color.GREEN);
		buttonStyle.down = skin.newDrawable("play", Color.BLUE);
		buttonStyle.checked = skin.newDrawable("play");
		buttonStyle.over = skin.newDrawable("play", Color.RED);
		skin.add("default", buttonStyle);
		return buttonStyle;
	}

}
