package edu.gricar.feri;

import java.util.Random;

public class Rosenbrock {

	final public static int dimension = 2;
	
	public static double[] populacija = new double[dimension];
	
	public static void main(String[] args) {
//		System.out.println(Rosenbrock.rosenbrockFunction(tmp));
	}
	
	public static double rosenbrockFunction(double[] tmp) {
		
		double sum = 0;
		
//		Rosenbrock.getRandomX(tmp);
				
		for(int i = 0; i < tmp.length-1; i++) {
			sum += 100 * Math.pow(Math.pow(tmp[i], 2) - tmp[i+1], 2) + Math.pow(tmp[i] - 1 , 2);
		}
		return sum;
	}
	
	public static double[] getRandomX(double[] tmp) {
		Random rand = new Random();
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = rand.nextDouble() * 15 - 5; // (-5, 10)
		}
		return tmp;
	}
}