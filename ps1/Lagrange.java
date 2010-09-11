/*
 * Lagrange.java
 *
 * Author:          <your name>, <your e-mail address>
 * TF:              <your TF's name>
 * Date modified:   <current date>
 */

/**
 * An application that reads positive integers from the user and
 * breaks them into sums of at most four positive perfect squares.
 */
public class Lagrange {
    /* Put your data members below. */


    
    /**
     * largestSquare - returns the largest perfect square less than or
     * or equal to the specified positive integer.
     */
    private static int largestSquare(int n) {
	if (n <= 0)
	    throw new IllegalArgumentException("n must be positive");
	int temp = (int)Math.sqrt(n);
	return temp * temp;
    }

    /* Put your constructors and methods below. */

}
