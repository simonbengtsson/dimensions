package se.chalmers.tda367.vt13.dimensions.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Deque;

import se.chalmers.tda367.vt13.dimensions.model.LevelHandler;
import se.chalmers.tda367.vt13.dimensions.model.progresshandler.ProgressLevel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Storage {

	public static void saveProgress(){
		Collection<ProgressLevel> progress = LevelHandler.getInstance().getProgressLevels();
		FileHandle file = null;
		if(Gdx.files.isLocalStorageAvailable()){
			file = Gdx.files.internal(Constants.SAVE_FOLDER + "/" + Constants.SAVE_FILE);
		}
		try {
			new ObjectOutputStream(file.write(false)).writeObject(progress);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public static boolean loadProgress(){
		FileHandle file = null;
		if(Gdx.files.isLocalStorageAvailable()){
			file = Gdx.files.internal(Constants.SAVE_FOLDER + "/" + Constants.SAVE_FILE);
		}
		Deque<ProgressLevel> pl = null;
		try {
			pl =(Deque<ProgressLevel>) new ObjectInputStream(file.read()).readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return LevelHandler.getInstance().loadProgressFromFile(pl);
	}

}
