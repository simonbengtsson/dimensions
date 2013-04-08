package se.chalmers.tda367.vt13.dimensions.tester;

import java.text.SimpleDateFormat;

import se.chalmers.tda367.vt13.dimensions.model.Model;

public class TesterKling implements Runnable {
	private Model m;
	private long previousTime;
	public TesterKling(Model m){
		this.m = m;
	}
	
	@Override
	public void run() {
		while(true){
			long currentTime = System.currentTimeMillis();
			float diff = (float)(currentTime - previousTime)/1000;
			m.update(diff);
			
			log("posY=" + m.getPlayer().getY() + " posX=" + m.getPlayer().getX()
					+ " velocity=" + m.getPlayer().getVelocityY());
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		new Thread(new TesterKling(new Model())).start();

	}

	public void log(String s){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(System.currentTimeMillis());
		System.out.println(time + ": " + s);
	}
	

}
