package se.chalmers.tda367.vt13.dimensions.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class Vector3Test {
private Vector3 zero,ones,twos;
	public void init(){
		 zero = new Vector3();
		 ones = new Vector3(1,1,1);
		 twos = new Vector3(2,2,2);
	}
	
	@Test
	public void equalsTest(){
		// if a.equals(b) -> a.hashcode == b.hashcode
		init();
		assertFalse(zero.equals(ones));
		Vector3 cloneones = ones.clone();
		assertTrue(ones != cloneones);
		assertTrue(ones.equals(cloneones));
		assertTrue(ones.hashCode() == cloneones.hashCode());
		assertFalse(ones.hashCode() == twos.hashCode());
	}
	
	@Test
	public void mathematicalOperationsTest(){
		init();
		
		//Expected outcome: X,Y,Z = 3,3,3
		assertTrue(ones.getX() == 1 && ones.getY() == 1 && ones.getZ() == 1);
		ones.add(twos);
		assertTrue(ones.getX() == 3 && ones.getY() == 3 && ones.getZ() == 3);
		
		// Expected outxome XYZ = -1,-1,-1 (2 - 3) on every position
		twos.subtract(ones);
		assertTrue(twos.getX() == -1 && twos.getY() == -1 && twos.getZ() == -1);
		
		//Expected outcome 6,6,6
		ones.scale(2);
		assertTrue(ones.getX() == 6 && ones.getY() == 6 && ones.getZ() == 6);
		
		
	}
}
