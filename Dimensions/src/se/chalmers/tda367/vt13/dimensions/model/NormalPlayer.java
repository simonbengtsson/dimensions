package se.chalmers.tda367.vt13.dimensions.model;

public class NormalPlayer extends Player {
	private Model model;
	
	public NormalPlayer(Model m){
		super(0, 100);
		this.model = m;
		setJumpInitialSpeed(10);
	}
	
	public void onPlatform(){
		setFalling(false);
		setVelocityY(0);
	}
	
	public void calculateState(float time){
		if(isFalling()){
			setPosY((int) (getPosY() + (getVelocityY()*time) + (0.5*model.getGravity()*time*time)));
			setVelocityY(getVelocityY() + (model.getGravity() * time));
		}
		setPosX((int) (getPosX() + (model.getWorldSpeed()*time)));
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

}
