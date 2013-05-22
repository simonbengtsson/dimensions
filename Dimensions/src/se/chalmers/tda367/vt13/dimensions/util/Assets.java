package se.chalmers.tda367.vt13.dimensions.util;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	public static final String SPIKEOBSTACLE_IMAGE = "data/spikes.png";
	public static final String SPIKEOBSTACLE_SOUND = "";

	public static final String CHECKPOINTPOWERUP_IMAGE = "";
	public static final String CHECKPOINTPOWERUP_SOUND = "";
	public static final String DIMENSIONCHANGE_IMAGE = "data/SpeedPowerUpMini.png";
	public static final String DIMENSIONCHANGE_SOUND = "sound/SpeedPowerUp.mp3";
	public static final String SPEEDPOWERUP_IMAGE = "data/SpeedPowerUpImg.png";
	public static final String SPEEDPOWERUP_SOUND = "sound/SpeedPowerUp.mp3";
	public static final String LOWGRAVITYPOWERUP_IMAGE = "data/LowGravityPowerUpImg.png";
	public static final String LOWGRAVITYPOWERUP_SOUND = "sound/SpeedPowerUp.mp3";

	private static Map<String, Texture> textures = new HashMap<String, Texture>();
	private static Map<String, Music> music = new HashMap<String, Music>();
	private static Map<String, Sound> sounds = new HashMap<String, Sound>();
	private static Map<String, TextureRegion> regions = new HashMap<String, TextureRegion>();

	public static Texture getTexture(String file) {
		return textures.get(file);
	}

	public static Sound getSound(String file) {
		return sounds.get(file);
	}

	public static Music getMusic(String file) {
		return music.get(file);
	}

	public static TextureRegion getTextureRegion(String name) {
		return regions.get(name);
	}

	public static void registerTexture(String file) {
		if (!textures.containsKey(file) && !file.equals("")) {
			Texture t = new Texture(Gdx.files.internal(file));
			textures.put(file, t);
		}
	}

	public static void registerSound(String file) {
		if (!sounds.containsKey(file) && !file.equals("")) {
			Sound t = Gdx.audio.newSound(Gdx.files.internal(file));
			sounds.put(file, t);
		}
	}

	public static void registerMusic(String file) {
		if (!music.containsKey(file) && !file.equals("")) {
			Music m = Gdx.audio.newMusic(Gdx.files.internal(file));
			music.put(file, m);
		}
	}

	public static void registerTextureRegion(String name, TextureRegion t) {
		if (!regions.containsKey(name) && !regions.containsValue(t)) {
			regions.put(name, t);
		}
	}

	public static void playSound(String file) {
		sounds.get(file).play();
	}

	public static void loadAll() {

	}
}