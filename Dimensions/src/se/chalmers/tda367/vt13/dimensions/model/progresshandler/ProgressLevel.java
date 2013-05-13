package se.chalmers.tda367.vt13.dimensions.model.progresshandler;

import se.chalmers.tda367.vt13.dimensions.levels.Level;

/**
 * A class to handle progress for a individual level.
 * 
 * @author Kim
 * 
 */
public class ProgressLevel {
	private boolean isCompleted;
	private String name;
	private int highScore;
	private Level level;

	public ProgressLevel(){
		name = "";
	}
	
	public ProgressLevel(String name){
		this.name = name;
	}
	
	public ProgressLevel(String name, Level level, int score) {
		this.name = name;
		this.level = level;
		this.highScore = score;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(boolean b) {
		isCompleted = b;
	}

	public String getName() {
		return name;
	}

	public int getHighScore() {
		return highScore;
	}

	public void evaluateNewScore(int i) {
		if(i >= highScore){
			highScore = i;
		}
	}
	
	public Level getLevel(){
		return level;
	}

}
