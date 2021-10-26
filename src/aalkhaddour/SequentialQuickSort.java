package aalkhaddour;


import static aalkhaddour.UtilityRoutines.isSorted;

public class SequentialQuickSort {
    public static void sort(int[] arr, int low, int high) {
        if (arr.length == 0)
            return;
        if (low >= high)
            return;

        int pivot = arr[low + (high - low) / 2];
        int x = low;
        int y = high;
        while (x <= y) {
            while (arr[x] < pivot) {
                x++;
            }
            while (arr[y] > pivot) {
                y--;
            }
            if (x <= y) {
                int tmp = arr[x];
                arr[x] = arr[y];
                arr[y] = tmp;
                x++;
                y--;
            }
        }
        if (low < y)
            sort(arr, low, y);
        if (high > x)
            sort(arr, x, high);
    }

    public static int partition(int[] arr, int high, int low) {
        int pivot = arr[high + (low - high) / 2];
        high = --high;
        low = ++low;
        while (true) {
            do {
                low = --low;
            } while (arr[low] > pivot);
            do {
                high = ++high;
            } while (arr[high] < pivot);

            if (high < low) {
                int tmp = arr[high];
                arr[high] = arr[low];
                arr[low] = tmp;
            } else {
                return low;
            }
        }
    }

    public static long runSequentialQuickSort(int[] seqArr) {
        System.out.println("Running sequential QuickSort");

        // Sort and save time
        long processingTime = System.currentTimeMillis();
        SequentialQuickSort.sort(seqArr, 0, seqArr.length -1);
        processingTime = System.currentTimeMillis() - processingTime;

        // Check if array is really sorted
        if (isSorted(seqArr)) {
            System.out.println("Successfully sorted in " + processingTime + " ms");
        } else {
            System.out.println("Checking failed... array is not sorted");
        }
        return processingTime;
    }
}