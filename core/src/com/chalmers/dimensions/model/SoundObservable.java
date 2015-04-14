package com.chalmers.dimensions.model;

import java.util.List;

public interface SoundObservable {
	public void addObserver(SoundObserver s);

	public void removeObserver(SoundObserver s);

	public void playSound();

	public List<SoundObserver> getObservers();
}
