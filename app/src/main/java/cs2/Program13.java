package cs2;

import java.util.Scanner;

// Angad Tendulkar
// Period 6
// Program 13

// This program will do a linear search on an array of 20 elements

public class Program13 {
	public static <T> int search(T[] arr, int length, T index) {
		for (int i = 0; i < length; i++) {
			if (arr[i] == index) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		Integer[] arr = new Integer[] {7, 3, 9, 2, 5, 1, 8, 4, 6, 0, 11, 19, 12, 15, 10, 18, 14, 16,
				13, 17};
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("Enter a number to search for (-1 to exit): ");
			int num = sc.nextInt();

			if (num == -1) {
				break;
			}

			int index = search(arr, arr.length, num);
			if (index == -1) {
				System.out.println("Number not found");
			} else {
				System.out.println("Number found at index " + index);
			}
		}

		sc.close();
	}
}
