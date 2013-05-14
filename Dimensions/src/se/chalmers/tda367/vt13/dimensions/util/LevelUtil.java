package se.chalmers.tda367.vt13.dimensions.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
import se.chalmers.tda367.vt13.dimensions.model.levels.Level;
import se.chalmers.tda367.vt13.dimensions.model.levels.LevelHandler;

/**
 * Class for reading an existing level from a given file
 * 
 * @author kimegenvall
 * 
 */
public class LevelUtil {

	/**
	 * Reads the List of GameObjects from the specified file with filename s
	 * 
	 * @param s
	 * @return
	 */
	public static List<GameObject> readLevelName(String s) {

		try {
			FileInputStream fin = new FileInputStream(s);
			ObjectInputStream ois = new ObjectInputStream(fin);

			List<GameObject> tst = (List<GameObject>) ois.readObject();
			System.out.println("Reading " + s + " Succeeded");

			ois.close();
			return tst;
		} catch (FileNotFoundException e) {
			System.out.println("Filen kunde inte hittas");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Loads all levels from a specified folder and gives them to the
	 * LevelHandler.
	 */
	public static void loadAllLevels() {
		File folder = new File(Constants.LEVELFOLDER);
		File[] files = folder.listFiles();
		for (File f : files) {
			Level level = null;
			try {
				level = (Level) new ObjectInputStream(new FileInputStream(f))
						.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			LevelHandler.getInstance().registerLevel(level);
		}
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
					Constants.LEVELFOLDER + File.pathSeparator + l.getName()
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
		List<Level> levels = LevelHandler.getInstance().getListOfLevels();
		for (Level l : levels) {
			saveToFile(l);
		}
	}

}
