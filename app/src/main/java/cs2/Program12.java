package cs2;

import java.util.Scanner;

// Angad Tendulkar
// Period 6
// Program 12

// This program will have a recursive method called
// power (long base, long exponent) that will eventually
// return 3*3*3*3 if the base is 3 and the exponent is 4

// Assume exponent is always >1

public class Program12 {
	public static long power(long base, long exponent) {
		if (exponent == 1) {
			return base;
		}
		return base * power(base, exponent - 1);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("Enter a base (-1 to quit): ");
			long base = sc.nextInt();

			if (base == -1) {
				break;
			}

			System.out.print("Enter an exponent (-1 to quit): ");
			long exponent = sc.nextInt();

			if (exponent == -1) {
				break;
			}

			System.out.println(
					base + " to the power of " + exponent + " is " + power(base, exponent));
		}

		sc.close();
	}
}

/*
Enter a base (-1 to quit): 3
Enter an exponent (-1 to quit): 4
3 to the power of 4 is 81
Enter a base (-1 to quit): -1

Enter a base (-1 to quit): 7
Enter an exponent (-1 to quit): 9
7 to the power of 9 is 40353607
Enter a base (-1 to quit): -1

Enter a base (-1 to quit): 10
Enter an exponent (-1 to quit): 10
10 to the power of 10 is 10000000000
Enter a base (-1 to quit): -1
 */
