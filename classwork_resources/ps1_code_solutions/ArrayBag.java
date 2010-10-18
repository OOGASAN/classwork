/* 
 * ArrayBag.java
 * 
 * Author:          Computer Science E-119 staff
 * Modified by:     <your name>, <your e-mail address>
 * Date modified:   <current date>
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
    
    /*********************************************************/
    /**   Solutions  *****************************************/
    /*********************************************************/
    
    /**
     * roomLeft - returns the number of additional items that the bag
     * has room to store.
     */
    public int roomLeft() {
        return items.length - numItems;
    }
    
    /**
     * isEmpty - returns true if the bag is empty and false otherwise.
     */
    public boolean isEmpty() {
        return (numItems == 0);
    }
    
    /**
     * increaseCapacity - increases the capacity of the bag by the
     * specified amount.
     */
    public void increaseCapacity(int increment) {
        if (increment < 0) {
            throw new IllegalArgumentException("increment must be non-negative");
        }
        
        if (increment == 0) {
            return;
        }
        
        // Create the larger array.
        Object[] temp = new Object[items.length + increment];
        
        // Copy the existing items into the larger array.
        for (int i = 0; i < numItems; i++) {
            temp[i] = items[i];
        }
        
        // Replace the old array with the new one.
        items = temp;
    }
    
    /**
     * addItems - adds the items (if any) in other to this bag.
     * If there isn't room to add all of the items, none of them are
     * added and the method returns false.  Otherwise, it returns true.
     */
    public boolean addItems(Bag other) {
        if (other == null)
            throw new IllegalArgumentException("argument must be non-null");
        
        // If the other ArrayBag is empty, we're done.
        if (other.numItems() == 0)
            return true;
        
        // If we have enough room for all of the items in the
        // otherBag, add them one by one.
        if (other.numItems() <= (items.length - numItems)) {
            Object[] otherItems = other.toArray();
            for (int i = 0; i < otherItems.length; i++)
                add(otherItems[i]);
            
            return true;
        } else {
            // There isn't enough room to add all the items.
            return false;
        }
    }    
    
    /**
     * intersectionWith - creates and returns an bag containing
     * one occurrence of any item that is found in both the calling 
     * object and the parameter other.
     */
    public Bag intersectionWith(Bag other) {
        if (other == null)
            throw new IllegalArgumentException("argument must be non-null");
        
        // Create the ArrayBag for the intersection.
        int otherMaxSize = other.numItems() + other.roomLeft();
        int intersectMaxSize = Math.max(items.length, otherMaxSize);
        ArrayBag intersection = new ArrayBag(intersectMaxSize);
        
        // If the other ArrayBag is empty, we're done.
        if (other.numItems() == 0)
            return intersection;
        
        // Build the intersection.
        for (int i = 0; i < numItems; i++) {
            if (other.contains(items[i]) && !intersection.contains(items[i])) {
                intersection.add(items[i]);
            }
        }
        
        return intersection;
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
    }
}
