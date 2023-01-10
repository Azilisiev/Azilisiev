package handlers;

import java.util.Random;

public class Randomizer {
	private static Random randomGenerator = new Random();
	
	public static int weaponRoll(String roll) {
		int damageRoll = 0;
		String[] rollParams = roll.split("d");
		int numberOfDice = Integer.parseInt(rollParams[0]);
		int numberOfFaces = Integer.parseInt(rollParams[1]);
		for (int i = 0; i < numberOfDice; i++) {
			damageRoll += randBetween(1,numberOfFaces);
		}
		return damageRoll;
	}
	
	public static int randBetween(int min,int max) {
		
		return randomGenerator.nextInt(max - min + 1) + min;
	}

}
