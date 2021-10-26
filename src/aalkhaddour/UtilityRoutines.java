package aalkhaddour;
public class UtilityRoutines {

    public static boolean isSorted(int [] arr) {
        // Sequentially check if array is sorted
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1])
                return false;
        }
        return true;
    }
}
