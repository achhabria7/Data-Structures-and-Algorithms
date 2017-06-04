/**
 * Your implementation of a DoublyLinkedList
 *
 * @author Anmol Chhabria
 * @version 1.0
 */
public class DoublyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void addAtIndex(int index, T data) throws IllegalArgumentException, 
        IndexOutOfBoundsException {

        checkIndex(index, size + 1);
        checkData(data);
        
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            LinkedListNode<T> current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }

            LinkedListNode<T> temp =
                    new LinkedListNode<T>(data, current, current.getNext());
            current.getNext().setPrevious(temp);
            current.setNext(temp);
            size++;
        }
    }

    @Override
    public void addToFront(T data) throws IllegalArgumentException {

        checkData(data);

        LinkedListNode<T> newest = new LinkedListNode<T>(data);

        if (isEmpty()) {
            head = newest;
            tail = newest;
        } else {
            newest.setNext(head);
            head.setPrevious(newest);
            head = newest;
        }

        size++;
    }

    @Override
    public void addToBack(T data) throws IllegalArgumentException {

        checkData(data);

        if (isEmpty()) {
            addToFront(data);
        } else {
            LinkedListNode<T> newest = new LinkedListNode<T>(data, tail, null);
            tail.setNext(newest);
            tail = newest;
            size++;
        }
    }

    @Override
    public T removeAtIndex(int index) throws IndexOutOfBoundsException {

        checkIndex(index, size);

        if (index == 0) {
            return removeFromFront();
        } else if (index == (size - 1)) {
            return removeFromBack();
        } else {
            //index = 0
            LinkedListNode<T> current = head;
            LinkedListNode<T> currentNext = head.getNext();

            //start at node after head
            for (int i = 1; i < index; i++) {
                current = current.getNext();
                currentNext = current.getNext();
            }

            T ret = currentNext.getData();

            current.setNext(currentNext.getNext());
            currentNext.getNext().setPrevious(current);
            size--;
            return ret;
        }
    }

    @Override
    public T removeFromFront() {

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
    public T removeFromBack() {

        if (isEmpty()) {
            return null;
        }

        T ret = tail.getData();

        if (size == 1) {
            head = null;
            tail = null;
            size--;
            return ret;

        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
            size--;

            return ret;
        }
    }

    @Override
    public boolean removeAllOccurrences(T data)
            throws IllegalArgumentException {

        checkData(data);
        boolean ret = false;

        if (!isEmpty()) {
            LinkedListNode<T> current = tail;

            for (int i = size - 1; i > -1; i--) {

                if (current.getData().equals(data)) {
                    removeAtIndex(i);
                    ret = true;
                }

                current = current.getPrevious();
            }
        }

        return ret;
    }

    @Override
    public T get(int index) {

        checkIndex(index, size);

        if (index == 0) {
            return head.getData();
        } else if (index == size - 1) {
            return tail.getData();
        } else {

            LinkedListNode<T> current = head;

            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }

            return current.getData();
        }

    }

    @Override
    public Object[] toArray() {

        Object[] array = new Object[size];

        LinkedListNode<T> current = head;

        for (int i = 0; i < size; i++) {
            array[i] = current.getData();
            current = current.getNext();
        }

        return array;
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

        head = null;
        tail = null;
        size = 0;

    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
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
