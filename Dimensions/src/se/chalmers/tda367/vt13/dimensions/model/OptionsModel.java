package se.chalmers.tda367.vt13.dimensions.model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Input.Keys;

public class OptionsModel {
	private static OptionsModel instance;

	private boolean fullscreen;
	private boolean newFullscreen;
	private Dimension screenSize;
	private Dimension newScreenSize;
	private int refreshRate;
	private int newRefreshRate;
	private int bitsPerPixel;
	private int newBitsPerPixel;
	private boolean vSync;
	private boolean newVSync;
	private int jumpKey;
	private int newJumpKey;
	private int dashKey;
	private int newDashKey;

	// Required methods.

	private OptionsModel() {

	}

	/**
	 * Implements Singleton pattern, only one instance is allowed.
	 * 
	 * @return
	 */
	public static synchronized OptionsModel getInstance() {
		if (instance == null) {
			instance = new OptionsModel();
		}
		return instance;
	}

	/**
	 * Prevents the class from being cloned.
	 */
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public String toString() {
		return "fullscreen:" + fullscreen + " screenSize.width:"
				+ screenSize.width + " screenSize.height:" + screenSize.height
				+ " refreshRate:" + refreshRate + " bitsPerPixel:"
				+ bitsPerPixel + " vSync:" + vSync + " jumpKey:" + jumpKey
				+ " dashKey:" + dashKey;
	}

	// Usefull methods.

	/**
	 * Gets the monitor resolution and applies it to the game. Enables
	 * fullscreen.
	 */
	public void setFullscreenSmart() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		fullscreen = true;
	}

	/**
	 * Applys all settings. Every setting can be saved in the model, but isnt
	 * always applied by default.
	 */
	public void applySettings() {
		fullscreen = newFullscreen;
		screenSize = new Dimension(newScreenSize);
		refreshRate = newRefreshRate;
		bitsPerPixel = newBitsPerPixel;
		vSync = newVSync;
		jumpKey = newJumpKey;
		dashKey = newDashKey;

		// Gdx.graphics.setDisplayMode(screenSize.width, screenSize.height,
		// fullscreen);
		// Gdx.graphics.setVSync(vSync);
	}

	public void resetToDefault() {
		newJumpKey = Keys.UP;
		newDashKey = Keys.DOWN;
		newScreenSize = new Dimension(1000, 500);
		newFullscreen = false;
		newRefreshRate = 120;
		newBitsPerPixel = 10;
		newVSync = false;
	}

	public DisplayMode[] getDisplayModes() {
		return Gdx.graphics.getDisplayModes();
	}

	public void setDisplayMode(DisplayMode d) {
		newScreenSize.height = d.height;
		newScreenSize.width = d.width;
		newRefreshRate = d.refreshRate;
		newBitsPerPixel = d.bitsPerPixel;

	}

	public void setVSync(boolean s) {
		newVSync = s;
	}

	public boolean getVSync() {
		return vSync;
	}

	public void setJumpKey(int key) {
		newJumpKey = key;
	}

	public int getJumpKey() {
		return jumpKey;
	}

	public int getDashKey() {
		return dashKey;
	}

	public void setDashKey(int key) {
		newDashKey = key;
	}

	public void setScreenSize(int width, int height) {
		newScreenSize.width = width;
		newScreenSize.height = height;
	}

	public void setFullscreen(boolean fullscreen) {
		newFullscreen = fullscreen;
	}

	public boolean isFullscreen() {
		return fullscreen;
	}

	// File handling

	public void saveToFile() {
		String content = toFileString();
		try {
			
			File file = new File("dimensions.settings");

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readFromFile() {
		Scanner in = null;
		try {
			in = new Scanner(new FileReader("dimensions.settings"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String settings = in.next();
		String[] s = settings.split("::");
		initilizeSettings(s);
	}

	private String toFileString() {
		String s = fullscreen + "::" + screenSize.width + "::"
				+ screenSize.height + "::" + refreshRate + "::" + bitsPerPixel
				+ "::" + vSync + "::" + jumpKey + "::" + dashKey;
		return s;
	}

	private void initilizeSettings(String[] s) {
		newFullscreen = Boolean.parseBoolean(s[0]);
		newScreenSize = new Dimension(Integer.parseInt(s[1]),
				Integer.parseInt(s[2]));
		newRefreshRate = Integer.parseInt(s[3]);
		newBitsPerPixel = Integer.parseInt(s[4]);
		newVSync = Boolean.parseBoolean(s[5]);
		newJumpKey = Integer.parseInt(s[6]);
		newDashKey = Integer.parseInt(s[7]);
	}

}
