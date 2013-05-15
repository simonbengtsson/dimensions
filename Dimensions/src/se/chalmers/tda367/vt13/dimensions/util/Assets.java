package se.chalmers.tda367.vt13.dimensions.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets extends AssetManager {
	
	private static AssetManager manager;
	public static Skin skin;
	public static Music music;
	public static Sound speedPowerUp;

	public static Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}
	
	public static void load () {
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("pack.atlas"));
		skin = new Skin(Gdx.files.internal("skin2.json"), atlas);
	}
	
	public AssetManager getAssetMangager(){
		return manager;
	}

	public static void playSound(Sound sound) {

	}
}