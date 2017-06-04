package src;

/**
 * Your implementation of an ArrayList.
 *
 * @author Anmol Chhabria
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[ArrayListInterface.INITIAL_CAPACITY];
    }

    @Override
    public void addAtIndex(int index, T data) throws IndexOutOfBoundsException,
        IllegalArgumentException {

        checkData(data);
        checkIndex(index, size + 1);

        if (size == backingArray.length) {
            resize(2 * backingArray.length);
        }

        for (int i = size - 1; i >= index; i--) {
            backingArray[i + 1] = backingArray[i];
        }

        backingArray[index] = data;
        size++;


    }

    @Override
    public void addToFront(T data) throws IllegalArgumentException {

        checkData(data);
        if (size == backingArray.length) {
            resize(2 * backingArray.length);
        }

        for (int i = size - 1; i >= 0; i--) {
            backingArray[i + 1] = backingArray[i];
        }

        backingArray[0] = data;
        size++;
    }

    @Override
    public void addToBack(T data) throws IllegalArgumentException {

        checkData(data);

        if (size == backingArray.length) {
            resize(2 * backingArray.length);
        }

        backingArray[size++] = data;
    }

    @Override
    public T removeAtIndex(int index) throws IndexOutOfBoundsException {

        checkIndex(index, size);

        T ret = backingArray[index];
        backingArray[index] = null;

        for (int i = index; i < size; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        backingArray[size - 1] = null;
        size--;
        return ret;

    }

    @Override
    public T removeFromFront() {

        if (isEmpty()) {
            return null;
        }

        T ret = backingArray[0];

        for (int i = 0; i < size; i++) {
            backingArray[i] = backingArray[i + 1];
        }

        backingArray[size - 1] = null;
        size--;
        return ret;
    }

    @Override
    public T removeFromBack() {

        if (isEmpty()) {
            return null;

        } else {

            T ret = backingArray[size - 1];
            backingArray[size - 1] = null;
            size--;
            return ret;
        }
    }

    @Override
    public T get(int index) {

        checkIndex(index, size);
        return backingArray[index];

    }

    @Override
    public boolean isEmpty() {

        return (size == 0);

    }

    @Override
    public int size() {

        return size;

    }

    @Override
    public void clear() {

        for (int i = 0; i < size; i++) {
            backingArray[i] = null;
        }

        size = 0;

    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }

    /**
     * Checks index bounds.
     *
     * @param index The index where you want the new element.
     * @param size The size of the array.
     * @throws java.lang.IndexOutOfBoundsException if index is negative
     * or index > size.
     * @throws java.lang.IllegalArgumentException if data is null.
     */

    protected void checkIndex(int index, int size)
            throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
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

    /**
     * Resizes the array to to cap size.
     *
     * @param cap The capacity you want to resize the array to.
     */

    protected void resize(int cap) {
        T[] temp = (T[]) new Object[cap];
        for (int i = 0; i < size; i++) {
            temp[i] = backingArray[i];
        }
        backingArray = temp;
    }
}
