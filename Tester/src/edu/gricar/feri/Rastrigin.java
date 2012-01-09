package edu.gricar.feri;

import java.util.Random;

public class Rastrigin {

	final public static int dimension = 2;
	
	public static double[] populacija = new double[dimension];
	
	public static void main(String[] args) {
//		System.out.println(Rastrigin.rastriginFunction(tmp));
	}
	
	public static double rastriginFunction(double[] tmp) {
		
		double sum = 0;
		
//		Rastrigin.getRandomX(tmp);
				
		for(int i = 0; i < tmp.length; i++) {
			sum += (Math.pow(tmp[i], 2) - 10 * Math.cos(2 * Math.PI * tmp[i]));
		}
		return 10 * tmp.length + sum;
	}
	
	public static double[] getRandomX(double[] tmp) {
		Random rand = new Random();
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = rand.nextDouble() * 10.24 - 5.12; // (-5.12, 5.12)
		}
		return tmp;
	}
}
