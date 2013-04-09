package se.chalmers.tda367.vt13.dimensions.model;

public class SlowPowerUp implements PowerUp{
	private int posX;
	private int posY;
	private int useSpeed = 5;
	private int duration = 10;
	private Model m;
	
	public SlowPowerUp(int x, int y, Model m){
		posX = x;
		posY = y;
		this.m = m;
	}
	
	@Override
	public int getPosX() {
		return posX;
	}

	@Override
	public int getPosY() {
		return posY;
	}

	@Override
	public void use() {
		m.setWorldSpeed(useSpeed, duration);
	}

}
