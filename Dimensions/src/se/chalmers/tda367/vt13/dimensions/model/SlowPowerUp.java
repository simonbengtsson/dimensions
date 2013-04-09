package se.chalmers.tda367.vt13.dimensions.model;

public class SlowPowerUp extends GameObject implements PowerUp{
	private int useSpeed = 5;
	private int duration = 10;
	private Model m;
	
	public SlowPowerUp(int x, int y, Model m){
		super(x, y);
		this.m = m;
	}

	@Override
	public void use() {
		m.setWorldSpeed(useSpeed, duration);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
	}

}
