package se.chalmers.tda367.vt13.dimensions.model.progresshandler;

/**
 * A class to handle progress for a individual level.
 * 
 * @author Kim
 * 
 */
public class Level {
	private boolean isCompleted;
	private String name;
	private int score;

	public Level(String name) {
		this.name = name;
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

	public int getScore() {
		return score;
	}

	public void setScore(int i) {
		score = i;
	}

}
