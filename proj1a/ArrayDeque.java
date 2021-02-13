public class ArrayDeque <T> {
    /* Deque (usually pronounced like “deck”) is an irregular acronym of
    * double-ended queue. Double-ended queues are sequence containers with
    * dynamic sizes that can be expanded or contracted on both ends
    * (either its front or its back). */
    private int size;
    private T[] items;
    private int sentinel;  //Points to the first item index
    private int RFACTOR = 2;
    private T AVAILABLE;

    /* Create an empty list */
    public ArrayDeque() {
        size = 0;   //size: number of items in the array. Not the last item position, sentinel+size-1 instead.
        items = (T []) new Object[8];  //Initial array length = 8
        //sentinel = 0; //Cannot assign 0. might cause problem with edge case
    }


    //Constant time unless resize
    /** Add item (type T) to the first of the array. */
    public void addFirst(T item) {
        if (isEmpty()) {items[0] = item; sentinel = 0;}  //Empty array
        else {
            if (size >= items.length - 1) { resize(size*RFACTOR); } //Resize if array is full
            items[loopindex(sentinel - 1)] = item;
                sentinel = loopindex(sentinel - 1);
            }
        size++;
    }

    /** Add item (type T) to the last of the array. */
    public void addLast(T item) {
        if (isEmpty()) {items[0] = item; sentinel = 0;}  //Empty array
        else {
            if (size >= items.length - 1) { resize(size*RFACTOR); }  //Resize if array is full
            items[loopindex(sentinel + size)] = item;
        }
        size++;
    }

    /** Add item (type T) to the first of the array.
     * @return the item, or null if not available. */
    public T removeFirst(){
        if (isEmpty()) { return null; }  //Empty array
        else {
            T poppedItem = items[sentinel];
            items[sentinel] = AVAILABLE;
            sentinel = loopindex(sentinel +1);
            size--;
            return poppedItem;
        }
    }

    public T removeLast() {
        if (isEmpty()) { return null; }  //Empty array
        else {
            T poppedItem = items[loopindex(sentinel+size-1)]; //sentinel+size-1 is the location of the last item
            items[loopindex(sentinel+size-1)] = AVAILABLE;
            size--;
            return poppedItem;
        }
    }

    //Constant time
    /** Return the item at location index. Return null if index is not valid. */
    public T get(int index){
        if (!inrange((index))) {
            System.out.println("Index Out of bound.");
            return null;
        }
        else {
            return items[loopindex(sentinel+index)];
        }
    }


    public T getLast() {
        if (size ==0) { return null;}
        else return items[loopindex(sentinel+size-1)];
    }

    /** return the number of the items in the array*/
    public int size() { return size; }

    /** Check if the array is empty. Return true if it is Empty. */
    public boolean isEmpty() {
        if (size ==0) { return true; }
        else { return false; }
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        if (isEmpty()) {System.out.println("Empty list.");}
        else {
            //int printSeq = sentinel;
            for (int i=sentinel; i<size+sentinel; i++) {
                System.out.print (items[loopindex(i)] + " ");
            }
        }
        System.out.print("\n");
    }

    /** Resize the matrix , takes the new matrix size, return the new matrix */
    private void resize(int newSize)  {
        T[] a = (T []) new Object[newSize];
        System.arraycopy(items, 0, a, 0, size);  //This is only operated when the array is full
        items = a;
    }


    /** Helper: return the index loop when it overflows from either side */
    private int loopindex(int number) {
        if (number < 0) {
            while (!inrange(number)) {
                number = number +items.length;
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
        if (number >= 0 && number < items.length) {return true;}
        else {return false;}
    }
}
