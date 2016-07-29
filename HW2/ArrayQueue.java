/**
 * Your implementation of a Queue backed by an array.
 *
 * @author Tongtong Zhao
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // DO NOT ALTER OR ADD INSTANCE VARIABLES
    private T[] backingArray;
    private int size;
    private int back;
    private int front;

    /**
     * Constructs a Queue with an initial capacity of {@code INITIAL_CAPACITY}.
     *
     * You must use constructor chaining to implement this constructor.
     */
    public ArrayQueue() {
        this(INITIAL_CAPACITY);
    }


    /**
     * Constructs a Queue with the specified initial capacity of
     * {@code initialCapacity}.
     *
     * @param initialCapacity the initial capacity of the backing array
     */
    public ArrayQueue(int initialCapacity) {
        backingArray = (T[]) new Object[initialCapacity];
        size = 0;
        front = 0;
        back = 0;
    }


    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data enqueued cannot be null.");
        }
        if (size >= backingArray.length) {
            T[] temp = (T[]) new Object[2 * size];
            for (int i = 0; i < size; i++) {
                temp[i] = backingArray[(i + front) % size];
            }
            backingArray = temp;
            front = 0;
            back = size;
            backingArray[size] = data;
        } else {
            back = (front + size) % backingArray.length;
            backingArray[back] = data;
        }
        size++;
    }


    @Override
    public T dequeue() {
        if (size == 0) {
            throw new java.util.NoSuchElementException(
                    "The queue is empty.");
        }
        T temp = backingArray[front];
        backingArray[front] = null;
        if (back != front) {
            front = (front + 1) % backingArray.length;
        } else {
            back = 0;
            front = 0;
        }
        size--;
        return temp;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return (size == 0);
    }


    // DO NOT ALTER OR USE ANYTHING BEYOND THIS POINT!


    /**
     * Used for testing and grading purposes.
     * DO NOT USE THIS METHOD IN YOUR IMPLEMENTATION!
     *
     * @return the backing array of this queue
     */
    public Object[] getBackingArray() {
        return backingArray;
    }

}
