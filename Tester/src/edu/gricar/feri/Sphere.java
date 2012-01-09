package edu.gricar.feri;

import java.util.Random;

public class Sphere {

	final public static int dimension = 2;
	
	public static double[] populacija = new double[dimension];
	
	public static void main(String[] args) {
//		System.out.println(Sphere.sphereFunction(tmp));
	}
	
	public static double sphereFunction(double[] tmp) {
		
		double sum = 0;
		
//		Sphere.getRandomX(tmp);
				
		for(int i = 0; i < tmp.length; i++) {
			sum += Math.pow(tmp[i], 2);
		}
		return sum;
	}
	
	public static double[] getRandomX(double[] tmp) {
		Random rand = new Random();
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = rand.nextDouble() * 10.24 - 5.12;
		}
		return tmp;
	}
}