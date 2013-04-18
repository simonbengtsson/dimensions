package se.chalmers.tda367.vt13.dimensions.model;

import java.util.List;

import se.chalmers.tda367.vt13.dimensions.controller.SoundObserver;

public interface SoundObservable {
	public void addObserver(SoundObserver s);
	public void removeObserver(SoundObserver s);
	public void playSound();
	public List<SoundObserver> getObservers();
}
