package se.chalmers.tda367.vt13.dimensions.model;

public abstract class Player extends GameObject {
	private boolean isFalling;
	private double jumpInitialSpeed;
	private double standardVelocityX;
	
	public Player(double x, double y, double z, double standardVelocityX) {
		super(x, y, z);
		isFalling = true;
		this.standardVelocityX = standardVelocityX;
		
	}
	
	public void jump(){
		setVelocityY(jumpInitialSpeed);
		isFalling = true;
	}
	public abstract void calculateState(float time);
	
	public boolean collision(GameObject g){
		return false;
	}

	public boolean isFalling() {
		return isFalling;
	}

	public void setFalling(boolean isFalling) {
		this.isFalling = isFalling;
	}

	public double getJumpInitialSpeed() {
		return jumpInitialSpeed;
	}
	
	public double getStandardVelocityX(){
		return standardVelocityX;
	}

	public void setJumpInitialSpeed(double jumpInitialSpeed) {
		this.jumpInitialSpeed = jumpInitialSpeed;
	}
}
