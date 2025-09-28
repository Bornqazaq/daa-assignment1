import java.util.Random;

public class QuickSort {

    private static final Random rand = new Random();

    public static void quickSort(int[] arr, int left, int right) {
        MetricsTracker.enterRecursion();

        while (left < right) {
            int pivotIndex = left + rand.nextInt(right - left + 1);
            int pivot = arr[pivotIndex];
            int i = left;
            int j = right;

            while (i <= j) {
                while (arr[i] < pivot) {
                    MetricsTracker.countComparison();
                    i++;
                }
                while (arr[j] > pivot) {
                    MetricsTracker.countComparison();
                    j--;
                }
                if (i <= j) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                    j--;
                }
            }

            if (j - left < right - i) {
                quickSort(arr, left, j);
                left = i;
            } else {
                quickSort(arr, i, right);
                right = j;
            }
        }

        MetricsTracker.exitRecursion();
    }

    public static void main(String[] args) {
        int[] smallArr = {5, 3, 8, 1, 2, 7};

        MetricsTracker.reset();
        quickSort(smallArr, 0, smallArr.length - 1);
        System.out.print("Sorted small array: ");
        for (int num : smallArr) {
            System.out.print(num + " ");
        }
        System.out.println();

        int[] largeArr = new int[1000];
        for (int i = 0; i < largeArr.length; i++) {
            largeArr[i] = (int) (Math.random() * 10000);
        }

        MetricsTracker.reset();
        long start = System.nanoTime();
        quickSort(largeArr, 0, largeArr.length - 1);
        long end = System.nanoTime();

        System.out.println("Execution Time (ns): " + (end - start));
        System.out.println("Max Recursion Depth: " + MetricsTracker.maxDepth);
        System.out.println("Comparisons: " + MetricsTracker.comparisons);
    }
}