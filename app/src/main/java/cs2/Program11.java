// Angad Tendulkar
// Program 11
// Period 6

// This program will have a method called squareOfAsterisks
// that will take an integer as a parameter and print a square

package cs2;

import java.util.Scanner;

public class Program11 {
	public static void squareOfAsterisks(int n, char fillChar) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(fillChar + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("Enter a positive integer (0 to quit): ");
			int n = sc.nextInt();

			System.out.println("Enter a single character to fill the square: ");
			String fillChar = sc.next();

			if (n == 0) {
				break;
			}
			squareOfAsterisks(n, fillChar.charAt(0));
		}

		sc.close();
	}
}
