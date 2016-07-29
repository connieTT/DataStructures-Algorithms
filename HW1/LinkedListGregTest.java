import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LinkedListGregTest {
    public static final int TIMEOUT = 500;

    private SinglyLinkedList<String> list;

    @Before
    public void setUp() {
        list = new SinglyLinkedList<>();
    }

    @Test(timeout = TIMEOUT)
    public void test_addToIndex() {
        list.addToIndex(0, "a");
        assertArrayEquals(new String[]{"a"}, list.toArray());
        list.addToIndex(1, "b");
        assertArrayEquals(new String[]{"a","b"}, list.toArray());
        list.addToIndex(2, "c");
        assertArrayEquals(new String[]{"a","b","c"}, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void test_addAll() {
        ArrayList<String> someList = new ArrayList<>();
        someList.add("a");
        someList.add("b");
        someList.add("c");
        someList.add("d");

        list.addAll(someList);
        assertEquals(someList.size(), list.size());

        assertArrayEquals(someList.toArray(), list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void test_setAtIndex() {
        list.add("a");
        list.add("b");
        list.add("c");

        list.setAtIndex(0, "d");
        list.setAtIndex(1,"e");
        list.setAtIndex(2,"f");
        assertArrayEquals(new String[]{"d", "e", "f"}, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void test_add() {
        list.add("a");
        assertEquals("a", list.getHead().getData());
        assertEquals("a", list.getTail().getData());
        assertEquals(1, list.size());
        list.add("b");
        assertEquals("a", list.getHead().getData());
        assertEquals("b", list.getTail().getData());
        assertEquals(2, list.size());
        list.add("c");
        assertArrayEquals(new String[]{"a", "b", "c"}, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void test_ToArray() {
        assertArrayEquals(new String[]{}, list.toArray());
        list.add("a");
        assertArrayEquals(new String[]{"a"}, list.toArray());
        list.add("b");
        assertArrayEquals(new String[]{"a", "b"}, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void test_addToFront() {
        list.addToFront("b");
        assertArrayEquals(new String[]{"b"}, list.toArray());
        list.addToFront("a");
        assertArrayEquals(new String[]{"a", "b"}, list.toArray());

    }

    @Test(timeout = TIMEOUT)
    public void test_removeFromFront() {
        list.add("a");
        assertEquals("a", list.removeFromFront());
        assertArrayEquals(new String[]{}, list.toArray());
        list.add("a");
        list.add("b");
        assertEquals("a", list.removeFromFront());
        assertArrayEquals(new String[]{"b"}, list.toArray());
        assertEquals("b", list.removeFromFront());
        assertNull(list.removeFromFront());
    }

    @Test(timeout = TIMEOUT)
    public void test_removeAtIndex() {
        list.add("a");
        assertEquals("a", list.removeAtIndex(0));
        assertArrayEquals(new String[]{}, list.toArray());
        list.add("a");
        list.add("b");
        assertEquals("b", list.removeAtIndex(1));
        assertArrayEquals(new String[]{"a"}, list.toArray());
        assertEquals("a", list.removeAtIndex(0));
        list.add("a");
        list.add("b");
        list.add("c");
        assertEquals("c", list.removeAtIndex(2));
        assertArrayEquals(new String[]{"a", "b"}, list.toArray());
        list.clear();
        list.add("a");
        list.add("b");
        list.add("c");
        assertEquals("b", list.removeAtIndex(1));
        assertArrayEquals(new String[]{"a", "c"}, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void test_clear() {
        list.add("a");
        list.add("b");
        list.clear();
        assertArrayEquals(new String[]{}, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void test_get() {
        list.add("a");
        assertEquals("a", list.get(0));
        list.add("b");
        assertEquals("b", list.get(1));
    }

    @Test(timeout = TIMEOUT)
    public void test_contains() {
        list.add("a");
        assertTrue(list.contains("a"));
        list.add("b");
        assertTrue(list.contains("b"));
        list.add("c");
        list.add("d");
        assertTrue(list.contains("d"));
        assertFalse(list.contains("z"));
    }

    @Test(timeout = TIMEOUT)
    public void test_remove() {
        list.add("a");
        assertEquals("a", list.remove());
        assertArrayEquals(new String[]{}, list.toArray());
        list.add("a");
        list.add("b");
        assertEquals("b", list.remove());
        assertArrayEquals(new String[]{"a"}, list.toArray());
        assertEquals("a", list.remove());
        assertNull(list.remove());
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void test_addToIndex_indexException() {
        list.addToIndex(-1, "a");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void test_addToIndex_indexExceptionTwo() {
        list.addToIndex(1, "a");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void test_addToIndex_arg_exception() {
        list.addToIndex(0, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void test_addAll_arg_exception() {
        list.addAll(null);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void test_setAtIndex_indexException() {
        list.setAtIndex(-1, "a");
    }
    
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void test_setAtIndex_indexExceptionTwo() {
        list.setAtIndex(0, "a");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void test_setAtIndex_indexExceptionThree() {
        list.setAtIndex(1, "a");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void test_setAtIndex_indexException4() {
        list.add("a");
        list.setAtIndex(0, null);
    }

}