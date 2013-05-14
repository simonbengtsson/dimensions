package se.chalmers.tda367.vt13.dimensions.util;

import se.chalmers.tda367.vt13.dimensions.view.GameView;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Class acting as an adapter from TiledMaps to the model.
 */
public class TiledMapHandler {
	
	private GameView view;
	
	public TiledMapHandler() {
		
	}
	
	public TiledMapHandler(GameView gameView) {
		this.view = gameView;
	}
	
	public boolean isCellGround(int x, int y) {
		return ((TiledMapTileLayer) view.getCurrentMap().getLayers().get(1))
				.getCell(x, y) != null;
	}
	
	public boolean isCellObstacle(int x, int y) {
		return ((TiledMapTileLayer) view.getCurrentMap().getLayers().get(2))
				.getCell(x, y) != null;
	}
	
	public void setGameView(GameView view) {
		this.view = view;
	}

	public TiledMap getMap(String mapString) {
		return new TmxMapLoader().load(mapString);
	}

}
