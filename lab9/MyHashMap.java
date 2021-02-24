import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {

    /** Buckets size: size */
    /** Each bucket has an LinkedList */
    private int size;
    private double loadF = 0.75;
    private int numItems;
    private LinkedList<Entry>[] buckets;
    private HashSet<K> myKeySet;

    public MyHashMap() {
        size = 16;
        loadF = 0.75;
        numItems = 0;
        buckets = (LinkedList<Entry>[]) new LinkedList[size];
        myKeySet = new HashSet<K>(size);
        //for (int i = 0; i < size; i++) {
            //buckets[i] = new LinkedList<V>();
        //}
    }

    public MyHashMap(int initialSize) {
        size = initialSize;
        loadF = 0.75;
        numItems = 0;
        buckets = (LinkedList<Entry>[]) new LinkedList[size];
        myKeySet = new HashSet<K>(size);
        //for (int i = 0; i < size; i++) {
            //buckets[i] = new LinkedList<V>();
       // }
    }

    public MyHashMap(int initialSize, double loadFactor) {
        size = initialSize;
        loadF = loadFactor;
        numItems = 0;
        buckets = (LinkedList<Entry>[]) new LinkedList[size];
        myKeySet = new HashSet<K>(size);
        //for (int i = 0; i < size; i++) {
            //buckets[i] = new LinkedList<V>();
        //}
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        size = 16;
        loadF = 0.75;
        numItems = 0;
        buckets = (LinkedList<Entry>[]) new LinkedList[size];
        myKeySet = new HashSet<K>(size);
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (find(key) == null) {
            return null;
        } else {
            return (V) find(key).value();
        }
    }

    private Entry find(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int j = hash(key);
        if (buckets[j] == null) {
            return null;
        } else {
            LinkedList findList = buckets[j];
            int entryListSize = findList.size();
            for (int i = 0; i < entryListSize; i++) {
                Entry findEntry = (Entry) findList.get(i);
                if (findEntry.key.equals(key)) {
                    return findEntry;
                }
            }
            return null;
        }
    }


    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return numItems;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V val) {
        if (key == null) throw new IllegalArgumentException("First argument is null");
        if (val == null) {
            myKeySet.remove(key);  //May need to remove from the map
            numItems--;
            return;
        }

        Entry findEntry = find(key);
        if (findEntry != null) {
            findEntry.val = val;
        } else {
            if (((double) numItems / size) > loadF) {
                resize(size * 2);
            }
            int i = hash(key);
            if (buckets[i] == null) {
                buckets[i] = new LinkedList<Entry>();
            }
            Entry newEntry = new Entry(key, val);
            buckets[i].addFirst(newEntry);
            numItems++;
            myKeySet.add(key);
        }
    }

    private void resize(int newsize) {
        MyHashMap<K, V> temp = new MyHashMap<K, V>(newsize);
        // Iterate through buckets, size
        for (int i = 0; i < size; i++) {
            if (buckets[i] != null) {
                int listsize = this.buckets[i].size();
                for (int j = 0; j < listsize; j++) {
                    Entry findEntry = (Entry) buckets[i].get(j);
                    temp.put((K)findEntry.key, (V)findEntry.val);
                    temp.myKeySet.add((K)findEntry.key);
                }
            }
        }
        this.size = temp.size;
        this.numItems = temp.numItems;
        this.buckets = temp.buckets;
        this.myKeySet = temp.myKeySet;
    }

    // hash function for keys - returns value between 0 and size-1
    // (from Java 7 implementation, protects against poor quality hashCode() implementations)
    private int hash(K key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (size-1);
    }

    /**
   * Removes the mapping for the specified key from this map if present.
   * Not required for Lab 8. If you don't implement this, throw an
   * UnsupportedOperationException.
    */

    @Override
    public V remove(K key) {

        Entry findEntry = (Entry) find(key);
        if (findEntry == null) {
            return null;
        } else {
            int keyHash = hash(key);
            LinkedList findList = buckets[keyHash];
            findList.remove(findEntry);
            numItems--;
            myKeySet.remove(key);
            return (V)findEntry.value();
        }

    }


    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return myKeySet;
    }

    /** Returns an iterator iterates over stored keys, any order */
    @Override
    public Iterator<K> iterator() {
        return myKeySet.iterator();
    }

    private class Entry<K, V> {
        K key;
        V val;
        //Entry next;

        private Entry(K k, V v) {
            key = k;
            val = v;
            //next = null;
        }

        private Entry() {
            key = null;
            val = null;
            //next = null;
        }

        private V value() {
            return val;
        }
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }


}
