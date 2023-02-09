// Angad Tendulkar
// Period 6
// Program 02

// This program will play rock-paper-scissors with the user.

package cs2;

import java.util.Scanner;

public class Program02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int wins = 0;
		int losses = 0;
		int ties = 0;

		while (true) {
			System.out.print("Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: ");
			String ans = sc.next().toLowerCase();

			if (ans.equals("quit") || ans.equals("q")) {
				System.out.println("Bye!");
				break;
			} else if (ans.equals("rock") || ans.equals("r") || ans.equals("paper")
					|| ans.equals("p") || ans.equals("scissors") || ans.equals("s")) {
				// generate a random number between 0 and 2
				int rand = (int) (Math.random() * 3);

				// 0 = rock, 1 = paper, 2 = scissors
				if (rand == 0) {
					System.out.println("Computer chose rock");
				} else if (rand == 1) {
					System.out.println("Computer chose paper");
				} else if (rand == 2) {
					System.out.println("Computer chose scissors");
				}

				// convert user choice to a number
				int user = 0;
				if (ans.equals("rock") || ans.equals("r")) {
					user = 0;
					System.out.println("User chose rock");
				} else if (ans.equals("paper") || ans.equals("p")) {
					user = 1;
					System.out.println("User chose paper");
				} else if (ans.equals("scissors") || ans.equals("s")) {
					user = 2;
					System.out.println("User chose scissors");
				} else {
					System.out.println("Invalid input");
					continue;
				}

				// compare the two numbers
				if (user == rand) {
					System.out.println("It's a tie!");
					ties++;
				} else if ((rand + 1) % 3 == user) {
					System.out.println("You win!");
					wins++;
				} else if ((rand + 2) % 3 == user) {
					System.out.println("You lose!");
					losses++;
				} else {
					System.out.println("Invalid input");
				}
			} else {
				System.out.println("Invalid input");
			}
		}

		sc.close();

		System.out.println("Wins: " + wins);
		System.out.println("Losses: " + losses);
		System.out.println("Ties: " + ties);
	}
}

/*output:
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: p
Computer chose paper
User chose paper
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: p
Computer chose rock
User chose paper
You win!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: p
Computer chose paper
User chose paper
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: p
Computer chose paper
User chose paper
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: p
Computer chose scissors
User chose paper
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: p
Computer chose scissors
User chose paper
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: p
Computer chose scissors
User chose paper
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: p
Computer chose paper
User chose paper
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: p
Computer chose scissors
User chose paper
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: p
Computer chose rock
User chose paper
You win!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: p
Computer chose rock
User chose paper
You win!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: q
Bye!
Wins: 3
Losses: 4
Ties: 4

Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose rock
User chose rock
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose rock
User chose rock
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose rock
User chose rock
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose paper
User chose rock
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose paper
User chose rock
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose rock
User chose rock
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose paper
User chose rock
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose paper
User chose rock
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose paper
User chose rock
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose rock
User chose rock
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose paper
User chose rock
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose paper
User chose rock
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose rock
User chose rock
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose paper
User chose rock
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose paper
User chose rock
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose rock
User chose rock
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose rock
User chose rock
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose rock
User chose rock
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose rock
User chose rock
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose paper
User chose rock
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose rock
User chose rock
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose paper
User chose rock
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose scissors
User chose rock
You win!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose paper
User chose rock
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose rock
User chose rock
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose rock
User chose rock
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose rock
User chose rock
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose paper
User chose rock
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose paper
User chose rock
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: q
Bye!
Wins: 1
Losses: 14
Ties: 14

Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose paper
User chose rock
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: p
Computer chose paper
User chose paper
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: s
Computer chose paper
User chose scissors
You win!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: s
Computer chose paper
User chose scissors
You win!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: p
Computer chose rock
User chose paper
You win!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose scissors
User chose rock
You win!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: s
Computer chose paper
User chose scissors
You win!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: pr
Invalid input
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: rrrp
Invalid input
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: p
Computer chose rock
User chose paper
You win!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: s
Computer chose rock
User chose scissors
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose rock
User chose rock
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: p
Computer chose scissors
User chose paper
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: s
Computer chose scissors
User chose scissors
It's a tie!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: p
Computer chose scissors
User chose paper
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: s
Computer chose rock
User chose scissors
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose paper
User chose rock
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose paper
User chose rock
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose scissors
User chose rock
You win!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose paper
User chose rock
You lose!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose scissors
User chose rock
You win!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: r
Computer chose scissors
User chose rock
You win!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: ps
Invalid input
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: p
Computer chose rock
User chose paper
You win!
Enter (r)ock, (p)aper, (s)cissors, or (q)uit to exit: q
Bye!
Wins: 10
Losses: 8
Ties: 3
*/
