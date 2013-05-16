package se.chalmers.tda367.vt13.dimensions.model.progresshandler;

import se.chalmers.tda367.vt13.dimensions.model.Level;

/**
 * A class to handle progress for a individual level.
 * 
 * @author Kim
 * 
 */
public class ProgressLevel {
	private boolean isCompleted;
	private int highScore;
	private Level level;
	
	public ProgressLevel(Level level) {
		this.level = level;
		isCompleted = false;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(boolean b) {
		isCompleted = b;
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
