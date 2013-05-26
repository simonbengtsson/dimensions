package se.chalmers.tda367.vt13.dimensions.util;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

import se.chalmers.tda367.vt13.dimensions.model.LevelHandler;
import se.chalmers.tda367.vt13.dimensions.model.progresshandler.ProgressLevel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Storage {

	public static void saveProgress() {
		Collection<ProgressLevel> progress = LevelHandler.getInstance()
				.getProgressLevels();
		FileHandle file = null;
		FileHandle folder = new FileHandle(new File(Constants.SAVE_FOLDER));
		if (!folder.exists())
			folder.mkdirs();

		file = folder.child(Constants.SAVE_FILE);
		try {
			new ObjectOutputStream(file.write(false)).writeObject(progress);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static boolean loadProgress() {
		FileHandle file = null;
		if (Gdx.files.isLocalStorageAvailable()) {
			file = Gdx.files.internal(Constants.SAVE_FOLDER + "/"
					+ Constants.SAVE_FILE);
		}
		Collection<ProgressLevel> pl = null;
		try {
			pl = (Collection<ProgressLevel>) new ObjectInputStream(file.read())
					.readObject();
		} catch (GdxRuntimeException e) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return LevelHandler.getInstance().loadProgressFromFile(pl);
	}

}
