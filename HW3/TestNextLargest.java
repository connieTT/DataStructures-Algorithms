import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 *
 * @author Greg
 * @version 1.0
 */
public class TestNextLargest {

    private BST<Integer> bst;
    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        bst = new BST<>();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void test_exception() {
        bst.nextLargest(null);
    }

    @Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
    public void test_no_such_ele_exception() {
        bst.nextLargest(0);
    }

    @Test(timeout = TIMEOUT)
    public void testNextLargest() {
        /** all left
         *              3
         *             /
         *            2
         *           /
         *          1
         */
        bst.add(3);
        bst.add(2);
        bst.add(1);
        assertEquals((Integer) 2, bst.nextLargest(1));
        assertEquals((Integer) 3, bst.nextLargest(2));
        assertNull(bst.nextLargest(3));

        bst.clear();
        /** left-right
         *             3
         *            /
         *           1
         *            \
         *             2
         */
        bst.add(3);
        bst.add(1);
        bst.add(2);
        assertEquals((Integer) 3, bst.nextLargest(2));
        assertEquals((Integer) 2, bst.nextLargest(1));
        assertNull(bst.nextLargest(3));

        bst.clear();
        /** all right
         *              1
         *               \
         *                2
         *                 \
         *                  3
         */
        bst.add(1);
        bst.add(2);
        bst.add(3);
        assertEquals((Integer) 2, bst.nextLargest(1));
        assertEquals((Integer) 3, bst.nextLargest(2));
        assertNull(bst.nextLargest(3));

        bst.clear();
        /** right left
         *                  1
         *                   \
         *                    3
         *                   /
         *                  2
         */
        bst.add(1);
        bst.add(3);
        bst.add(2);
        assertEquals((Integer) 3, bst.nextLargest(2));
        assertEquals((Integer) 2, bst.nextLargest(1));
        assertNull(bst.nextLargest(3));
    }

}