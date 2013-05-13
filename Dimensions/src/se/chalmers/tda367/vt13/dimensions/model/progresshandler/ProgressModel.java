package se.chalmers.tda367.vt13.dimensions.model.progresshandler;

import java.util.List;

/**
 * This class handles the progress for a device. Later implementations may
 * handle profiles.
 * 
 * @author Kim
 * 
 */
public class ProgressModel {
	private static ProgressModel instance;
	private List<Level> allLevels;
	private List<Level> completedLevels;
	private List<Level> notCompletedLevels;

	private ProgressModel() {

	}

	public static synchronized ProgressModel getInstance() {
		if (instance == null) {
			instance = new ProgressModel();
		}
		return instance;
	}

}
