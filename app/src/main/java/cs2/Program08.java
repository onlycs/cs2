// Angad Tendulkar
// Period 6
// Program 8

// This program will check if a number is prime and check primes 1-100

package cs2;

import java.util.Scanner;

public class Program08 {
	public static boolean is_prime(int i) {
		// This program will determine if i is prime

		if (i == 1) {
			return false;
		}

		// loop over every number from 2 to i
		for (int j = 2; j < i; j++) {
			if (i % j == 0) {
				return false;
			}
		}

		return true;
	}

	public static void is_prime_user() {
		Scanner sc = new Scanner(System.in);

		// This program will ask the user for a number
		// then check if it is prime with Program08A

		System.out.print("Enter a number: ");
		int i = sc.nextInt();

		if (is_prime(i)) {
			System.out.println(i + " is prime");
		} else {
			System.out.println(i + " is not prime");
		}
	}

	public static void primes_100() {
		// This program will print primes 1-100
		for (int i = 1; i <= 100; i++) {
			if (is_prime(i)) {
				System.out.println(i);
			}
		}
	}

	public static void main(String[] args) {
		is_prime_user();
		primes_100();
	}
}
