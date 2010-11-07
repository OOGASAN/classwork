/*
 * Lagrange.java
 *
 * Solutions, Spring 2008
 *
 * Author: Computer Science E-119 staff
 */

import java.util.*;

/**
 * An application that reads positive integers from the user and
 * breaks them into sums of at most four positive perfect squares.
 */
public class Lagrange {
    private int number;      // the number we are trying to break up
    private int[] terms;     // the terms in the sum
    private int numTerms;    // the number of terms used thus far

    /*
     * largestSquare - returns the largest perfect square less than or
     * or equal to the specified positive integer.
     */
    private static int largestSquare(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n must be positive");
        int temp = (int)Math.sqrt(n);
        return temp * temp;
    }

    /**
     * constructor
     */
    public Lagrange(int num) {
        terms = new int[4];
        numTerms = 0;
        this.number = num;
    }
    
    /**
     * toString - used when printing the result.  Creates a string of the
     * following form:
     *     num = term0 + term1 + ...
     * If the terms do not add up to the number, it concludes with a ?
     * to indicate that the process of finding the sum is not yet complete.
     */
    public String toString() {
        String str = number + " = ";
        int sum = 0;

        for (int i = 0; i < numTerms; i++) {
            str += terms[i];
            sum += terms[i];
            str += (sum < number) ? " + " : "";
        }
        str += (sum < number) ? "?" : "";

        return str;    
    }
    
    /**
     * findSum - this is the key recursive method that we use to
     * find the sum.  It breaks the specified number into a sum of at
     * most maxTerms perfect squares.  A given invocation of this
     * method tries different values for the current term in the sum,
     * and then it makes a recursive call to break up what's left of
     * the number.
     */
    public boolean findSum(int num, int maxTerms) {
        if (num == 0)
            return true;     // base case: we've found a solution
        else if (maxTerms == 0)
            return false;    // base case: we've run out of terms, so backtrack

        // Try the possible values for the current term -- beginning
        // with the largest square less than or equal to num and
        // working down from there.  The index of the loop starts out
        // as the square root of the largest square and is decremented
        // by one each time.  Inside the loop, we square i to get the
        // actual value.  Because we only try valid values, we don't
        // need an isValid() check inside the loop.
        int startSquare = largestSquare(num);
        for (int i = (int)Math.sqrt(startSquare); i > 0; i--) {
            int square = i * i;

            // This is one example of what we had in mind for the
            // grad-credit/extra-credit problem.  Using square for *all*
            // of the remaining terms would give us a sum of square*maxTerms.
            // As a result, if this product is less than num, then
            // square -- or any smaller value -- can't possibly work
            // as the value of the current term, so we backtrack now.
            if ((square * maxTerms) < num)
                return false;

            // The following two lines correspond to applyValue() from
            // the backtracking template.
            terms[numTerms] = square;
            numTerms++;
    
            // Make a recursive call to find the remaining terms.
            // Note that we call the method to break what's left of the
            // number into at most (maxTerms - 1) perfect squares.
            if (findSum(num - square, maxTerms - 1))
                return true;
    
            // If we get here, we've backtracked. 
            // The following two lines correspond to removeValue()
            // from the backtracking template.
            numTerms--;
            terms[numTerms] = 0;
        }
    
        return false;      // backtrack
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a positive integer (-1 to quit): ");
            int n = in.nextInt();
            in.nextLine();
            
            if (n == -1) {
                System.out.println("Goodbye!");
                return;
            } else if (n <= 0)
                continue;
    
            Lagrange lag = new Lagrange(n);
            if (lag.findSum(n, 4)) {
                System.out.println(lag);
                System.out.println();
            } else {
                System.out.println("could not find an expression for " + n);
                System.out.println();
            }
        }
    }
}

