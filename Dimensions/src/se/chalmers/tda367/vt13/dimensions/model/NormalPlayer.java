package se.chalmers.tda367.vt13.dimensions.model;

public class NormalPlayer extends Player {
	private Model m;
	
	public NormalPlayer(Model m){
		super(0, 100, 0, 10);
		this.m = m;
		setJumpInitialSpeed(10);
		setVelocityX(10);
	}
	
	public void onPlatform(){
		setFalling(false);
		setVelocityY(0);
	}
	
	public void calculateState(float time){
		if(isFalling()){
			setPosY((getPosY() + (getVelocityY()*time) + (0.5*m.getGravity()*time*time)));
			setVelocityY(getVelocityY() + (m.getGravity() * time));
		}
		setPosX((double) (getPosX() + (getVelocityX()*time)));
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

}
