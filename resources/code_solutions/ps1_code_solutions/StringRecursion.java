/*
 * StringRecursion.java
 *
 * Computer Science E-119, Harvard University
 *
 * A collection of recursive methods that manipulate strings.
 */

public class StringRecursion {
    /**
     * printReverse - takes a String and prints it in reverse order
     */
    public static void printReverse(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        
        // Print the rest of the string in reverse order.
        printReverse(str.substring(1));
        
        // Once everything else is printed, print the first character.
        System.out.print(str.charAt(0));
    }

    /**
     * reverse - takes a String and returns a String that is the
     * reverse of the original one.
     */
    public static String reverse(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        
        return reverse(str.substring(1)) + str.charAt(0);
    }

    /**
     * trim - uses recursion to remove leading and trailing spaces
     * from str and return the result
     */
    public static String trim(String str) {
        // base case 1: null or empty string
        if (str == null || str.length() == 0) {
            return str;
        }

        char first = str.charAt(0);
        char last = str.charAt(str.length() - 1);

        // base case 2: nothing to trim
        if (first != ' ' && last != ' ') {
            return str;
        }

        // trim one space from the front and/or back
        if (first == ' ') {
            str = str.substring(1);
        }
        if (last == ' ') {
            str = str.substring(0, str.length() - 1);
        }

        // recursively trim the remaining string and return the result
        return trim(str);
    }
}