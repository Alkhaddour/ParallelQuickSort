package aalkhaddour;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

import static aalkhaddour.Config.MIN_SPLIT_SIZE;
import static aalkhaddour.Config.N_THREADS;

public class ParallelQuickSort extends RecursiveAction {
    private final int[] arr;
    private final int low;
    private final int high;

    public ParallelQuickSort(int[] arr) {
        // public wrapper
        this(arr, 0, arr.length - 1);
    }
    private ParallelQuickSort(int[] arr, int low, int high) {
        this.arr = arr;
        this.low = low;
        this.high = high;
    }

    public static long runParallelQuickSort( int[] parArr) {
        System.out.println("Running parallel QuickSort with "+ N_THREADS + " threads");
        SafePool safePool = new SafePool(N_THREADS);
        // Sort and save time
        long processingTime = System.currentTimeMillis();
        safePool.parallelSort(parArr);
        processingTime = System.currentTimeMillis() - processingTime;

        // Check if array is really sorted
        if (UtilityRoutines.isSorted(parArr)) {
            System.out.println("Successfully sorted in " + processingTime + " ms");
        } else {
            System.out.println("Checking failed... array is not sorted");
        }
        return processingTime;
    }

    @Override
    protected void compute() {
        if (high - low < MIN_SPLIT_SIZE) { // if array is smaller than MIN_SPLIT_SIZE then apply sequential sort
            SequentialQuickSort.sort(arr, low, high);
        }
        else { // else split it and sort on parallel
            int mid = SequentialQuickSort.partition(arr, low, high);
            ForkJoinTask task = null;

            if (low < mid) {
                task = new ParallelQuickSort(arr, low, mid).fork();
            }
            if (mid + 1 < high) {
                new ParallelQuickSort(arr, mid + 1, high).invoke();
            }
            if (task != null) {
                task.join();
            }
        }
    }
}