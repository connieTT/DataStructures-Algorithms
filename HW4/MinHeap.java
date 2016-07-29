/**
 * Your implementation of a Min Heap
 *
 * @author Tongtong Zhao
 * @version 1.1
 */
public class MinHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    // Do NOT add or modify any of these instance variables
    private T[] backingArray;
    private int size;

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * Do NOT hardcode this value. Use the CONSTANT provided in the interface
     *
     * Should be O(1)
     */
    public MinHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Creates a Heap with an initial capacity of initialCapacity
     *
     * @param initialCapacity capacity of the new array to initialize
     *                        This value should be the length of the array
     *                        regardless of our heap being 1-indexed
     *
     * Should be O(1)
     */
    public MinHeap(int initialCapacity) {
        backingArray = (T[]) new Comparable[initialCapacity];
        size = 0;
    }


    /**
     * Creates a Heap from an initial set of values
     * 
     * !!! UPDATED in version 1.1 !!!
     * For this constructor, initialize the backing array to fit the passed in
     * data exactly.
     * Example:
     *   If an 5 elements are passed in, your backing array should be of size 6
     *   since the backing array is 1-indexed.
     *
     * When this constructors returns, the backing array should satisfy all
     * the properties of a heap
     *
     * You should implement this the way it was mentioned in lecture
     * The BuildHeap algorithm visualized on the following page is how it should
     * be implemented and is the same method that was taught in class
     * https://www.cs.usfca.edu/~galles/visualization/Heap.html
     *
     * @param values values to initialize the heap with
     *               T... values is the same as T[] values
     *               You may assume that none of the arguments passed in
     *               will be null
     */
    @SafeVarargs
    public MinHeap(T... values) {
        backingArray = (T[]) new Comparable[values.length + 1];
        for (int i = 0; i < values.length; i++) {
            backingArray[i + 1] = values[i];
        }
        size = values.length;
        for (int i = values.length / 2; i > 0; i--) {
            minHeapify(i);
        }
    }
    /**
     * Heapify method
     * @param i the position to start heapify
     */
    private void minHeapify(int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int smallest = i;
        if (left <= size
                && backingArray[left].compareTo(backingArray[smallest]) < 0) {
            smallest = left;
        }
        if (right <= size
                && backingArray[right].compareTo(backingArray[smallest]) < 0) {
            smallest = right;
        }
        if (smallest != i) {
            T temp = backingArray[i];
            backingArray[i] = backingArray[smallest];
            backingArray[smallest] = temp;
            minHeapify(smallest);
        }
    }

    @Override
    public void add(T data) {
        if (null == data) {
            throw new IllegalArgumentException("The data cannot be null.");
        }
        if (size >= backingArray.length - 1) {
            reSize();
        }
        backingArray[size + 1] = data;
        size++;
        upHeap(size);
    }
    /**
     * Resize the back array if the size reach array length
     */
    private void reSize() {
        T[] temp = backingArray;
        backingArray = (T[]) new Comparable[backingArray.length * 2];
        for (int i = 1; i < temp.length; i++) {
            backingArray[i] = temp[i];
        }
    }
    /**
     * Upheap the element until reach to the root
     * @param index index of the element to upheap
     */
    private void upHeap(int index) {
        if (index != 1) {
            if (backingArray[index].compareTo(backingArray[index / 2]) < 0) {
                T temp = backingArray[index];
                backingArray[index] = backingArray[index / 2];
                backingArray[index / 2] = temp;
                upHeap(index / 2);
            }
        }
    }

    @Override
    public T remove() {
        if (backingArray[1] == null) {
            throw new java.util.NoSuchElementException("The heap is empty.");
        }
        T temp = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        downHeap(1);
        return temp;
    }
    /**
     * Downheap the element until reach to the end of the array
     * @param index the index of the element to donwheap
     */
    private void downHeap(int index) {
        if (index * 2 <= size) {
            if (index * 2 == size) {
                if (backingArray[index].compareTo(
                        backingArray[index * 2]) > 0) {
                    T item = backingArray[index];
                    backingArray[index] = backingArray[index * 2];
                    backingArray[index * 2] = item;
                }
            } else {
                if (backingArray[index * 2].compareTo(
                        backingArray[index * 2 + 1]) <= 0) {
                    if (backingArray[index].compareTo(
                            backingArray[index * 2]) > 0) {
                        T temp = backingArray[index];
                        backingArray[index] = backingArray[index * 2];
                        backingArray[index * 2] = temp;
                        downHeap(index * 2);
                    }
                } else {
                    if (backingArray[index].compareTo(
                            backingArray[index * 2 + 1]) > 0) {
                        T temp = backingArray[index];
                        backingArray[index] = backingArray[index * 2 + 1];
                        backingArray[index * 2 + 1] = temp;
                        downHeap(index * 2 + 1);
                    }
                }
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    // Do NOT edit or use this method in your code
    @Override
    public Comparable[] getBackingArray() {
        return backingArray;
    }
}
