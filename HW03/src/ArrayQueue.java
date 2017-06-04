import Java.lang.Math;

/**
 * Your implementation of an array-backed queue.
 *
 * @author Anmol Chhabria
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int front;
    private int back;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {

        backingArray [QueueInterface.INITIAL_CAPACITY];

    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you <b>must not</b>
     * explicitly reset front or back to 0.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() throws NoSuchElementException {

        if (isEmpty()) {
            throw new NoSuchElementException ("Queue is empty.");
        }

        T ret = backingArray[front];
        backingArray[front] = null;
        front = (front + 1) % backingArray.length;
        size--;
        return ret;

    }

    /**
     * Add the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to 1.5 times the current backing array length, rounding down
     * if necessary. If a regrow is necessary, you should copy elements to the
     * front of the new array and reset front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    @Override
    public void enqueue(T data) {

        if (size == backingArray.length) {
            resize(Math.floor(1.5 * size));
        }

        int avail = (front + size) % data.length;
        back = avail;
        data[avail] = data;
        size++;

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
     * Returns the backing array of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY!
        return backingArray;
    }

    /**
     * Checks index bounds.
     *
     * @param index The index where you want the new element.
     * @param size The size of the array.
     * @throws java.lang.IndexOutOfBoundsException if index is negative
     * or index >= size.
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

    protected void resize(int cap) {
        T[] temp = (T[]) new Object[cap];
        for (int i = 0; i < size; i++) {
            temp[i] = backingArray[i];
        }
        backingArray = temp;
    }
}
