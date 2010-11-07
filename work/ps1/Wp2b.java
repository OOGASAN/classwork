public class Wp2b {
	public static void main(String[] args) {
		int[] arr1 = {1, 1, 2, 2, 2, 3, 3, 4};
		System.out.println(mostFrequentValue(arr1)); // 2
		int[] arr2 = {0};
		System.out.println(mostFrequentValue(arr2)); // 0
		int[] arr3 = {0, 0, 3, 3, 5, 5};	
		System.out.println(mostFrequentValue(arr3)); // 0

	}
	
	// only include this for prob. 2a
	public static int mostFrequentValue(int[] arr) {
		int mostFreqNum = 0;
		int mostFreqNumOccur = 0;
		int curValNumOccur = 0;
		
		for (int i = 0; i < arr.length; i++) {
			if (i > 0 && arr[i] >  arr[i-1]) {
				curValNumOccur = 0;
			}
			curValNumOccur++;
			if (curValNumOccur > mostFreqNumOccur) {
				mostFreqNumOccur++;
				mostFreqNum = arr[i];
			}
		}
		
		return mostFreqNum;
	}
}	