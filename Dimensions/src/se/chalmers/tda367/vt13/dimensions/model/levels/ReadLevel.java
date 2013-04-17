package se.chalmers.tda367.vt13.dimensions.model.levels;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.List;

import se.chalmers.tda367.vt13.dimensions.model.GameObject;
/** Class for reading an existing level from a given  file
 * 
 * @author kimegenvall
 *
 */
public class ReadLevel {
	/** Empty constructor
	 * 
	 */
	public ReadLevel(){};
	
	/** Reads the List of GameObjects from the specified file with filename s 
	 * 
	 * @param s
	 * @return
	 */
	public List<GameObject> readLevelName(String s){
		
		
		try{
			FileInputStream fin = new FileInputStream(s);
			ObjectInputStream ois = new ObjectInputStream(fin);
			
			List<GameObject> tst = (List<GameObject>) ois.readObject();
			System.out.println("Reading " + s + " Succeeded");
			
			
			ois.close();
			return tst;
		}
		catch(FileNotFoundException e){
			System.out.println("Filen kunde inte hittas");
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
