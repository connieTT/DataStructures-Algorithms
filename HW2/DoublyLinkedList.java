
/**
 * Your implementation of a DoublyLinkedList. Note the slightly different
 * time complexities from a SinglyLinkedList in the interface.
 *
 * @author Tongtong Zhao
 * @version 1.0
 */
public class DoublyLinkedList<T> implements LinkedListInterface<T> {

    // DO NOT ALTER OR ADD INSTANCE VARIABLES
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data to add cannot be null.");
        }
        if (size == 0) {
            head = new LinkedListNode<>(data);
            tail = head;
        } else {
            if (size == 1) {
                tail = new LinkedListNode<>(data);
                head.setNext(tail);
                tail.setPrev(head);
            } else {
                LinkedListNode<T> temp = new LinkedListNode<>(data);
                tail.setNext(temp);
                temp.setPrev(tail);
                tail = temp;
            }
        }
        size++;
    }


    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data to add cannot be null.");
        }
        if (size == 0) {
            head = new LinkedListNode<>(data);
            tail = head;
        } else {
            LinkedListNode<T> temp = new LinkedListNode<>(data);
            temp.setNext(head);
            head.setPrev(temp);
            head = temp;
        }
        size++;
    }


    @Override
    public T remove() {
        if (size == 0) {
            return null;
        }
        T data = tail.getData();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            LinkedListNode<T> temp = tail;
            tail = temp.getPrev();
            temp.setPrev(null);
            tail.setNext(null);
            temp = null;
        }
        size--;
        return data;
    }


    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        T data = head.getData();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            LinkedListNode<T> temp = head;
            head = temp.getNext();
            head.setPrev(null);
            temp.setNext(null);
            temp = null;
        }
        size--;
        return data;
    }


    @Override
    public boolean isEmpty() {
        return (size == 0);
    }


    @Override
    public int size() {
        return size;
    }


    // DO NOT MODIFY CODE OR USE CODE BEYOND THIS POINT.


    @Override
    public LinkedListNode<T> getHead() {
        return head;
    }


    @Override
    public LinkedListNode<T> getTail() {
        return tail;
    }
}
