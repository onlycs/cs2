// Angad Tendulkar
// Period 6
// Program 8

// This program will check if a number is prime and check primes 1-100

package cs2;

import java.util.ArrayList;
import java.util.Scanner;


interface OptionFn {
	void run();
}


// Should have made this long ago
class Options {
	public ArrayList<String> keys = new ArrayList<String>();
	public ArrayList<String> values = new ArrayList<String>();
	public ArrayList<OptionFn> functions = new ArrayList<OptionFn>();

	public Options add(String key, String value, OptionFn fn) {
		keys.add(key);
		values.add(value);
		functions.add(fn);

		return this;
	}

	public void run() {
		Scanner sc = new Scanner(System.in);

		// This program will ask the user to choose an option
		// then return the id of the option

		// print the options
		for (int i = 0; i < keys.size(); i++) {
			System.out.println(keys.get(i) + ": " + values.get(i));
		}

		// ask the user to choose an option
		System.out.print("\nEnter an option: ");
		String option = sc.next().trim();

		// return the id of the option
		int index = keys.indexOf(option);
		functions.get(index).run();
	}
}


public class Program08 {
	public static boolean is_prime(int i) {
		// This program will determine if i is prime
		
		if (i==1) {
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
		Options opts =
				new Options().add("1", "Check if a number is prime", Program08::is_prime_user)
						.add("2", "Print all primes from 1-100", Program08::primes_100)
						.add("q", "Quit", () -> {
							System.exit(0);
						});

		while (true) {
			opts.run();
		}
	}
}
