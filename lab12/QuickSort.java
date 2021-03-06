import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import org.junit.Test;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item: q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /** Returns a random item from the given queue. */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted  A Queue of unsorted items
     * @param pivot     The item to pivot on
     * @param less      An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are less than the given pivot.
     * @param equal     An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are equal to the given pivot.
     * @param greater   An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        // Your code here!
        while(!unsorted.isEmpty()) {
            Item i1 = unsorted.dequeue();
            if (i1.compareTo(pivot) < 0) {
                less.enqueue(i1);
            } else if (i1.compareTo(pivot) > 0) {
                greater.enqueue(i1);
            } else { equal.enqueue(i1); }
        }
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        // Your code here!
        //take items, choose pivot, partition()
        if (items == null) { throw new NullPointerException(); }
        if(items.size() <= 1) { return items; }


        Item pivot = getRandomItem(items);
        Queue<Item> less = new Queue<Item>();
        Queue<Item> equal = new Queue<Item>();
        Queue<Item> greater = new Queue<Item>();

        partition(items, pivot, less, equal, greater);

        less = quickSort(less);
        greater = quickSort(greater);

        return catenate(catenate(less, equal), greater);
    }

    @Test
    public void test() {

        //Testing partition
        Queue<String> students = new Queue<String>();
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
        students.enqueue("Clif");
        students.enqueue("Demi");
        System.out.println("Before partition: " + students);
        /*
        String randItem = getRandomItem(students);
        Queue<String> less = new Queue<String>();
        Queue<String> equal = new Queue<String>();
        Queue<String> greater = new Queue<String>();
        partition(students, randItem, less, equal, greater);
        System.out.println("After partition, less is : " + less);
        System.out.println("After partition, pivot is : " + randItem);
        System.out.println("After partition, greater is : " + greater);
        */ //Above partition test is working
        students = quickSort(students);
        System.out.println("After partition: " + students);
    }
}
