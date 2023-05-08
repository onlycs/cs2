package cs2;

import java.util.Scanner;

// Angad Tendulkar
// Progam 15
// Period 6

// This program will


public class Program15 {
	// write a method that inputs a string and determines if it is a palindrome
	// dont use charAt but you can use substring

	public static void isPalendrome(String str, int len) {


		String p1;
		String p2;

		if (len % 2 == 0) {
			p1 = str.substring(0, len / 2);
			p2 = str.substring(len / 2, len);
		} else {
			// ignore middle character
			p1 = str.substring(0, len / 2);
			p2 = str.substring(len / 2 + 1, len);
		}

		String p2rev = "";

		for (int i = p2.length() - 1; i >= 0; i--) {
			p2rev += p2.substring(i, i + 1);
		}

		if (p1.equals(p2rev)) {
			System.out.println("Palindrome");
		} else {
			System.out.println("Not a palindrome");
		}
	}

	// method called countA accepting a dtring param and counts
	// the number of A's without using charAt. substring is ok
	public static int countA(String str) {
		int count = 0;

		for (int i = 0; i < str.length(); i++) {
			if (str.substring(i, i + 1).equalsIgnoreCase("a")) {
				count++;
			}
		}

		return count;
	}

	// write a method that inputs two strings. first will be a sentence or more
	// the second will be a string. the method will count the number of times
	// the second string appears in the first string. the second string will
	// be more than one character
	public static void countString() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a string: ");
		String str = sc.nextLine();

		System.out.print("Enter a string to count for: ");
		String str2 = sc.nextLine();

		int count = 0;

		for (int i = 0; i < str.length() - str2.length() + 1; i++) {
			if (str.substring(i, i + str2.length()).equals(str2)) {
				count++;
			}
		}

		System.out.println("Number of times " + str2 + " appears in \"" + str + "\": " + count);
	}

	public static void main(String[] args) {
		// part 1
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a string: ");
		String str = sc.nextLine();

		int len = str.length();

		isPalendrome(str, len);


		// part 2
		System.out.print("Enter a string: ");
		str = sc.nextLine();

		System.out.println("Number of A's: " + countA(str));


		// part 3
		countString();
	}
}

/*
Enter a string: owprrcjiew
Not a palindrome
Enter a string: jewreow
Number of A's: 0
Enter a string: okewcr
Enter a string to count for: kr
Number of times kr appears in "okewcr": 0

Enter a string: racecar
Palindrome      
Enter a string: mmmmammma
Number of A's: 2
Enter a string: thisiisiismmomn
Enter a string to count for: is
Number of times is appears in "thisiisiismmomn": 3

Enter a string: loil    
Not a palindrome
Enter a string: llk
Number of A's: 0
Enter a string: aaaaaaaaaaalaaaaalaaaa
Enter a string to count for: ala
Number of times ala appears in "aaaaaaaaaaalaaaaalaaaa": 2
 */
