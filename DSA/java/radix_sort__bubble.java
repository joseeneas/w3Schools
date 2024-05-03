public class radix_sort__bubble {

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void radixSortWithBubbleSort(int[] arr) {
        int maxVal = arr[0];
        for (int num : arr) {
            if (num > maxVal) maxVal = num;
        }
        
        int exp = 1;
        while (maxVal / exp > 0) {
            int[][] radixArray = new int[10][arr.length];
            int[] count = new int[10];
            
            for (int num : arr) {
                int radixIndex = (num / exp) % 10;
                radixArray[radixIndex][count[radixIndex]++] = num;
            }
            
            for (int i = 0; i < 10; i++) {
                int[] tempArray = new int[count[i]];
                System.arraycopy(radixArray[i], 0, tempArray, 0, count[i]);
                bubbleSort(tempArray);
                System.arraycopy(tempArray, 0, radixArray[i], 0, count[i]);
            }
            
            int index = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < count[i]; j++) {
                    arr[index++] = radixArray[i][j];
                }
            }
            
            exp *= 10;
        }
    }

    public static void main(String[] args) {
        int[] myArray = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.print("Original array: ");
        for (int num : myArray) System.out.print(num + " ");
        System.out.println();
        
        radixSortWithBubbleSort(myArray);
        
        System.out.print("Sorted array: ");
        for (int num : myArray) System.out.print(num + " ");
        System.out.println();
    }
}

//Java