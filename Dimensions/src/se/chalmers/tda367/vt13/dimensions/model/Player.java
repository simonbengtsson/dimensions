package se.chalmers.tda367.vt13.dimensions.model;

public interface Player {
	public int getX();
	public int getY();
	public void jump();
	public void calculateState(float time);
	public float getVelocityY();
}
