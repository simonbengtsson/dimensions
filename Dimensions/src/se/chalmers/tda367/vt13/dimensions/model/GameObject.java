package se.chalmers.tda367.vt13.dimensions.model;


public abstract class GameObject {
	private double velocityY;
	private double velocityX;
	private int posX;
	private int posY;
	private Collider collider;
	
	public GameObject(int x, int y){
		posX = x;
		posY = y;
		velocityX = 0;
		velocityY = 0;
		collider = new Collider();
	}
	
	public int getPosX(){
		return posX;
	}
	
	public int getPosY(){
		return posY;
	}
	
	public void setPosX(int x){
		posX = x;
	}
	
	public void setPosY(int y){
		posY = y;
	}
	
	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	
	public Collider getCollider(){
		return collider;
	}
	
	public abstract void draw();
}
