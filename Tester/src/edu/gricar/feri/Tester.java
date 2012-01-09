package edu.gricar.feri;

import java.util.Random;
import java.util.Vector;

public class Tester {
	
	public static final int[] testneDimenzije = {2, 5, 10, 30, 50};
	public static final int[] testnaOvrednotenja = {1000, 2000};
	public static double[] allResult = new double[30];
	public static Vector<Vector <Object>> vector = new Vector<Vector <Object>>();
	public static Vector<Object> tempVector = new Vector<Object>();
	public static double average = 0;
	public static double tempResult = 0;
	public static double avgStdDev = 0;
	public static double stdDev[] = new double[30];
	public static double[] populacija;
	public static double[] one;
	public static double[] oneplus;
	
	public static double randomWalkAlgorithm(int dimension, int maxEvaluations, int funType) {
		
		populacija = new double[dimension];
		
		int eval = 0;
		
		double best = 0;
		
		switch(funType) {
		case 1: Sphere.getRandomX(populacija); best = Sphere.sphereFunction(populacija); break;
		case 2: Rastrigin.getRandomX(populacija); best = Rastrigin.rastriginFunction(populacija); break;
		case 3: SumSquares.getRandomX(populacija); best = SumSquares.sumsqauresFunction(populacija); break;
		case 4: Zakharov.getRandomX(populacija); best = Zakharov.zakharovFunction(populacija); break;
		default: System.out.println("Napaka"); break;
		}
		
		while (eval < maxEvaluations) {
			
			eval++;
			double tmp = 0;
			
			switch(funType) {
			case 1: best = Sphere.sphereFunction(populacija); break;
			case 2: best = Rastrigin.rastriginFunction(populacija); break;
			case 3: best = SumSquares.sumsqauresFunction(populacija); break;
			case 4: best = Zakharov.zakharovFunction(populacija); break;
			default: System.out.println("Napaka"); break;
			}
			
			if (tmp > best) best = tmp; 
		}
		return best;
	}
	
	public static double esEnaPlusEnaAlgorithm(int dimension, int maxEvaluation, int funType) {
		
		one = new double[dimension];
		oneplus = new double[dimension];
		int k = 10;
		double c = 0.8;
	    int num_eval = 1;
	    int succ = 0;
	    double varianceOne = 5.0; // èrepinšek = 5.0 ; original je 1.0 
	    int everyK = 0;
	    
		switch(funType) {
		case 1: one = Sphere.getRandomX(one); break;
		case 2: one = Rastrigin.getRandomX(one); break;
		case 3: one = SumSquares.getRandomX(one); break;
		case 4: one = Zakharov.getRandomX(one); break;
		default: System.out.println("Napaka"); break;
		}

	    while (num_eval < maxEvaluation) {
	    	everyK = (everyK++) % k;
	        if (everyK == 0) { //1/5 rule
	            if ((succ / k) > 0.2) varianceOne = varianceOne / c;
	            else if ((succ / k) < 0.2) varianceOne = varianceOne * c;
	            succ = 0;
	        }
	        
	        oneplus = new double[dimension];
	        
	        for(int i=0; i<one.length;i++) 
	        	oneplus[i] = one[i];
	       // oneplus = one.clone(); ^ gor navrh èrepinšek izboljšava =)
	        
	        oneplus = esMutate(oneplus, varianceOne, funType); 
	        num_eval++;
	        
	        switch(funType) {
	        case 1: 
	        	if(Sphere.sphereFunction(oneplus) < Sphere.sphereFunction(one)) {
	        		succ++; //for 1/5 rule
	        		one = oneplus;
	        	}
	        	break;
	        case 2:
	        	if(Rastrigin.rastriginFunction(oneplus) < Rastrigin.rastriginFunction(one)) {
		        	succ++; //for 1/5 rule
		            one = oneplus;
		        }
		        break;
	        case 3:
	        	if(SumSquares.sumsqauresFunction(oneplus) < SumSquares.sumsqauresFunction(one)) {
		        	succ++; //for 1/5 rule
		            one = oneplus;
		        }
		        break;
	        case 4: 
	        	if(Zakharov.zakharovFunction(oneplus) < Zakharov.zakharovFunction(one)) {
		        	succ++; //for 1/5 rule
		            one = oneplus;
		        }
		        break;
	        } 
	    }
	    
	    switch(funType) {
	    case 1: return Sphere.sphereFunction(one); 
	    case 2: return Rastrigin.rastriginFunction(one); 
	    case 3: return SumSquares.sumsqauresFunction(one);
	    case 4: return Zakharov.zakharovFunction(one);
	    default: return -1;
	    }
	}
	
	public static double[] esMutate(double[] oneplus, double varianceOne, int funType) {
		for (int i = 0; i < oneplus.length; i++) {
			oneplus[i] = oneplus[i] + esGetGaussian(0, varianceOne);
			
			switch (funType) {
			case 1:
			case 2:
				if (oneplus[i] < -5.12) oneplus[i] = -5.12;
				if (oneplus[i] > 5.12) oneplus[i] = 5.12;
				break;
			case 3:
			case 4:
				if (oneplus[i] < -5.0) oneplus[i] = -5.0;
				if (oneplus[i] > 10.0) oneplus[i] = 10.0;
				break;
			}
		}
		return oneplus;
	}
	
	public static double esGetGaussian(double mean, double aVariance) {
		Random rand = new Random();
		return (mean + rand.nextGaussian() * aVariance);
	}
	
	public static double standardDeviation() {
		
		avgStdDev = 0;
		stdDev = new double[30];
		
		for(int i = 0; i < allResult.length; i++) {
			if(average >= allResult[i])
				stdDev[i] = average - allResult[i];
			else
				stdDev[i] = allResult[i] - average;
		}
		for(int i = 0; i < stdDev.length; i++)
			avgStdDev += stdDev[i];
			
		return avgStdDev /= allResult.length; //30
	}
	
	public static void main(String[] args) {
		
//		test randomWalk
		tempVector = new Vector<Object>();
		average = 0;
		for(int funkcija = 1; funkcija < 5; funkcija++) { // testiramo funkcije Sphere, Rastrigin, SumSquares in Zakharov
			for(int dim : testneDimenzije) { // testiramo na dimenzijah 2, 5, 10, 30, 50
				//					System.out.println("Dimenzija:" + dim);
				for(int eval : testnaOvrednotenja) { // testiramo na veè kombinacijah evaluacij in sicer dimenzija*1000 ter dimenzija*2000
					//						System.out.println(eval*dim);
					for(int zagon = 0; zagon < 30; zagon++) { // vsako izmed vseh možnih kombinacij poganjamo 30-krat
						tempResult = randomWalkAlgorithm(dim, eval, funkcija);
						average += tempResult;
						allResult[zagon] = tempResult;
					}
					average /= 30;
					
					tempVector.add("randomWalk");
					switch(funkcija) {
					case 1: tempVector.add("Sphere"); break;
					case 2: tempVector.add("Rastrigin"); break;
					case 3: tempVector.add("SumSquares"); break;
					case 4: tempVector.add("Zakharov"); break;
					}
					
					tempVector.add(dim);
					tempVector.add(eval + "*D");
					tempVector.add(average);
					tempVector.add(standardDeviation());
					vector.add(tempVector);
					
					System.out.println("Povpreèje metode RadomWalk s funkcijo " + funkcija + " pri številu evaluacij " + eval + " in dimenziji " + dim + " je: " + average);
					
					tempVector = new Vector<Object>();
					average = 0;
				}
			}
		}
		
//		test EvolutionStrategy(1+1)
		tempVector = new Vector<Object>();
		average = 0;
		for(int funkcija = 1; funkcija < 5; funkcija++) { // testiramo funkcije Sphere, Rastrigin, SumSquares in Zakharov
			for(int dim : testneDimenzije) { // testiramo na dimenzijah 2, 5, 10, 30, 50
				//					System.out.println("Dimenzija:" + dim);
				for(int eval : testnaOvrednotenja) { // testiramo na veè kombinacijah evaluacij in sicer dimenzija*1000 ter dimenzija*2000
					//						System.out.println(eval*dim);
					for(int zagon = 0; zagon < 30; zagon++) { // vsako izmed vseh možnih kombinacij poganjamo 30-krat
						tempResult = esEnaPlusEnaAlgorithm(dim, eval, funkcija);
						average += tempResult;
						allResult[zagon] = tempResult;
					}
					average /= 30;
					
					tempVector.add("evolutionStrategy(1+1)");
					switch(funkcija) {
					case 1: tempVector.add("Sphere"); break;
					case 2: tempVector.add("Rastrigin"); break;
					case 3: tempVector.add("SumSquares"); break;
					case 4: tempVector.add("Zakharov"); break;
					}
					tempVector.add(dim);
					tempVector.add(eval + "*D");
					tempVector.add(average);
					tempVector.add(standardDeviation());
					vector.add(tempVector);
					
					System.out.println("Povpreèje metode ES(1+1) s funkcijo " + funkcija + " pri številu evaluacij " + eval + " in dimenziji " + dim + " je: " + average);
					
					tempVector = new Vector<Object>();
					average = 0;
				}
			}
		}
	}
}