// Angad Tendulkar
// Program 11
// Period 6

// This program will have a method called squareOfAsterisks
// that will take an integer as a parameter and print a square


import java.util.*;

interface OptionFn {
    void run();
}

// Should have made this long ago
class Options {
    public ArrayList<String> keys = new ArrayList<String>();
    public ArrayList<String> values = new ArrayList<String>();
    public ArrayList<OptionFn> functions = new ArrayList<OptionFn>();

    public Options add(String key, String value, OptionFn fn) {
        keys.add(key);
        values.add(value);
        functions.add(fn);

        return this;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        // This program will ask the user to choose an option
        // then return the id of the option

        // print the options
        for (int i = 0; i < keys.size(); i++) {
            System.out.println(keys.get(i) + ": " + values.get(i));
        }

        // ask the user to choose an option
        System.out.print("\nEnter an option: ");
        String option = sc.next().trim();

        // return the id of the option
        int index = keys.indexOf(option);
        functions.get(index).run();
    }
}

public class Program11 {
    public static void squareOfAsterisks(int n, String fillChar) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(fillChar + " ");
            }
            System.out.println();
        }
    }

    public static void squareOfAsterisks(int n) {
        squareOfAsterisks(n, "#");
    }

    public static void main(String[] args) {
        Options opts = new Options().add("1", "Print a square of asterisks", () -> {
                    Scanner sc = new Scanner(System.in);
                    
                    System.out.print("How big should the square be? ");
                    int ct = sc.nextInt();
                    
                    squareOfAsterisks(ct);
                })
                .add("2", "Print a square of whatever character you want", () -> {
                    Scanner sc = new Scanner(System.in);
                    
                    System.out.print("How big should the square be? ");
                    int ct = sc.nextInt();
                    
                    System.out.print("What character to use? ");
                    String sq_char = Character.toString(sc.next().charAt(0));
                    
                    squareOfAsterisks(ct, sq_char);
                })
                .add("q", "Quit", () -> {
                    System.exit(0);
                });
                
        while (true) {
            opts.run();
        }
    }
}

/*
1: Print a square of asterisks
2: Print a square of whatever character you want
q: Quit

Enter an option: 1
How big should the square be? 4
# # # # 
# # # # 
# # # # 
# # # # 
1: Print a square of asterisks
2: Print a square of whatever character you want
q: Quit

Enter an option: q

== run 2 ==

1: Print a square of asterisks
2: Print a square of whatever character you want
q: Quit

Enter an option: 2
How big should the square be? 3
What character to use? $
$ $ $ 
$ $ $ 
$ $ $ 
1: Print a square of asterisks
2: Print a square of whatever character you want
q: Quit

Enter an option: q

== run 3 ==

1: Print a square of asterisks
2: Print a square of whatever character you want
q: Quit

Enter an option: 2
How big should the square be? 5
What character to use? m
m m m m m 
m m m m m 
m m m m m 
m m m m m 
m m m m m 
1: Print a square of asterisks
2: Print a square of whatever character you want
q: Quit

Enter an option: q
 */
