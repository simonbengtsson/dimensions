package se.chalmers.tda367.vt13.dimensions.model.levels;

import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.Platform;

public interface Level {
	public List<Platform> getListOfPlatforms();
	public void addPlatform(Platform p);
	public void removePlatform(Platform p);
}
