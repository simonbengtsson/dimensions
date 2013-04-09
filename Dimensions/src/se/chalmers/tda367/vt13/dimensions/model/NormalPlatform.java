package se.chalmers.tda367.vt13.dimensions.model;

public class NormalPlatform implements Platform {
	private int height;
	private int width;
	private int posY;
	private int posX;
	
	
	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getPosY() {
		return posY;
	}

	@Override
	public int getPosX() {
		return posX;
	}

}
