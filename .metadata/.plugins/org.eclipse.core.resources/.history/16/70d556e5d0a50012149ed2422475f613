package se.chalmers.tda367.vt13.dimensions.model;

public class Vector3 {
	private double x;
	private double y;
	private double z;
		
	public Vector3(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double[] getArray(){
		return new double[]{x, y ,z};
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public double getZ(){
		return z;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}

	public void setZ(double z){
		this.z = z;
	}
	
	public void setXYZ(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void multiply(double m){
		multiply(m, m, m);
	}
	
	public double getLength(){
		return Math.sqrt((x*x) + (y*y) + (z*z));
	}
	
	public void multiply(double x, double y, double z){
		this.x = this.x * x;
		this.y = this.y * y;
		this.z = this.z * z;
	}
	
	public Vector3 clone(){
		return new Vector3(x, y, z);
	}
	
	public int hashCode(){
		int hash = 0;
		hash += 5*x;
		hash += 7*y;
		hash += 9*z;
		return (int)hash;
	}
	
	public boolean equals(Object o){
		if(o instanceof Vector3){
			Vector3 v = (Vector3)o;
			if(v.getX() == x && v.getY() == y && v.getZ() == z){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public String toString(){
		return "x=" + x + " y=" + y + " z=" + z;
	}
	
}
