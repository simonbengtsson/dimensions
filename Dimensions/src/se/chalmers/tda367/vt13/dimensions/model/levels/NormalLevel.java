package se.chalmers.tda367.vt13.dimensions.model.levels;

@SuppressWarnings("serial")
public class NormalLevel extends Level {
	public NormalLevel(String levelname, String backgroundfilepath){
		super(levelname, backgroundfilepath);
		stairCase(gameobjects);
		dropDown(gameobjects);
		stairCase(gameobjects);
		spawnPowerUp(gameobjects,1,100,120);
		spawnPowerUp(gameobjects,1,400,300);
	
	
		WriteLevel rv = new WriteLevel();
		rv.saveToFile(levelname, this);
	}

}
