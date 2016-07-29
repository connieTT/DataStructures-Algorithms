import java.util.Collection;
import java.util.Iterator;

/**
 * Your implementation of a SinglyLinkedList.
 *
 * @author Tongtong Zhao
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {

    // DO NOT ALTER OR ADD INSTANCE VARIABLES
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data to add cannot be null.");
        }
        if (isEmpty()) {
            head = new LinkedListNode<>(data);
            tail = head;
        } else {
            if (size == 1) {
                tail = new LinkedListNode<>(data);
                head.setNext(tail);
            } else {
                LinkedListNode<T> temp = new LinkedListNode<>(data);
                tail.setNext(temp);
                tail = temp;
            }
        }
        size++;
    }


    @Override
    public void addToIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The index is out of bound.");
        }
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data to add cannot be null.");
        }
        if (isEmpty()) {
            head = new LinkedListNode<>(data);
            tail = head;
            size++;
        } else {
            if (index == 0) {
                addToFront(data);
            } else {
                if (index == size) {
                    add(data);
                } else {
                    LinkedListNode<T> temp = head;
                    for (int i = 0; i < index - 1; i++) {
                        temp = temp.getNext();
                    }
                    LinkedListNode<T> myNode = new LinkedListNode<>(data);
                    myNode.setNext(temp.getNext());
                    temp.setNext(myNode);
                    size++;
                }
            }
        }
    }


    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data to add cannot be null.");
        }
        if (isEmpty()) {
            head = new LinkedListNode<>(data);
            tail = head;
        } else {
            LinkedListNode<T> temp = new LinkedListNode<>(data);
            temp.setNext(head);
            head = temp;
        }
        size++;
    }


    @Override
    public void addAll(Collection<T> collection) {
        if (collection == null) {
            throw new IllegalArgumentException(
                    "The collection cannot be null.");
        }
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            add(element);
        }
    }


    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data to check cannot be null.");
        }
        if (isEmpty()) {
            return false;
        } else {
            if (size == 1) {
                return (head.getData() == data);
            } else {
                LinkedListNode<T> temp = head;
                for (int i = 0; i < size; i++) {
                    if (temp.getData() == data) {
                        return true;
                    }
                    temp = temp.getNext();
                }
                return false;
            }
        }
    }


    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index does not exist.");
        }
        if (index == 0) {
            return head.getData();
        }
        if (index == size - 1) {
            return tail.getData();
        } else {
            LinkedListNode<T> temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
            return temp.getData();
        }
    }


    @Override
    public T remove() {
        if (isEmpty()) {
            return null;
        } else {
            if (size == 1) {
                T data = tail.getData();
                tail = null;
                head = null;
                size--;
                return data;
            } else {
                T data = tail.getData();
                LinkedListNode<T> temp = head;
                for (int i = 0; i < size - 2; i++) {
                    temp = temp.getNext();
                }

                temp.setNext(null);
                tail = temp;
                size--;
                return data;
            }
        }
    }


    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("The index does not exist.");
        }
        if (index == 0) {
            return removeFromFront();
        }
        if (index == size - 1) {
            return remove();
        }

        LinkedListNode<T> prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.getNext();
        }
        LinkedListNode<T> temp = prev.getNext();
        prev.setNext(temp.getNext());
        T data = temp.getData();
        size--;
        return data;
    }


    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        } else {
            if (size == 1) {
                tail = null;
            }

            T data = head.getData();
            head = head.getNext();
            size--;
            return data;
        }
    }


    @Override
    public T setAtIndex(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index does not exist.");
        }
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data to set cannot be null.");
        }
        LinkedListNode<T> temp = head;
        T theData = head.getData();
        if (index == 0) {
            head.setData(data);
            return theData;
        }
        if (index == size - 1) {
            theData = tail.getData();
            tail.setData(data);
            return theData;
        } else {
            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
            theData = temp.getData();
            temp.setData(data);
            return theData;
        }
    }


    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = get(i);
        }
        return array;
    }


    @Override
    public boolean isEmpty() {
        return (size == 0);
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
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
