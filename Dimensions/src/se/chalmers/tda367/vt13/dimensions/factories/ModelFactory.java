package se.chalmers.tda367.vt13.dimensions.factories;

import se.chalmers.tda367.vt13.dimensions.model.Model;
import se.chalmers.tda367.vt13.dimensions.model.NormalPlayer;
import se.chalmers.tda367.vt13.dimensions.model.Player;
import se.chalmers.tda367.vt13.dimensions.model.levels.Level;

public class ModelFactory {
	
	public static Model getModel(){
		Player p = new NormalPlayer();
		Model m = new Model(new Level(), p);
		p.setModel(m);
		return m;
	}
}
