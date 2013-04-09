package se.chalmers.tda367.vt13.dimensions.model;

public class NormalPlayer implements Player {
	private float velocityY = 0;
	private int posX;
	private int posY;
	private boolean isFalling;
	private Model model;
	private int jumpInitialSpeed = 10;
	
	public NormalPlayer(Model m){
		posX = 0;
		posY = 100;
		this.model = m;
	}
	
	@Override
	public int getX() {
		return posX;
	}

	@Override
	public int getY() {
		return posY;
	}
	
	public float getVelocityY(){
		return velocityY;
	}
	
	public boolean isFalling(){
		return isFalling;
	}

	@Override
	public void jump() {
		velocityY = jumpInitialSpeed;
		isFalling = true;
	}
	
	public void onPlatform(){
		isFalling = false;
		velocityY = 0;
	}
	
	public void calculateState(float time){
		if(isFalling){
			posY = (int) (posY + (velocityY*time) + (0.5*model.getGravity()*time*time));
			velocityY = velocityY + (model.getGravity() * time);
		}
		posX = (int) (posX + (model.getWorldSpeed()*time));
	}

}
