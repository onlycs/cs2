import java.util.Scanner;

// Angad Tendulkar
// Peroid 6
// Program 20

// This program will calculate the greatest common divisor of two numbers

public class Program20 {
	public static int ctest = 0;

	public static int gcd(int a, int b) {
		// a is greater than b here

		if (a < b) return gcd(b, a);
		else if (a % b == 0) return b;
		else return gcd(b, a % b);
	}

	public static void test(int a, int b) {
		System.out.println("===".repeat(10));
		System.out.printf("gcd(%d, %d) => %d\n", a, b, gcd(a, b));
	}

	public static String prompt(String prompt) {
		Scanner sc = new Scanner(System.in);
		System.out.print(prompt);
		String ln = sc.nextLine();

		if (ln.equals("q") || ln.equals("quit")) {
			System.exit(0);
		}

		return ln;
	}

	public static void main(String[] args) {
		System.out.println("Program 20: GCD");
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("\nRun %d\n", ++ctest);
			
			String a_str = prompt("Enter number 1 (q to quit): ");
			String b_str = prompt("Enter number 2 (q to quit): ");

			int a = Integer.parseInt(a_str);
			int b = Integer.parseInt(b_str);

			test(a, b);
		}
	}
}

/*
Program 20: GCD

Run 1
Enter number 1 (q to quit): 8
Enter number 2 (q to quit): 24
==============================
gcd(8, 24) => 8

Run 2
Enter number 1 (q to quit): 8
Enter number 2 (q to quit): 20
==============================
gcd(8, 20) => 4

Run 3
Enter number 1 (q to quit): 9
Enter number 2 (q to quit): 36
==============================
gcd(9, 36) => 9

Run 4
Enter number 1 (q to quit): 9
Enter number 2 (q to quit): 39
==============================
gcd(9, 39) => 3

Run 5
Enter number 1 (q to quit): q
 */
