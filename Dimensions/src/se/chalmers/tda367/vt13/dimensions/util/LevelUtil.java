package se.chalmers.tda367.vt13.dimensions.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.Level;
import se.chalmers.tda367.vt13.dimensions.model.LevelHandler;

// TODO Clean up and remove.

/**
 * Class for reading an existing level from a given file
 * 
 * @author kimegenvall
 * 
 */
public class LevelUtil {

	/**
	 * Loads all levels from a specified folder and gives them to the
	 * LevelHandler.
	 */
	public static void loadAllLevels() {
		LevelHandler.getInstance().load();
	}

	/**
	 * Saves a Level to a file
	 * 
	 * @param l
	 */
	public static void saveToFile(Level l) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(new File(
					Constants.LEVELFOLDER + "/" + l.getName()
							+ Constants.LEVELFILE_EXTENSION)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.writeObject(l);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Saves all Levels in the LevelHandler to file.
	 */
	public static void saveAllLevelsToFile() {
		List<Level> levels = LevelHandler.getInstance().getLevels();
		for (Level l : levels) {
			saveToFile(l);
		}
	}

}
