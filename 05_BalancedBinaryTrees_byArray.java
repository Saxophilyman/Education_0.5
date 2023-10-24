import java.util.*;

public class AlgorithmsDataStructures2 {

    public static int[] GenerateBBSTArray(int[] a) {
        if (a.length == 0) {
            return null;
        }
        Arrays.sort(a);
        int BBSTArray[] = new int[a.length];
        recursionForGenerateBBSTArray(0, a.length - 1,  0, BBSTArray, a);
        return BBSTArray;
    }

    private static void recursionForGenerateBBSTArray(int leftBound, int rightBound,  int rootIndex, int[] BBSTArray, int[] sortedArray) {
        if (leftBound == 0 && rightBound == 0){
            BBSTArray[rootIndex] = sortedArray[0];
        }
        int middle = leftBound + ((rightBound - leftBound) / 2);
        if ((rightBound - leftBound) % 2 == 1) {
            middle++;
        }
        if (leftBound < middle && middle <= rightBound) {
            BBSTArray[rootIndex] = sortedArray[middle];
            recursionForGenerateBBSTArray(leftBound, middle - 1,  2 * rootIndex + 1, BBSTArray, sortedArray);
            recursionForGenerateBBSTArray(middle, rightBound, 2 * rootIndex + 2, BBSTArray, sortedArray);
        }
    }
}
