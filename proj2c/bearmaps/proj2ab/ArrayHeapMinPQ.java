package bearmaps.proj2ab;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.HashMap;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    /** Offset array by 1. Index 0 is Empty. First item at 1.
     * So, leftChild(k) is k * 2; rightChild(k) is k * 2 + 1;
     * parent at k / 2 */
    private ArrayList<itemNode> items;
    private HashMap<T, Integer> index;  //for storage of items only, for contain method
    private int size; //

    @Override
    public void add(T item, double priority) {

        if (items == null) { //new Array
            items = new ArrayList<>();
            items.add(new itemNode(item, (Double)priority));
            items.add(1, new itemNode(item, priority));
            index = new HashMap<>();
            size = 1;
            index.put(item, size);
        }  else if (contains(item)) {
            throw new IllegalArgumentException("Duplicate item.");
        } else {
            items.add(new itemNode(item, priority));
            int newIndex = items.size() - 1;  //Always the last item index
            index.put(item, newIndex);
            swimup(newIndex);
            size++;
        }
    }

    private void swimup(int k) {
        if (isValid(parentIndex(k))) {
            if (items.get(parentIndex(k)).getPriority() > items.get(k).getPriority()) {
                swap(k, parentIndex(k));
                swimup(parentIndex(k));
            }
        }
    }


    private void swimdown(int k) {
        if (isValid(leftChildIndex(k))) {
            int smallChildIndex = leftChildIndex(k);
            if (isValid(rightChildIndex(k))) {
                if (items.get(smallChildIndex).getPriority() > items.get(rightChildIndex(k)).getPriority()) {
                    smallChildIndex = rightChildIndex(k);
                }
            }

            if (items.get(smallChildIndex).getPriority() < items.get(k).getPriority()) {
                swap(k, smallChildIndex);
                swimdown(smallChildIndex);
            }
        }
    }

    private void swap(int k, int pk) {
        itemNode temp = items.get(k);  // create temp node, storing k node
        items.set(k, items.get(pk));   // in items[], switch place
        items.set(pk, temp);
        index.put(items.get(pk).getItem(), pk); // get(pk) is previous item at k
        index.put(items.get(k).getItem(), k);
     }

     private boolean isValid(int k) {
        return (k > 0 && k <= size());
     }

    @Override
    public boolean contains(T item) {
        if (this.items == null) { return false;
        } else {
            return index.containsKey(item);
        }
    }

    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        return items.get(1).getItem();
    }

    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        
        itemNode lastNode = items.get(size() - 1);
        T smallItem = getSmallest();  // Top item to pop
        items.set(1, lastNode);    //Move last node to item 1
        index.put(lastNode.getItem(), 1);  //change index map for last item
        
        size--;
        index.remove(smallItem);  //remove smallest item key from index map
        items.remove(size());    // shorten the heap arrayList
        swimdown(1);  // swim down from the top

        return smallItem;
    }

    @Override
    public int size() {
        //return items.size() - 1;
        return size;
    }


    @Override
    public void changePriority(T item, double priority) {
         if (!contains(item)) {
             throw new NoSuchElementException("Item not exists.");
         }
         int itemIndex = index.get(item);
         //System.out.println("current index is " + itemIndex);

         itemNode n = items.get(itemIndex);
         n.setPriority(priority);
        //System.out.println("current priority is " + n.getPriority());

         //Start placing it to the right place
        // Swim up if priority < parent.priority
        swimup(itemIndex);
        // swim down if priority > smallest child priority
        itemIndex = index.get(item);
        swimdown(itemIndex);
    }



    private static int leftChildIndex(int k) {
        return k * 2;
    }

    private static int rightChildIndex(int k) {
        return k * 2 + 1;
    }

    private static int parentIndex(int k) {
        return k / 2;
    }


    /** Each item stored in itemNode in the ArrayList */
    private class itemNode implements Comparable<itemNode> {
        T item;
        double priority;

        private itemNode(T ite, double p) {
            item = ite;
            priority = p;
        }

        private double getPriority() {
            return this.priority;
        }

        private T getItem() {
            return this.item;
        }

        private void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(ArrayHeapMinPQ.itemNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        //@Override
        @SuppressWarnings("unchecked")
        public boolean equals(itemNode o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return  o.getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }
}



