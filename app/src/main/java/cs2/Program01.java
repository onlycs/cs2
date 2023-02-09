// Angad Tendulkar
// Period 6
// Program 01

// This program will generate a random number between 5 and 100 and ask the user to guess it.

package cs2;

import java.util.Scanner;

public class Program01 {
	static Scanner sc = new Scanner(System.in);

	// create a new random number to start
	static int rand = (int) (Math.random() * 96) + 5;

	public static void on_invalid() {
		System.out.println("Invalid input");
	}

	public static void reset() {
		System.out.print("Do you want to play again? (y/n): ");
		String ans = sc.next().toLowerCase();

		if (ans.equals("y") || ans.equals("yes")) {
			// regenerate the random number
			rand = (int) (Math.random() * 96) + 5;
			game();
		} else if (ans.equals("n")) {
			System.out.println("Bye!");
			System.exit(0);
		} else {
			on_invalid();
			reset();
		}
	}

	public static void game() {
		int guesses = 0;

		while (true) {
			System.out.print("Enter a number between 5 and 100, -1 to quit: ");
			int num = sc.nextInt();

			// increment the number of guesses
			guesses++;

			if (num == -1) {
				reset();
			} else if (num < 5 || num > 100) {
				// decrement the number of guesses because invalid
				guesses--;
				on_invalid();
			} else if (num == rand) {
				System.out.printf("You guessed it in %d guesses!\n", guesses);
				reset();
			} else if (num > rand) {
				System.out.println("Your guess is too high");
			} else if (num < rand) {
				System.out.println("Your guess is too low");
			} else {
				// decrement the number of guesses because invalid
				guesses--;
				on_invalid();
			}
		}
	}

	public static void main(String[] args) {
		game();
	}
}

/* Output
Enter a number between 5 and 100, -1 to quit: 50
Your guess is too high
Enter a number between 5 and 100, -1 to quit: -1
Do you want to play again? (y/n): y
Enter a number between 5 and 100, -1 to quit: 50
Your guess is too high
Enter a number between 5 and 100, -1 to quit: 20
Your guess is too low
Enter a number between 5 and 100, -1 to quit: 30
Your guess is too low
Enter a number between 5 and 100, -1 to quit: 40
Your guess is too low
Enter a number between 5 and 100, -1 to quit: 45
Your guess is too low
Enter a number between 5 and 100, -1 to quit: 49
Your guess is too high
Enter a number between 5 and 100, -1 to quit: 48
You guessed it in 7 guesses!
Do you want to play again? (y/n): y
Enter a number between 5 and 100, -1 to quit: 60
Your guess is too high
Enter a number between 5 and 100, -1 to quit: 80
Your guess is too high
Enter a number between 5 and 100, -1 to quit: 20
Your guess is too low
Enter a number between 5 and 100, -1 to quit: 40
Your guess is too high
Enter a number between 5 and 100, -1 to quit: 20
Your guess is too low
Enter a number between 5 and 100, -1 to quit: 0
Invalid input
Enter a number between 5 and 100, -1 to quit: -1
Do you want to play again? (y/n): n
Bye!

Enter a number between 5 and 100, -1 to quit: 20
Your guess is too low
Enter a number between 5 and 100, -1 to quit: 50
Your guess is too low
Enter a number between 5 and 100, -1 to quit: 70
Your guess is too high
Enter a number between 5 and 100, -1 to quit: 60
Your guess is too high
Enter a number between 5 and 100, -1 to quit: 50
Your guess is too low
Enter a number between 5 and 100, -1 to quit: 55
Your guess is too low
Enter a number between 5 and 100, -1 to quit: 57
Your guess is too high
Enter a number between 5 and 100, -1 to quit: 56
You guessed it in 8 guesses!
Do you want to play again? (y/n): n
Bye!

Enter a number between 5 and 100, -1 to quit: 20
Your guess is too low
Enter a number between 5 and 100, -1 to quit: -1
Do you want to play again? (y/n): y
Enter a number between 5 and 100, -1 to quit: 50
Your guess is too high
Enter a number between 5 and 100, -1 to quit: 25
Your guess is too low
Enter a number between 5 and 100, -1 to quit: -1
Do you want to play again? (y/n): y
Enter a number between 5 and 100, -1 to quit: 10000
Invalid input
Enter a number between 5 and 100, -1 to quit: 90
Your guess is too high
Enter a number between 5 and 100, -1 to quit: 50
Your guess is too high
Enter a number between 5 and 100, -1 to quit: -1
Do you want to play again? (y/n): y
Enter a number between 5 and 100, -1 to quit: 50
Your guess is too high
Enter a number between 5 and 100, -1 to quit: 25
Your guess is too low
Enter a number between 5 and 100, -1 to quit: 37
Your guess is too low
Enter a number between 5 and 100, -1 to quit: 45
Your guess is too high
Enter a number between 5 and 100, -1 to quit: 40
Your guess is too low
Enter a number between 5 and 100, -1 to quit: 42
Your guess is too low
Enter a number between 5 and 100, -1 to quit: 43
You guessed it in 7 guesses!
Do you want to play again? (y/n): n
Bye!
 */
