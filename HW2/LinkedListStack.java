/**
 * Your implementation of a Stack backed by a LinkedList.
 *
 * @author Tongtong Zhao
 * @version 1.0
 */
public class LinkedListStack<T> implements StackInterface<T> {

    // DO NOT ALTER OR ADD INSTANCE VARIABLES
    private LinkedListInterface<T> backingList;

    /**
     * Constructs an empty Stack.
     */
    public LinkedListStack() {
        backingList = new DoublyLinkedList<>();
    }


    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data to push cannot be null");
        }
        backingList.addToFront(data);
    }


    @Override
    public T pop() {
        if (backingList.isEmpty()) {
            throw new java.util.NoSuchElementException(
                    "You cannot pop when stack is empty.");
        }
        return backingList.removeFromFront();
    }


    @Override
    public int size() {
        return backingList.size();
    }


    @Override
    public boolean isEmpty() {
        return backingList.isEmpty();
    }


    // DO NOT ALTER OR USE ANYTHING BEYOND THIS POINT!


    /**
     * Used for testing and grading purposes.
     * DO NOT USE THIS METHOD IN YOUR IMPLEMENTATION!
     *
     * @return the backing list of this stack
     */
    public LinkedListInterface<T> getBackingList() {
        return backingList;
    }

}