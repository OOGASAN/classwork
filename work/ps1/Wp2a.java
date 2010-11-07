public class Wp2a {
	public static void main(String[] args) {
//		int[] arr1 =null;
//		System.out.println(isSorted(arr1)); // throw except.
		int[] arr2 = {};
		System.out.println(isSorted(arr2)); // true
		int[] arr3 = {0,3,5};	
		System.out.println(isSorted(arr3)); // true
		int[] arr4 = {3,0,5};
		System.out.println(isSorted(arr4)); // false
		int[] arr5 = {1};
		System.out.println(isSorted(arr5)); // true

	}
	
	// only include this for prob. 2a
	public static boolean isSorted(int[] arr) {
		if (arr == null) {
			throw new IllegalArgumentException("argument value cannot be null");	
		}
		for (int i = 1; i < arr.length; i++) {
			if (arr[i - 1] > arr[i]) {
				return false;
			}
		}
		return true;
	}
}	