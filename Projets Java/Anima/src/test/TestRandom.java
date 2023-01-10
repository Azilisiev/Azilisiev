package test;

import java.util.Scanner;

public class TestRandom {

	public static void main(String[] args) {
		Randomizer rdm = new Randomizer();
		Scanner sc= new Scanner(System.in);//System.in is a standard input stream  
		System.out.print("Enter Max people : ");
		int max = sc.nextInt();
		System.out.print("Enter Min people : ");
		int min = sc.nextInt();
		System.out.print("Enter Female % : ");
		int fem = sc.nextInt();
		System.out.print("Enter Fighter % : ");
		int fight = sc.nextInt();
		
		int people = rdm.pickNumber(min, max);
		boolean isFem = false;
		boolean isFight = false;

		for (int i = 0; i < people; i++) {
			String person = "";
			if(rdm.pickNumber(0, 100)<= fem) {
				person += "F,";
				isFem = true;
			}
			else {
				person += "M,";
			}
			if(rdm.pickNumber(0, 100)<= fight) {
				isFight = true;
			}
			else {
				person += "Civilian";
			}
			System.out.println(person+"\n");
		}
	}

}
