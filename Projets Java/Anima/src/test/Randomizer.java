package test;

public class Randomizer {
	
	//picks a number between two numbers
	public int pickNumber(double min, double max) {
		double number =  Math.random() * (max-min) + min ;
		return (int) number;
	}

}
