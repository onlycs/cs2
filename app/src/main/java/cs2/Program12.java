package cs2;

import java.util.Scanner;

// Angad Tendulkar
// Period 6
// Program 12

// This program will have a recursive method called
// power (int base, int exponent) that will eventually
// return 3*3*3*3 if the base is 3 and the exponent is 4

// Assume exponent is always >1

public class Program12 {
	public static int power(int base, int exponent) {
		if (exponent == 1) {
			return base;
		}
		return base * power(base, exponent - 1);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("Enter a base (-1 to quit): ");
			int base = sc.nextInt();

			if (base == -1) {
				break;
			}

			System.out.print("Enter an exponent (-1 to quit): ");
			int exponent = sc.nextInt();

			if (exponent == -1) {
				break;
			}

			System.out.println(
					base + " to the power of " + exponent + " is " + power(base, exponent));
		}
	}
}
