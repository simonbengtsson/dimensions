package se.chalmers.tda367.vt13.dimensions.model;

import java.io.Serializable;


/**
 * A class to handle progress for a individual level.
 * 
 * @author Kim
 * 
 */
public class ProgressLevel implements Serializable {
	private static final long serialVersionUID = 1938174933064799673L;

	private boolean isCompleted;
	private int highScore;
	private String name;

	public ProgressLevel(String name) {
		this.name = name;
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
		if (i >= highScore) {
			highScore = i;
		}
	}

	public void gameFinished(int score, boolean completed) {
		evaluateNewScore(score);
		isCompleted = isCompleted || completed;
	}

	public String getLevel() {
		return name;
	}

}
