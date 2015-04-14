package com.chalmers.dimensions.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

import com.chalmers.dimensions.model.LevelHandler;
import com.chalmers.dimensions.model.ProgressLevel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Storage {

	public static void saveProgress() {
		Collection<ProgressLevel> progress = LevelHandler.getInstance()
				.getProgressLevels();
		FileHandle file = Gdx.files.local(Constants.SAVE_FILE);
		try {
			new ObjectOutputStream(file.write(false)).writeObject(progress);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public static void loadProgress() {
		FileHandle file = Gdx.files.local(Constants.SAVE_FILE);
		Collection<ProgressLevel> pl = null;
		try {
			pl = (Collection<ProgressLevel>) new ObjectInputStream(file.read())
					.readObject();
		} catch (GdxRuntimeException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LevelHandler.getInstance().loadProgressFromFile(pl);
	}

}
