import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;
import java.util.List;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Tongtong Zhao
 * @version 1.0
 */
public class Sorting {

    /**
     * Your implementation of bubble sort.
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
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Invalid arr or comparator!");
        }
        boolean isSorted;
        for (int i = 0; i < arr.length; i++) {
            isSorted = true;
            for (int j = 1; j < arr.length - i; j++) {
                if (comparator.compare(arr[j], arr[j - 1]) < 0) {
                    T temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                i = arr.length;
            }
        }
    }
    
    /**
     * Your implementation of insertion sort.
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
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Invalid arr or comparator!");
        }
        for (int i = 1; i < arr.length; i++) {
            T temp = arr[i];
            int j;
            for (j = i - 1; j >= 0
                    && comparator.compare(temp, arr[j]) < 0; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = temp;
        }
    }

    /**
     * Your implementation of selection sort.
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
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Invalid arr or comparator!");
        }
        int first;
        for (int i = arr.length - 1; i > 0; i--) {
            first = 0;
            for (int j = 1; j <= i; j++) {
                if (comparator.compare(arr[j], arr[first]) > 0) {
                    first = j;
                }
            }
            T temp = arr[first];
            arr[first] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * Your implementation of quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
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
     * Note that there may be duplicates in the array, but they may not
     * necessarily stay in the same relative order.
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
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException(
                    "Invalid arr, comparator or rand!");
        }
        quickSort(arr, comparator, rand, 0, arr.length);
    }
    /**
     * Helper method for quickSort
     * @param arr Array to be sorted
     * @param comparator Comparator used for sorting
     * @param rand Random object to get pivot
     * @param start Starting index to sort from
     * @param end Last index to sort till
     * @param <T> Type of data to sort
     */
    private static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                      Random rand, int start, int end) {
        if (end - start >= 2) {
            int pivot = rand.nextInt(end - start) + start;
            swap(arr, start, pivot);
            int i = start + 1;
            int j = end - 1;
            while (i <= j) {
                if (comparator.compare(arr[i], arr[start]) <= 0) {
                    i++;
                } else {
                    if (comparator.compare(arr[j], arr[start]) > 0) {
                        j--;
                    } else {
                        swap(arr, i++, j--);
                    }
                }
            }
            swap(arr, start, j);
            if (start < j) {
                quickSort(arr, comparator, rand, start, j);
            }

            if (end > i) {
                quickSort(arr, comparator, rand, i, end);
            }
        }
    }
    /**
     * Helper method to swap array elements
     * @param arr Array to swap elements in
     * @param ind1 Index of first element to swap
     * @param ind2 Index of second element to swap
     * @param <T> Type of data in array
     */
    private static <T> void swap(T[] arr, int ind1, int ind2) {
        T temp = arr[ind1];
        arr[ind1] = arr[ind2];
        arr[ind2] = temp;
    }

    /**
     * Your implementation of merge sort.
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
     * You can create more arrays to run merge sort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Invalid arr or comparator!");
        }
        mergeSort(arr, 0, arr.length - 1, comparator);
    }
    /**
     * Helper method for mergeSort
     * recursively sort the divided array
     * @param <T> the generic type
     * @param arr the array
     * @param low lower bound
     * @param high higher bound
     * @param comparator the comparator
     */
    private static <T> void mergeSort(
            T[] arr, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid, comparator);
            mergeSort(arr, mid + 1, high, comparator);
            merge(arr, low, mid, high, comparator);
        }
    }
    /**
     * MergeSort helper method
     * merge the 2 sorted array
     * @param <T> the generic type
     * @param arr the array
     * @param low lower bound
     * @param high higher bound
     * @param mid the mid position
     * @param comparator the comparator
     */
    private static <T> void merge(T[] arr, int low,
                                  int mid, int high, Comparator<T> comparator) {
        T[] temp = (T[]) new Object[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            if (comparator.compare(arr[i], arr[j]) <= 0) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= high) {
            temp[k++] = arr[j++];
        }

        for (int index = 0; index < temp.length; index++) {
            arr[index + low] = temp[index];
            temp[index] = null;
        }
    }

    /**
     * Your implementation of LSD (least significant digit) radix sort.
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
            throw new IllegalArgumentException("Array cannot be null");
        }
        int min = arr[0];
        for (int anArr : arr) {
            if (anArr < min) {
                min = anArr;
            }
        }
        int markData = min;
        if (markData < 0) {
            markData *= -1;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] += markData;
        }

        List<Integer>[] digits = new LinkedList[10];
        for (int i = 0; i < 10; i++) {
            digits[i] = new LinkedList<>();
        }
        boolean flag = false;
        int divisor = 1;
        while (!flag) {
            flag = true;
            for (int number : arr) {
                int digit = (number / divisor) % 10;
                digits[digit].add(number);
                if (digit > 0) {
                    flag = false;
                }
            }
            int i = 0;
            for (int j = 0; j < 10; j++) {
                for (int number : digits[j]) {
                    arr[i++] = number;
                }
                digits[j].clear();
            }
            divisor *= 10;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] -= markData;
        }
        return arr;
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
        int result = pow(base, exp / 2);
        if (exp % 2 == 1) {
            return result * result * base;
        } else {
            return result * result;
        }
    }
}
