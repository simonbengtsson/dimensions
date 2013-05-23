package se.chalmers.tda367.vt13.dimensions.util;

import se.chalmers.tda367.vt13.dimensions.model.MapHandler;
import se.chalmers.tda367.vt13.dimensions.view.GameView;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Class acting as an adapter from TiledMaps to the model.
 */
public class TiledMapHandler implements MapHandler {
	
	private GameView view;
	private final int GROUND_LAYER = 1;
	private final int OBSTACLE_LAYER = 2;
	
	public TiledMapHandler() {
		
	}
	
	public TiledMapHandler(GameView gameView) {
		this.view = gameView;
	}
	
	@Override
	public boolean isCellGround(int x, int y) {
		return ((TiledMapTileLayer) view.getCurrentMap().getLayers().get(GROUND_LAYER))
				.getCell(x, y) != null;
	}
	
	@Override
	public boolean isCellObstacle(int x, int y) {
		return ((TiledMapTileLayer) view.getCurrentMap().getLayers().get(OBSTACLE_LAYER))
				.getCell(x, y) != null;
	}
	
	public void setGameView(GameView view) {
		this.view = view;
	}

	public TiledMap getMap(String mapString) {
		System.out.println(mapString);
		return new TmxMapLoader().load(mapString);
	}

}
