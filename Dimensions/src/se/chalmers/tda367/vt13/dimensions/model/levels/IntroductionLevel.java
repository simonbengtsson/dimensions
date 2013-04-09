package se.chalmers.tda367.vt13.dimensions.model.levels;

import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.Platform;

public class IntroductionLevel implements Level {
	private List<Platform> platforms;
	private int worldSpeed = 10;
	
	@Override
	public List<Platform> getListOfPlatforms() {
		return platforms;
	}

	@Override
	public void addPlatform(Platform p) {
		platforms.add(p);
		
	}

	@Override
	public void removePlatform(Platform p) {
		platforms.remove(p);
		
	}

	@Override
	public int getStandardWorldSpeed() {
		return worldSpeed;
		
	}

}
