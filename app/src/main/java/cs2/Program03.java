// Angad Tendulkar
// Period 6
// Program 03

// This program will ask the user to enter 14 numbers from 0 through 8. It will then
// count how many times each number was entered.

package cs2;

import java.util.Scanner;

public class Program03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] nums = new int[14];
		int[] counts = new int[9];

		for (int i = 0; i < nums.length; i++) {
			System.out.printf("Enter number %d from 0 through 8: ", i + 1);
			int num = sc.nextInt();
			if (num < 0 || num > 8) {
				System.out.println("Invalid input");

				// deincrement i so that i++ will make it the same value
				i--;

				continue;
			}
			nums[i] = num;
		}

		for (int i = 0; i < nums.length; i++) {
			counts[nums[i]]++;
		}

		for (int i = 0; i < counts.length; i++) {
			System.out.println(i + " was entered " + counts[i] + " times");
		}

		sc.close();
	}
}

/* output
Enter number 1 from 0 through 8: 10
Invalid input
Enter number 1 from 0 through 8: 20
Invalid input
Enter number 1 from 0 through 8: 2
Enter number 2 from 0 through 8: 3
Enter number 3 from 0 through 8: 4
Enter number 4 from 0 through 8: 4
Enter number 5 from 0 through 8: 9
Invalid input
Enter number 5 from 0 through 8: 8
Enter number 6 from 0 through 8: 84
Invalid input
Enter number 6 from 0 through 8: 2
Enter number 7 from 0 through 8: 4
Enter number 8 from 0 through 8: 1
Enter number 9 from 0 through 8: 9
Invalid input
Enter number 9 from 0 through 8: 8
Enter number 10 from 0 through 8: 8
Enter number 11 from 0 through 8: 2
Enter number 12 from 0 through 8: 3
Enter number 13 from 0 through 8: 1
Enter number 14 from 0 through 8: 8
0 was entered 0 times
1 was entered 2 times
2 was entered 3 times
3 was entered 2 times
4 was entered 3 times
5 was entered 0 times
6 was entered 0 times
7 was entered 0 times
8 was entered 4 times

Enter number 1 from 0 through 8: 8
Enter number 2 from 0 through 8: 7
Enter number 3 from 0 through 8: 6
Enter number 4 from 0 through 8: 5
Enter number 5 from 0 through 8: 4
Enter number 6 from 0 through 8: 3
Enter number 7 from 0 through 8: 2
Enter number 8 from 0 through 8: 1
Enter number 9 from 0 through 8: 0
Enter number 10 from 0 through 8: 4
Enter number 11 from 0 through 8: 8
Enter number 12 from 0 through 8: 2
Enter number 13 from 0 through 8: 0
Enter number 14 from 0 through 8: 1
0 was entered 2 times
1 was entered 2 times
2 was entered 2 times
3 was entered 1 times
4 was entered 2 times
5 was entered 1 times
6 was entered 1 times
7 was entered 1 times
8 was entered 2 times

Enter number 1 from 0 through 8: 29
Invalid input
Enter number 1 from 0 through 8: 0
Enter number 2 from 0 through 8: 2
Enter number 3 from 0 through 8: 4
Enter number 4 from 0 through 8: 1
Enter number 5 from 0 through 8: 9
Invalid input
Enter number 5 from 0 through 8: 8
Enter number 6 from 0 through 8: 3
Enter number 7 from 0 through 8: 2
Enter number 8 from 0 through 8: 1
Enter number 9 from 0 through 8: 0
Enter number 10 from 0 through 8: 0
Enter number 11 from 0 through 8: 8
Enter number 12 from 0 through 8: 3
Enter number 13 from 0 through 8: 2
Enter number 14 from 0 through 8: 1
0 was entered 3 times
1 was entered 3 times
2 was entered 3 times
3 was entered 2 times
4 was entered 1 times
5 was entered 0 times
6 was entered 0 times
7 was entered 0 times
8 was entered 2 times
 */
