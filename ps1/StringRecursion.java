/*
 * Author: Sam Tymorek, samtymorek@hotmail.com
 * 
 */

import java.util.*;

public class StringRecursion {  
    
    public static void print_reverse(String str) {
        if (str == null ||  str.length() == 0) {
            return;
        } else {
            char temp = str.charAt(str.length()-1);
            String sub = str.substring(0,str.length()-1); 
            
            System.out.print(temp);
            print_reverse(sub);
            return;
        }
    }    
        
    public static String reverse(String str) {
        if (str == null) {
            return null;
        } else if (str.length() == 0 || str.length() == 1) {
            return str;
        } else {
            char temp = str.charAt(str.length()-1);
            String sub = str.substring(0,str.length()-1);

            return temp + reverse(sub);
        } 
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a string to reverse");
        String stringToTest = in.nextLine();
        print_reverse(stringToTest);
        System.out.println("");        
        System.out.println("Enter a string to reverse");
        stringToTest = in.nextLine();
        System.out.println(reverse(stringToTest)); 

    } 
}