public class MergeSort {

    private static final int CUTOFF = 10;

    public static void mergeSort(int[] arr, int left, int right, int[] buffer) {
        if (right - left + 1 <= CUTOFF) {
            insertionSort(arr, left, right);
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid, buffer);
        mergeSort(arr, mid + 1, right, buffer);

        merge(arr, left, mid, right, buffer);
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] buffer) {
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
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
        int[] arr = {5, 3, 8, 1, 2, 7};
        int[] buffer = new int[arr.length];

        mergeSort(arr, 0, arr.length - 1, buffer);

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}