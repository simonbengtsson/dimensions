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
	public static Music music;
	public static Sound speedPowerUp;
	
	public static final String SPIKEOBSTACLE_IMAGE = "data/spikes.png";
	public static final String SPIKEOBSTACLE_SOUND = "";

	public static final String SPEEDPOWERUP_IMAGE = "data/SpeedPowerUpImg.png";
	public static final String SPEEDPOWERUP_SOUND = "sound/SpeedPowerUp.mp3";
	public static final String LOWGRAVITYPOWERUP_IMAGE = "data/SpeedPowerUpImg.png";
	public static final String LOWGRAVITYPOWERUP_SOUND = "sound/SpeedPowerUp.mp3";
	
	public static final String LEVELFILE_EXTENSION = ".dat";
	public static final String LEVELFOLDER = "levels";

	public static Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}
	
	public static void load () {
	}
	
	public AssetManager getAssetMangager(){
		return manager;
	}

	public static void playSound(Sound sound) {

	}
}