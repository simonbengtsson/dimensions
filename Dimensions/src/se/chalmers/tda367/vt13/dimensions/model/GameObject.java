package se.chalmers.tda367.vt13.dimensions.model;

public abstract class GameObject {
	private Vector3 velocity;
	private Vector3 position;
	private Collider collider;
	
	public GameObject(double x, double y, double z){
		position = new Vector3(x, y, z);
		velocity = new Vector3(0, 0, 0);
		collider = new Collider();
	}
	
	public Vector3 getPos(){
		return position.clone();
	}
	
	public Vector3 getVelocity() {
		return velocity.clone();
	}
	
	public void setPos(double x, double y, double z){
		position.setXYZ(x, y, z);
	}
	
	public void setVelocity(double x, double y, double z) {
		velocity.setXYZ(x, y, z);
	}
	
	public void setPos(Vector3 v){
		position = v.clone();
	}
	
	public void setVelocity(Vector3 v) {
		velocity = v.clone();
	}
	
	public double getPosX(){
		return position.getX();
	}
	
	public double getPosY(){
		return position.getY();
	}
	
	public double getPosZ(){
		return position.getZ();
	}
	
	public double getVelocityX(){
		return velocity.getX();
	}
	
	public double getVelocityY(){
		return velocity.getY();
	}
	
	public double getVelocityZ(){
		return velocity.getZ();
	}
	
	public void setPosX(double x){
		position.setX(x);
	}
	
	public void setPosY(double y){
		position.setY(y);
	}
	
	public void setPosZ(double z){
		position.setZ(z);
	}
	
	public void setVelocityX(double x){
		velocity.setX(x);
	}
	
	public void setVelocityY(double y){
		velocity.setY(y);
	}
	
	public void setVelocityZ(double z){
		velocity.setZ(z);
	}
	
	public Collider getCollider(){
		return collider;
	}
	
	public abstract void draw();
}
