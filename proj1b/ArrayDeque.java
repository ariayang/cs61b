public class ArrayDeque<T> implements Deque<T> {
    /* Deque (usually pronounced like “deck”) is an irregular acronym of
    * * double-ended queue. Double-ended queues are sequence containers with
    * dynamic sizes that can be expanded or contracted on both ends
    * (either its front or its back). */
    private int size;
    private T[] items;
    private int sentinel;  //Points to the first item index
    private int RFACTOR = 2;
    private T AVAILABLE = null;

    /* Create an empty list */
    public ArrayDeque() {
        size = 0;   //size: number of items in the array. last item at sentinel+size-1
        items = (T []) new Object[8];  //Initial array length = 8
        //sentinel = 0; //Cannot assign 0. might cause problem with edge case
    }


    //Constant time unless resize
    /** Add item (type T) to the first of the array. */
    @Override
    public void addFirst(T item) {
        if (isEmpty()) { //Empty array
            items[0] = item;
            sentinel = 0;
        } else {
            if (size == items.length) {
                resize(size * RFACTOR);
            } //Resize if array is full
            items[loopindex(sentinel - 1)] = item;
            sentinel = loopindex(sentinel - 1);
        }
        size++;
    }

    /** Add item (type T) to the last. */
    @Override
    public void addLast(T item) {
        if (isEmpty()) {  //Empty
            items[0] = item;
            sentinel = 0;
        } else {
            if (size == items.length) {
                resize(size * RFACTOR);
            }  //Resize if array is full
            items[loopindex(sentinel + size)] = item;
        }
        size++;
    }


    /** Add item (type T) to the first of the array.
     * @return the item, or null if not available. */
    @Override
    public T removeFirst() {
        if (isEmpty()) { //Empty array
            return null;
        } else {
            T poppedItem = items[sentinel];
            items[sentinel] = AVAILABLE;
            if (size > 1) {
                sentinel = loopindex(sentinel + 1);
            } else {  //Empty list after
                sentinel = 0;
            }
            size--;
            if (ifsizeDown()) {
                sizeDown();
            }
            return poppedItem;
        }
    }

    //Ensure resizing doesn't cause nulls. (0.0/1.176)
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null; //Empty array
        } else {
            T poppedItem = items[loopindex(sentinel + size - 1)]; //last item
            items[loopindex(sentinel + size - 1)] = AVAILABLE;
            if (size == 1) {
                sentinel = 0;  //Empty list after
            }
            size--;
            if (ifsizeDown()) {
                sizeDown();
            }
            return poppedItem;
        }
    }

    //Constant time
    /** Return the item at location index.
     * Return null if index is not valid. */
    @Override
    public T get(int index) {
        if (!inrange((index))) {
            System.out.println("Index Out of bound.");
            return null;
        } else {
            return items[loopindex(sentinel + index)];
        }
    }


    private T getLast() {
        if (size == 0) {
            return null;
        } else {
            return items[loopindex(sentinel + size - 1)];
        }
    }

    /** return the number of the items in the array*/
    @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    @Override
    public void printDeque() {
        if (isEmpty()) {
            System.out.println("Empty list.");
        } else {
            for (int i = sentinel; i < size + sentinel; i++) {
                System.out.print(items[loopindex(i)] + " ");
            }
        }
        System.out.print("\n");
    }

    /** Resize the matrix , takes the new matrix size, return the new matrix */
    private void resize(int newSize)  {
        T[] a = (T []) new Object[newSize];
        System.arraycopy(items, sentinel, a, 0, size - sentinel);  //when array is full
        System.arraycopy(items, 0, a, size - sentinel, sentinel);
        items = a;
        sentinel = 0;
    }


    /** Helper: return the index loop when it overflows from either side */
    private int loopindex(int number) {
        if (number < 0) {
            while (!inrange(number)) {
                number = number + items.length;
            }
        }
        if (number >= items.length) {
            while (!inrange(number)) {
                number = number - items.length;
            } }
        return number;
    }

    /** Helper: check if an input number is a valid index number */
    private boolean inrange(int number) {
        return (number >= 0 && number < items.length);
    }

    /** size down function to half
     * change the array with size reduction */
    private void sizeDown() {
        int arrayL = items.length;
        T[] a = (T []) new Object[arrayL / 2];
        if ((size + sentinel) >= arrayL) { //2 copies
            System.arraycopy(items, sentinel, a, 0, arrayL - sentinel);
            System.arraycopy(items, 0, a, arrayL - sentinel, size - arrayL + sentinel);
        } else {  //1 copies
            System.arraycopy(items, sentinel, a, 0, size);
        }
        items = a;
        sentinel = 0;
    }

    /** Helper: decide if size down needed < 25% */
    private boolean ifsizeDown() {
        double ratio = (double) size / items.length;
        return !(ratio > 0.25 || items.length <= 16);
    }
}
