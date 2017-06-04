/**
 * Your implementation of a linked queue.
 *
 * @author YOUR NAME HERE
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {

        if (isEmpty()) {
            return null;
        }

        T ret = head.getData();

        if (size == 1) {
            head = null;
            tail = null;
            size--;

        } else {
            head = head.getNext();
            head.setPrevious(null);
            size--;

        }

        return ret;

    }

    @Override
    public void enqueue(T data) throws IllegalArgumentException{

        checkData(data);

        if (isEmpty()) {
            LinkedNode<T> newest = new LinkedNode<T>(data);

            if (isEmpty()) {
                head = newest;
                tail = newest;
            } else {
                newest.setNext(head);
                head = newest;
            }

            size++;
        } else {
            LinkedNode<T> newest = new LinkedNode<T>(data, tail, null);
            tail.setNext(newest);
            tail = newest;
            size++;
        }


    }

    @Override
    public boolean isEmpty() {

        return (size == 0);

    }

    @Override
    public int size() {

        return size;

    }

    /**
     * Returns the head of this queue.
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

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }

    /**
     * Checks index bounds.
     *
     * @param index The index where you want the new element.
     * @param size The size of the array.
     * @throws java.lang.IndexOutOfBoundsException if index is negative
     * or index >= size.
     * @throws java.lang.IllegalArgumentException if data is null.
     */

    protected void checkIndex(int index, int size)
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

    protected void checkData(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        }
    }

}
