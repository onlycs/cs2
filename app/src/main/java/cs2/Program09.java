// Angad Tendulkar
// Period 6
// Program 9

package cs2;

import java.util.Scanner;

public class Program09 {
	public static Scanner sc = new Scanner(System.in);

	public static double average(int n) {
		// make the user enter n scores
		// then return the average

		int sum = 0;

		for (int i = 0; i < n; i++) {
			System.out.print("Enter a score: ");
			int score = sc.nextInt();
			sum += score;
		}

		return ((double) sum) / ((double) n);
	}

	public static int qualityPoints(double i) {
		// this method will return
		// 4 if i is 90-100
		// 3 if i is 80-89
		// 2 if i is 70-79
		// 1 if i is 60-69
		// 0 if i is 0-59

		if (i >= 90) {
			return 4;
		} else if (i >= 80) {
			return 3;
		} else if (i >= 70) {
			return 2;
		} else if (i >= 60) {
			return 1;
		} else {
			return 0;
		}
	}

	public static void main(String[] args) {
		System.out.print("Enter the number of scores: ");
		int n = sc.nextInt();

		double avg = average(n);

		System.out.println("Average: " + avg);

		int quality = qualityPoints(avg);

		System.out.println("GPA: " + quality);

		sc.close();
	}
}
