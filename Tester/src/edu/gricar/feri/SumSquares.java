package edu.gricar.feri;

import java.util.Random;

public class SumSquares {
	
	final public static int dimension = 20;
	
	public static double[] populacija = new double[dimension];
	
	public static void main(String[] args) {
		//		System.out.println(Rastrigin.rastriginFunction(tmp));
	}
	
	public static double sumsqauresFunction(double[] tmp) {
		double sum = 0;
		
		for(int i = 0; i < tmp.length; i++) {
			sum = sum + tmp[i] * Math.pow(tmp[i], 2);
		}
		
		return sum;
	}
	
	public static double[] getRandomX(double[] tmp) {
		Random rand = new Random();
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = rand.nextDouble() * 10.24 - 5.12; // (-5.12, 5.12)
		}
		return tmp;
	}

}

/*n = 20;
s = 0;
for j = 1:n  
    s=s+j*x(j)^2; 
end
y = s;*/