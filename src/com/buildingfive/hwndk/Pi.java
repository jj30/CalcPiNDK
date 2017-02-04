package com.buildingfive.hwndk;
import java.lang.Math;


public class Pi { 
	static double a0 = 1.0;
	static double b0 = Math.pow(2, -0.5);
	static double t0 = div2(1, 4);
	static double p0 = 1.0;
	static double pi = 0.0;
	
	static public double div2(int x, int y) {
		return (double) x / (double) y;
	}
	
	static public double div2(double x, int y) {
		return x / (double) y;
	}
	
	static public double div2(double x, double y) {
		return x / y;
	}
	
	static public double bellards(int n) {
		double pi = 0.0;
		double delta = 0.0;
		
		for (int i = 0; i < n; i++) { 			
			delta = div2(Math.pow(-2, 5), ((4 * i) + 1)) 
					- div2(1, ((4 * i) + 3))
					+ div2(Math.pow(2, 8), ((10 * i) + 1))
					- div2(Math.pow(2, 6), ((10 * i) + 3))
					- div2(Math.pow(2, 2), ((10 * i) + 5))
					- div2(Math.pow(2, 2), ((10 * i) + 7))
					+ div2(1, ((10 * i) + 9));
			
			delta *= div2(Math.pow(-1, i), Math.pow(2, 10 * i));
			delta *= Math.pow(2, -6);
			pi += delta;
		}
		
		return pi;
	}
	
	static public double chudnovsky(int n) {
		double ourPi = 0.0;
		double delta = 0.0;
		
		for (int j = 0; j < n; j++) {
			delta = Math.pow(-1, j);
			delta *= factorial(6 * j);
			delta *= (13591409 + (545140134 * j));
			
			delta /= factorial(j * 3);
			delta /= Math.pow(factorial(j), 3);
			delta /= Math.pow(640320, ((3 * j) + 1.5));
			
			ourPi += delta;
		} 
		
		ourPi = 1 / (12 * ourPi);
		return ourPi;
	}
	
	static public double gaussLegendre(int n) {
		return iterate(n);
	}
	
	static private double iterate(int j) {
		return div2(Math.pow((a(j) + b(j)), 2), 4 * t(j));
	}
	
	
	static private double a(int m) {
		//arithmetic mean
		if (m == 0) 
			return a0;
		else
			return div2(a(m - 1) + b(m - 1), 2);
	}
	
	static private double b(int k) { 
		//geometric mean
		if (k == 0)
			return b0;
		else
			return Math.pow(a(k - 1) * b(k - 1), 0.5);
	}
	
	static double t(int j) {
		if (j == 0) 
			return div2(1, 4);
		else
			return t(j - 1) - p(j - 1) * Math.pow((a(j - 1) - a(j)), 2);
	}
	
	static double p(int m) {
		if (m == 0)
			return p0;
		else
			return 2 * p(m - 1);
	}	

	static long factorial(int k) {
		if (k == 0)
			return 1;
		else 
			return k * factorial(k - 1);
	}

	static double leibniz(int n) {
		double ourPi = 0.0;
		double delta = 0.0;

		for (int j = 0; j <= n; j++)
		{
			ourPi += div2(Math.pow(-3, -j), 2 * j + 1);
		}
		return Math.sqrt(12) * ourPi;
	}
	
}
