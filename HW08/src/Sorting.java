import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Queue;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Anmol Chhabria
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement cocktail sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {


        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }

        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null.");
        }


        boolean swap = true;
        int start = 0;
        int end = arr.length - 1;

        while (swap) {

            swap = false;
            for (int i = start; i < end; i++) {
                int comp = comparator.compare(arr[i], (arr[i + 1]));
                if (comp > 0) {
                    swap(i, i + 1, arr);
                    swap = true;
                }
            }

            if (!swap) {
                return;
            }

            end--;

            swap = false;
            for (int i = end; i > start; i--) {
                int comp = comparator.compare(arr[i], arr[i - 1]);
                if (comp < 0) {
                    swap(i, i - 1, arr);
                    swap = true;
                }
            }

            start++;
        }

        return;

    }


    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {

        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }

        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null.");
        }

        for (int i = 1; i < arr.length; i++) {
            boolean swap = true;
            int j = i;
            while (swap && j > 0) {
                if (comparator.compare(arr[j - 1], arr[j]) > 0) {
                    swap(j - 1, j, arr);
                    j--;
                } else {
                    swap = false;
                }
            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * Note that there may be duplicates in the array, but they may not
     * necessarily stay in the same relative order.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {

        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }

        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null.");
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int num = i;
            for (int j = i + 1; j < arr.length; j++) {
                int comp = comparator.compare(arr[j], arr[num]);
                if (comp < 0) {
                    num = j;
                }
            }
            swap(i, num, arr);
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {

        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }

        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null.");
        }

        if (rand == null) {
            throw new IllegalArgumentException("Random object cannot be null.");
        }

        quickSortHelper(0, arr.length, arr, rand, comparator);

    }


    /**
     * Helper method for QuickSort
     * @param <T> data type to sort
     * @param start beginning index of array
     * @param end ending index of array
     * @param arr Array to be sorted
     * @param rand Random number seed used for pivot
     * @param comparator Comparator function used to sort array
     */

    private static <T> void quickSortHelper(
            int start, int end, T[] arr,
            Random rand, Comparator<T> comparator) {

        if (end <= start) {
            return;
        }

        int pivotIndex = rand.nextInt(end - start) + start;
        T pivot = arr[pivotIndex];
        swap(start, pivotIndex, arr);
        //j
        int leftIndex = start + 1;
        //k
        int rightIndex = end - 1;

        if (rightIndex < leftIndex) {
            return;
        }

        while (leftIndex <= rightIndex) {

            while (leftIndex <= rightIndex
                    && comparator.compare(pivot, arr[rightIndex]) <= 0) {
                rightIndex--;
            }

            while (leftIndex <= rightIndex
                    && comparator.compare(pivot, arr[leftIndex]) >= 0) {
                leftIndex++;
            }

            if (leftIndex < rightIndex) {
                swap(leftIndex, rightIndex, arr);
                leftIndex++;
                rightIndex--;
            }
        }
        swap(rightIndex, start, arr);
        quickSortHelper(start, rightIndex, arr, rand, comparator);
        quickSortHelper(rightIndex + 1, end, arr, rand, comparator);
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {

        if (arr == null) {
            throw new IllegalArgumentException("Array can not be null.");
        }

        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null.");
        }

        if (arr.length == 1) {
            return;
        }

        int midIndex = arr.length / 2;
        T[] leftArray = (T[]) new Object[midIndex];
        for (int i = 0; i < midIndex; i++) {
            leftArray[i] = arr[i];
        }
        T[] rightArray = (T[]) new Object[arr.length - (midIndex)];
        int k = 0;
        for (int j = midIndex; j < arr.length; j++) {
            rightArray[k] = arr[j];
            k++;
        }

        mergeSort(leftArray, comparator);
        mergeSort(rightArray, comparator);

        int leftIndex = 0;
        int rightIndex = 0;
        int currentIndex = 0;

        while (leftIndex < midIndex && rightIndex < (arr.length - midIndex)) {
            if (comparator.compare(rightArray[rightIndex],
                    leftArray[leftIndex]) >= 0) {
                arr[currentIndex] = leftArray[leftIndex];
                leftIndex++;
            } else {
                arr[currentIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            currentIndex++;
        }

        while (leftIndex < midIndex) {
            arr[currentIndex] = leftArray[leftIndex];
            leftIndex++;
            currentIndex++;
        }

        while (rightIndex < (arr.length - midIndex)) {
            arr[currentIndex] = rightArray[rightIndex];
            rightIndex++;
            currentIndex++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] lsdRadixSort(int[] arr) {

        if (arr == null) {
            throw new IllegalArgumentException("The array is null.");
        }

        Queue<Integer>[] buckets = new Queue[19];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }

        int maxKey = findMax(arr);

        int power = 0;
        boolean sorted = false;
        while (!sorted) {
            int div = maxKey / pow(10, power);
            sorted = true;
            if (div > 0) {
                sorted = false;
                power += 1;
            }
        }


        for (int num = 0; num < power; num++) {

            for (int j = 0; j < arr.length; j++) {
                int sign = (arr[j] < 0) ? -1 : 1;
                int absNum = (Math.abs(arr[j]) / pow(10, num)) % 10;
                int digitBucket = (absNum * sign) + 9;
                buckets[digitBucket].add(arr[j]);
            }

            int in = 0;

            for (Queue<Integer> bucket: buckets) {
                while (!bucket.isEmpty()) {
                    arr[in++] = bucket.remove();
                }
            }

        }

        return arr;
    }

    /**
     * Find the max item in an array
     * @param <T> data type to sort
     * @param arr in which to find max
     * @return return absolute value of max number
     */

    private static <T> int findMax(int[] arr) {

        int maxKey = 0;
        for (int n = 0; n < arr.length; n++) {
            if (Math.abs(arr[n]) > maxKey) {
                maxKey = Math.abs(arr[n]);
            }
        }
        return maxKey;
    }

    /**
     * Swap two items in an array
     * @param <T> data type to sort
     * @param i item #1 to modify
     * @param j item #2 to modify
     * @param array array which needs item swap
     */

    private static <T> void swap(int i, int j, T[] array) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sorts instead of {@code Math.pow()}.
     *
     * DO NOT MODIFY THIS METHOD.
     *
     * @throws IllegalArgumentException if both {@code base} and {@code exp} are
     * 0
     * @throws IllegalArgumentException if {@code exp} is negative
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * pow(base, (exp / 2) + 1);
        }
    }
}
