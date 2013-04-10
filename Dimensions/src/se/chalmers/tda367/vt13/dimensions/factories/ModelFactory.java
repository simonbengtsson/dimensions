package se.chalmers.tda367.vt13.dimensions.factories;

import se.chalmers.tda367.vt13.dimensions.model.Model;
import se.chalmers.tda367.vt13.dimensions.model.levels.Level;

public class ModelFactory {
	
	public static Model getModel(){
		return new Model(new Level());
	}
}
