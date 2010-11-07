import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * LLBag.java
 *
 * Author:      Sam Tymorek, samtymorek@hotmail.com
 *
 */

public class LLBag implements Bag{
    
    private class Node {
        private Object item;
        private Node next;
        
        private Node(Object i, Node n) {
            item = i;
            next = n;
        }
    }    

    private Node head;     // dummy head node
    private int numItems;
    
    public LLBag() {
        head = new Node(null, null);
        numItems = 0;
    }
    
    /* 
     * getNode - private helper method that returns a reference to the
     * ith node in the linked list.  It assumes that the value of the
     * parameter is valid.  
     * 
     * If i == -1, it returns a reference to the dummy head node.
     */
    private Node getNode(int i) {
        Node trav = head;
        int travIndex = -1;
        while (travIndex < i) {
            travIndex++;
            trav = trav.next;
        }
        return trav;
    }
    
    /** 
     * add - adds the specified item to the Bag.  Returns true on
     * success.
     */   
    public boolean add(Object item) {
        Node newNode = new Node(item, null);
        newNode.next = head.next;
        head.next = newNode;
        
        numItems++;
        return true;
    }

    /**
     * returns true if the specified item is in the Bag, and false
     * otherwise.
     */    
    public boolean contains(Object item) {
        Node trav = head.next;
        while (trav != null) {
            if (trav.item.equals(item))
                return true;
            trav = trav.next;
        }
        
        return false;        
    }

    /**
     * returns true if the calling object contain all of the items in
     * otherBag, and false otherwise.  Also returns false if otherBag 
     * is null or empty. 
     */
    public boolean containsAll(Bag otherBag) {
        if (otherBag == null || otherBag.numItems() == 0)
            return false;

        // have to convert otherBag to array to step through its items
        Object[] otherItems = otherBag.toArray();
        for (int i = 0; i < otherItems.length; i++) {
            // as soon as we find an item in otherBag that the calling object does not contain, we're done
            if (!contains(otherItems[i]))
                return false;
        }
        
        return true;
    }
    
    /**
     * grab - returns a reference to a randomly chosen in the Bag.
     */
    public Object grab() {
        if (numItems == 0)
            throw new NoSuchElementException("the bag is empty");
        int whichOne = (int)(Math.random() * numItems);
        
        return getNode(whichOne).item;
    }
    
    /** 
     * remove - removes one occurrence of the specified item (if any)
     * from the Bag.  Returns true on success and false if the
     * specified item (i.e., an object equal to item) is not in the Bag.
     */
    public boolean remove(Object item) {    
        Node trav = head.next;
        Node trail = head;
        
        while (trav != null) {
            if (trav.item.equals(item)) {
                trail.next = trav.next;                        
                numItems--;
                return true;                
            }    
            trail = trav;
            trav = trav.next;
        }
        
        return false;        
    }
    
    /**
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray() {
        Object[] copy = new Object[numItems];
        
        Node trav = head.next;
        int travIndex = 0;
        while (travIndex < numItems) {
            copy[travIndex] = trav.item;
            travIndex++;
            trav = trav.next;
        }     
        
        return copy;
    }
 
    /**
     * toString - converts this LLBag into a readable String object.
     * Overrides the Object version of this method.
     */
    public String toString() {
        String str = "{";
        
        Node trav = head.next;
        int travIndex = 0;
        while (travIndex < numItems) {
            str = str + " " + trav.item;
            travIndex++;
            trav = trav.next;            
        }        
        str = str + " }";
        
        return str;        
    }
    /**
     * numItems - returns the number of items in the Bag.
     */
    public int numItems() {
        return numItems;
    }    
    
    /* Test the LLBag implementation. */
    public static void main(String[] args) {
        // Create a Scanner object for user input.
        Scanner in = new Scanner(System.in);
        
        // Create an LLBag named bag1.
        System.out.print("Size of bag 1: ");
        int size = in.nextInt();
        Bag bag1 = new LLBag();
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
        
        // increase the bag's size and put some new items in
        int increment;
        System.out.print("enter amount to add to bag size:");
        increment = in.nextInt();
        size = bag1.numItems() + increment;
        in.nextLine();
        for (int i = bag1.numItems(); i < size; i++) {
            System.out.print("item " + i + ": ");
            itemStr = in.nextLine();
            bag1.add(itemStr);
        }

        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        // Get an item to remove from bag1, remove it, and reprint the bag.
        System.out.print("item to remove: ");
        itemStr = in.nextLine();
        if (bag1.contains(itemStr))
            bag1.remove(itemStr);
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
        
        // see if bag1 contains all the items in bag2
        if (bag1.containsAll(bag2))
            System.out.println("bag 1 contains all items in bag 2");
        else
            System.out.println("bag 1 does not contain all items in bag 2");               
    }    
}