package se.chalmers.tda367.vt13.dimensions.tester;

import java.text.SimpleDateFormat;
import java.util.Random;

import se.chalmers.tda367.vt13.dimensions.factories.ModelFactory;
import se.chalmers.tda367.vt13.dimensions.model.*;
import se.chalmers.tda367.vt13.dimensions.model.levels.*;

public class TesterKling implements Runnable {
	private Model m;
	private long previousTime = System.currentTimeMillis();
	private Random r = new Random();
	public TesterKling(Model m){
		this.m = m;
	}
	
	@Override
	public void run() {
		while(true){
			if(r.nextInt(100) > 95){
				m.playerJump();
				System.out.println("Jumped!");
			}
			
			if(r.nextInt(100) > 96){
				PowerUp p = new SlowPowerUp(10, 10, m);
				p.use();
				System.out.println("Now slow!");
			}
			
			long currentTime = System.currentTimeMillis();
			float diff = (float)(currentTime - previousTime)/1000;
			m.update(diff);
			
			
			log("\tposY=" + (int)m.getPlayer().getPosY() + "\t\tvelocityY=" + (int)m.getPlayer().getVelocityY()
					+ "\t\tposX=" + (int)m.getPlayer().getPosX() + "\t\tvelocityX=" + (int)m.getPlayer().getVelocityX());
			
			previousTime = currentTime;
			try {
				Thread.sleep(250);
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
	

}
