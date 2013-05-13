package se.chalmers.tda367.vt13.dimensions.util;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class TileMapHandler {

	public static boolean isCellGround(GameWorld world, int x, int y) {
		return ((TiledMapTileLayer) world.getCurrentMap().getLayers().get(1))
				.getCell(x, y) != null;
	}
	
	public static boolean isCellObstacle(GameWorld world, int x, int y) {
		return ((TiledMapTileLayer) world.getCurrentMap().getLayers().get(2))
				.getCell(x, y) != null;
	}

}
