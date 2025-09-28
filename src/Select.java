import java.util.Arrays;

public class Select {

    public static int select(int[] arr, int k) {
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] arr, int left, int right, int k) {
        MetricsTracker.enterRecursion();

        if (left == right) {
            MetricsTracker.exitRecursion();
            return arr[left];
        }

        int pivotIndex = medianOfMedians(arr, left, right);
        pivotIndex = partition(arr, left, right, pivotIndex);

        int result;
        if (k == pivotIndex) {
            result = arr[k];
        } else if (k < pivotIndex) {
            result = select(arr, left, pivotIndex - 1, k);
        } else {
            result = select(arr, pivotIndex + 1, right, k);
        }

        MetricsTracker.exitRecursion();
        return result;
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            MetricsTracker.countComparison();
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }

        swap(arr, right, storeIndex);
        return storeIndex;
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        int[] medians = new int[(n + 4) / 5];

        int index = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            Arrays.sort(arr, i, subRight + 1);
            int median = arr[i + (subRight - i) / 2];
            medians[index++] = median;
        }

        int medianOfMedians = (medians.length == 1)
                ? medians[0]
                : select(medians, medians.length / 2);

        for (int i = left; i <= right; i++) {
            if (arr[i] == medianOfMedians) return i;
        }

        return left;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }

        int k = arr.length / 2;

        MetricsTracker.reset();
        long start = System.nanoTime();
        int result = select(arr, k);
        long end = System.nanoTime();

        System.out.println("K-th smallest element: " + result);
        System.out.println("Execution Time (ns): " + (end - start));
        System.out.println("Max Recursion Depth: " + MetricsTracker.maxDepth);
        System.out.println("Comparisons: " + MetricsTracker.comparisons);
    }
}