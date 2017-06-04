import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Anmol Chhabria
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
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
            throw new IllegalArgumentException("Data is null.");
        } else {
            for (T item: data) {
                if (item == null) {
                    throw new IllegalArgumentException("Data is null.");
                } else {
                    add(item);
                }
            }
        }

    }

    @Override
    public void add(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        } else if (size == 0) {
            root = new AVLNode<T>(data);
            size++;
        } else {
            root = addRepeat(root, data);
            size++;
        }

    }

    /**
     * Helper method to return -1 for
     * a leaf node or height for any other node
     *
     * @param avlNode Node for which you need height
     * @return int of avlNode height
     */


    private int nullHeight(AVLNode<T> avlNode) {

        if (avlNode == null) {
            return -1;
        }

        return avlNode.getHeight();
    }


    /**
     * Helper method for add
     * @param aNode node from the tree
     * @param data to be added in tree
     * @return the node with the new data
     */

    private AVLNode<T> addRepeat(AVLNode<T> aNode, T data) {
        if (aNode == null) {
            aNode = new AVLNode<T>(data);
        } else {

            if ((data.compareTo(aNode.getData()) < 0)) {
                aNode.setLeft(addRepeat(aNode.getLeft(), data));
                updateBH(aNode);
                if ((aNode.getBalanceFactor() > 1)) {
                    if (data.compareTo(aNode.getLeft().getData()) < 0) {
                        return rotateRight(aNode);
                    } else {
                        aNode.setLeft(rotateLeft(aNode.getLeft()));
                        return rotateRight(aNode);
                    }
                }
            } else if ((data.compareTo(aNode.getData()) > 0)) {
                aNode.setRight(addRepeat(aNode.getRight(), data));
                updateBH(aNode);
                if (aNode.getBalanceFactor() < -1) {
                    if (data.compareTo(aNode.getRight().getData()) > 0) {
                        return rotateLeft(aNode);
                    } else {
                        aNode.setRight(rotateRight(aNode.getRight()));
                        return rotateLeft(aNode);
                    }
                }
            } else {
                size--;
                return aNode;
            }
        }

        return aNode;
    }

    /**
     * Helper method to update height
     * and balance factors
     *
     * @param avlNode The node for which height and balance
     *                factor are being updated
     */


    private void updateBH(AVLNode<T> avlNode) {
        avlNode.setHeight(Math.max(nullHeight(avlNode.getLeft()),
                nullHeight(avlNode.getRight())) + 1);
        avlNode.setBalanceFactor(nullHeight(avlNode.getLeft())
                - nullHeight(avlNode.getRight()));
    }

    /**
     * Rotate right method.
     *
     * @param avlNode The node from the tree which is rotated.
     * @return The node that is in the old position of avlNode
     */


    private AVLNode<T> rotateRight(AVLNode<T> avlNode) {

        AVLNode<T> nodeL = avlNode.getLeft();
        if (nodeL.getRight() != null) {
            avlNode.setLeft(nodeL.getRight());
        } else {
            avlNode.setLeft(null);
        }
        nodeL.setRight(avlNode);
        updateBH(avlNode);
        updateBH(nodeL);
        return nodeL;
    }

    /**
     * Rotate Left method.
     *
     * @param avlNode The node from the tree which is rotated.
     * @return The node that is in the old position of avlNode
     */

    private AVLNode<T> rotateLeft(AVLNode<T> avlNode) {

        AVLNode<T> nodeR = avlNode.getRight();
        if (nodeR.getLeft() != null) {
            avlNode.setRight(nodeR.getLeft());
        } else {
            avlNode.setRight(null);
        }
        nodeR.setLeft(avlNode);
        updateBH(avlNode);
        updateBH(nodeR);
        return nodeR;
    }

    @Override
    public T remove(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        } else if (size == 0) {
            throw new NoSuchElementException("The tree is empty."
                    + "There is nothing to remove.");
        } else {
            AVLNode<T> ret = new AVLNode<T>(null);
            root = remove(root, data, ret);
            size--;
            return ret.getData();
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


    private AVLNode<T> getRightmost(AVLNode<T> aNode) {

        if (aNode == null) {
            return null;
        }

        if (aNode.getRight() == null) {
            return aNode;
        } else {
            return getRightmost(aNode.getRight());
        }
    }

    /**
     * Remove helper method.
     *
     * @param aNode The node from the tree which is passed in.
     * @param data The data which is being removed from the tree.
     * @param removed The original data returned by the method.
     * @throws NoSuchElementException when the data is not in the tree.
     * @return avlNode that is removed from the tree
     */

    private AVLNode<T> remove(AVLNode<T> aNode, T data, AVLNode<T> removed) {

        if (aNode == null) {
            throw new NoSuchElementException("Data was not found in the tree.");
        }

        AVLNode<T> leftNode = aNode.getLeft();
        AVLNode<T> rightNode = aNode.getRight();

        if (data.compareTo(aNode.getData()) == 0) {
            removed.setData(aNode.getData());
            if (leftNode == null && rightNode != null) {
                return rightNode;
            } else if (rightNode == null && leftNode != null) {
                return leftNode;
            } else if (rightNode == null && leftNode == null) {
                return null;
            } else {
                AVLNode<T> toRemove = new AVLNode<T>(null);
                AVLNode<T> newNode = getRightmost(leftNode);
                if (newNode != null) {
                    aNode.setData(newNode.getData());
                }
                if (leftNode != null) {
                    aNode.setLeft(remove(leftNode,
                            newNode.getData(), toRemove));
                    updateBH(aNode);
                }
            }

        } else if (data.compareTo(aNode.getData()) < 0) {
            aNode.setLeft(remove(leftNode, data, removed));
            updateBH(aNode);
        } else if (data.compareTo(aNode.getData()) > 0) {
            aNode.setRight(remove(rightNode, data, removed));
            updateBH(aNode);
        }

        if (aNode.getBalanceFactor() > 1
                && aNode.getLeft().getBalanceFactor() >= 0) {
            return rotateRight(aNode);
        }

        if (aNode.getBalanceFactor() > 1
                && aNode.getLeft().getBalanceFactor() < 0) {
            aNode.setLeft(rotateLeft(aNode.getLeft()));
            return rotateRight(aNode);
        }

        if (aNode.getBalanceFactor() < -1
                && aNode.getRight().getBalanceFactor() <= 0) {
            return rotateLeft(aNode);
        }

        if (aNode.getBalanceFactor() < -1
                && aNode.getRight().getBalanceFactor() > 0) {
            aNode.setRight(rotateRight(aNode.getRight()));
            return rotateLeft(aNode);
        }

        return aNode;
    }

    @Override
    public T get(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        } else {

            T ret = getRepeat(root, data);

            if (ret == null) {
                throw new NoSuchElementException("Data not found.");
            } else {
                return ret;
            }
        }
    }

    /**
     * Helper method for get using recursion
     * @param avlNode from the tree
     * @param data that needs to be found in tree
     * @return the rightmost node in the tree
     */

    private T getRepeat(AVLNode<T> avlNode, T data) {

        if (avlNode == null) {
            return null;
        }

        T nodeData = avlNode.getData();

        if (nodeData.equals(data)) {
            return nodeData;
        } else if (data.compareTo(nodeData) < 0 || nodeData == null) {
            return getRepeat(avlNode.getLeft(), data);
        } else if (data.compareTo(nodeData) > 0) {
            return getRepeat(avlNode.getRight(), data);
        }

        return null;
    }

    @Override
    public boolean contains(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Data is null.");

        } else {

            return (getRepeat(root, data) != null);
        }
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public List<T> preorder() {

        ArrayList<T> list = new ArrayList<T>();

        if (root == null) {
            return null;
        }

        preOrderRepeat(root, list);
        return list;

    }

    /**
     * Helper method for preorder traversal in the tree
     * @param nodeToAdd from the tree
     * @param listToAdd all the nodes to
     */

    private void preOrderRepeat(AVLNode<T> nodeToAdd, ArrayList<T> listToAdd) {

        if (nodeToAdd == null) {
            return;
        }

        listToAdd.add(nodeToAdd.getData());
        preOrderRepeat(nodeToAdd.getLeft(), listToAdd);
        preOrderRepeat(nodeToAdd.getRight(), listToAdd);
    }

    @Override
    public List<T> postorder() {

        if (root == null) {
            return null;
        }

        ArrayList<T> list = new ArrayList<T>();
        postOrderRepeat(root, list);
        return list;

    }

    /**
     * Helper method for postorder traversal in the tree
     * using recursion
     * @param node from the tree
     * @param listToAdd all the nodes to
     */

    private void postOrderRepeat(AVLNode<T> node, ArrayList<T> listToAdd) {

        if (node == null) {
            return;
        }

        postOrderRepeat(node.getLeft(), listToAdd);
        postOrderRepeat(node.getRight(), listToAdd);
        listToAdd.add(node.getData());

    }

    @Override
    public List<T> inorder() {

        if (root == null) {
            return null;
        }

        ArrayList<T> list = new ArrayList<T>();
        inOrderRepeat(root, list);
        return list;
    }

    /**
     * Helper method for inorder traversal in the tree
     * using recursion
     * @param node from the tree
     * @param listToAdd all the nodes to
     */

    private void inOrderRepeat(AVLNode<T> node, ArrayList<T> listToAdd) {

        if (node == null) {
            return;
        }

        inOrderRepeat(node.getLeft(), listToAdd);
        listToAdd.add(node.getData());
        inOrderRepeat(node.getRight(), listToAdd);
    }

    @Override
    public List<T> levelorder() {

        List<T> retList = new ArrayList<T>();
        Queue<AVLNode<T>> levelOrderQueue = new LinkedList<AVLNode<T>>();

        if (root == null) {
            return retList;
        }

        levelOrderQueue.add(root);
        retList.add(root.getData());

        while (!levelOrderQueue.isEmpty()) {
            AVLNode<T> temp = levelOrderQueue.poll();

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
            return heightOfTree(root);
        }

    }

    /**
     * Helper method for height of tree that increases the left
     * count and the right count
     * @param node from the tree
     * @return height of the tree
     */

    private int heightOfTree(AVLNode<T> node) {

        if (node == null) {
            return -1;
        }

        int left = heightOfTree(node.getLeft());
        int right = heightOfTree(node.getRight());

        if (left > right) {
            return left + 1;
        } else {
            return right + 1;
        }
    }
    
    @Override
    public AVLNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}
