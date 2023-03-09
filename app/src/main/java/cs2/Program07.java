// Angad Tendulkar
// Period 6
// Program 7

// This program will generate a random number in a range

package cs2;

import java.util.Scanner;

public class Program07 {
	public static int randomInRange(int i, int j) {
		// This method will return a random number
		// inclusive of the range of i to j
		// if j > i return 0

		if (j < i) {
			return 0;
		}

		// find the possible number of numbers
		int range = j - i + 1;

		// generate a random number
		int rand = (int) (Math.random() * range) + i;

		return rand;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("Enter an integer (-1 to exit): ");
			int i = sc.nextInt();

			if (i == -1) {
				break;
			}

			System.out.print("Enter another integer (-1 to exit): ");
			int j = sc.nextInt();

			if (j == -1) {
				break;
			}

			int rand = randomInRange(i, j);

			System.out.println("Random number: " + rand);
		}

		sc.close();
	}
}
