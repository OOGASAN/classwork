/*
 * Lagrange.java
 *
 * Author:          Sam Tymorek, samtymorek@hotmail.com
 * TF:              <your TF's name>
 * Date modified:   09/21/2010
 *
 * Known bugs: In some cases, prints out extra terms. For example:
 * Enter a positive integer (-1 to quit):140
 * 140=9+121+9+1
 * However, works in other cases, for example:
 * Enter a positive integer (-1 to quit):17
 * 17=16+1
 */

/**
 * An application that reads positive integers from the user and
 * breaks them into sums of at most four positive perfect squares.
 */

import java.util.*;

public class Lagrange {
    /* Put your data members below. */

    // number entered by user
    private static int origNum;
      
    // the list we will fill with perfect squares that add up to the number entered by user
    public ArrayList<Integer> terms = new ArrayList<Integer>();
    
    // list of all squares less than number input by user
    private static ArrayList<Integer> squaresLessThanNum = new ArrayList<Integer>();
    
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
    
    /**
     * getSquaresLessThanNum - adds all the squares less than a given 
     * number to an arraylist
     */
    private static void getSquaresLessThanNum(int n) {
        if (n < 0)
            throw new IllegalArgumentException("n must be positive");
        if (n < 4) {
            squaresLessThanNum.add(1);
            return;
        } else {
            int temp = largestSquare(n);
            squaresLessThanNum.add(temp);
            temp--;
            getSquaresLessThanNum(temp);
        }    
    }    

    /* Put your constructors and methods below. */  
    public Lagrange() {
        if (squaresLessThanNum.size() > 0) {
            squaresLessThanNum.clear();
        }
    }    

    private boolean findSum(int n, int i) {

        // get sum of terms so far
        int sumOfTerms = 0;
        for (Integer t : terms)
             sumOfTerms += t;
        if (sumOfTerms == origNum && i <= 4)
            return true;
        
        for (int j = i; j < squaresLessThanNum.size(); j++) {
            if (terms.size() < 4) {
                terms.add(squaresLessThanNum.get(j));
                int newNum = n - squaresLessThanNum.get(j);
                if (findSum(newNum, i))
                    return true;
                terms.remove(squaresLessThanNum.get(j));    
            }    
        }
        
        return false;
    }    
    
    public void getLagrangeNums(int n) {
        origNum = n;
        getSquaresLessThanNum(origNum);
        findSum(origNum,0);
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
            lagrange.getLagrangeNums(num); 
           
            System.out.print(Integer.toString(num) + "=");
            for(int i = 0; i < lagrange.terms.size(); i++) {
                if (i != lagrange.terms.size() - 1) {
                    System.out.print(Integer.toString(lagrange.terms.get(i)) + "+");
                } else {   
                    System.out.println(Integer.toString(lagrange.terms.get(i)));
                }
            }
        }
    }

}

