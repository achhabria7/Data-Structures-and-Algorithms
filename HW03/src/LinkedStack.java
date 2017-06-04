/**
 * Your implementation of a linked stack.
 *
 * @author YOUR NAME HERE
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public boolean isEmpty() {

        return (size == 0);
    }

    @Override
    public T pop() {

        if (isEmpty()) {
            return null;
        }

        T ret = head.getData();

        if (size == 1) {
            head = null;
            size--;

        } else {
            head = head.getNext();
            size--;
        }

        return ret;

    }

    @Override
    public void push(T data) {

        checkData(data);

        LinkedListNode<T> newest = new LinkedListNode<T>(data);

        if (isEmpty()) {
            head = newest;
        } else {
            newest.setNext(head);
            head = newest;
        }

        size++;

    }

    @Override
    public int size() {

        return size;

    }

    /**
     * Returns the head of this stack.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}
