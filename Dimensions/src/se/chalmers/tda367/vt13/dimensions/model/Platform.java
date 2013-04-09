package se.chalmers.tda367.vt13.dimensions.model;

public abstract class Platform extends GameObject {
	private int width;
	private int height;
	public Platform(int x, int y, int width, int height){
		super(x, y);
		this.width = width;
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
}