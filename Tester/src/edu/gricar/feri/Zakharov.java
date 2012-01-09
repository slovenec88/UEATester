package edu.gricar.feri;

import java.util.Random;

public class Zakharov {

	final public static int dimension = 2;
	
	public static double[] populacija = new double[dimension];
	
	public static void main(String[] args) {
//		System.out.println(Zakharov.zakharovFunction(tmp));
	}
	
	public static double zakharovFunction(double[] tmp) {
		
		double suma1 = 0, suma2 = 0;
		
//		Zakharov.getRandomX(tmp);
				
		for(int i = 0; i < tmp.length; i++) {
			suma1 += Math.pow(tmp[i], 2);
			suma2 += 0.5 * i * tmp[i];
		}
		return suma1 + Math.pow(suma2, 2) + Math.pow(suma2, 4);
	}
	
	public static double[] getRandomX(double[] tmp) {
		Random rand = new Random();
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = rand.nextDouble() * 15 - 5; // (-5, 10)
		}
		return tmp;
	}
}