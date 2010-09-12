/* 
 * ArrayBag.java
 * 
 * Author:          Computer Science E-119 staff
 * Modified by:     Sam Tymorek, samtymorek@hotmail.com
 * Date modified:   9/12/2010
 */

import java.util.*;

/**
 * An implementation of a Bag ADT using an array.
 */
public class ArrayBag implements Bag {
    /** 
     * The array used to store the items in the bag.
     */
    private Object[] items;
    
    /** 
     * The number of items in the bag.
     */
    private int numItems;
    
    public static final int DEFAULT_MAX_SIZE = 50;
    
    /**
     * Default, no-arg constructor - creates a new, empty ArrayBag with 
     * the default maximum size.
     */
    public ArrayBag() {
        items = new Object[DEFAULT_MAX_SIZE];
        numItems = 0;
    }
    
    /** 
     * A constructor that creates a new, empty ArrayBag with the specified
     * maximum size.
     */
    public ArrayBag(int maxSize) {
        if (maxSize <= 0)
            throw new IllegalArgumentException("maxSize must be > 0");
        items = new Object[maxSize];
        numItems = 0;
    }
    
    /** 
     * add - adds the specified item to the Bag.  Returns true on
     * success and false if there is no more room in the Bag.
     */
    public boolean add(Object item) {
        if (item == null)
            throw new IllegalArgumentException("item must be non-null");
        if (numItems == items.length)
            return false;              // no more room!
        else {
            items[numItems] = item;
            numItems++;
            return true;
        }
    }
    
    /** 
     * remove - removes one occurrence of the specified item (if any)
     * from the Bag.  Returns true on success and false if the
     * specified item (i.e., an object equal to item) is not in the Bag.
     */
    public boolean remove(Object item) {
        for (int i = 0; i < numItems; i++) {
            if (items[i] != null && items[i].equals(item)) {
                // Shift the remaining items left by one.
                System.arraycopy(items, i+1, items, i, numItems-i-1);
                items[numItems-1] = null;
                
                numItems--;
                return true;
            }
        }
        
        return false;  // item not found
    }
    
    /**
     * contains - returns true if the specified item is in the Bag, and
     * false otherwise.
     */
    public boolean contains(Object item) {
        for (int i = 0; i < numItems; i++) {
            if (items[i] != null && items[i].equals(item))
                return true;
        }
        
        return false;
    }
    
    /**
     * containsAll - does this ArrayBag contain all of the items in
     * otherBag?  Returns false if otherBag is null or empty. 
     */
    public boolean containsAll(Bag otherBag) {
        if (otherBag == null || otherBag.numItems() == 0)
            return false;
        
        Object[] otherItems = otherBag.toArray();
        for (int i = 0; i < otherItems.length; i++) {
            if (!contains(otherItems[i]))
                return false;
        }
        
        return true;
    }
    
    /**
     * numItems - returns the number of items in the Bag.
     */
    public int numItems() {
        return numItems;
    }
    
    /**
     * grab - returns a reference to a randomly chosen in the Bag.
     */
    public Object grab() {
        if (numItems == 0)
            throw new NoSuchElementException("the bag is empty");
        int whichOne = (int)(Math.random() * numItems);
        return items[whichOne];
    }
    
    /**
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray() {
        Object[] copy = new Object[numItems];
        System.arraycopy(items, 0, copy, 0, numItems);
        return copy;
    }
    
    /**
     * toString - converts this ArrayBag into a readable String object.
     * Overrides the Object version of this method.
     */
    public String toString() {
        String str = "{";
        
        for (int i = 0; i < numItems; i++)
            str = str + " " + items[i];
        str = str + " }";
        
        return str;
    }
    
    /**
     * roomLeft - returns the number of additional items that the ArrayBag has room to store. 
     */
     public int roomLeft() {
         int room = items.length - numItems; 
         return room; 
     }
     
     /**
      * isEmpty - returns true if the ArrayBag is empty, else returns false
      */
     public boolean isEmpty() {
         if (numItems == 0)
             return true;
         else
             return false;
     }
     
     /**
      * increaseCapacity - increases the maximum capacity of the bag by the specified amount.
      */
     public void increaseCapacity(int increment) {
         if (increment < 0)
             throw new IllegalArgumentException("number to increase size by must be positive");   
         if (increment == 0)
             return;
         
         Object[] bigItems = new Object[numItems + increment];
         //arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
         // Copies an array from the specified source array, beginning at the specified position, to the specified position of the destination array.
         // System.arraycopy(items, i+1, items, i, numItems-i-1);
         System.arraycopy(items, 0, bigItems, 0, items.length);
         
         items = bigItems;
     }
 
     public boolean addItems(Bag other) {
         // awesome code here...  
         return false;
     }
     
    /* Test the ArrayBag implementation. */
    public static void main(String[] args) {
        // Create a Scanner object for user input.
        Scanner in = new Scanner(System.in);
        
        // Create an ArrayBag named bag1.
        System.out.print("Size of bag 1: ");
        int size = in.nextInt();
        Bag bag1 = new ArrayBag(size);
        in.nextLine();    // consume the rest of the line
        
        // Read in strings, add them to bag1, and print out bag1.
        String itemStr;        
        for (int i = 0; i < size; i++) {
            System.out.print("item " + i + ": ");
            itemStr = in.nextLine();
            bag1.add(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        // Select a random item and print it.
        Object item = bag1.grab();
        System.out.println("grabbed " + item);
        System.out.println();
        
        // Iterate over the objects in bag1, printing them one per
        // line.
        Object[] items = bag1.toArray();
        for (int i = 0; i < items.length; i++)
            System.out.println(items[i]);
        System.out.println();
        
        // Get an item to remove from bag1, remove it, and reprint the bag.
        System.out.print("item to remove: ");
        itemStr = in.nextLine();
        if (bag1.contains(itemStr))
            bag1.remove(itemStr);
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        // Print how much room is left
        int room = bag1.roomLeft();
        if (room == 1)
            System.out.println("bag 1 has room for " + room + " more item");
        else
            System.out.println("bag 1 has room for " + room + " more items"); 
        
        // increase the bag's capacity and put some new items in
        int increment;
        System.out.print("enter amount to increase bag size:");
        increment = in.nextInt();
        bag1.increaseCapacity(increment);
        size = bag1.numItems() + increment;
        in.nextLine();
        for (int i = bag1.numItems(); i < size; i++) {
            System.out.print("item " + i + ": ");
            itemStr = in.nextLine();
            bag1.add(itemStr);
        }

        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        // create a new bag and try to add it's items to bag 1
        System.out.print("Size of bag 2: ");
        int size2 = in.nextInt();
        Bag bag2 = new ArrayBag(size2);
        in.nextLine();    
        
        // Read in strings, add them to bag2, and print out bag2.
        for (int i = 0; i < size2; i++) {
            System.out.print("item " + i + ": ");
            itemStr = in.nextLine();
            bag2.add(itemStr);
        }
        System.out.println("bag 2 = " + bag2);
        System.out.println();
        
        boolean bag2Added = bag1.addItems(bag2);
        if (bag2Added) {
            System.out.println("bag 1 = " + bag1);
            System.out.println();
        } else {
            System.out.println("there is not enough room in bag 1 for this operation");
        }
        
        // check if bag is empty
        boolean isBag1Empty = bag1.isEmpty();
        // TODO: find cleaner way to do this...
        if (isBag1Empty) {
            System.out.println("bag 1 = " + bag1);
            System.out.println();
        } else {
            System.out.println("emptying bag1...");
            Object[] itemsToRemove = bag1.toArray();
            for (int i = 0; i < itemsToRemove.length; i++)
                bag1.remove(itemsToRemove[i]);
            isBag1Empty = bag1.isEmpty(); 
            if (isBag1Empty) {
                System.out.println("bag 1 = " + bag1);
                System.out.println();
            }
        }
                
    }
}
