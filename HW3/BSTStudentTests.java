import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Madeleine on 6/4/2016.
 */
public class BSTStudentTests {
    private BST<Integer> bst;
    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        bst = new BST<>();
    }

    @Test(timeout = TIMEOUT)
    public void testConstructorWithCollection() {
        ArrayList<Integer> toAdd = new ArrayList<>();
        assertEquals(0, toAdd.size());

        toAdd.add(10);
        toAdd.add(-1);
        toAdd.add(13);
        toAdd.add(15);
        toAdd.add(5);
        toAdd.add(13);

        assertEquals(6, toAdd.size());

        bst = new BST<>(toAdd);
        System.out.println("Actual Tree: " + bst.toString());
        System.out.println("Expected Tree: {10, {-1, {}, {5, {}, {}}}, {13, {}, {15, {}, {}}}}");
        assertEquals(5, bst.size());

        BSTNode<Integer> root = bst.getRoot();
        assertNotNull(root);
        assertEquals((Integer) 10, root.getData());

        BSTNode<Integer> traverse = root.getLeft();
        assertNotNull(traverse);
        assertEquals((Integer) (-1), traverse.getData());

        traverse = traverse.getRight();
        assertNotNull(traverse);
        assertEquals((Integer) 5, traverse.getData());

        traverse = traverse.getRight();
        assertNull(traverse);

        traverse = root.getRight();
        assertNotNull(traverse);
        assertEquals((Integer) 13, traverse.getData());

        traverse = traverse.getRight();
        assertNotNull(traverse);
        assertEquals((Integer) 15, traverse.getData());

        traverse = traverse.getLeft();
        assertNull(traverse);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddException() {
        bst.add(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        bst.add(10);
        bst.add(-1);
        bst.add(13);
        bst.add(15);
        bst.add(5);
        bst.add(5);
        bst.add(-6);
        assertEquals("If Actual == 7, check that size doesn't increase when you try to add a data point already in the tree", 6, bst.size());

        BSTNode<Integer> root = bst.getRoot();
        assertNotNull(root);
        assertEquals((Integer) 10, root.getData());

        BSTNode<Integer> one = root.getLeft();
        assertNotNull(one);
        assertEquals((Integer) (-1), one.getData());

        BSTNode<Integer> two = one.getLeft();
        assertNotNull(two);
        assertEquals((Integer) (-6), two.getData());
        assertNull(two.getLeft());
        assertNull(two.getRight());

        BSTNode<Integer> three = one.getRight();
        assertNotNull(three);
        assertEquals((Integer) 5, three.getData());
        assertNull(three.getLeft());
        assertNull(three.getRight());

        BSTNode<Integer> four = root.getRight();
        assertNotNull(four);
        assertEquals((Integer) 13, four.getData());
        assertNull(four.getLeft());

        BSTNode<Integer> five = four.getRight();
        assertNotNull(five);
        assertEquals((Integer) 15, five.getData());
        assertNull(five.getLeft());
        assertNull(five.getRight());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveIllegalArgException() {
        bst.remove(null);
    }

    //If you failed this exception, it's because your remove method doesn't throw
    //a NoSuchElementException when size == 0.
    @Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
    public void testRemoveNoSuchElementExceptionOne() {
        bst.remove(0);
    }

    //If you failed this exception, it's because your remove method doesn't throw
    //a NoSuchElementException when attempting to remove a data point not in the tree.
    @Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
    public void testRemoveNoSuchElementExceptionTwo() {
        bst.add(1);
        bst.remove(0);
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        bst.add(10);
        bst.add(-1);
        bst.add(-6);
        bst.add(5);
        bst.add(-7);
        bst.add(13);
        bst.add(15);
        assertEquals(7, bst.size());
        assertNotNull(bst.getRoot());
        assertEquals((Integer) 10, bst.getRoot().getData());

        assertEquals((Integer) (-6), bst.remove(-6));
        assertEquals(6, bst.size());
        assertNotNull(bst.getRoot());
        assertNotNull(bst.getRoot().getLeft());
        assertEquals((Integer) (-1), bst.getRoot().getLeft().getData());
        assertNotNull(bst.getRoot().getLeft().getLeft());
        assertEquals((Integer) (-7), bst.getRoot().getLeft().getLeft().getData());
        assertFalse(bst.getRoot().getLeft().getData().equals((Integer) (-5)));

        assertEquals((Integer) (-1), bst.remove(-1));
        assertEquals(5, bst.size());
        assertNotNull(bst.getRoot());
        assertNotNull(bst.getRoot().getLeft());
        assertEquals((Integer) (-7), bst.getRoot().getLeft().getData());
        assertNull(bst.getRoot().getLeft().getLeft());
        assertNotNull(bst.getRoot().getLeft().getRight());
        assertEquals((Integer) 5, bst.getRoot().getLeft().getRight().getData());

        assertEquals((Integer) 15, bst.remove(15));
        assertEquals(4, bst.size());
        assertNotNull(bst.getRoot());
        assertNotNull(bst.getRoot().getRight());
        assertNull(bst.getRoot().getRight().getRight());

        //If you've passed the assertions up to this point, we already know you
        //can remove things with no children, one child, and two children so I'm
        //going to fastforward through the rest up til the removal of the root.
        assertEquals((Integer) 13, bst.remove(13));
        assertEquals((Integer) 5, bst.remove(5));
        assertEquals((Integer) (-7), bst.remove(-7));

        assertEquals((Integer) 10, bst.remove(10));
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
    }

    //If you failed this exception, it's because your get method doesn't throw
    //a NoSuchElementException when attempting to get a data point from an empty tree.
    @Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
    public void testGetNoSuchElementExceptionOne() {
        bst.get(0);
    }

    @Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
    public void testGetNoSuchElementExceptionTwo() {
        bst.add(1);
        bst.get(0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetIllegalArgException() {
        bst.get(null);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        bst = new BST<>();
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        bst.add(50);
        bst.add(25);
        bst.add(10);
        bst.add(75);
        bst.add(60);
        bst.add(70);

        assertEquals(6, bst.size());
        assertEquals(50, (int) bst.getRoot().getData());

        assertEquals((Integer) 50, bst.get(50));
        assertEquals((Integer) 50, bst.get(50));
        assertEquals((Integer) 25, bst.get(25));
        assertEquals((Integer) 10, bst.get(10));
        assertEquals((Integer) 75, bst.get(75));
        assertEquals((Integer) 60, bst.get(60));
        assertEquals((Integer) 70, bst.get(70));
    }

    @Test(timeout = TIMEOUT)
    public void testContains(){
        assertEquals(false, bst.contains(3));

        bst.add(10);
        bst.add(-1);
        bst.add(13);
        bst.add(-6);

        assertEquals(true, bst.contains(10));
        assertEquals(true, bst.contains(10));
        assertEquals(true, bst.contains(-1));
        assertEquals(true, bst.contains(13));
        assertEquals(true, bst.contains(-6));
        assertEquals(false, bst.contains(4));
        assertEquals(false, bst.contains(-7));
        assertEquals(false, bst.contains(-32));
    }

    //If you failed this exception, it's because your get method doesn't throw
    //a NoSuchElementException when attempting to get a data point from an empty tree.
    @Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
    public void testNextLargestNoSuchElementExceptionOne() {
        bst.nextLargest(10);
    }

    @Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
    public void testNextLargestNoSuchElementExceptionTwo() {
        bst.add(1);
        bst.nextLargest(0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testNextLargestIllegalArgException() {
        bst.nextLargest(null);
    }

    //Gregory Kimble's getNextLargestTest.
    @Test(timeout = TIMEOUT)
    public void testGetNextLargest() {
        bst = new BST<>();
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

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

    //Grayson Bianco's toString test.
    @Test(timeout = TIMEOUT)
    public void testToString() {
        String expected1 = "{5, {0, {-5, {}, {}}, {3, {}, {}}}, {10, {7, {}, " +
                "{}}, {13, {}, {}}}}";
        String expected2 = "{7, {0, {-2, {}, {}}, {5, {}, {}}}, {12, {11, {}," +
                " {}}, {15, {}, {16, {}, {}}}}}";

        assertNull(bst.getRoot());
        assertEquals(0, bst.size());

        bst.add(5);
        bst.add(0);
        bst.add(-5);
        bst.add(3);
        bst.add(10);
        bst.add(7);
        bst.add(13);

        String actual1 = bst.toString();

        assertEquals(expected1, actual1);

        bst.clear();
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        bst.add(7);
        bst.add(0);
        bst.add(12);
        bst.add(-2);
        bst.add(5);
        bst.add(11);
        bst.add(15);
        bst.add(16);

        BSTNode<Integer> traverse = bst.getRoot();
        assertEquals((Integer) 7, traverse.getData());
        assertEquals((Integer) 0, traverse.getLeft().getData());
        assertEquals((Integer) (-2), traverse.getLeft().getLeft().getData());
        assertEquals((Integer) 5, traverse.getLeft().getRight().getData());
        assertEquals((Integer) 12, traverse.getRight().getData());
        assertEquals((Integer) 11, traverse.getRight().getLeft().getData());
        assertEquals((Integer) 15, traverse.getRight().getRight().getData());
        assertEquals((Integer) 16, traverse.getRight().getRight().getRight()
                .getData());

        String actual2 = bst.toString();

        assertEquals(expected2, actual2);
    }

    @Test(timeout = TIMEOUT)
    public void testGetMax() {
        bst = new BST<>();
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        assertNull(bst.getMax());

        bst.add(50);
        assertEquals((Integer) 50, bst.getMax());

        bst.add(25);
        assertEquals((Integer) 50, bst.getMax());

        bst.add(40);
        assertEquals((Integer) 50, bst.getMax());

        bst.add(75);
        assertEquals((Integer) 75, bst.getMax());

        bst.add(60);
        assertEquals((Integer) 75, bst.getMax());

        bst.add(100);
        assertEquals((Integer) 100, bst.getMax());
    }

    @Test(timeout = TIMEOUT)
    public void testGetMin() {
        bst = new BST<>();
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        assertNull(bst.getMin());

        bst.add(50);
        assertEquals((Integer) 50, bst.getMin());

        bst.add(25);
        assertEquals((Integer) 25, bst.getMin());

        bst.add(40);
        assertEquals((Integer) 25, bst.getMin());

        bst.add(9);
        assertEquals((Integer) 9, bst.getMin());

        bst.add(0);
        assertEquals((Integer) 0, bst.getMin());

        bst.add(-7);
        assertEquals( -7, (int) bst.getMin());
    }

    @Test(timeout = TIMEOUT)
    public void testSize() throws Exception{
        assertEquals(0, bst.size());

        bst.add(0);
        assertEquals(1, bst.size());

        bst.add(1);
        assertEquals(2, bst.size());

        bst.add(2);
        assertEquals(3, bst.size());

        bst.add(2);
        assertEquals(3, bst.size());

        try {
            bst.remove(321);
        } catch (java.util.NoSuchElementException e) {
            assertEquals(3, bst.size());
        }

        bst.remove(2);
        assertEquals(2, bst.size());

        bst.remove(1);
        assertEquals(1, bst.size());

        bst.remove(0);
        assertEquals(0, bst.size());
    }

    //TAs' Test.
    @Test(timeout = TIMEOUT)
    public void testPreorder() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        bst.add(5);
        bst.add(0);
        bst.add(-5);
        bst.add(3);
        bst.add(10);
        bst.add(7);
        bst.add(13);

        List<Integer> correctList = new ArrayList<Integer>(
                Arrays.asList(new Integer[]{5, 0, -5, 3, 10, 7, 13}));
        assertEquals(correctList, bst.preorder());
    }

    //TAs' Test.
    @Test(timeout = TIMEOUT)
    public void testInorder() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        bst.add(5);
        bst.add(0);
        bst.add(-5);
        bst.add(3);
        bst.add(10);
        bst.add(7);
        bst.add(13);

        List<Integer> correctList = new ArrayList<Integer>(
                Arrays.asList(new Integer[]{-5, 0, 3, 5, 7, 10, 13}));
        assertEquals(correctList, bst.inorder());
    }

    //TAs' Test.
    @Test(timeout = TIMEOUT)
    public void testPostorder() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        bst.add(5);
        bst.add(0);
        bst.add(-5);
        bst.add(3);
        bst.add(10);
        bst.add(7);
        bst.add(13);

        List<Integer> correctList = new ArrayList<Integer>(
                Arrays.asList(new Integer[]{-5, 3, 0, 7, 13, 10, 5}));
        assertEquals(correctList, bst.postorder());
    }

    //TAs' Test.
    @Test(timeout = TIMEOUT)
    public void testLevelorder() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        bst.add(5);
        bst.add(0);
        bst.add(-5);
        bst.add(3);
        bst.add(10);
        bst.add(7);
        bst.add(13);

        List<Integer> correctList = new ArrayList<Integer>(
                Arrays.asList(new Integer[]{5, 0, 10, -5, 3, 7, 13}));
        assertEquals(correctList, bst.levelorder());
    }

    //TAs' test.
    @Test(timeout = TIMEOUT)
    public void testClear() {
        bst = new BST<>();
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        bst.add(50);
        bst.add(25);
        bst.add(75);
        bst.add(10);
        bst.add(5);
        bst.add(2);
        assertEquals(6, bst.size());

        bst.clear();
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        assertEquals((-1), bst.height());

        bst.add(1);
        assertEquals(0, bst.height());

        bst.add(2);
        assertEquals(1, bst.height());

        bst.add(3);
        assertEquals(2, bst.height());

        bst.add(-1);
        assertEquals(2, bst.height());

        bst.add(0);
        assertEquals(2, bst.height());

        bst.add(-2);
        assertEquals(2, bst.height());

        bst.add(-5);
        assertEquals(3, bst.height());
    }
}