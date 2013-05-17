package se.chalmers.tda367.vt13.dimensions.model;

import static org.junit.Assert.*;

import org.junit.Test;

import se.chalmers.tda367.vt13.dimensions.controller.Dimensions;
import se.chalmers.tda367.vt13.dimensions.controller.screens.GameScreen;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.Dimension;
import se.chalmers.tda367.vt13.dimensions.model.GameWorld.State;
import se.chalmers.tda367.vt13.dimensions.model.powerup.DimensionChangePowerUp;
import se.chalmers.tda367.vt13.dimensions.util.TiledMapHandler;
import se.chalmers.tda367.vt13.dimensions.view.GameView;

import java.util.*;
public class ModelTests {
private GameScreen g = new GameScreen(new Dimensions());
private GameWorld w;
private Level lev;

	//@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	
	public void create(){
		TiledMapHandler tiledMapHandler = new TiledMapHandler();
		CollisionHandler collisionHandler = new CollisionHandler(tiledMapHandler);
		LevelHandler l = LevelHandler.getInstance();
		
		l.load();
		
		
		List<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects.add(new DimensionChangePowerUp(new Vector3(20, 4, 10), new Vector3(
				1, 1, 1), new Vector3()));
		gameObjects.add(new DimensionChangePowerUp(new Vector3(30, 4, 10), new Vector3(
				1, 1, 1), new Vector3()));
		this.lev = new Level("Example", -0.05f, gameObjects, Dimension.XY,
				"data/tiledMaps/levelXY.tmx", "data/tiledMaps/levelXZ.tmx", 205);

		
		this.w = new GameWorld(this.lev, collisionHandler);
	}
	
	//@Test
	public void testDimensionChange(){
		create();

		
		// M�ste kommentera bort timern i Gameworld.swapDimension() f�r att det ska fungera.
		
		assertTrue(w.getDimension() == Dimension.XY);
		w.swapDimension();
		//w.update();
		Timer t = new Timer();
		t.schedule(new TimerTask(){
			@Override
			public void run(){
				assertTrue(w.getDimension() == Dimension.XZ);
			}
		},3500);
		//w.swapDimension();
		assertTrue(w.getDimension() == Dimension.XZ);
		w.swapDimension();
		w.swapDimension();
		assertTrue(w.getDimension() == Dimension.XZ); 
	}
	
	@Test 
	public void testGameOverOrCompleted(){
		create();
		
		assertFalse(w.isGameOver());
		w.getPlayer().setPosition(new Vector3(w.getCurrentLevel().getLevelFinishedPosition()+1,1,1));
		assertTrue(w.isLevelFinished());
		
		w.getPlayer().setPosition(new Vector3(w.getCurrentLevel().getLevelFinishedPosition()-1,1,1));
		assertFalse(w.isLevelFinished());
		
		w.getPlayer().setPosition(new Vector3(w.getCurrentLevel().getLevelFinishedPosition()+1,-1,1));
		assertTrue(w.isGameOver());
		assertTrue(w.isLevelFinished());
		
		
	}
	
	@Test
	public void testCheckpoint(){
		create();
		CheckPoint startCp = w.getCheckPoint();
		w.placeCheckPoint();
		CheckPoint newCp = w.getCheckPoint();
		assertTrue(startCp != newCp);
		 
			
		
	}
	

	

}