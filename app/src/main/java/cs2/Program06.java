// Angad Tendulkar
// Period 6
// Program 6

// This program is a game of battleship

package cs2;

import java.util.*;

enum Square {
	EMPTY, HIT, MISS, SHIP;

	public String toString() {
		switch (this) {
			case EMPTY:
				return " ";
			case HIT:
				return "X";
			case MISS:
				return "O";
			case SHIP:
				return "S";
		}
		return " ";
	}
}


public class Program06 {
	Square[][] board;
	Scanner sc = new Scanner(System.in);
	int shots = 25;

	public void init_board() {
		Square[][] board = new Square[8][10];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = Square.EMPTY;
			}
		}

		// select a random location for the first square of the ship
		int row = (int) (Math.random() * board.length);
		int col = (int) (Math.random() * board[0].length);


		// make a while loop that will pick a random direction, check if a boat will fit, if it does add the boat and exit the loop, and if it doesn't, loops again
		boolean direction_possible = false;
		while (!direction_possible) {
			// pick a random direction
			int direction = (int) (Math.random() * 4);
			switch (direction) {
				case 0: // up
					// check if the ship will fit on the board
					if (row - 3 >= 0) {
						direction_possible = true;

						// create 4 squares for the ship
						for (int i = 0; i < 4; i++) {
							board[row - i][col] = Square.SHIP;
						}
					}
					break;
				case 1: // right
					// check if the ship will fit on the board
					if (col + 3 < board[0].length) {
						direction_possible = true;

						// create 4 squares for the ship
						for (int i = 0; i < 4; i++) {
							board[row][col + i] = Square.SHIP;
						}
					}
					break;
				case 2: // down
					// check if the ship will fit on the board
					if (row + 3 < board.length) {
						direction_possible = true;

						// create 4 squares for the ship
						for (int i = 0; i < 4; i++) {
							board[row + i][col] = Square.SHIP;
						}
					}
					break;
				case 3: // left
					// check if the ship will fit on the board
					if (col - 3 >= 0) {
						direction_possible = true;

						// create 4 squares for the ship
						for (int i = 0; i < 4; i++) {
							board[row][col - i] = Square.SHIP;
						}
					}
					break;
			}
		}

		this.board = board;
	}

	public void display_board() {
		System.out.print("  ");
		for (int i = 0; i < board[0].length; i++) {
			System.out.print(i + " ");
		}
		System.out.println();

		for (int i = 0; i < board.length; i++) {
			// you can increment characters to get the next letter
			System.out.print((char) ('a' + i) + " ");

			// print the row
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}

			System.out.println();
		}
	}

	public void display_frame(String status) {
		System.out.print("\f");
		System.out.println("    Welcome to Battleship!    ");
		System.out.println("  X=Hit               O=Miss  ");
		System.out.println("                              ");
		System.out.println(status);
		System.out.println("        Shots left: " + shots + "\n");
		display_board();
		System.out.println("\n\n");
	}

	public int[] ask() {
		System.out.print("Enter a row and column (i.e. a5) or 'quit': ");
		String input = sc.nextLine();

		if (input.equals("quit")) {
			System.exit(0);
		}

		// grab the row and column from the input
		int row = input.charAt(0) - 'a';
		int col = input.charAt(1) - '0';

		// check if the user entered a valid row and column
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
				|| input.length() != 2) {
			display_frame("Invalid row or column. Try again.");
			return ask();
		}

		// check if the user has already guessed this square
		if (board[row][col] == Square.HIT || board[row][col] == Square.MISS) {
			display_frame("You have already guessed this square. Try again.");
			return ask();
		}

		return new int[] {row, col};
	}

	public void start() {
		init_board();

		String status = "Guess a square to hit the ship.";

		while (true) {
			display_frame(status);
			int[] guess = ask();

			if (board[guess[0]][guess[1]] == Square.SHIP) {
				board[guess[0]][guess[1]] = Square.HIT;
				status = "You hit the ship! Guess again.";
			} else {
				board[guess[0]][guess[1]] = Square.MISS;
				status = "You missed the ship. Guess again.";
			}

			shots--;

			boolean won =
					Arrays.stream(board).flatMap(Arrays::stream).noneMatch(s -> s == Square.SHIP);

			if (shots == 0 && !won) {
				System.out.println("You lose");
				System.exit(0);
			}

			if (won)
				break;
		}

		System.out.println("\fYou won!");
	}

	public static void main(String[] args) {
		new Program06().start();
	}
}
