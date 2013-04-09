package se.chalmers.tda367.vt13.dimensions.tester;

import java.text.SimpleDateFormat;
import java.util.Random;

import se.chalmers.tda367.vt13.dimensions.model.Model;
import se.chalmers.tda367.vt13.dimensions.model.levels.IntroductionLevel;

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
			if(r.nextInt(100) > 90){
				m.getPlayer().jump();
				System.out.println("Jumped!");
			}
			
			long currentTime = System.currentTimeMillis();
			float diff = (float)(currentTime - previousTime)/1000;
			m.update(diff);
			
			
			log("posY=" + m.getPlayer().getY() + " posX=" + m.getPlayer().getX()
					+ " velocity=" + m.getPlayer().getVelocityY());
			
			previousTime = currentTime;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		new Thread(new TesterKling(new Model(new IntroductionLevel()))).start();

	}

	public void log(String s){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(System.currentTimeMillis());
		System.out.println(time + ": " + s);
	}
	

}
