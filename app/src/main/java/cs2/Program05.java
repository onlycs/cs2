package cs2;

import java.util.Scanner;

public class Program05 {
	String[] squares = new String[70];
	int tortoise = 0;
	int hare = 0;

	public void move_tortoise() {
		int move = 0;
		int rand = (int) (Math.random() * 10);
		if (rand < 5) {
			move = 3;
		} else if (rand < 7) {
			move = -2;
		} else {
			move = 1;
		}

		tortoise += move;
	}

	public void move_hare() {
		int move = 0;
		int rand = (int) (Math.random() * 10);
		if (rand < 3) {
			move = 0;
		} else if (rand < 5) {
			move = 6;
		} else if (rand < 6) {
			move = -4;
		} else if (rand < 8) {
			move = 2;
		} else {
			move = -1;
		}

		hare += move;
	}

	public void array_regen() {
		for (int i = 0; i < squares.length; i++) {
			squares[i] = " ";
		}

		// tortoise and hare pos's are unchecked. prevent oob.
		if (tortoise < 0) {
			tortoise = 0;
		} else if (tortoise > squares.length - 1) {
			tortoise = squares.length - 1;
		}

		if (hare < 0) {
			hare = 0;
		} else if (hare > squares.length - 1) {
			hare = squares.length - 1;
		}

		if (tortoise == hare) {
			// only allow one character to be printed per square
			// prevent oob
			if (tortoise > 2 && tortoise < squares.length - 2) {
				squares[tortoise - 2] = "O";
				squares[tortoise - 1] = "U";
				squares[tortoise] = "C";
				squares[tortoise + 1] = "H";
			} else if (tortoise < 2) {
				squares[tortoise] = "O";
				squares[tortoise + 1] = "U";
				squares[tortoise + 2] = "C";
				squares[tortoise + 3] = "H";
			} else {
				squares[tortoise - 3] = "O";
				squares[tortoise - 2] = "U";
				squares[tortoise - 1] = "C";
				squares[tortoise] = "H";
			}
		} else {
			squares[tortoise] = "T";
			squares[hare] = "H";
		}

		// finish line
		squares[squares.length - 1] = "|";
	}

	public void tick() {
		move_tortoise();
		move_hare();
		array_regen();

		System.out.print("\f");
		for (String s : squares) {
			System.out.print(s);
		}
		System.out.println();
	}


	public void run() throws InterruptedException {
		Scanner sc = new Scanner(System.in);

		System.out.print("What should the tick speed be (in milliseconds)? ");
		int tick_speed = sc.nextInt();
		sc.close();

		while (tortoise < squares.length - 1 && hare < squares.length - 1) {
			tick();
			Thread.sleep(tick_speed);
		}

		if (tortoise >= squares.length - 1) {
			System.out.println("Tortoise wins.");
		} else {
			System.out.println("Hare wins.");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Program05 p = new Program05();
		p.run();
	}
}
