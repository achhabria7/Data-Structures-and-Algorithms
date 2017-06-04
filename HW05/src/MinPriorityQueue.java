import java.util.NoSuchElementException;

/**
 * Your implementation of a min priority queue.
 * 
 * @author Anmol Chhabria
 * @version 1.0
 */
public class MinPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueueInterface<T> {

    private HeapInterface<T> backingHeap;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a priority queue.
     */
    public MinPriorityQueue() {

        backingHeap = new MinHeap<T>();

    }

    @Override
    public void enqueue(T item) throws IllegalArgumentException {

        checkData(item);
        backingHeap.add(item);
    }

    @Override
    public T dequeue() {

        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }

        return backingHeap.remove();
    }

    @Override
    public boolean isEmpty() {

        return (backingHeap.size() == 0);

    }

    @Override
    public int size() {

        return (backingHeap.size());
    }

    @Override
    public void clear() {

        backingHeap.clear();

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
    public HeapInterface<T> getBackingHeap() {
        // DO NOT CHANGE THIS METHOD!
        return backingHeap;
    }

}
