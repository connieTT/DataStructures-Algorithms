/**
 * Your implementation of a Stack backed by an array.
 *
 * @author Tongtong Zhao
 * @version 1.0
 */
public class ArrayStack<T> implements StackInterface<T> {

    // DO NOT ALTER OR ADD INSTANCE VARIABLES
    private T[] backingArray;
    private int size;

    /**
     * Constructs a Stack with an initial capacity of {@code INITIAL_CAPACITY}.
     *
     * You must use constructor chaining to implement this constructor.
     */
    public ArrayStack() {
        this (INITIAL_CAPACITY);
    }


    /**
     * Constructs a Stack with the specified initial capacity of
     * {@code initialCapacity}.
     *
     * @param initialCapacity the initial capacity of the backing array
     */
    public ArrayStack(int initialCapacity) {
        // @SuppressWarnings("unchecked")
        backingArray = (T[]) new Object[initialCapacity];
        size = 0;
    }


    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data pushed is null.");
        }
        if (size >= backingArray.length) {
            T[] temp = (T[]) new Object[2 * size];
            for (int i = 0; i < size; i++) {
                temp[i] = backingArray[i];
            }
            temp[size] = data;
            backingArray = temp;
        } else {
            backingArray[size] = data;
        }
        size++;
    }


    @Override
    public T pop() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("You cannot use pop "
                    + "when stack is empty.");
        }
        T temp = backingArray[size - 1];
        backingArray[size - 1] = null;
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
     * @return the backing array of this stack
     */
    public Object[] getBackingArray() {
        return backingArray;
    }
}