import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Your implementation of a Binary Search Tree.
 *
 * @author Tongtong Zhao
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST.
     * YOU DO NOT NEED TO IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The collection cannot be null.");
        }
        for (T element: data) {
            if (element == null) {
                throw new IllegalArgumentException(
                        "The element cannot be null.");
            }
            add(element);
        }
    }

    @Override
    public void add(T data) {
        if (data ==  null) {
            throw new IllegalArgumentException(
                    "The data to add cannot be null.");
        }
        if (root == null) {
            root = new BSTNode<>(data);
            size++;
        }
        add(data, root);
    }
    /**
     * Finds the position that the data should be placed, adds a new node
     * with data and modifies the subtree of the start point.
     * @param data the data to add to the tree
     * @param current the place to stard the comparison with the data
     */
    private void add(T data, BSTNode<T> current) {
        if (current.getData().compareTo(data) != 0) {
            if (current.getData().compareTo(data) > 0) {
                if (current.getLeft() == null) {
                    current.setLeft(new BSTNode<>(data));
                    size++;
                } else {
                    current = current.getLeft();
                    add(data, current);
                }
            } else if (current.getData().compareTo(data) < 0) {
                if (current.getRight() == null) {
                    current.setRight(new BSTNode<>(data));
                    size++;
                } else {
                    current = current.getRight();
                    add(data, current);
                }
            }
        }
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data to remove cannot be null.");
        }
        T result = get(data);
        root = remove(data, root);
        size--;
        return result;
    }
    /**
     * Removes the node that contains the data user asked
     * for and then modified the subtree of the starting point
     * @param data the data to add to the tree
     * @param start the start node to begin search
     *              for the intended removed node with data
     * @return the modified node of the start node
     */
    private BSTNode<T> remove(T data, BSTNode<T> start) {
        if (start == null) {
            size++;
            throw new java.util.NoSuchElementException(
                    "No such element in the tree.");
        }
        if ((start.getData().compareTo(data) > 0)) {
            start.setLeft(remove(data, start.getLeft()));
        } else if ((start.getData().compareTo(data)) < 0) {
            start.setRight(remove(data, start.getRight()));
        } else if ((start.getLeft() != null)
                && (start.getRight() != null)) {
            start.setData(predecessor(start.getLeft()).getData());
            start.setLeft(removeMax(start.getLeft()));
        } else {
            start = (
                    start.getLeft() != null)
                    ? start.getLeft() : start.getRight();
        }
        return start;
    }
    /**Removes largest possible data within
     * the subtree of the node
     * @param n the node you want to find successor for
     * @return the modified node
     */
    private BSTNode removeMax(BSTNode n) {
        if (n.getRight() != null) {
            n.setRight(removeMax(n.getRight()));
            return n;
        } else {
            return n.getLeft();
        }
    }
    /**
     * Find predecessor of a node
     * @param current input node
     * @return node that represent parent of the left leaf
     */
    private BSTNode<T> predecessor(BSTNode<T> current) {
        if (current.getRight() == null) {
            return current;
        } else {
            return predecessor(current.getRight());
        }
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data to get cannot be null.");
        }
        return get(data, root);
    }
    /**
     * Helper method for get
     * @param current input node
     * @param data data to add
     * @return return data found
     */
    public T get(T data, BSTNode<T> current) {
        if (current == null) {
            throw new java.util.NoSuchElementException(
                    "The data is not found.");
        }
        if (current.getData().equals(data)) {
            return current.getData();
        }
        if (current.getData().compareTo(data) > 0) {
            return get(data, current.getLeft());
        } else {
            return get(data, current.getRight());
        }
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data to check cannot be null.");
        }
        return contains(data, root);
    }
    /**
     * Helper method for contains
     * @param current input node
     * @param data data to add
     * @return return true if the element is in BST
     */
    private boolean contains(T data, BSTNode<T> current) {
        if (current == null) {
            return false;
        }
        if (current.getData().equals(data)) {
            return true;
        }
        if (current.getData().compareTo(data) > 0) {
            current = current.getLeft();
            return contains(data, current);
        } else {
            current = current.getRight();
            return contains(data, current);
        }
    }

    @Override
    public T nextLargest(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data to check cannot be null.");
        }
        if (root == null) {
            throw new java.util.NoSuchElementException(
                    "The data is not found.");
        }
        return nextLargest(data, root);
    }

    /**
     * Help method to find the next largest
     * @param data the data to find
     * @param current the current node
     * @return the next largest of the given data
     */
    private T nextLargest(T data, BSTNode<T> current) {
        if (current.getData().compareTo(data) == 0) {
            if (current.getRight() != null) {
                return successor(current.getRight()).getData();
            } else {
                // find the smallest ancestor
                return null;
            }
        } else if (current.getData().compareTo(data) > 0) {
            //go left
            if (current.getLeft() == null) {
                throw new java.util.NoSuchElementException(
                        "The data is not found.");
            } else {
                return nextLargest(data, current.getLeft());
            }
        } else {
            //go right
            if (current.getRight() == null) {
                throw new java.util.NoSuchElementException(
                        "The data is not found.");
            } else {
                return nextLargest(data, current.getRight());
            }
        }
    }
    /**
     * Find successor of a node
     * @param current input node
     * @return node that represent parent of the left leaf
     */
    private BSTNode<T> successor(BSTNode<T> current) {
        if (current.getLeft() == null) {
            return current;
        } else {
            return successor(current.getLeft());
        }
    }

    @Override
    public String toString() {
        return toString(root);
    }
    /**
     * Returns a string representation of the tree. The string should be
     * formatted as follows:
     *        {currentData, leftSubtree, rightSubtree}
     * @param node input node
     * @return String representation of the tree
     */
    private String toString(BSTNode<T> node) {
        if (node == null) {
            return "{}";
        } else {
            return ("{" + node.getData() + ", "
                    + toString(node.getLeft()) + ", "
                    + toString(node.getRight()) + "}");
        }
    }

    @Override
    public T getMax() {
        if (root == null) {
            return null;
        }
        return getMax(root);
    }
    /**
     * Preorder traversal of the tree
     * @param node input node
     * @return the largest data
     */
    private T getMax(BSTNode<T> node) {
        if (node.getRight() == null) {
            return node.getData();
        } else {
            return getMax(node.getRight());
        }
    }

    @Override
    public T getMin() {
        if (root == null) {
            return null;
        }
        return getMin(root);
    }
    /**
     * Preorder traversal of the tree
     * @param node input node
     * @return the smallest data
     */
    private T getMin(BSTNode<T> node) {
        if (node.getLeft() == null) {
            return node.getData();
        } else {
            return getMin(node.getLeft());
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }
    /**
     * Preorder traversal of the tree
     * @param node input node
     * @param list the list contain BST elements
     */
    private void preorder(BSTNode<T> node, List<T> list) {
        if (node != null) {
            list.add(node.getData());
            preorder(node.getLeft(), list);
            preorder(node.getRight(), list);
        }
    }

    @Override
    public List<T> postorder() {
        List<T> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }
    /**
     * Postorder traversal of the tree
     * @param node input node
     * @param list the list contain BST elements
     */
    private void postorder(BSTNode<T> node, List<T> list) {
        if (node != null) {
            postorder(node.getLeft(), list);
            postorder(node.getRight(), list);
            list.add(node.getData());
        }
    }

    @Override
    public List<T> inorder() {
        List<T> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }
    /**
     * Inorder traversal of the tree
     * @param node input node
     * @param list the list contain BST elements
     */
    private void inorder(BSTNode<T> node, List<T> list) {
        if (node != null) {
            inorder(node.getLeft(), list);
            list.add(node.getData());
            inorder(node.getRight(), list);
        }
    }

    @Override
    public List<T> levelorder() {
        List<T> list = new ArrayList<>();
        int height = height(root) + 1;
        for (int i = 1; i <= height; i++) {
            level(root, i, list);
        }
        return list;
    }
    /**
     * Level order traversal of the tree
     * @param level level of the recursion call
     * @param list the list contain BST elements
     * @param node node that needs to be travel for next recursion call
     */
    private void level(BSTNode<T> node, int level, List<T> list) {
        if (node != null) {
            if (level == 1) {
                list.add(node.getData());
            } else {
                level(node.getLeft(), level - 1, list);
                level(node.getRight(), level - 1, list);
            }
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        return height(root);
    }
    /**
     * Helper method that return height of BST
     * @param node input node
     * @return height
     */
    private int height(BSTNode<T> node) {
        if (node == null) {
            return -1;
        }
        if (height(node.getLeft()) > height(node.getRight())) {
            return height(node.getLeft()) + 1;
        } else {
            return height(node.getRight()) + 1;
        }
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}
