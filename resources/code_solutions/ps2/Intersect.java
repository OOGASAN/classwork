/*
 * Intersect.java
 * 
 * Computer Science E-119, Harvard University
 */

import java.util.*;

public class Intersect {
    /**
     * intersect - returns an array containing the intersection
     * of the values in a1 and a2, using an approach based
     * on merging.
     */
    public static int[] intersect(int[] a1, int[] a2) {
        // Create an array for the intersection that
        // is the size of the smaller of a1 and a2.
        int[] inter = new int[Math.min(a1.length, a2.length)];
        
        // Begin by sorting both arrays.  Mergesort
        // or Shell sort would also be acceptable here.
        Sort.quickSort(a1);
        Sort.quickSort(a2);
        
        int i = 0;   // index for a1
        int j = 0;   // index for a2
        int k = 0;   // index for inter
        
        while (i < a1.length && j < a2.length) {
            if (a1[i] == a2[j]) {
                inter[k] = a1[i];

                // Increment i and j, skipping over any 
                // duplicates of the value that was just copied.
                do {
                    i++;
                } while (i < a1.length && a1[i] == inter[k]);
                do {
                    j++;
                } while (j < a2.length && a2[j] == inter[k]);
                
                k++;
            } else if (a1[i] < a2[j]) {
                i++;      // advance the index on the smaller item
            } else {
                j++;
            }
        }
        
        return inter;
    }
 
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        
        System.out.print("How many elements should the arrays contain? ");
        int len = console.nextInt();
        System.out.print("What should the max value be? ");
        int max = console.nextInt();
        
        int[] a1 = SortCount.randomArray(len, max);
        int[] a2 = SortCount.randomArray(len, max);
        int[] inter = intersect(a1, a2);
        
        System.out.println(Arrays.toString(a1));
        System.out.println(Arrays.toString(a2));
        System.out.println(Arrays.toString(inter));
    }
}                        