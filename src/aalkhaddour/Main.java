package aalkhaddour;

import java.util.Arrays;
import java.util.Random;

import static aalkhaddour.Config.*;
import static aalkhaddour.ParallelQuickSort.runParallelQuickSort;
import static aalkhaddour.SequentialQuickSort.runSequentialQuickSort;


public class Main {
    public static void main(String[] args) {
        long totalSeqExecTime = 0;
        long totalParExecTime = 0;
        for (int i = 0; i < N_TRIALS; i++) {
            System.out.println("Trial " + (i+1) + "/" + N_TRIALS);

            // We should test sequential and parallel sorting on the same array each time
            int[] seqArr = new Random().ints(ARRAY_SIZE, MIN_INT, MAX_INT).toArray();
            int[] parArr = Arrays.copyOf(seqArr, seqArr.length);

            totalSeqExecTime = totalSeqExecTime + runSequentialQuickSort(seqArr);
            totalParExecTime = totalParExecTime + runParallelQuickSort(parArr);
        }
        System.out.println("Total execution time for sequential sorting: " + totalSeqExecTime);
        System.out.println("Total execution time for parallel sorting: " + totalParExecTime);
        System.out.println("Ratio sequential/parallel: " + (double)totalSeqExecTime/totalParExecTime);
    }






}