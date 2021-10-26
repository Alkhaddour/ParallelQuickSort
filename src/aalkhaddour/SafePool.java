package aalkhaddour;

import java.util.concurrent.ForkJoinPool;

public class SafePool implements AutoCloseable {
    ForkJoinPool pool;

    public SafePool(int n_threads) {
        pool = new ForkJoinPool(n_threads);
    }
    @Override
    public void close() {
        if (pool != null) {
            pool.shutdown();
        }
    }
    public void parallelSort(int[] arr) {
        pool.invoke(new ParallelQuickSort(arr));
    }
}