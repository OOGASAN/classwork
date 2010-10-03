public class Intersect {
    
    public static int[] intersect(int[] arr1, int[] arr2) {
        Sort.quickSort(arr1);
        Sort.quickSort(arr2);
        
        int length3 = 0;
        if (arr1.length < arr2.length) {
            length3 = arr1.length;
        } else {
            length3 = arr2.length;
        }
        int[] arr3 = new int[length3];    
        
        int k = 0;
        for (int i = 0; i < arr1.length; i++) {
            int j = i;
            // if i is greater than 0, check if arr[i] != arr[i-1] to eliminate dups
//            if (i > 0 && arr1[i] == arr1[i-1]) {
//                break;
//            }    
            
                if (j < arr2.length) {            
                    try {
                        while (arr2[j] <= arr1[i]) {    
                            if (arr1[i] == arr2[j]) {
                                arr3[k] = arr1[i];
                                k++;
                                j++;
                                break;    
                            }
                            j++; 
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("exception!");
                    }  
                }    
        }    
        
        // array will be padded with 0s for indices not filled
        return arr3;    
    }
    
    public static void main(String[] args) {
        int length1 = (int) (Math.random() * 8) + 2;
        int length2 = (int) (Math.random() * 8) + 2;        
        int[] arr1 = SortCount.randomArray(length1, 10);    
        int[] arr2 = SortCount.randomArray(length2, 10);    
        
        System.out.println("Array 1:");
        SortCount.printArray(arr1);
        System.out.println("Array 2:");
        SortCount.printArray(arr2);
        System.out.println("Intersection: ");
        SortCount.printArray(intersect(arr1, arr2));        
    }        
    
}    