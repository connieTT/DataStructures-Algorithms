import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

/**
 * Basic tests for the implementation of a BST.
 *
 * @author CS 1332 TAs
 * @version 1.1
 */
public class MinHeapStudentTests {

    private MinHeap<Integer> minHeap;
    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        minHeap = new MinHeap<Integer>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddConstructor() {
        minHeap = new MinHeap<Integer>(4, 6, 8, 3, 2);

        assertEquals(6, minHeap.getBackingArray().length);

        assertNull(minHeap.getBackingArray()[0]);
        assertEquals((Integer) 2, minHeap.getBackingArray()[1]);
        assertEquals((Integer) 3, minHeap.getBackingArray()[2]);
        assertEquals((Integer) 8, minHeap.getBackingArray()[3]);
        assertEquals((Integer) 4, minHeap.getBackingArray()[4]);
        assertEquals((Integer) 6, minHeap.getBackingArray()[5]);

        assertEquals(5, minHeap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {

        minHeap.add(4);
        minHeap.add(6);
        minHeap.add(8);
        minHeap.add(3);
        minHeap.add(2);

        assertEquals(10, minHeap.getBackingArray().length);

        assertNull(minHeap.getBackingArray()[0]);
        assertEquals((Integer) 2, minHeap.getBackingArray()[1]);
        assertEquals((Integer) 3, minHeap.getBackingArray()[2]);
        assertEquals((Integer) 8, minHeap.getBackingArray()[3]);
        assertEquals((Integer) 6, minHeap.getBackingArray()[4]);
        assertEquals((Integer) 4, minHeap.getBackingArray()[5]);
        assertNull(minHeap.getBackingArray()[6]);
        assertNull(minHeap.getBackingArray()[7]);
        assertNull(minHeap.getBackingArray()[8]);
        assertNull(minHeap.getBackingArray()[9]);

        assertEquals(5, minHeap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        minHeap.add(4);
        minHeap.add(6);
        minHeap.add(8);
        minHeap.add(3);
        minHeap.add(2);

        assertEquals(10, minHeap.getBackingArray().length);
        assertEquals(5, minHeap.size());

        assertEquals((Integer) 2, minHeap.remove());
        assertEquals(4, minHeap.size());
        assertArrayEquals(
                new Integer[] {null, 3, 4, 8, 6,
                        null, null, null, null, null}, minHeap.getBackingArray());

        assertEquals((Integer) 3, minHeap.remove());
        assertEquals(3, minHeap.size());
        assertArrayEquals(new Integer[] {null, 4, 6, 8, null, null,
                null, null, null, null}, minHeap.getBackingArray());

        assertEquals((Integer) 4, minHeap.remove());
        assertEquals(2, minHeap.size());
        assertArrayEquals(new Integer[] {null, 6, 8, null, null, null,
                        null, null, null, null},
                minHeap.getBackingArray());

        assertEquals((Integer) 6, minHeap.remove());
        assertEquals(1, minHeap.size());
        assertArrayEquals(new Integer[] {null, 8, null, null, null, null,
                        null, null, null, null},
                minHeap.getBackingArray());

        assertEquals((Integer) 8, minHeap.remove());
        assertEquals(0, minHeap.size());
        assertArrayEquals(new Integer[10], minHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        assertTrue(minHeap.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testSize() {
        assertEquals(0, minHeap.size());
        minHeap.add(1);
        assertEquals(1, minHeap.size());
        minHeap.add(2);
        assertEquals(2, minHeap.size());
        minHeap.remove();
        assertEquals(1, minHeap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        minHeap.add(4);
        minHeap.add(6);
        minHeap.add(8);
        minHeap.add(3);
        minHeap.add(2);

        minHeap.clear();
        assertEquals(0, minHeap.size());
        assertArrayEquals(new Integer[10], minHeap.getBackingArray());
    }
}
