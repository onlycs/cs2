package cs2;

// Angad Tendulkar


import java.util.*;

public class Program14 {
	public static String multiConCat(String from, int ct) {
		if (ct <= 1) {
			return from;
		}

		return from + " " + multiConCat(from, ct - 1);
	}

	public static String multiConCat(String from) {
		return multiConCat(from, 2);
	}

	public static void mcc_user_with_ct() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Text to repeat: ");
		String text = sc.nextLine();

		System.out.print("Times to repeat: ");
		int ct = sc.nextInt();

		System.out.println(multiConCat(text, ct));
	}

	public static void mcc_user() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Text to repeat: ");
		String text = sc.nextLine();

		System.out.println(multiConCat(text));
	}

	public static void main(String[] args) {
		mcc_user_with_ct();
		mcc_user();
	}
}

/* output:
Text to repeat: ccc
Times to repeat: 132
ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc ccc
Text to repeat: l
l l

Text to repeat: hello
Times to repeat: 3
hello hello hello
Text to repeat: hi 
hi hi

Text to repeat: t
Times to repeat: 5
t t t t t
Text to repeat: tt
tt tt
 */
