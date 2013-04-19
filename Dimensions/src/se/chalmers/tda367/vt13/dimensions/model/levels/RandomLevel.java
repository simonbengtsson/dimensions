package se.chalmers.tda367.vt13.dimensions.model.levels;

import java.util.List;
import java.util.Random;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;

public class RandomLevel extends Level {
	
	public RandomLevel(String levelname,String pathname){
		super(levelname,pathname);
		for(int i = 0; i<5; i++){
			randomGenerateLevel(gameobjects,100,50);
		}
		
		for(int i = 0; i<5; i++){
			randomGenerateLevel(gameobjects,1000,50);
		}
		spawnPowerUp(gameobjects,2,100,200);
		
		WriteLevel rv = new WriteLevel();
		rv.saveToFile(levelname, this);
	}
	
	public void randomGenerateLevel(List <GameObject> l,int high,int low ){
		Random rd = new Random();
		int i =  rd.nextInt(2);
		
		int  xval = rd.nextInt(high-low) + low;
		int yval = rd.nextInt(80-low) + low;
		switch(i){
		case 0:
			stairCase(l);
			
		case 1: dropDown(l);
		
		case 2: spawnSingleBlock(l,xval,yval,120,50);
		}
	}

}
