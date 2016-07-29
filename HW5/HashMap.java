import java.util.List;
import java.util.Set;

/**
 * Your implementation of HashMap.
 * 
 * @author Tongtong Zhao
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] backingArray;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        backingArray = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
        size = 0;
    }


    @Override
    public V put(K key, V value) {
        if (key == null | value == null) {
            throw new IllegalArgumentException("The key or value is null.");
        }
        if (size + 1 > backingArray.length * MAX_LOAD_FACTOR) {
            regrow();
        }
        MapEntry<K, V> add = new MapEntry<>(key, value);
        add.setRemoved(false);
        int hash;
        //Negate the hashcode if it's negative
        if (key.hashCode() < 0) {
            hash = (-1 * key.hashCode()) % backingArray.length;
        } else {
            hash = key.hashCode() % backingArray.length;
        }
        //Start adding
        for (int i = 0; i < backingArray.length; i++) {
            //Detect the element in the slot in each iteration
            int slot = (hash + i) % backingArray.length;
            //if the slot is null, we reached the end of the bucket
            if (backingArray[slot] == null) {
                //if we got here, that being said we can not find the key and we
                //reached the end of the bucket,therefore we need to restart the
                //iteration to find the first available slot
                for (int j = 0; j < backingArray.length; j++) {
                    slot = (hash + j) % backingArray.length;
                    //the first available slot is in the end
                    if (backingArray[slot] == null) {
                        backingArray[slot] = add;
                        size++;
                        return null;
                    }
                    //the first available slot is previous removed element
                    if (backingArray[slot].isRemoved()) {
                        backingArray[slot] = add;
                        size++;
                        return null;
                    }
                }
            }
            //if the key is existed in the bucket and is not removed,
            //we reset the value; otherwise go to next iteration
            if (backingArray[slot].getKey().equals(key)
                    & !backingArray[slot].isRemoved()) {
                V oldValue = backingArray[slot].getValue();
                backingArray[slot] = add;
                return oldValue;
            }
        }
        //after table.length iteration, we still can not find the element
        //and no null is reach, we regrow the table.
        regrow();
        return put(key, value);
    }
    /**
     * Regrow backing array
     */
    private void regrow() {
        size = 0;
        MapEntry<K, V>[] old = backingArray;
        backingArray = (MapEntry<K, V>[])
                new MapEntry[backingArray.length * 2 + 3];
        for (int i = 0; i < old.length; i++) {
            if (old[i] != null) {
                if (!old[i].isRemoved()) {
                    put(old[i].getKey(), old[i].getValue());
                }
            }
        }
    }


    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key is null.");
        }
        int hash;
        if (key.hashCode() < 0) {
            hash = (-1 * key.hashCode()) % backingArray.length;
        } else {
            hash = key.hashCode() % backingArray.length;
        }
        //iterate to find the element
        for (int i = 0; i < backingArray.length; i++) {
            int slot = (hash + i) % backingArray.length;
            //reached the end of the bucket
            if (backingArray[slot] == null) {
                throw new java.util.NoSuchElementException(
                        "The key is not found.");
            }
            //found the element
            if (backingArray[slot].getKey().equals(key)) {
                //the element has been removed
                if (backingArray[slot].isRemoved()) {
                    throw new java.util.NoSuchElementException(
                            "The key is not found.");
                }
                //the element needs to be removed
                backingArray[slot].setRemoved(true);
                size--;
                return backingArray[slot].getValue();
            }
        }
        return null;
    }


    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key is null.");
        }
        int hash;
        if (key.hashCode() < 0) {
            hash = (-1 * key.hashCode()) % backingArray.length;
        } else {
            hash = key.hashCode() % backingArray.length;
        }
        for (int i = 0; i < backingArray.length; i++) {
            int slot = (hash + i) % backingArray.length;
            if (backingArray[slot] == null) {
                throw new java.util.NoSuchElementException(
                        "The key is not found.");
            }
            if (backingArray[slot].getKey().equals(key)) {
                if (backingArray[slot].isRemoved()) {
                    throw new java.util.NoSuchElementException(
                            "The key is not found.");
                } else {
                    return backingArray[slot].getValue();
                }
            }
        }
        return null;
    }


    @Override
    public int count(V value) {
        if (value == null) {
            throw new IllegalArgumentException("The value is null.");
        }
        int count = 0;
        for (int i = 0; i < backingArray.length; i++) {
            if (backingArray[i] != null
                    && !backingArray[i].isRemoved()) {
                if (backingArray[i].getValue().equals(value)) {
                    count++;
                }
            }
        }
        return count;
    }


    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key is null.");
        }
        int hash;
        if (key.hashCode() < 0) {
            hash = (-1 * key.hashCode()) % backingArray.length;
        } else {
            hash = key.hashCode() % backingArray.length;
        }
        for (int i = 0; i < backingArray.length; i++) {
            int slot = (hash + i) % backingArray.length;
            if (backingArray[slot] == null) {
                return false;
            }
            if (backingArray[slot].getKey().equals(key)) {
                return !backingArray[slot].isRemoved();
            }
        }
        return false;
    }


    @Override
    public void clear() {
        backingArray = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public Set<K> keySet() {
        Set<K> set = new java.util.HashSet<>();
        for (int i = 0; i < backingArray.length; i++) {
            if (backingArray[i] != null) {
                if (!backingArray[i].isRemoved()) {
                    set.add(backingArray[i].getKey());
                }
            }
        }
        return set;
    }


    @Override
    public List<V> values() {
        List<V> list = new java.util.LinkedList<>();
        for (int i = 0; i < backingArray.length; i++) {
            if (backingArray[i] != null) {
                if (!backingArray[i].isRemoved()) {
                    list.add(backingArray[i].getValue());
                }
            }
        }
        return list;
    }


    @Override
    public void resizeBackingArray(int length) {
        if (length <= 0 || length < size) {
            throw new IllegalArgumentException(
                    "The resize length is not valid.");
        }
        MapEntry<K, V>[] temp = (MapEntry<K, V>[]) new MapEntry[length];
        for (int i = 0; i < backingArray.length; i++) {
            if (backingArray[i] != null && !backingArray[i].isRemoved()) {
                MapEntry<K, V> insert =
                        new MapEntry<>(backingArray[i].getKey(),
                                backingArray[i].getValue());
                int position = (backingArray[i].getKey().hashCode() < 0) ? ((
                        -1 * backingArray[i].getKey().hashCode())
                        % temp.length) : (
                        backingArray[i].getKey().hashCode() % temp.length);

                if (temp[position] == null) {
                    temp[position] = insert;
                } else {
                    int currentPosition = (position + 1) % temp.length;
                    while (currentPosition != position) {
                        if (temp[currentPosition] == null) {
                            temp[currentPosition] = insert;
                            currentPosition = (position - 1) % temp.length;
                        }
                        currentPosition = (currentPosition + 1) % temp.length;
                    }
                }
            }
        }
        backingArray = temp;
    }

    // DO NOT MODIFY OR USE CODE BEYOND THIS POINT.

    @Override
    public MapEntry<K, V>[] getArray() {
        return backingArray;
    }

}
