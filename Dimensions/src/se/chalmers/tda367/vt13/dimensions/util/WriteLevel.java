package se.chalmers.tda367.vt13.dimensions.util;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import se.chalmers.tda367.vt13.dimensions.model.Level;
/** Class for writing the List of GameObjects from a Level to a file
 * 
 * @author kimegenvall
 *
 */
public class WriteLevel {
	
	/** Empty constructor
	 * 
	 */
	public WriteLevel(){
	}
	
	/** Method for saving the list of the specifed Level l to a file named s
	 *  Psuh
	 * @param s
	 * @param l
	 */
	public void saveToFile(String s, Level l){
		try{
			// Create outputstream for desired name of file
			FileOutputStream fos = new FileOutputStream(s+".dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			// Write the list to the file and close the stream
			oos.writeObject(l.getGameObjects());
			oos.flush();
			oos.close();
			
			System.out.println("Writing " + s + " to file has succeeded");
					
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
