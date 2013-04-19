package se.chalmers.tda367.vt13.dimensions;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

/*
 * Class for starting up the desktop version of Dimension
 */
public class DimensionsDesktop {
	
	public static void main(String[] args){
		new LwjglApplication(new Dimensions(), "Dimensions-Desktop", 1000, 500, false);
	}
	
}