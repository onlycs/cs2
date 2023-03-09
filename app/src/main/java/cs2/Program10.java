// Angad Tendulkar
// Period 6
// Program 10

// This program will print food critics reviews

package cs2;

import java.util.Scanner;

public class Program10 {
	public static Scanner sc = new Scanner(System.in);

	public static void numStars(int n) {
		// this method will print n stars on the same line

		for (int i = 0; i < n; i++) {
			System.out.print("*");
		}
	}

	public static void main(String[] args) {
		int[] scores = {8, 4, 9, 8, 10, 9, 6, 9, 7, 8};

		System.out.print("Rater\tTheir Score\tNumber of Stars for the Meal");

		for (int i = 0; i < scores.length; i++) {
			System.out.printf("\n%d\t%d\t\t", i, scores[i]);
			numStars(scores[i]);
		}
		System.out.println();
	}
}
