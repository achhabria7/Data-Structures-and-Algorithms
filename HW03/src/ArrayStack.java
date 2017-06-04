import Java.lang.Math;

/**
 * Your implementation of an array-backed stack.
 *
 * @author ANMOL CHHABRIA
 * @version 1.0
 */
public class ArrayStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayStack.
     */
    public ArrayStack() {

        backingArray[StackInterface.INITIAL_CAPACITY];

    }

    @Override
    public boolean isEmpty() {

        return (size == 0);

    }

    /**
     * Pop from the stack.
     *
     * Do not shrink the backing array.
     *
     * @see StackInterface#pop()
     */
    @Override
    public T pop() throws NoSuchElementException {

        if (isEmpty()) {
            throw new NoSuchElementException("The stack is empty");
        }

        T ret = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return ret;

    }

    /**
     * Push the given data onto the stack.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to 1.5 times the current backing array length, rounding down
     * if necessary.
     *
     * @see StackInterface#push()
     */
    @Override
    public void push(T data) throws IllegalArgumentException {

        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        }

        if (size == backingArray.length) {
            resize(Math.floor (size * 1.5));
        }

        backingArray[++size] = data;

    }

    @Override
    public int size() {

    }

    /**
     * Returns the backing array of this stack.
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
