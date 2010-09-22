/*
 * Lagrange.java
 *
 * Author:          Sam Tymorek, samtymorek@hotmail.com
 * TF:              <your TF's name>
 * Date modified:   09/21/2010
 */

/**
 * An application that reads positive integers from the user and
 * breaks them into sums of at most four positive perfect squares.
 */

import java.util.*;

public class Lagrange {
    /* Put your data members below. */
    private int[] terms = new int[4];

    public static ArrayList<Integer> squaresLessThan = new ArrayList<Integer>();
      
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
    
    // TEMPorarily public
    private static void getSquaresLessThanNum(int n) {
        if (n < 0)
            throw new IllegalArgumentException("n must be positive");
        if (n < 4) {
            squaresLessThan.add(1);
            return;
        } else {
            int temp = largestSquare(n);
            squaresLessThan.add(temp);
            temp--;
            getSquaresLessThanNum(temp);
        }    
    }    

    /* Put your constructors and methods below. */  
    public int numTerms() {
        int num = 0;
        for (int i=0; i<terms.length; i++) {
            if(terms[i] > 0)
                num++;
        };
        return num;
    }

    public Lagrange() {
        if (squaresLessThan.size() > 0) {
            squaresLessThan.clear();
        }
    }    
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        
        while(true) {
            System.out.print("Enter a positive integer (-1 to quit):");
            int num = in.nextInt(); 
            if (num == -1) {
                System.out.println("Goodbye!");
                break;
            }
            
            Lagrange lagrange = new Lagrange();
//            lagrange.findSum(num); 
           
            System.out.print(Integer.toString(num) + "=");
            for(int i = 0; i < lagrange.numTerms(); i++) {
                if (i != lagrange.numTerms() - 1) {
                    System.out.print(Integer.toString(lagrange.terms[i]) + "+");
                } else {   
                    System.out.println(Integer.toString(lagrange.terms[i]));
                }
            }
            lagrange.getSquaresLessThanNum(num);
            System.out.println(lagrange.squaresLessThan);
        }
    }

}
