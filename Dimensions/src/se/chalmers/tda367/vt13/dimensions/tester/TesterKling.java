package se.chalmers.tda367.vt13.dimensions.tester;

import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.badlogic.gdx.graphics.g3d.model.Model;

import se.chalmers.tda367.vt13.dimensions.controller.GameController;
import se.chalmers.tda367.vt13.dimensions.model.*;
import se.chalmers.tda367.vt13.dimensions.model.levels.Level;

public class TesterKling implements Runnable {
	private GameModel m;
	private long previousTime = System.currentTimeMillis();
	private Random r = new Random();
	private List<Integer> points = new ArrayList<Integer>();
	private long timeStart = System.currentTimeMillis();
	public TesterKling(GameModel m){
		this.m = m;
	}
	
	@Override
	public void run() {
		while(true){
//			if(r.nextInt(100) > 90){
//				PowerUp p = new SpeedPowerUp(new Vector3(), new Vector3(), new Vector3());
//				p.use(m);
//				System.out.println("Now slow!");
//			}
			
			m.updateModel();
			
			points.add((int)m.getPlayer().getPosition().getY());
			
			if(m.getPlayer().getPosition().getY() < 10){
				drawGraph();
				break;
			}
			
			log("\tposY=" + (int)m.getPlayer().getPosition().getY() + "\t\tvelocityY=" + (int)m.getPlayer().getSpeed().getY()
					+ "\t\tposX=" + (int)m.getPlayer().getPosition().getX() + "\t\tvelocityX=" + (int)m.getPlayer().getSpeed().getX());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		Level lv = new Level("Level1");
		Player player = new Player(new Vector3(10,100,0), new Vector3(50, 50, 0), new Vector3(2, 0, 0)
			, -10f, 15f, false);
		// LEVEL WILL TAKE CARE OF THIS LATER (Model constructor with level parameter?)
		
		GameModel model = new GameModel(lv.getList(), player);
		
		
		new Thread(new TesterKling(model)).start();

	}

	public void log(String s){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS");
		String time = sdf.format(System.currentTimeMillis());
		System.out.println(time + ": " + s);
	}
	
	public void drawGraph(){
		DrawGraph mainPanel = new DrawGraph(points);

	    JFrame frame = new JFrame("DrawGraph");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().add(mainPanel);
	    frame.pack();
	    frame.setLocationByPlatform(true);
	    frame.setVisible(true);
	}
	

}
