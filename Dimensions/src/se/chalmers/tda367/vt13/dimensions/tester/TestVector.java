package se.chalmers.tda367.vt13.dimensions.tester;

import se.chalmers.tda367.vt13.dimensions.model.Vector3;

public class TestVector {
	public static void main(String[] args){
		Vector3 v = new Vector3(1, 1, 1);
		System.out.println(v.toString());
		v.scale(5);
		System.out.println(v.toString());
	}
}
