package se.chalmers.tda367.vt13.dimensions.model.levels;

import se.chalmers.tda367.vt13.dimensions.model.Obstacle;
import se.chalmers.tda367.vt13.dimensions.model.Vector3;
import se.chalmers.tda367.vt13.dimensions.model.powerup.PowerUp;
import se.chalmers.tda367.vt13.dimensions.util.Constants;

@SuppressWarnings("serial")
public class NormalLevel extends Level {
	public NormalLevel(String levelname, String backgroundfilepath) {
		super(levelname, backgroundfilepath);
		spawnSingleBlock(getGameObjects(), new Vector3(), new Vector3(20000, 50, 500));
		spawnPowerUp(gameobjects, PowerUp.SPEED, new Vector3(700, 200, 200));
		spawnPowerUp(gameobjects, PowerUp.LOW_GRAVITY, new Vector3(1600, 400,
				200));
		// DIMENSION CHANGE TESTING WITH OBSTACLES
		Obstacle spikes1 = new Obstacle(new Vector3(300, 110, 0), new Vector3(
				50, 50, 50), new Vector3(), Constants.SPIKEOBSTACLE_IMAGE,
				Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes1);
		Obstacle spikes2 = new Obstacle(new Vector3(900, 110, 100),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes2);
		Obstacle spikes3 = new Obstacle(new Vector3(1500, 110, 400),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes3);
		Obstacle spikes4 = new Obstacle(new Vector3(2100, 110, 500),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes4);
		Obstacle spikes5 = new Obstacle(new Vector3(2700, 110, 200),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes5);
		Obstacle spikes6 = new Obstacle(new Vector3(3300, 110, 500),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes6);
		Obstacle spikes7 = new Obstacle(new Vector3(3900, 110, 100),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes7);
		Obstacle spikes8 = new Obstacle(new Vector3(4500, 110, 400),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes8);
		Obstacle spikes9 = new Obstacle(new Vector3(5100, 110, 200),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes9);
		Obstacle spikes10 = new Obstacle(new Vector3(5700, 110, 0),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes10);
		Obstacle spikes11 = new Obstacle(new Vector3(6300, 110, 300),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes11);
		Obstacle spikes12 = new Obstacle(new Vector3(6900, 110, 500),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes12);
		Obstacle spikes13 = new Obstacle(new Vector3(7500, 110, 0),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes13);
		Obstacle spikes14 = new Obstacle(new Vector3(8100, 110, 100),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes14);
		Obstacle spikes15 = new Obstacle(new Vector3(8700, 110, 400),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes15);
		Obstacle spikes16 = new Obstacle(new Vector3(9300, 110, 500),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes16);
		Obstacle spikes17 = new Obstacle(new Vector3(9900, 110, 200),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes17);
		Obstacle spikes18 = new Obstacle(new Vector3(10500, 110, 500),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes18);
		Obstacle spikes19 = new Obstacle(new Vector3(11100, 110, 100),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes19);
		Obstacle spikes20 = new Obstacle(new Vector3(11700, 110, 400),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes20);
		Obstacle spikes21 = new Obstacle(new Vector3(12300, 110, 200),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes21);
		Obstacle spikes22 = new Obstacle(new Vector3(12900, 110, 0),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes22);
		Obstacle spikes23 = new Obstacle(new Vector3(13500, 110, 300),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes23);
		Obstacle spikes24 = new Obstacle(new Vector3(14100, 110, 500),
				new Vector3(50, 50, 50), new Vector3(),
				Constants.SPIKEOBSTACLE_IMAGE, Constants.SPIKEOBSTACLE_SOUND);
		gameobjects.add(spikes24);

		WriteLevel rv = new WriteLevel();
		rv.saveToFile(levelname, this);
	}

}
