import java.util.NoSuchElementException;

/**
 * Your implementation of a min heap.
 *
 * @author Anmol Chhabria
 * @version 1.0
 */
public class MinHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial size of {@code STARTING_SIZE} for the
     * backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MinHeap() {

        backingArray = (T[]) new Comparable[HeapInterface.STARTING_SIZE];
        size = 0;

    }

    @Override
    public void add(T item) throws IllegalArgumentException {

        checkData(item);

        if (size >= backingArray.length - 1) {
            resize((int) Math.floor(backingArray.length * 1.5));
        }

        backingArray[++size] = item;
        upheap(size);
    }

    @Override
    public T remove() {

        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }

        T ret = backingArray[1];
        swap(1, size);
        backingArray[size] = null;
        size--;
        downheap(1);
        return ret;

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

        if (!isEmpty()) {
            T[] temp = (T[]) new Comparable[HeapInterface.STARTING_SIZE];
            backingArray = temp;
            size = 0;
        }
    }

    /**
     * Returns the index of the parent
     * array[j] where the heap starts at array[1]
     *
     * @param j The index of element
     * @return index of the parent
     */
    private int parent(int j) {

        return j / 2;
    }

    /**
     * Returns the index of the left child of
     * array[j] where the heap starts at array[1]
     *
     * @param j The index of element
     * @return index of the left child
     */
    private int left(int j) {
        return (2 * j);
    }

    /**
     * Returns the index of the right child of
     * array[j] where the heap starts at array [1]
     *
     * @param j The index of element
     * @return index of the right child
     */

    private int right(int j) {
        return (2 * j) + 1;
    }

    /**
     * Indicates if the element at index j has a left
     * child in the heap
     *
     * @param j The index of element to check if there is left child
     * @return boolean if array[j] has left child
     */

    private boolean hasLeft(int j) {
        return (left(j) <= size);
    }

    /**
     * Indicates if the element at index j has a right
     * child in the heap
     *
     * @param j The index of element to check if there is right child
     * @return boolean if array[j] has right child
     */

    private boolean hasRight(int j) {
        return (right(j) <= size);
    }

    /**
     * Switches the element at index i with the element
     * at index j in the array
     *
     * @param i The index of element that needs to be switched
     * @param j The index of element that needs to be switched
     */

    private void swap(int i, int j) {
        T temp = backingArray[i];
        backingArray[i] = backingArray[j];
        backingArray[j] = temp;
    }

    /**
     * Moves the element at index j in the array
     * up the heap to maintain the heap properties
     *
     * @param j The index of the array element you need to upheap
     */
    private void upheap(int j) {
        while (j > 1) {
            int p = parent(j);

            if (backingArray[j].compareTo(backingArray[p]) >= 0) {
                return;
            }

            swap(j, p);
            j = p;
        }
    }

    /**
     * Moves the element at index j in the array
     * down the heap to maintain the heap properties
     *
     * @param j The index of the array element you need to downheap
     */

    private void downheap(int j) {
        while (hasLeft(j)) {
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;
            if (hasRight(j)) {
                int rightIndex = right(j);
                if (backingArray[leftIndex].compareTo(
                        backingArray[rightIndex]) > 0) {
                    smallChildIndex = rightIndex;
                }
            }

            if (backingArray[smallChildIndex].compareTo(backingArray[j]) > 0) {
                return;
            }

            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }

    /**
     * Resizes the array to to cap size.
     *
     * @param cap The capacity you want to resize the queue to.
     */

    private void resize(int cap) {
        T[] temp = (T[]) new Comparable[cap];
        for (int i = 0; i < size + 1; i++) {
            temp[i] = backingArray[i];
        }
        backingArray = temp;
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

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT CHANGE THIS METHOD!
        return backingArray;
    }

}
