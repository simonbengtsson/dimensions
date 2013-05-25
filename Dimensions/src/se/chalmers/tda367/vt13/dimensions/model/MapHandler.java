package se.chalmers.tda367.vt13.dimensions.model;

/**
 * Interface for adapting framework maps, such as LibGDX TiledMaps,
 * to the model.
 */
public interface MapHandler {

	/**
	 * Returns true if the cell x, y contains a ground block.
	 * @param x the x-coordinate of the cell
	 * @param y the y-coordinate of the cell
	 * @return true if  the cell contains ground, otherwise false
	 */
	public boolean isCellGround(int x, int y);
	
	/**
	 * Returns true if the cell x, y contains an obstacle block.
	 * @param x the x-coordinate of the cell
	 * @param y the y-coordinate of the cell
	 * @return true if the cell contains an obstacle, otherwise false
	 */
	public boolean isCellObstacle(int x, int y);
		
}
