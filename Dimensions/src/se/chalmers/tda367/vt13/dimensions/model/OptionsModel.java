package se.chalmers.tda367.vt13.dimensions.model;

import java.awt.Dimension;
import java.awt.Toolkit;

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
		
		Gdx.graphics.setDisplayMode(screenSize.width, screenSize.height,
				fullscreen);
		Gdx.graphics.setVSync(vSync);
	}
	
	public void resetToDefault(){
		jumpKey = Keys.UP;
		dashKey = Keys.DOWN;
		screenSize = new Dimension(1000, 500);
		fullscreen = false;
	}
	
	public DisplayMode[] getDisplayModes(){
		return Gdx.graphics.getDisplayModes();
	}
	
	public void setDisplayMode(DisplayMode d){
		newScreenSize.height = d.height;
		newScreenSize.width = d.width;
		newRefreshRate = d.refreshRate;
		newBitsPerPixel = d.bitsPerPixel;
		
	}
	
	public void setVSync(boolean s){
		newVSync = s;
	}

	public boolean getVSync(){
		return vSync;
	}
	
	public void setJumpKey(int key) {
		newJumpKey = key;
	}

	public int getJumpKey() {
		return jumpKey;
	}
	
	public int getDashKey(){
		return dashKey;
	}
	
	public void setDashKey(int key){
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
		// TODO implement
	}

	public void readFromFile() {
		// TODO implement
	}
}
