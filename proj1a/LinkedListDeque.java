public class LinkedListDeque <T> {
    /* Deque (usually pronounced like “deck”) is an irregular acronym of
     * double-ended queue. Double-ended queues are sequence containers with
     * dynamic sizes that can be expanded or contracted on both ends
     * (either its front or its back). */
    private int size;
    private LLNode sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new LLNode();
    }

    private class LLNode <T> {
        private LLNode prev;
        private T item;
        private LLNode next;

        private <T> LLNode()  {
            prev = null;
            item = null;
            next = null;
        }

        private LLNode(LLNode pre, T ite, LLNode nex) {
            prev = prev;
            item = ite;
            next = nex;
        }
    }


    /* circular sentinel: next point to the first node. first and last points to each other */

    /* Adds an item of type T to the front of the deque. */
    public void addFirst (T item) {
        LLNode newNode = new LLNode();
        newNode.item = item;
        if (isEmpty()) {
            sentinel.next = newNode;
            newNode.prev = newNode;
            newNode.next = newNode;
        }
        else {
            newNode.next = sentinel.next;
            newNode.prev = sentinel.next.prev;
            sentinel.next.prev = newNode;
            sentinel.next = newNode;
        }
        size++;
    }

    /* Adds an item of type T to the back of the deque. */
    public void addLast (T item ) {
        LLNode newNode = new LLNode();
        newNode.item = item;
        if (isEmpty()) {
            sentinel.next = newNode;
            newNode.prev = newNode;
            newNode.next = newNode;
        }
        else {
            newNode.prev = sentinel.next.prev;
            newNode.next = sentinel.next;
            sentinel.next.prev.next = newNode;
            sentinel.next.prev = newNode;
        }
        size++;
    }

    /* Return True if the list is empty */
    public boolean isEmpty() {
        return (size == 0);
    }

    /* return size (int) of the list */
    public int size() {
        return size; }

    /* Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        LLNode cursor = sentinel.next;
        if (isEmpty()) {System.out.println("Empty list.");}
        else {
            for (int i = 0; i < size; i++) {
                System.out.print (cursor.item + " ");
                cursor = cursor.next;
            }
        }
        System.out.print("\n");
    }


    /* Removes and returns the item at the front of the deque.
    If no such item exists, returns null. */
    public T removeFirst() {
        LLNode cursor = new LLNode();
        cursor = sentinel.next;
        if (isEmpty()) {
            return null; }
        else if (sentinel.next == cursor.next) {
            sentinel.next = null;
        }
        else {
            sentinel.next = cursor.next;
            cursor.next.prev = cursor.prev;
            cursor.prev.next = cursor.next;
        }
        size--;
        return (T) cursor.item;
    }

    public T removeLast() {
        LLNode cursor = new LLNode();
        cursor = sentinel.next;  //cursor points to the first node
        if (isEmpty()) {
            return null; }
        else if (sentinel.next == cursor.next) {  //Case of Only one node
            sentinel.next = null;
        }
        else {
            cursor = cursor.prev;  //cursor points to the last node
            sentinel.next.prev = cursor.prev;
            cursor.prev.next = sentinel.next;
            //Lesson learned: assigned cursor (dynamic, local) to this node,
            // So I lost this cur.prev.next pointer with this statement.
        }
        size--;
        return (T) cursor.item;
    }


    /* Gets the item at the given index, where 0 is the front,
    1 is the next item, and so forth. If no such item exists,
    returns null. Must not alter the deque! */
    //TODO: did not pass the test. Need fix.
    public T get(int index) {
        LLNode cursor = sentinel.next;
        if (isEmpty()) {
            return null; }
        else if (sentinel.next == cursor.next) {
            if (index == 0) {
                return (T) cursor.item; }
            else {
                return null; }
        }
        else {
            int i = 0;
            while (sentinel.next != cursor.next)  {
                if (i == index) {
                    return (T) cursor.item; }
                else { cursor = cursor.next; i++;}
            }
            return null;
        }
    }

    /* Same as get, but uses recursion. */
    public T getRecursive (int index) {

        LLNode cursor = sentinel.next;  //first node
        if (isEmpty()) {
            return null; }
        LLNode foundNode = findNodeRecursive(index, cursor);
        if (foundNode == null) {
            return null; }
        else {
            return (T)foundNode.item; }
    }

    private LLNode findNodeRecursive(int index, LLNode n) {
        if (index == 0) {
            return n; }
        else if (index <0) {
            return null; }
        else {return findNodeRecursive(index - 1, n.next);}
    }

}
