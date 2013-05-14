package se.chalmers.tda367.vt13.dimensions.util;

import java.util.List;
import java.util.Random;

import se.chalmers.tda367.vt13.dimensions.model.*;
import se.chalmers.tda367.vt13.dimensions.model.powerup.*;

public class LevelCreator {
	private float lastx = 0;
	private float lasty = 50;
	private float lastz = 0;
	private List<GameObject> l;

	public LevelCreator(List<GameObject> l) {
		this.l = l;
	}

	/* ####################### READ ########################### */
	/*
	 * ~~~~~~~INSTRUCTIONS FOR PLACING PLATFORMS ~~~~~~~~~~/ Parameters: first
	 * value: offset from the end of the previous platform in X Example: 0 will
	 * place it edge to edge of previous platform (in x-led) 50 will place it 50
	 * units away from the end of the previous platform
	 * 
	 * second value: offset from the previous platform in Y Example: 0 will
	 * place it at the same height 50 will place it 50 units higher than the
	 * previous platform -50 will place it 50 units lower than the previous
	 * platform
	 * 
	 * 
	 * For spawnSingleBlock(l,x,y,width,height): x as first value above y as
	 * second value above width specifies the length of the platform height
	 * specifies the height of the platform
	 * 
	 * 
	 * NOTE: Patterns such as stairCase() and dropDown() has predefined values
	 * that should not be changed.
	 */

	/**
	 * Spawns the platform where the player will start.
	 * 
	 * @param l
	 */
	public void spawnStartingPlatform() {
		spawnSingleBlock(0, 10, 300, 50);

	}

	/**
	 * Adds a staircase pattern of platforms to the list Pattern: ----- -----
	 * -----
	 * 
	 * @param l
	 */
	public void stairCase() {
		listAddShortPf(50, 10);
		listAddShortPf(50, 50);
		listAddShortPf(50, 50);

	}

	/**
	 * Adds a dropDown pattern of platforms to the list Pattern:
	 * ----_______-----
	 * 
	 * @param l
	 */
	public void dropDown() {
		listAddShortPf(50, 5);
		listAddLongPf(0, -70);
		listAddShortPf(0, 70);
	}

	/**
	 * Adds a long platform to the list Pattern: ________________
	 * 
	 * @param l
	 */
	public void longPlatform() {
		listAddLongPf(50, 0);
		listAddLongPf(0, 0);
		listAddLongPf(0, 0);
	}

	/**
	 * Adds a 'short' platform to the list with predefined values
	 * 
	 * @param l
	 *            List
	 * @param x
	 *            Offset x from previous platform
	 * @param y
	 *            Offset y from previous platform
	 */
	public void listAddShortPf(float x, float y) {
		this.lastx = lastx + x;
		this.lasty = lasty + y;

		l.add(new Platform(new Vector3(lastx, lasty, 0),
				new Vector3(50, 50, 0), new Vector3()));
		this.lastx = lastx + 50;

	}

	/**
	 * Method for adding powerups to the List representing the level i = 1:
	 * Speed i = 2: Slow i = 3: ...
	 * 
	 * @param l
	 *            List
	 * @param i
	 *            int value representing each powerup
	 * @param x
	 *            x-coordinate of spawn
	 * @param y
	 *            y-coordinate of spawn
	 */

	public void spawnPowerUp(int powerUpType, Vector3 position) {
		switch (powerUpType) {
		case PowerUp.SPEED:
			l.add(new SpeedPowerUp(new Vector3(position), new Vector3(1, 1, 1),
					new Vector3()));
			break;

		case PowerUp.LOW_GRAVITY:
			l.add(new LowGravityPowerUp(new Vector3(position), new Vector3(1,
					1, 1), new Vector3()));
			break;
		}
	}

	/**
	 * Adds a 'long' platform to the list with predefined values
	 * 
	 * @param l
	 *            List
	 * @param x
	 *            x-coordinate offset from previous platform
	 * @param y
	 *            y-coordinate offset from previous platform
	 */
	public void listAddLongPf(float x, float y) {
		this.lastx = lastx + x;
		this.lasty = lasty + y;
		l.add(new Platform(new Vector3(lastx, lasty, 0),
				new Vector3(130, 50, 0), new Vector3()));
		this.lastx = lastx + 130;

	}

	/**
	 * Adds a single block in a user(coder) defined position
	 * 
	 * @param l
	 *            List
	 * @param x
	 *            x-coordinate offset from previous platform
	 * @param y
	 *            y-coordinate offset from previous platform
	 * @param length
	 *            Specifies the length of the platform
	 * @param height
	 *            Specifies the height of the platform
	 */
	public void spawnSingleBlock(float x, float y,
			float length, float height) {
		this.lastx = lastx + x;
		this.lasty = lasty + y;
		l.add(new Platform(new Vector3(lastx, lasty, -200f), new Vector3(
				length, height, 2000), new Vector3()));
		this.lastx = lastx + length;
	}

	public void spawnSingleBlock(Vector3 offset,
			Vector3 size) {
		this.lastx = lastx + offset.getX();
		this.lasty = lasty + offset.getY();
		this.lastz = lastz + offset.getZ();
		l.add(new Platform(new Vector3(lastx, lasty, lastz), new Vector3(size),
				new Vector3()));
		this.lastx = lastx + size.getX();
	}
	
	public void randomGenerateLevel(int high, int low) {
		Random rd = new Random();
		int i = rd.nextInt(4);

		int xval = rd.nextInt(high - low) + low;
		int yval = rd.nextInt(80 - low) + low;
		switch (i) {
		case 0:
			stairCase();
			break;
		case 1:
			dropDown();
			break;
		case 2:
			spawnSingleBlock(xval, yval, 120, 50);
			break;
		case 3:
			longPlatform();
			break;
		}
	}
	
	public List<GameObject> creationCompleted(){
		return l;
	}
	
	public void add(GameObject g){
		l.add(g);
	}

}
