package se.chalmers.tda367.vt13.dimensions.model;


public abstract class GameObject {
	private double velocityY;
	private double velocityX;
	private double posX;
	private double posY;
	private Collider collider;
	
	public GameObject(int x, int y){
		posX = x;
		posY = y;
		velocityX = 0;
		velocityY = 0;
		collider = new Collider();
	}
	
	public double getPosX(){
		return posX;
	}
	
	public double getPosY(){
		return posY;
	}
	
	public void setPosX(double x){
		posX = x;
	}
	
	public void setPosY(double y){
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
