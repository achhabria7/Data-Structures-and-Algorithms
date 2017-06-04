import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Your implementation of a binary search tree.
 *
 * @author Anmol Chhabria
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
    public BST(Collection<T> data) throws IllegalArgumentException {

        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        } else {
            for (T item : data) {
                this.add(item);
            }
        }
    }

    @Override
    public void add(T data) throws IllegalArgumentException {
        checkData(data);
        root = add(root, data);
    }

    /**
     * Add helper method.
     *
     * @param aNode The node that is passed in to the tree.
     * @param data The data which needs to be added to the tree.
     * @return Node that is added to the tree
     */

    private BSTNode<T> add(BSTNode<T> aNode, T data) {

        if (aNode == null) {
            aNode = new BSTNode<T>(data);
            size++;
        } else {

            if ((data.compareTo((T) aNode.getData()) < 0)) {
                aNode.setLeft(add(aNode.getLeft(), data));
            } else if ((data.compareTo((T) aNode.getData()) > 0)) {
                aNode.setRight(add(aNode.getRight(), data));
            } else {
                return aNode;
            }
        }

        return aNode;
    }

    @Override
    public T remove(T data) {

        checkData(data);

        BSTNode<T> ret = remove(root, data);

        if (ret == null) {
            throw new NoSuchElementException("Data is not found.");
        } else {
            root = remove(root, data);
            size--;
            return data;
        }
    }

    /**
     * Helper method to get predecessor data for a node with 2 children when
     * said node is being removed from the tree.
     *
     * @param aNode The node for which you
     *              need the predecessor (rightmost node).
     * @return T data of the rightmost node to aNode.
     */


    private T getRightmost(BSTNode<T> aNode) {

        if (aNode == null) {
            return null;
        }

        if (aNode.getRight() == null) {
            return aNode.getData();
        } else {
            return getRightmost(aNode.getRight());
        }
    }

    /**
     * Remove helper method.
     *
     * @param aNode The node from the tree which is passed in.
     * @param data The data which is being removed from the tree.
     * @throws NoSuchElementException when the data is not in the tree.
     * @return BSTNode that is removed from the tree
     */

    private BSTNode<T> remove(BSTNode<T> aNode, T data) {

        if (aNode == null) {
            return null;
        }

        BSTNode<T> leftNode = aNode.getLeft();
        BSTNode<T> rightNode = aNode.getRight();

        if (data.compareTo(aNode.getData()) == 0) {

            if (leftNode == null) {
                return rightNode;
            } else if (rightNode == null) {
                return leftNode;
            } else {
                T newNode = getRightmost(leftNode);
                aNode.setData(newNode);
                aNode.setLeft(remove(leftNode, newNode));
            }

        } else if(data.compareTo(aNode.getData()) < 0) {
            aNode.setLeft(remove(leftNode, data));
        } else if (data.compareTo(aNode.getData()) > 0) {
            aNode.setRight(remove(rightNode, data));
        }

        return aNode;
    }

    @Override
    public T get(T data) throws
            IllegalArgumentException, NoSuchElementException {

        checkData(data);

        T ret = get(root, data);

        if (ret == null) {
            throw new NoSuchElementException("Data not found.");
        } else {
            return ret;
        }
    }

    /**
     * Get method helper.
     *
     * @param aNode The node from the tree which is passed in.
     * @param data The data that is being searched for in the tree.
     * @return T data that is being looked for by get.
     */

    public T get(BSTNode<T> aNode, T data) {

        if (aNode == null) {
            return null;
        }

        T nodeData = aNode.getData();

        if (nodeData.equals(data)) {
            return nodeData;
        } else if (data.compareTo(nodeData) < 0 || nodeData == null) {
            return get(aNode.getLeft(), data);
        } else if (data.compareTo(nodeData) > 0) {
            return get(aNode.getRight(), data);
        }

        return null;
    }

    @Override
    public boolean contains(T data) throws IllegalArgumentException {

        checkData(data);

        return (get(root, data) != null);

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {

        ArrayList<T> list = new ArrayList<T>();

        preorder(root, list);
        return list;

    }

    /**
     * Preorder traversal helper method.
     *
     * @param newNode The node for which the traversal must be completed.
     * @param aList The list to which all nodes are being added to.
     */

    private void preorder(BSTNode<T> newNode, ArrayList<T> aList) {

        if (newNode == null) {
            return;
        }

        aList.add(newNode.getData());
        preorder(newNode.getLeft(), aList);
        preorder(newNode.getRight(), aList);
    }

    @Override
    public List<T> postorder() {

        ArrayList<T> list = new ArrayList<T>();

        postorder(root, list);
        return list;
    }

    /**
     * Postorder traversal helper method.
     *
     * @param newNode The node for which the traversal must be completed.
     * @param aList The list to which all nodes are being added to.
     */

    private void postorder(BSTNode<T> newNode, ArrayList<T> aList) {

        if (newNode == null) {
            return;
        }

        postorder(newNode.getLeft(), aList);
        postorder(newNode.getRight(), aList);
        aList.add(newNode.getData());
    }

    @Override
    public List<T> inorder() {

        ArrayList<T> list = new ArrayList<T>();

        inorder(root, list);
        return list;

    }

    /**
     * Inorder traversal helper method.
     *
     * @param newNode The node for which the traversal must be completed.
     * @param aList the list to which all nodes are being added.
     */

    private void inorder(BSTNode<T> newNode, ArrayList<T> aList) {

        if (newNode == null) {
            return;
        }

        inorder(newNode.getLeft(), aList);
        aList.add(newNode.getData());
        inorder(newNode.getRight(), aList);

    }

    @Override
    public List<T> levelorder() {

        List<T> retList = new ArrayList<T>();
        Queue<BSTNode<T>> levelOrderQueue = new LinkedList<BSTNode<T>>();

        if (root == null) {
            return retList;
        }

        levelOrderQueue.add(root);
        retList.add(root.getData());

        while (!levelOrderQueue.isEmpty()) {
            BSTNode<T> temp = levelOrderQueue.poll();

            if (temp.getLeft() != null) {
                levelOrderQueue.add(temp.getLeft());
                retList.add((T) temp.getLeft().getData());
            }

            if (temp.getRight() != null) {
                levelOrderQueue.add(temp.getRight());
                retList.add((T) temp.getRight().getData());
            }
        }

        return retList;

    }

    @Override
    public void clear() {

        root = null;
        size = 0;
    }

    @Override
    public int height() {

        if (size == 0) {
            return -1;
        } else {
            return height(root);
        }
    }

    /**
     * Height helper method.
     *
     * @param aNode The node for which height is being calculated.
     * @return int of height for aNode
     */


    private int height(BSTNode<T> aNode) {

        if (aNode == null) {
            return -1;
        }

        int left = height(aNode.getLeft());
        int right = height(aNode.getRight());

        if (left > right) {
            return left + 1;
        } else {
            return right + 1;
        }
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }

    /**
     * Checks index bounds.
     *
     * @param index The index where you want the new element.
     * @param size The size of the array.
     * @throws java.lang.IndexOutOfBoundsException if index is negative
     */

    private void checkIndex(int index, int size)
            throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index "
                    + "is negative or greater than size.");
        }
    }

    /**
     * Checks if data is null.
     *
     * @param data The data you want to insert into the array.
     * @throws java.lang.IllegalArgumentException if data is null.
     */

    private void checkData(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        }
    }

}
