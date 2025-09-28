public class MergeSort {

    private static final int CUTOFF = 10;

    public static void mergeSort(int[] arr, int left, int right, int[] buffer) {
        if (left >= right) return;

        MetricsTracker.enterRecursion();

        if (right - left + 1 <= CUTOFF) {
            insertionSort(arr, left, right);
            MetricsTracker.exitRecursion();
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid, buffer);
        mergeSort(arr, mid + 1, right, buffer);

        merge(arr, left, mid, right, buffer);

        MetricsTracker.exitRecursion();
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] buffer) {
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            MetricsTracker.countComparison();
            if (arr[i] <= arr[j]) {
                buffer[k++] = arr[i++];
            } else {
                buffer[k++] = arr[j++];
            }
        }

        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];

        for (int m = 0; m < k; m++) {
            arr[left + m] = buffer[m];
        }
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] smallArr = {5, 3, 8, 1, 2, 7};
        int[] smallBuffer = new int[smallArr.length];

        MetricsTracker.reset();
        mergeSort(smallArr, 0, smallArr.length - 1, smallBuffer);

        System.out.print("Sorted small array: ");
        for (int num : smallArr) {
            System.out.print(num + " ");
        }
        System.out.println();

        int[] largeArr = new int[1000];
        for (int i = 0; i < largeArr.length; i++) {
            largeArr[i] = (int) (Math.random() * 10000);
        }
        int[] buffer = new int[largeArr.length];

        MetricsTracker.reset();

        long start = System.nanoTime();
        mergeSort(largeArr, 0, largeArr.length - 1, buffer);
        long end = System.nanoTime();

        System.out.println("Execution Time (ns): " + (end - start));
        System.out.println("Max Recursion Depth: " + MetricsTracker.maxDepth);
        System.out.println("Comparisons: " + MetricsTracker.comparisons);
    }
}