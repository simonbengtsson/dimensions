package se.chalmers.tda367.vt13.dimensions;

import se.chalmers.tda367.vt13.dimensions.controller.GameController;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

/*
 * Class for starting up the desktop version of Dimension
 */
public class DimensionsDesktop {
	
	public static void main(String[] args){
		new LwjglApplication(new GameController(), "Dimensions-Desktop", 1000, 800, false);
	}
	
}
