package cs2;

import java.util.Scanner;

public class Program04 {
	public static Scanner sc = new Scanner(System.in);
	public static boolean[] seats_first_class = new boolean[5];
	public static boolean[] seats_economy = new boolean[5];

	public static int seats_first_class_ct = 5;
	public static int seats_economy_ct = 5;

	public static void ask_for_seat_economy() {
		if (seats_economy_ct > 0) {
			System.out.println("Choose one of the following seats: ");

			// display available seats
			for (int i = 0; i < seats_economy.length; i++) {
				if (!seats_economy[i]) {
					System.out.println((i + 1 + seats_first_class.length));
				}
			}

			System.out.print("Which seat would you like? ");
			int seat = sc.nextInt();

			// check if the seat is in economy
			if (seat > seats_first_class.length
					&& seat <= seats_first_class.length + seats_economy.length) {

				// check if the seat is available
				if (!seats_economy[seat - seats_first_class.length - 1]) {
					// set the seat to unavailable
					seats_economy[seat - seats_first_class.length - 1] = true;
					seats_economy_ct--;

					System.out.println("Thank you for choosing seat " + seat + ".");
				} else {
					System.out.println("Sorry, this seat is not available.");
				}
			} else {
				System.out.println("Sorry, this seat does not exist in economy.");
			}
		} else {
			System.out.println("Sorry, all seats in Economy are full.");
			String answer = "";

			// repeatedly ask the user until they give a valid answer
			while (!answer.equals("y") && !answer.equals("yes") && !answer.equals("n")
					&& !answer.equals("no")) {
				System.out.print("Would you like to be placed in First Class (y/n)? ");
				answer = sc.next().toLowerCase();

				if (answer.equals("y") || answer.equals("yes")) {
					ask_for_seat_first_class();
				} else if (answer.equals("n") || answer.equals("no")) {
					System.out.println("Next flight leaves in 3 hours.");
					return;
				} else {
					System.out.println("Sorry, I didn't understand that.");
				}
			}
		}
	}

	public static void ask_for_seat_first_class() {
		if (seats_first_class_ct > 0) {
			System.out.println("Choose one of the following seats: ");

			// display available seats
			for (int i = 0; i < seats_first_class.length; i++) {
				if (!seats_first_class[i]) {
					System.out.println((i + 1));
				}
			}

			System.out.print("Please type the number of the seat you want: ");
			int seat = sc.nextInt();

			// check if the seat is in first class
			if (seat > 0 && seat <= seats_first_class.length) {
				// check if the seat is available
				if (!seats_first_class[seat - 1]) {
					// set the seat to unavailable
					seats_first_class[seat - 1] = true;
					seats_first_class_ct--;

					System.out.println("Thank you for choosing seat " + seat + ".");
				} else {
					System.out.println("Sorry, this seat is not available.");
				}
			} else {
				System.out.println("Sorry, this seat does not exist in first class.");
			}
		} else {
			System.out.println("Sorry, all seats in First Class are full.");
			String answer = "";

			// repeatedly ask the user until they give a valid answer
			while (!answer.equals("y") && !answer.equals("yes") && !answer.equals("n")
					&& !answer.equals("no")) {
				System.out.print("Would you like to be placed in Economy (y/n)? ");
				answer = sc.next().toLowerCase();

				if (answer.equals("y") || answer.equals("yes")) {
					ask_for_seat_economy();
				} else if (answer.equals("n") || answer.equals("no")) {
					System.out.println("Next flight leaves in 3 hours.");
					return;
				} else {
					System.out.println("Sorry, I didn't understand that.");
				}
			}
		}
	}

	public static void ask_for_seat() {
		System.out.print("Would you like to be placed in (1/F)irst Class or (2/E)conomy? ");
		String answer = sc.next().toLowerCase();

		if (answer.equals("1") || answer.equals("f") || answer.equals("first")) {
			ask_for_seat_first_class();
		} else if (answer.equals("2") || answer.equals("e") || answer.equals("economy")) {
			ask_for_seat_economy();
		} else {
			System.out.println("Sorry, I didn't understand that.");
			ask_for_seat();
		}

		answer = "";

		// ask to run program again
		while (!answer.equals("y") && !answer.equals("yes") && !answer.equals("n")
				&& !answer.equals("no")) {
			System.out.print("Would you like to book another seat (y/n)? ");
			answer = sc.next().toLowerCase();

			if (answer.equals("y") || answer.equals("yes")) {
				ask_for_seat();
			} else if (answer.equals("n") || answer.equals("no")) {
				System.out.println("Thank you for flying with us.");
				System.exit(0);
			} else {
				System.out.println("Sorry, I didn't understand that.");
			}
		}
	}

	public static void main(String[] args) {
		ask_for_seat();
	}
}

/*
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 1
Choose one of the following seats: 
1
2
3
4
5
Please type the number of the seat you want: 1
Thank you for choosing seat 1.
Would you like to book another seat (y/n)? y
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 1
Choose one of the following seats: 
2
3
4
5
Please type the number of the seat you want: 2
Thank you for choosing seat 2.
Would you like to book another seat (y/n)? y
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 1
Choose one of the following seats: 
3
4
5
Please type the number of the seat you want: 3
Thank you for choosing seat 3.
Would you like to book another seat (y/n)? y
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 1
Choose one of the following seats: 
4
5
Please type the number of the seat you want: 4
Thank you for choosing seat 4.
Would you like to book another seat (y/n)? y
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 1
Choose one of the following seats: 
5
Please type the number of the seat you want: 5
Thank you for choosing seat 5.
Would you like to book another seat (y/n)? y
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 1
Sorry, all seats in First Class are full.
Would you like to be placed in Economy (y/n)? y
Choose one of the following seats: 
6
7
8
9
10
Which seat would you like? 6
Thank you for choosing seat 6.
Would you like to book another seat (y/n)? y
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 1
Sorry, all seats in First Class are full.
Would you like to be placed in Economy (y/n)? n
Would you like to book another seat (y/n)? coder@workspace:~/code/cs2$  cd /home/coder/code/cs2 ; /usr/bin/env /usr/lib/jvm/java-19-openjdk/bin/java @/tmp/cp_9uy3q8zxul7o3wr6qa7cp56n.argfile cs2.Program04 
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 1
Choose one of the following seats: 
1
2
3
4
5
Please type the number of the seat you want: 1
Thank you for choosing seat 1.
Would you like to book another seat (y/n)? y
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 1
Choose one of the following seats: 
2
3
4
5
Please type the number of the seat you want: 2
Thank you for choosing seat 2.
Would you like to book another seat (y/n)? y
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 1
Choose one of the following seats: 
3
4
5
Please type the number of the seat you want: 3
Thank you for choosing seat 3.
Would you like to book another seat (y/n)? y
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 1
Choose one of the following seats: 
4
5
Please type the number of the seat you want: 4
Thank you for choosing seat 4.
Would you like to book another seat (y/n)? y
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 1
Choose one of the following seats: 
5
Please type the number of the seat you want: 5
Thank you for choosing seat 5.
Would you like to book another seat (y/n)? y
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 1
Sorry, all seats in First Class are full.
Would you like to be placed in Economy (y/n)? n
Next flight leaves in 3 hours.
Would you like to book another seat (y/n)? y
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 1
Sorry, all seats in First Class are full.
Would you like to be placed in Economy (y/n)? y
Choose one of the following seats: 
6
7
8
9
10
Which seat would you like? 6
Thank you for choosing seat 6.
Would you like to book another seat (y/n)? y
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 2
Choose one of the following seats: 
7
8
9
10
Which seat would you like? 7
Thank you for choosing seat 7.
Would you like to book another seat (y/n)? y
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 2
Choose one of the following seats: 
8
9
10
Which seat would you like? 8
Thank you for choosing seat 8.
Would you like to book another seat (y/n)? y
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 2
Choose one of the following seats: 
9
10
Which seat would you like? 9
Thank you for choosing seat 9.
Would you like to book another seat (y/n)? y
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 2
Choose one of the following seats: 
10
Which seat would you like? 10
Thank you for choosing seat 10.
Would you like to book another seat (y/n)? y
Would you like to be placed in (1/F)irst Class or (2/E)conomy? 1
Sorry, all seats in First Class are full.
Would you like to be placed in Economy (y/n)? n
Next flight leaves in 3 hours.
Would you like to book another seat (y/n)? n
Thank you for flying with us.
 */
