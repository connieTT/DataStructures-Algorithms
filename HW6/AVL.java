import java.util.Collection;
import java.util.List;

/**
 * Your implementation of an AVL Tree.
 *
 * @author YOUR NAME HERE
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {

    // Do not make any new instance variables.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     */
    public AVL() {

    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data entered is null");
        }
        for (T element : data) {
            if (element == null) {
                throw new IllegalArgumentException(
                        "One of the data entered is null");
            }
        }
        for (T element : data) {
            add(element);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data added cannot be null.");
        }
        root = add(data, root);
        size++;
    }
    /**
     * Recursive add helper method.
     * It traverses the tree to find the appropriate location.
     * @param data the data to be added, node the starting node
     * @param node the starting node to search
     * @return the node to be inserted
     */
    private AVLNode<T> add(T data, AVLNode<T> node) {
        if (node == null) {
            node = new AVLNode<>(data);
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(add(data, node.getLeft()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(add(data, node.getRight()));
        } else {
            size--;
        }
        return rotate(node);
    }
    /**
     * Rotates the node's subtree appropriately in many cases
     * @param node the starting node
     * @return the modified node
     */
    private AVLNode<T> rotate(AVLNode<T> node) {
        if (node == null) {
            return null;
        }
        if (calculateBF(node) == -2) {
            if (calculateBF(node.getRight()) == 1) {
                node = rightLeftRotation(node);
            } else if (calculateBF(node.getRight()) == -1
                    || calculateBF(node.getRight()) == 0) {
                node = leftRotation(node);
            }
        } else if (calculateBF(node) == 2) {
            if (calculateBF(node.getLeft()) == -1) {
                node = leftRightRotation(node);
            } else if (calculateBF(node.getLeft()) == 1
                    || calculateBF(node.getLeft()) == 0) {
                node = rightRotation(node);
            }
        }
        if (node.getLeft() != null) {
            rotate(node.getLeft());
        }
        if (node.getRight() != null) {
            rotate(node.getRight());
        }
        node.setHeight(Math.max(height(node.getLeft()),
                height(node.getRight())) + 1);
        node.setBalanceFactor(calculateBF(node));
        return node;
    }
    /**
     * Calculate the balance factor
     * @param node calculate the balance factor of this node
     * @return the value of balance factor
     */
    private int calculateBF(AVLNode<T> node) {
        return height(node.getLeft()) - height(node.getRight());
    }
    /**
     * The rotation method for the RightRight case
     * @param node the starting node
     * @return the modified node after the rotation
     */
    private AVLNode<T> rightRotation(AVLNode<T> node) {
        AVLNode<T> leftChild = node.getLeft();
        node.setLeft(leftChild.getRight());
        leftChild.setRight(node);
        node.setHeight(height(node));
        leftChild.setHeight(height(leftChild));
        return leftChild;
    }
    /**
     * The rotation method for the LeftLeft case
     * @param node the starting node
     * @return the modified node after the rotation
     */
    private AVLNode<T> leftRotation(AVLNode<T> node) {
        AVLNode<T> rightChild = node.getRight();
        node.setRight(rightChild.getLeft());
        rightChild.setLeft(node);
        node.setHeight(height(node));
        rightChild.setHeight(height(rightChild));
        return rightChild;
    }
    /**
     * The rotation method for the LeftRight case
     * @param node the starting node
     * @return the modified node after the rotation
     */
    private AVLNode<T> leftRightRotation(AVLNode<T> node) {
        node.setLeft(leftRotation(node.getLeft()));
        return rightRotation(node);
    }
    /**
     * The rotation method for the RightLeft case
     * @param node the starting node
     * @return the modified node after the rotation
     */
    private AVLNode<T> rightLeftRotation(AVLNode<T> node) {
        node.setRight(rightRotation(node.getRight()));
        return leftRotation(node);
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data entered cannot be null.");
        }
        AVLNode<T> toReturn = new AVLNode<>(null);
        root = remove(data, root, toReturn);
        return toReturn.getData();
    }
    /**
     * Recursive remove helper method
     * @param node the node to start removing
     * @param data the data to remove
     * @param toReturn the node to remove or null
     * @return the new parent node
     */
    private AVLNode<T> remove(T data, AVLNode<T> node, AVLNode<T> toReturn) {
        if (node == null) {
            throw new java.util.NoSuchElementException(
                    "No such data in the tree.");
        }
        if (data.compareTo(node.getData()) == 0) {
            size--;
            toReturn.setData(node.getData());
            if (node.getLeft() == null) {
                node = node.getRight();
            } else if (node.getRight() == null) {
                node = node.getLeft();
            } else {
                node.setData(leftMost(node.getRight()));
                AVLNode<T> temp = new AVLNode<>(null);
                node.setRight(remove(node.getData(), node.getRight(), temp));
                //add it back
                size++;
            }
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(remove(data, node.getLeft(), toReturn));
        } else {
            node.setRight(remove(data, node.getRight(), toReturn));
        }
        return rotate(node);
    }

    /**
     * Finds the successor
     * @param node the starting node of successor
     * @return the predecessor's value
     */
    private T leftMost(AVLNode<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getData();
    }

    @Override
    public List<T> findPathBetween(T start, T end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException(
                    "The data cannot be null.");
        }
        
        return findPathBetween(root, start, end);
    }
    /**
     * Helper method to find the path
     * @param node the starting node to search
     * @param start the first value
     * @param end the second value
     * @return the found node
     */
    private List<T> findPathBetween(AVLNode<T> node, T start, T end) {
        List<T> path = findLCA(node, start, end);
        if (path == null) {
            throw new java.util.NoSuchElementException(
                    "The data not found.");
        } else {
            return path;
        }
    }
    /**
     * Helper method to find the least common ancestor
     * @param node the starting node to search
     * @param start the first value
     * @param end the second value
     * @return the found node
     */
    private List<T> findLCA(AVLNode<T> node, T start, T end) {
        if (node == null) {
            return null;
        }
        if (node.getData().compareTo(start) > 0
                && node.getData().compareTo(end) > 0) {
            return findLCA(node.getLeft(), start, end);
        }
        if (node.getData().compareTo(start) < 0
                && node.getData().compareTo(end) < 0) {
            return findLCA(node.getRight(), start, end);
        }
        List<T> path1 = new java.util.LinkedList<>();
        List<T> path2 = new java.util.LinkedList<>();
        List<T> firstpath = findPath(node, start, path1);
        List<T> secondpath = findPath(node, end, path2);
        boolean exist = ((firstpath != null) && (secondpath != null));
        if (exist) {
            List<T> finalPath = new java.util.LinkedList<>();
            for (int i = firstpath.size() - 1; i > 0; i--) {
                finalPath.add(firstpath.get(i));
            }
            for (int j = 0; j < secondpath.size(); j++) {
                finalPath.add(secondpath.get(j));
            }
            return finalPath;
        } else {
            return null;
        }
    }
    /**
     * Helper method to find the least common ancestor
     * @param node the starting node to search
     * @param temp the value to find
     * @param path the value to find
     * @return the found node
     */
    private List<T> findPath(AVLNode<T> node, T temp, List<T> path) {
        if (node == null) {
            return null;
        }
        path.add(node.getData());
        if (temp.compareTo(node.getData()) == 0) {
            return path;
        } else if (temp.compareTo(node.getData()) < 0) {
            return findPath(node.getLeft(), temp, path);
        } else {
            return findPath(node.getRight(), temp, path);
        }
    }

    @Override
    public T get(T data) {
        return get(data, root);
    }
    /**
     * Recursive get helper method
     * @param node the starting node to search
     * @param data the searching value
     * @return the value of the found node
     */
    private T get(T data, AVLNode<T> node) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data to get cannot be null.");
        }
        if (node == null) {
            throw new java.util.NoSuchElementException(
                    "No such data in the tree.");
        }
        if (data.compareTo(node.getData()) == 0) {
            return node.getData();
        } else if (data.compareTo(node.getData()) < 0) {
            return get(data, node.getLeft());
        } else {
            return get(data, node.getRight());
        }
    }

    @Override
    public boolean contains(T data) {
        return contains(data, root);
    }
    /**
     * Finds whether the AVLtree contains a node with the given data
     * @param data the intended node
     * @param node the node start the search
     * @return true if found, false if not
     */
    private boolean contains(T data, AVLNode<T> node) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data to check cannot be null.");
        }
        if (node == null) {
            return false;
        }
        if (data.compareTo(node.getData()) == 0) {
            return true;
        } else if (data.compareTo(node.getData()) > 0) {
            return contains(data, node.getRight());
        } else {
            return contains(data, node.getLeft());
        }
    }

    @Override
    public int size() {
        return size;
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
     * Finds the height of a node
     * @param node the intended node
     * @return the height of the node
     */
    private int height(AVLNode<T> node) {
        if (node == null) {
            return -1;
        }
        return (Math.max(height(node.getLeft()), height(node.getRight())) + 1);
    }

    @Override
    public int depth(T data) {
        return depth(data, 1, root);
    }
    
    /**
     * Recursive get helper method
     * @param node the starting node to search
     * @param data the searching value
     * @param depth cumulative depth
     * @return the value of the found node
     */
    private int depth(T data, int depth, AVLNode<T> node) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data cannot be null.");
        }
        if (node == null) {
            throw new java.util.NoSuchElementException(
                    "No such data found.");
        } else {
            if (node.getData().compareTo(data) > 0) {
                depth = depth + 1;
                return depth(data, depth, node.getLeft());
            } else if (node.getData().compareTo(data) < 0) {
                depth = depth + 1;
                return depth(data, depth, node.getRight());
            } else {
                return depth;
            }
        }
    }

    // DO NOT MODIFY OR USE CODE BEYOND THIS POINT

    @Override
    public AVLNode<T> getRoot() {
        return root;
    }

    @Override
    public void setRoot(AVLNode<T> node) {
        this.root = node; 
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

}
