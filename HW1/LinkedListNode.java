/**
 * Node class used for implementing your SinglyLinkedList.
 *
 * DO NOT ALTER THIS FILE!!
 *
 * @author CS 1332 TAs
 */
public class LinkedListNode<T> {

    private T data;
    private LinkedListNode<T> next;

    /**
     * Create a new LinkedListNode with the given data object and next node.
     *
     * @param data data to store in the node
     * @param next next node
     */
    public LinkedListNode(T data, LinkedListNode<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Create a new LinkedListNode with the given data object and no next node.
     *
     * @param data data to store in this node
     */
    public LinkedListNode(T data) {
        this(data, null);
    }

    /**
     * Set the data stored in the node.
     *
     * @param data new data.
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Get the data stored in the node.
     *
     * @return data in this node.
     */
    public T getData() {
        return data;
    }

    /**
     * Get the next node.
     *
     * @return next node.
     */
    public LinkedListNode<T> getNext() {
        return next;
    }

    /**
     * Set the next node.
     *
     * @param next new next node.
     */
    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node containing: " + data;
    }
}
