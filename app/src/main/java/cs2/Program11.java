package cs2;

// Angad Tendulkar
// Program 11
// Period 6

// This program will have a method called squareOfAsterisks
// that will take an integer as a parameter and print a square


import java.util.*;

public class Program11 {
	public static void squareOfAsterisks(int n, String fillChar) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(fillChar + " ");
			}
			System.out.println();
		}
	}

	public static void squareOfAsterisks(int n) {
		squareOfAsterisks(n, "#");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("How big should the square be? ");
		int ct = sc.nextInt();

		System.out.print("What character to use? ");
		String sq_char = Character.toString(sc.next().charAt(0));

		squareOfAsterisks(ct, sq_char);

		System.out.print("How big should the square be? ");
		ct = sc.nextInt();

		squareOfAsterisks(ct);
	}
}
