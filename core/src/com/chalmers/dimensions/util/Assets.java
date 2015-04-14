package com.chalmers.dimensions.util;

import java.util.HashMap;
import java.util.Map;

import com.chalmers.dimensions.view.MenuButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Assets {

	public static final String SPIKEOBSTACLE_IMAGE = "data/spikes.png";
	public static final String SPIKEOBSTACLE_SOUND = "";

	public static final String PLAYER_MINI_TEXTURE = "data/player-mini.png";
	public static final String CHASER_TEXTURE = "data/agent.png";
	public static final String PLAYERXZ_TEXTURE = "data/player-xy.png";


	public static final String BG_IMAGE = "data/bg.jpg";

	public static final String CHECKPOINTPOWERUP_IMAGE = "";
	public static final String CHECKPOINTPOWERUP_SOUND = "";
	public static final String DIMENSIONCHANGE_IMAGE = "data/DimensionChangePowerUpImg.png";
	public static final String DIMENSIONCHANGE_SOUND = "sound/SpeedPowerUp.mp3";
	public static final String SPEEDPOWERUP_IMAGE = "data/SpeedPowerUpImg.png";
	public static final String SPEEDPOWERUP_SOUND = "sound/SpeedPowerUp.mp3";
	public static final String LOWGRAVITYPOWERUP_IMAGE = "data/LowGravityPowerUpImg.png";
	public static final String LOWGRAVITYPOWERUP_SOUND = "sound/SpeedPowerUp.mp3";

	private static final BitmapFont fontImpact50 = new BitmapFont(
			Gdx.files.internal("impact50.fnt"), false);
	private static final BitmapFont fontStandard = new BitmapFont();

	private static Map<String, Texture> textures = new HashMap<String, Texture>();
	private static Map<String, Music> music = new HashMap<String, Music>();
	private static Map<String, Sound> sounds = new HashMap<String, Sound>();
	private static Map<String, TextureRegion> regions = new HashMap<String, TextureRegion>();
	private static Map<String, TiledMap> tiledMaps = new HashMap<String, TiledMap>();
	private static Map<String, MenuButton> menuButtons = new HashMap<String, MenuButton>();

	public static Texture getTexture(String file) {
		Texture t = textures.get(file);
		if (t == null) {
			registerTexture(file);
			t = textures.get(file);
		}
		return t;
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

	public static TiledMap getTiledMap(String file) {
		TiledMap t = tiledMaps.get(file);
		if (t == null) {
			registerTiledMap(file);
			t = tiledMaps.get(file);
		}
		return t;
	}

	public static MenuButton getMenuButton(String file) {
		MenuButton m = menuButtons.get(file);
		if (m == null) {
			registerMenuButton(file);
			m = menuButtons.get(file);
		}
		return m;
	}

	public static void registerMenuButton(String file) {
		if (!menuButtons.containsKey(file) && !file.equals("")) {
			MenuButton m = new MenuButton(file);
			menuButtons.put(file, m);
		}
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

	public static void registerTiledMap(String file) {
		if (!tiledMaps.containsKey(file) && !file.equals("")) {
			tiledMaps.put(file, new TmxMapLoader().load(file));
		}
	}

	public static void playSound(String file) {
		sounds.get(file).play();
	}

	public static BitmapFont getFontImpact50() {
		return fontImpact50;
	}

	public static BitmapFont getFontStandard() {
		return fontStandard;
	}

	public static void loadAllAudioVisual() {
		registerTexture(PLAYER_MINI_TEXTURE);
		registerTexture(CHASER_TEXTURE);
		registerTexture(BG_IMAGE);
		registerTextureRegion("bg",
				new TextureRegion(Assets.getTexture(Assets.BG_IMAGE)));
		registerTexture("data/agent.png");
		registerTexture("data/play.png");
		registerTexture("data/level_select.png");
		registerTexture("data/settings.png");
		registerTexture("data/exit.png");
		registerTexture("data/xp.jpg");

	}
}