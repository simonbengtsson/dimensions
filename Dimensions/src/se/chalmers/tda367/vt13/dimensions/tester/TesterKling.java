package se.chalmers.tda367.vt13.dimensions.tester;

import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import se.chalmers.tda367.vt13.dimensions.factories.ModelFactory;
import se.chalmers.tda367.vt13.dimensions.model.*;

public class TesterKling implements Runnable {
	private Model m;
	private long previousTime = System.currentTimeMillis();
	private Random r = new Random();
	private List<Integer> points = new ArrayList<Integer>();
	public TesterKling(Model m){
		this.m = m;
	}
	
	@Override
	public void run() {
		while(true){
//			if(r.nextInt(100) > 90){
//				m.playerJump();
//				System.out.println("Jumped!");
//			}
//			
//			if(r.nextInt(100) > 90){
//				PowerUp p = new SlowPowerUp(10, 10, m);
//				p.use();
//				System.out.println("Now slow!");
//			}
			
			long currentTime = System.currentTimeMillis();
			float diff = (float)(currentTime - previousTime)/1000;
			m.update(diff);
			
			points.add((int)m.getPlayer().getPosY());
			
//			if(m.getPlayer().getPosY() < 10){
//				drawGraph();
//				break;
//			}
			
			log("\tposY=" + (int)m.getPlayer().getPosY() + "\t\tvelocityY=" + (int)m.getPlayer().getVelocityY()
					+ "\t\tposX=" + (int)m.getPlayer().getPosX() + "\t\tvelocityX=" + (int)m.getPlayer().getVelocityX());
			
			previousTime = currentTime;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		Model m = ModelFactory.getModel();
		new Thread(new TesterKling(m)).start();

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
