/*
 * PairSums.java
 *
 * Computer Science E-119
 */

public class PairSums {
    /* Because there are two for loops, one nested, each potentially iterating
     * over all the elements in the array, this method takes O(n^2) time.
     */
    public static void findPairs (int[] arr, int target) {
	/* We fix an index into the array with the outer loop, which
	 * will be a candidate for the first element in a possible pair.
	 * Notice that we avoid letting first == arr.length - 1, otherwise,
	 * there would be no second element to try.
	 */
	for (int first = 0; first < arr.length - 1; first++) {
	    /* We now check all elements to the right of the fixed first
	     * element, looking for any pairs which sum to our target value.
	     * Note that we need not look to the left of the element indexed
	     * by "first," as we have already considered all pairs containing
	     * those elements.
	     */
	    int secondShouldBe = target - arr[first];
	    for (int second = first + 1; second < arr.length; second++) {
		if (arr[second] == secondShouldBe)
		    System.out.println(arr[first] + " + " + arr[second] 
				       + " = " + target);
	    }
	}
    }

    /* We first sort the array. Then, we put two indices, one at the
     * end of the array, and one at the very beginning. These two
     * indices will represent a potential pair -- if their sum is too
     * great, we move the right-hand index down to decrease the total
     * sum. If their sum is too small, we move the left-hand index up
     * to increase the sum. Because each index will move through the
     * array at most once, this part of the algorithm will take time
     * linear in the size of the array, or O(n). The quicksort
     * algorithm in the average case takes O(n log n) time, and this
     * term will dominate the linear O(n) term, which makes the total
     * running time of findPairsFaster O(n log n).
     */
    public static void findPairsFaster (int[] arr, int target) {
	Sort.quickSort(arr);

	int first = 0;
	int second = arr.length - 1;

	while (first < second) {
	    int pairsum = arr[first] + arr[second];
	    if (pairsum > target) {
		second--;
	    } else if (pairsum < target) {
		first++;
	    } else {
		/* In this case, pairsum == target, so output the pair */
		System.out.println(arr[first] + " + " + arr[second] 
				   + " = " + target);
           
		/* At this point, we know that only decrementing
		 * second will make the pairsum smaller or equal, and
		 * only incrementing first will also only make the
		 * pairsum bigger or equal, so we do both to ensure
		 * that unless there are duplicates, we'll get a new
		 * pairsum
		 */
		first++;
		second--;
           
		/* If we wanted to avoid duplicates, we could iterate both
		 * counters until they reached a new value, but we don't do
		 * so here.
		 */
	    }
	}
    }
    
    /**
     * printArray - prints the specified array in the following form:
     * { arr[0] arr[1] ... }
     */
    public static void printArray(int[] arr) {
	
	for (int i = 0; i < arr.length; i++)
	    System.out.print(arr[i] + " ");

	System.out.println("}");
    }

    public static void main(String[] args) {
	int[] arr = new int[10];
	for (int i = 0; i < 10; i++) {
	    arr[i] = (int)(50 * Math.random());
	}
	printArray(arr);
	int target = 25 + (int)(50 * Math.random());
	System.out.println("findPairs() on " + target + ":");
	findPairs(arr, target);
	System.out.println("findPairsFaster() on " + target + ":");
	findPairsFaster(arr, target);
    }
}
