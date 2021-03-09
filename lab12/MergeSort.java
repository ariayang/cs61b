import edu.princeton.cs.algs4.Queue;
import org.junit.Test;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        Queue sQ = new Queue();  //To store queue of queues
        Queue q1 = new Queue();  // temp queue to create one item queue
        Item item1 = items.peek();
        while(!items.isEmpty()) {
            item1 = items.dequeue();
            q1 = new Queue();
            q1.enqueue(item1);
            sQ.enqueue(q1);
        }
        return sQ;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue newQ = new Queue(); //store the combined sorted
        if (q1.isEmpty() && q2.isEmpty()) { return newQ; }

        while(!q1.isEmpty() && !q2.isEmpty()) {
            Item q1Item = q1.peek();
            Item q2Item = q2.peek();
            if (q1Item.compareTo(q2Item) < 0) {
                newQ.enqueue(q1Item);
                q1.dequeue();
            } else if (q1Item.compareTo(q2Item) > 0) {
                newQ.enqueue(q2Item);
                q2.dequeue();
            }
            else {
                newQ.enqueue(q1Item);
                q1.dequeue();
                newQ.enqueue(q2Item);
                q2.dequeue();
            }
        }

        while(!q1.isEmpty()) {
            newQ.enqueue(q1.dequeue());
        }
        while(!q2.isEmpty()) {
            newQ.enqueue(q2.dequeue());
        }

        return newQ;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        int half = items.size(); //size of the queue
        Queue sQ = makeSingleItemQueues(items);

        // pop 2 items from sQ, mergesorted into new Q; enqueue to the QofQ(sQ)
        // pop next 2, until sQ is empty or 1; if 1, merge last item into last Q.
        // repeat the process, until sQ.size() == 1;

        while(sQ.size() > 1) {
            Queue q1 = (Queue)sQ.dequeue();
            Queue q2 = (Queue) sQ.dequeue();
            sQ.enqueue(mergeSortedQueues(q1, q2));
        }
        return sQ;
    }

    @Test
    private void test() {
        Queue<String> students = new Queue<String>();
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
        students.enqueue("Clif");
        students.enqueue("Demi");
        System.out.println("Before sort: " + students);

        //Test make single Item queue
        Queue singleQ = makeSingleItemQueues(students);
        System.out.println("Single Item queue: " + singleQ);


        // Test MergeSort
        students = new Queue<String>();
        students.enqueue("Clice");
        students.enqueue("Danessa");
        students.enqueue("Fthan");
        students.enqueue("Glif");
        students.enqueue("Hemi");
        System.out.println("S1 is " + students);
        Queue students2 = new Queue<String>();
        students2.enqueue("Alice");
        students2.enqueue("Banessa");
        students2.enqueue("Ethan");
        students2.enqueue("Flif");
        students2.enqueue("Gemi");
        System.out.println("S2 is " + students2);
        Queue<String> mergeQ = mergeSortedQueues(students, students2);
        System.out.println("After merge: " + mergeQ);


        //Test mergeSOrtedQueues
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
        students.enqueue("Clif");
        students.enqueue("Demi");
        System.out.println("Before sort: " + students);
        students = mergeSort(students);
        System.out.println("After sort: " + students);
    }
}
