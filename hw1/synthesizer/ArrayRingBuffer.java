package synthesizer;
import java.util.Iterator;


public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    //private int fillCount;  //will cause error.
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (fillCount >= capacity) {
            throw new RuntimeException("Ring Buffer overflow");
        } else {
            rb[last] = x;
        }
        fillCount++;
        last++;
        last = indexLoop(last);
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        } else {
            T toThrow = rb[first];
            rb[first] = null;
            first++;
            first = indexLoop(first);
            fillCount--;
            return toThrow;
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        } else {
            return rb[first];
        }
    }

    private int indexLoop(int n) {
        if (n < 0) {
            while (!inRange(n)) {
                n += capacity();
            }
        } else if (n >= capacity()) {
            while (!inRange(n)) {
                n -= capacity();
            }
        }
        return n;
    }

    private boolean inRange(int n) {
        return (n >= 0 && n < capacity());
    }

    @Override
    public Iterator<T> iterator() {
        return new KeyIterator();
    }

    private class KeyIterator<T> implements Iterator<T> {
        private int ptr;
        KeyIterator() {
            ptr = first;
        }
        public boolean hasNext() {
            return (indexLoop(ptr) != last);
        }
        public T next() {
            T returnItem = (T) rb[indexLoop(ptr)];
            ptr++;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if ((ArrayRingBuffer) o == o) {
            Iterator thisArray = this.iterator();
            Iterator oArray = ((ArrayRingBuffer) o).iterator();
            while (thisArray.hasNext() && oArray.hasNext()) {
                if (!thisArray.next().equals(oArray.next())) {
                    return false;
                }
            }
            return (!thisArray.hasNext() && !oArray.hasNext());
        } else {
            return false;
        }
    }
}
