public class PairSums {
    private static void findPairs(int k, int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[i] + arr[j] == k) {
                    System.out.println(arr[i] + " + " + arr[j] + " = " + k);
                }    
            }    
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {10, 4, 7, 7, 8, 5, 15};
        SortCount.printArray(arr);
        findPairs(12, arr);
        
        int[] randArr = SortCount.randomArray(10, 20);
        SortCount.printArray(randArr);
        findPairs(12, randArr);
    }    
}    
